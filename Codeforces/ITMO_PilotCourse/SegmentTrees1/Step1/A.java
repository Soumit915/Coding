package Codeforces.ITMO_PilotCourse.SegmentTrees1.Step1;

import java.io.*;

public class A {
    public static int getnextPowerOf2(int n)
    {
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>30);

        return n+1;
    }
    public static void built(long[] segTree, int sn, long[] arr, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn] = arr[ll];
            return;
        }

        int mid = (ll+ul)/2;
        built(segTree, 2*sn+1, arr, ll, mid);
        built(segTree, 2*sn+2, arr, mid+1, ul);
        segTree[sn] = segTree[2*sn+1] + segTree[2*sn+2];
    }
    public static void update(long[] segTree, int sn, long val, int ind, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn] = val;
            return;
        }

        int mid = (ll+ul)/2;
        if(ind<=mid)
        {
            update(segTree, 2*sn+1, val, ind, ll, mid);
        }
        else
        {
            update(segTree, 2*sn+2, val, ind, mid+1, ul);
        }

        segTree[sn] = segTree[2*sn+1] + segTree[2*sn+2];
    }
    public static long query(long[] segTree, int sn, int start, int end, int ll, int ul)
    {
        //for no overlap
        if(start>ul || end<ll)
        {
            return 0;
        }

        //for total overlap
        if(start<=ll && end>=ul)
        {
            return segTree[sn];
        }

        int mid = (ll+ul)/2;
        return query(segTree, 2*sn+1, start, end, ll, mid)
                + query(segTree, 2*sn+2, start, end, mid+1, ul);
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int m = sc.nextInt();

        long[] arr = new long[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextLong();
        }

        int sn = 2*getnextPowerOf2(n)-1;
        long[] segTree = new long[sn];
        built(segTree, 0, arr, 0, n-1);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++)
        {
            int type = sc.nextInt();
            if(type == 1)
            {
                int index = sc.nextInt();
                long v = sc.nextLong();
                arr[index] = v;
                update(segTree, 0, v, index, 0, n-1);
            }
            else
            {
                int l = sc.nextInt();
                int r = sc.nextInt();
                sb.append(query(segTree, 0, l, r-1, 0, n-1)).append("\n");
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
