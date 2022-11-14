package Codeforces.ITMO_PilotCourse.SegmentTrees1.Step23;

import java.io.*;

public class Kth1 {
    static class Node
    {
        int val;
        int index;
        Node()
        {
            this.val = 0;
            this.index = -1;
        }
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
    public static void built(Node[] segTree, int sn, int[] arr, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn].val = arr[ll];
            segTree[sn].index = ll;
            return;
        }

        int mid = (ll+ul)/2;
        built(segTree, 2*sn+1, arr, ll, mid);
        built(segTree, 2*sn+2, arr, mid+1, ul);
        segTree[sn].val = segTree[2*sn+1].val + segTree[2*sn+2].val;
    }
    public static void update(Node[] segTree, int sn, int index, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn].val = segTree[sn].val ^ 1;
            return;
        }

        int mid = (ll+ul)/2;
        if(index<=mid)
        {
            update(segTree, 2*sn+1, index, ll, mid);
        }
        else
        {
            update(segTree, 2*sn+2, index, mid+1, ul);
        }
        segTree[sn].val = segTree[2*sn+1].val + segTree[2*sn+2].val;
    }
    public static int query(Node[] segTree, int sn, int k, int ll, int ul)
    {
        if(ll==ul)
        {
            return segTree[sn].index;
        }

        int mid = (ll+ul)/2;
        if(k<=segTree[2*sn+1].val)
        {
            return query(segTree, 2*sn+1, k, ll, mid);
        }
        else
        {
            return query(segTree, 2*sn+2, k-segTree[2*sn+1].val, mid+1, ul);
        }
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

        int sn = 2*getNextPowerOf2(n)-1;
        Node[] segTree = new Node[sn];
        for(int i=0;i<sn;i++)
            segTree[i] = new Node();
        built(segTree, 0, arr, 0, n-1);

        /*for(int i: segTree)
            System.out.print(i+" ");
        System.out.println();*/

        StringBuilder sb = new StringBuilder();
        while(m-->0)
        {
            int type = sc.nextInt();
            if(type == 1)
            {
                int index = sc.nextInt();
                update(segTree, 0, index, 0, n-1);
            }
            else {
                int k = sc.nextInt();
                sb.append(query(segTree, 0, k+1, 0, n-1)).append("\n");
            }
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
