package Hackerearth.DataStructures.SegmentTree;

import java.io.*;

public class RangeMinQuery {
    public static int nextPowerof2(int n)
    {
        n = n|(n>>1);
        n = n|(n>>2);
        n = n|(n>>4);
        n = n|(n>>8);
        return (n+1);
    }

    public static void built(int[] seg_Tree, int[] arr, int sn, int ll, int ul)
    {
        if(ll==ul)
        {
            seg_Tree[sn] = arr[ll];
            return;
        }

        int mid = (ll+ul)/2;
        built(seg_Tree, arr, 2*sn+1, ll, mid);
        built(seg_Tree, arr, 2*sn+2, mid+1, ul);
        seg_Tree[sn] = Math.min(seg_Tree[2*sn+1], seg_Tree[2*sn+2]);
    }
    public static int query(int[] seg_Tree, int ind, int start, int end, int l, int r)
    {
        //Check for total overlap
        if(start >= l && end <= r)
        {
            return seg_Tree[ind];
        }
        //Check for no overlap
        if(start>r || end<l)
        {
            return Integer.MAX_VALUE;
        }
        //Check for partial overlap
        int mid = (start+end)/2;
        return Math.min(query(seg_Tree, ind*2+1, start, mid, l, r),
                query(seg_Tree, ind*2+2, mid+1, end, l, r));
    }
    public static void update(int[] seg_Tree, int ind, int start, int end, int ni, int val)
    {
        if(start == end)
        {
            seg_Tree[ind] = val;
            return;
        }

        int mid = (start+end)/2;
        if(ni<=mid)
        {
            update(seg_Tree, 2*ind+1, start, mid, ni, val);
        }
        else
        {
            update(seg_Tree, 2*ind+2, mid+1, end, ni, val);
        }

        seg_Tree[ind] = Math.min(seg_Tree[2*ind+1], seg_Tree[2*ind+2]);
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        int sn = 2*nextPowerof2(n)-1;
        int[] seg_tree = new int[sn];
        for(int i=0;i<sn;i++)
            seg_tree[i] = Integer.MAX_VALUE;
        built(seg_tree,arr,0,0,n-1);

        while(q>0)
        {
            byte choice = sc.read();
            switch (choice)
            {
                case 'q': int l = sc.nextInt();
                    int r = sc.nextInt();
                    System.out.println(query(seg_tree, 0, 0, n-1, l-1, r-1));
                    break;

                case 'u': int ind = sc.nextInt();
                    int val = sc.nextInt();
                    update(seg_tree, 0, 0, n-1, ind-1, val);
                    break;
            }
            q--;
        }
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
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

    }
}
