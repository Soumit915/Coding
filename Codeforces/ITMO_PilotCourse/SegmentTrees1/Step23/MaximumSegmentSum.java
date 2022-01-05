package Codeforces.ITMO_PilotCourse.SegmentTrees1.Step23;

import java.io.*;

public class MaximumSegmentSum {
    static class Node{
        long sum;
        long segval;
        long prefix;
        long suffix;
    }
    public static int getNextPowerOf2(int n)
    {
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>30);
        return n+1;
    }
    public static void built(Node[] segTree, int sn, long[] arr, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn].sum = arr[ll];
            if(arr[ll]>0)
            {
                segTree[sn].prefix = arr[ll];
                segTree[sn].segval = arr[ll];
                segTree[sn].suffix = arr[ll];
            }
            return;
        }

        int mid = (ll+ul)/2;
        built(segTree, 2*sn+1, arr, ll, mid);
        built(segTree, 2*sn+2, arr, mid+1, ul);

        segTree[sn].sum = segTree[2*sn+1].sum + segTree[2*sn+2].sum;

        long middlesegval = segTree[2*sn+1].suffix + segTree[2*sn+2].prefix;
        segTree[sn].segval = Math.max(Math.max(segTree[2*sn+1].segval, segTree[2*sn+2].segval), middlesegval);

        segTree[sn].prefix = Math.max(segTree[2*sn+1].prefix, segTree[2*sn+1].sum + segTree[2*sn+2].prefix);
        segTree[sn].suffix = Math.max(segTree[2*sn+2].suffix, segTree[2*sn+2].sum + segTree[2*sn+1].suffix);
    }
    public static void update(Node[] segTree, int sn, int index, long val, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn] = new Node();
            segTree[sn].sum = val;
            if(val>0)
            {
                segTree[sn].prefix = val;
                segTree[sn].segval = val;
                segTree[sn].suffix = val;
            }
            return;
        }

        int mid = (ll+ul)/2;
        if(index<=mid)
        {
            update(segTree, 2*sn+1, index, val, ll, mid);
        }
        else {
            update(segTree, 2*sn+2, index, val, mid+1, ul);
        }

        segTree[sn].sum = segTree[2*sn+1].sum + segTree[2*sn+2].sum;

        long middlesegval = segTree[2*sn+1].suffix + segTree[2*sn+2].prefix;
        segTree[sn].segval = Math.max(Math.max(segTree[2*sn+1].segval, segTree[2*sn+2].segval), middlesegval);

        segTree[sn].prefix = Math.max(segTree[2*sn+1].prefix, segTree[2*sn+1].sum + segTree[2*sn+2].prefix);
        segTree[sn].suffix = Math.max(segTree[2*sn+2].suffix, segTree[2*sn+2].sum + segTree[2*sn+1].suffix);
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

        int sn = 2*getNextPowerOf2(n)-1;
        Node[] segTree = new Node[sn];
        for(int i=0;i<sn;i++)
            segTree[i] = new Node();
        built(segTree, 0, arr, 0, n-1);

        StringBuilder sb = new StringBuilder();
        sb.append(segTree[0].segval).append("\n");
        while(m-->0)
        {
            int index = sc.nextInt();
            long val = sc.nextLong();
            update(segTree, 0, index, val, 0, n-1);
            sb.append(segTree[0].segval).append("\n");
        }

        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String next() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == ' ')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
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

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
