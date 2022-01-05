package Hackerearth.DataStructures.SegmentTree;

import java.io.*;

public class GreatestCommonDiv {
    public static int nextPowerof2(int n)
    {
        n = n|(n>>1);
        n = n|(n>>2);
        n = n|(n>>4);
        n = n|(n>>8);
        return (n+1);
    }

    public static void maxbuilt(long[] seg_Tree, long[] arr, int sn, int ll, int ul)
    {
        if(ll==ul)
        {
            seg_Tree[sn] = arr[ll];
            return;
        }

        int mid = (ll+ul)/2;
        maxbuilt(seg_Tree, arr, 2*sn+1, ll, mid);
        maxbuilt(seg_Tree, arr, 2*sn+2, mid+1, ul);
        seg_Tree[sn] = Math.max(seg_Tree[2*sn+1], seg_Tree[2*sn+2]);
    }
    public static long maxquery(long[] seg_Tree, int ind, int start, int end, int l, int r)
    {
        //Check for total overlap
        if(start >= l && end <= r)
        {
            return seg_Tree[ind];
        }
        //Check for no overlap
        if(start>r || end<l)
        {
            return Long.MIN_VALUE;
        }
        //Check for partial overlap
        int mid = (start+end)/2;
        return Math.max(maxquery(seg_Tree, ind*2+1, start, mid, l, r),
                maxquery(seg_Tree, ind*2+2, mid+1, end, l, r));
    }
    public static void maxupdate(long[] seg_Tree, int ind, int start, int end, int ni, long val)
    {
        if(start == end)
        {
            seg_Tree[ind] = val;
            return;
        }

        int mid = (start+end)/2;
        if(ni<=mid)
        {
            maxupdate(seg_Tree, 2*ind+1, start, mid, ni, val);
        }
        else
        {
            maxupdate(seg_Tree, 2*ind+2, mid+1, end, ni, val);
        }

        seg_Tree[ind] = Math.max(seg_Tree[2*ind+1], seg_Tree[2*ind+2]);
    }

    public static void sumbuilt(long[] seg_Tree, long[] arr, int sn, int ll, int ul)
    {
        if(ll==ul)
        {
            seg_Tree[sn] = arr[ll];
            return;
        }

        int mid = (ll+ul)/2;
        sumbuilt(seg_Tree, arr, 2*sn+1, ll, mid);
        sumbuilt(seg_Tree, arr, 2*sn+2, mid+1, ul);
        seg_Tree[sn] = (seg_Tree[2*sn+1] + seg_Tree[2*sn+2]);
    }
    public static long sumquery(long[] seg_Tree, int ind, int start, int end, int l, int r)
    {
        //Check for total overlap
        if(start >= l && end <= r)
        {
            return seg_Tree[ind];
        }
        //Check for no overlap
        if(start>r || end<l)
        {
            return 0;
        }
        //Check for partial overlap
        int mid = (start+end)/2;
        return sumquery(seg_Tree, ind*2+1, start, mid, l, r)
                + sumquery(seg_Tree, ind*2+2, mid+1, end, l, r);
    }
    public static void sumupdate(long[] seg_Tree, int ind, int start, int end, int ni, long val)
    {
        if(start == end)
        {
            seg_Tree[ind] = val;
            return;
        }

        int mid = (start+end)/2;
        if(ni<=mid)
        {
            sumupdate(seg_Tree, 2*ind+1, start, mid, ni, val);
        }
        else
        {
            sumupdate(seg_Tree, 2*ind+2, mid+1, end, ni, val);
        }

        seg_Tree[ind] = (seg_Tree[2*ind+1] + seg_Tree[2*ind+2]);
    }
    public static long gcd(long a, long b)
    {
        if(a%b == 0)
            return b;
        return gcd(b, a%b);
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

        int sn = 2*nextPowerof2(n)-1;
        if((n&(n-1))==0)
            sn = 2*n-1;
        long[] maxseg_tree = new long[sn];
        for(int i=0;i<sn;i++)
            maxseg_tree[i] = Long.MIN_VALUE;
        maxbuilt(maxseg_tree,arr,0,0,n-1);

        long[] sumseg_tree = new long[sn];
        for(int i=0;i<sn;i++)
            sumseg_tree[i] = 0;
        sumbuilt(sumseg_tree,arr,0,0,n-1);

        StringBuilder sb = new StringBuilder();
        while(m-->0)
        {
            int type = sc.nextInt();
            if(type == 1)
            {
                int l = sc.nextInt()-1;
                int r = sc.nextInt()-1;
                int x = sc.nextInt();

                for(int i=l;i<=r;i++)
                {
                    arr[i] = x;
                    maxupdate(maxseg_tree, 0, 0, n-1, i, x);
                    sumupdate(sumseg_tree, 0, 0, n-1, i, x);
                }
            }
            else if(type == 2)
            {
                int l = sc.nextInt()-1;
                int r = sc.nextInt()-1;
                int x = sc.nextInt();

                for(int i=l;i<=r;i++)
                {
                    arr[i] = gcd(arr[i], x);
                    maxupdate(maxseg_tree, 0, 0, n-1, i, arr[i]);
                    sumupdate(sumseg_tree, 0, 0, n-1, i, arr[i]);
                }
            }
            else if(type == 3)
            {
                int l = sc.nextInt()-1;
                int r = sc.nextInt()-1;

                long val = maxquery(maxseg_tree, 0, 0, n-1, l, r);
                sb.append(val).append("\n");
            }
            else
            {
                int l = sc.nextInt()-1;
                int r = sc.nextInt()-1;

                long val = sumquery(sumseg_tree, 0, 0, n-1, l, r);
                sb.append(val).append("\n");
            }
        }

        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 18;
        final private DataInputStream din;
        final private byte[] buffer;
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
