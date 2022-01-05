package FacebookHackercup.QualificationRound_2020.D2_RunningOnFumes_Chapter2;

import java.util.*;
import java.io.*;

class Node
{
    int id;
    boolean isVisited;
    long cost;
    Node parent;
    Node(int id)
    {
        this.id = id;
        this.isVisited = false;
    }
}

class Tree
{
    ArrayList<Node> nodelist;
    ArrayList<Node> pathAB = new ArrayList<>();
    Tree(int n)
    {
        this.nodelist = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            nodelist.add(new Node(i));
        }
    }
    public void updateNodeInfo(int id, long cost, int pi)
    {
        Node parent;
        if(pi==-1)
            parent = null;
        else
            parent = nodelist.get(pi);
        Node nd = nodelist.get(id);
        nd.parent = parent;
        nd.cost = cost;
    }
    public void lca(int a, int b)
    {
        Node na = nodelist.get(a);
        Node nb = nodelist.get(b);
        lca(na, nb);
    }
    public void lca(Node a, Node b)
    {
        ArrayList<Node> pathb_root = new ArrayList<>();

        pathb_root.add(b);
        b.isVisited = true;
        Node ptrp = b.parent;
        while(ptrp!=null)
        {
            ptrp.isVisited = true;
            pathb_root.add(ptrp);
            ptrp = ptrp.parent;
        }

        ptrp = a;
        while(!ptrp.isVisited)
        {
            pathAB.add(ptrp);
            ptrp = ptrp.parent;
        }

        int i=pathb_root.size()-1;
        while(pathb_root.get(i)!=ptrp)
        {
            i--;
        }
        for(;i>=0;i--)
        {
            pathAB.add(pathb_root.get(i));
        }
    }
}

public class RunningOnFumes_Chapter2 {
    public static int nextPowerOf2(int n)
    {
        n = n|(n>>1);
        n = n|(n>>2);
        n = n|(n>>4);
        n = n|(n>>8);
        n = n|(n>>16);
        n = n|(n>>24);
        return (n+1);
    }
    public static long query(long[] segTree, int ind, int start, int end, int l, int r)
    {
        if(start<=l && end>=r)
        {
            return segTree[ind];
        }

        if(start>r || end<l)
        {
            return Long.MAX_VALUE;
        }

        int mid = (l+r)/2;
        return Math.min(query(segTree, 2*ind+1, start, end, l, mid),
                query(segTree, 2*ind+2, start, end, mid+1, r));
    }
    public static void update(long[] segTree, int ind, int l, int r, int ni, long val)
    {
        if(l==r)
        {
            segTree[ind] = val;
            return;
        }

        int mid = (l+r)/2;
        if(ni<=mid)
        {
            update(segTree, 2*ind+1, l, mid, ni, val);
        }
        else
        {
            update(segTree, 2*ind+2, mid+1, r, ni, val);
        }

        segTree[ind] = Math.min(segTree[2*ind+1], segTree[2*ind+2]);
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int t = sc.nextInt();

        for(int testi=1;testi<=t;testi++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;

            Tree tr = new Tree(n);
            for(int i=0;i<n;i++)
            {
                int pi = sc.nextInt();
                long ci = sc.nextLong();
                tr.updateNodeInfo(i, ci, pi-1);
            }

            tr.lca(a, b);

            ArrayList<Node> path = tr.pathAB;
            int pn = path.size();
            path.get(0).cost = 0;
            path.get(pn-1).cost = 0;
            for(int i=1;i<pn-1;i++)
            {
                if(path.get(i).cost == 0)
                {
                    path.get(i).cost = Long.MAX_VALUE;
                }
            }

            int sn = 2*nextPowerOf2(pn)-1;
            long[] segTree = new long[sn];
            for(int i=0;i<sn;i++)
                segTree[i] = Long.MAX_VALUE;

            long[] optimalcost = new long[pn];
            optimalcost[0] = 0;
            update(segTree, 0, 0, pn-1, 0, 0);
            for(int i=1;i<pn;i++)
            {
                long min = query(segTree, 0, Math.max(i-m, 0), i-1, 0, pn-1);
                //System.out.println(min);
                if(min == Long.MAX_VALUE)
                {
                    optimalcost[i] = Long.MAX_VALUE;
                }
                else if(path.get(i).cost == Long.MAX_VALUE)
                {
                    optimalcost[i] = Long.MAX_VALUE;
                }
                else
                {
                    optimalcost[i] = min+path.get(i).cost;
                    update(segTree, 0, 0, pn-1, i, optimalcost[i]);
                }
            }

            System.out.print("Case #"+testi+": ");
            if(optimalcost[pn-1]==Long.MAX_VALUE)
            {
                System.out.println(-1);
            }
            else {
                long ans = optimalcost[pn - 1];
                System.out.println(ans);
            }
        }
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
