package Codeforces.Round670Div2;

import java.io.*;
import java.util.*;

public class C {
    static class Node
    {
        int id;
        int child;
        Node parent;
        ArrayList<Edge> adjacentnode = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
            this.child = 0;
            this.parent = null;
        }
    }
    static class Edge implements Comparable<Edge>
    {
        Node u;
        Node v;
        int ucount;
        int vcount;
        long vfactor;
        Edge(Node u, Node v)
        {
            this.u = u;
            this.v = v;
            this.ucount = -1;
            this.vcount = -1;
        }
        public int compareTo(Edge e)
        {
            return Long.compare(this.vfactor, e.vfactor);
        }
    }
    static class Tree {
        ArrayList<Node> nodelist;
        ArrayList<Edge> edgelist;
        boolean[] isVisited;
        Tree(int n) {
            this.nodelist = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                nodelist.add(new Node(i));
            }
            this.edgelist = new ArrayList<>(n - 1);
            isVisited = new boolean[n];
        }

        public void addEdge(int xi, int yi) {
            Node nu = nodelist.get(xi);
            Node nv = nodelist.get(yi);
            Edge e = new Edge(nu, nv);

            nu.adjacentnode.add(e);
            nv.adjacentnode.add(e);
            this.edgelist.add(e);
        }
        public void dfs()
        {
            Node source = nodelist.get(0);

            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);
            boolean[] isVisited = new boolean[nodelist.size()];
            isVisited[source.id] = true;

            while (!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();
                if(ptr<cur.adjacentnode.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Edge e = cur.adjacentnode.get(ptr);
                    Node next;
                    if(e.u == cur)
                    {
                        next = e.v;
                    }
                    else next = e.u;

                    if(isVisited[next.id])
                        continue;
                    isVisited[next.id] = true;

                    if(next.adjacentnode.size()==1)
                    {
                        cur.adjacentnode.get(ptr).ucount = 1;
                    }
                    else
                    {
                        stk.push(next);
                        ptrstk.push(-1);
                    }
                }
                else
                {
                    int tot = 1;
                    for(Edge e: cur.adjacentnode)
                    {
                        Node next;
                        if(e.u == cur)
                        {
                            next = e.v;
                        }
                        else
                        {
                            next = e.u;
                        }

                        if(!stk.isEmpty() && next == stk.peek())
                            continue;
                        tot += e.ucount;
                    }

                    if(!stk.isEmpty())
                    {
                        stk.peek().adjacentnode.get(ptrstk.peek()).ucount = tot;
                    }
                }
            }
        }

        public void dfsAgain(Node source, Edge forbidden)
        {
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);
            isVisited[source.id] = true;

            while (!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adjacentnode.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Edge e = cur.adjacentnode.get(ptr);
                    if(e == forbidden)
                        continue;

                    Node next;
                    if(e.u == cur)
                    {
                        next = e.v;
                    }
                    else next = e.u;

                    if(isVisited[next.id])
                        continue;

                    isVisited[next.id] = true;
                    stk.push(next);
                    ptrstk.push(-1);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc= new Reader();
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            Tree tr = new Tree(n);

            for(int i=0;i<n-1;i++)
            {
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                tr.addEdge(u, v);
            }

            tr.dfs();
            for(Edge e: tr.edgelist)
            {
                e.vcount = tr.nodelist.size()-e.ucount;
            }

            Edge removed = null;
            for(Edge e: tr.edgelist)
            {
                //System.out.println(e.ucount+" "+e.vcount);
                if(e.ucount == e.vcount)
                {
                    removed = e;
                }
            }

            if(removed!=null) {
                tr.dfsAgain(removed.v, removed);
                int noded = -1;

                Node u = removed.u;
                for(Edge e: u.adjacentnode)
                {
                    if(e!=removed)
                    {
                        if(u == e.u)
                        {
                            noded = e.v.id;
                        }
                        else noded = e.u.id;
                    }
                }

                System.out.println((removed.u.id+1)+" "+(noded+1));
                System.out.println((removed.v.id+1)+" "+(noded+1));
            }
            else
            {
                Edge e = tr.edgelist.get(0);
                System.out.println((e.u.id+1)+" "+(e.v.id+1));
                System.out.println((e.u.id+1)+" "+(e.v.id+1));
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