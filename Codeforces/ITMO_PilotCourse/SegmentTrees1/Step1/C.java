package Codeforces.ITMO_PilotCourse.SegmentTrees1.Step1;

import java.io.*;

public class C {
    public static int nextPowerOf2(int n)
    {
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>30);
        return n+1;
    }
    public static void built(int[] minSegTree, int [] countSegTree, int sn, int[] arr, int ll, int ul)
    {
        if(ll==ul)
        {
            minSegTree[sn] = arr[ll];
            countSegTree[sn] = 1;
            return;
        }

        int mid = (ll+ul)/2;
        built(minSegTree, countSegTree, 2*sn+1, arr, ll, mid);
        built(minSegTree, countSegTree, 2*sn+2, arr, mid+1, ul);

        minSegTree[sn] = Math.min(minSegTree[2*sn+1], minSegTree[2*sn+2]);
        if(minSegTree[sn] == minSegTree[2*sn+1])
        {
            countSegTree[sn] += countSegTree[2*sn+1];
        }

        if(minSegTree[sn] == minSegTree[2*sn+2])
        {
            countSegTree[sn] += countSegTree[2*sn+2];
        }
    }
    public static void update(int[] minSegTree, int[] countSegTree, int sn,
                              int val, int index, int ll, int ul)
    {
        if(ll==ul)
        {
            minSegTree[sn] = val;
            countSegTree[sn] = 1;
            return;
        }

        int mid = (ll+ul)/2;
        if(index<=mid)
        {
            update(minSegTree, countSegTree, 2*sn+1, val, index, ll, mid);
        }
        else {
            update(minSegTree, countSegTree, 2*sn+2, val, index, mid+1, ul);
        }

        minSegTree[sn] = Math.min(minSegTree[2*sn+1], minSegTree[2*sn+2]);
        countSegTree[sn] = 0;
        if(minSegTree[sn] == minSegTree[2*sn+1])
        {
            countSegTree[sn] += countSegTree[2*sn+1];
        }

        if(minSegTree[sn] == minSegTree[2*sn+2])
        {
            countSegTree[sn] += countSegTree[2*sn+2];
        }
    }
    static int globalcount = 0;
    public static int query(int[] minSegTree, int[] countSegTree, int sn,
                            int start, int end, int l, int r)
    {
        //no overlap
        if(start>r || end<l)
        {
            globalcount = 0;
            return Integer.MAX_VALUE;
        }

        //total overlap
        if(start<=l && end>=r)
        {
            globalcount = countSegTree[sn];
            return minSegTree[sn];
        }

        int mid = (l+r)/2;
        int ql = query(minSegTree, countSegTree, 2*sn+1, start, end, l, mid);
        int lcount = globalcount;
        int qr = query(minSegTree, countSegTree, 2*sn+2, start, end, mid+1, r);
        int rcount = globalcount;

        int min = Math.min(ql, qr);
        int count = 0;

        if(min == ql)
        {
            count += lcount;
        }

        if(min==qr)
        {
            count += rcount;
        }

        globalcount = count;
        return min;
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        int sn = 2*nextPowerOf2(n)-1;
        int[] minSegTree = new int[sn];
        int[] countSegTree = new int[sn];
        built(minSegTree, countSegTree, 0, arr, 0, n-1);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++)
        {
            int type = sc.nextInt();
            if(type == 1)
            {
                int index = sc.nextInt();
                int v = sc.nextInt();
                arr[index] = v;
                update(minSegTree, countSegTree, 0, v, index, 0, n-1);
            }
            else
            {
                int l = sc.nextInt();
                int r = sc.nextInt();
                globalcount = 0;
                int q = query(minSegTree, countSegTree, 0, l, r-1, 0, n-1);
                sb.append(q).append(" ").append(globalcount).append("\n");
            }
        }

        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 18;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[100064]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
