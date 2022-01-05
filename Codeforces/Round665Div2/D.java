package Codeforces.Round665Div2;

import java.util.*;

public class D {
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

        Tree(int n) {
            this.nodelist = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                nodelist.add(new Node(i));
            }
            this.edgelist = new ArrayList<>(n - 1);
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
    }
    static long mod = (long) 1e9+7;
    static long mul(long a, long b) {
        return a*b%mod;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            Tree tr = new Tree(n);
            for(int i=1;i<n;i++)
            {
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                tr.addEdge(u, v);
            }

            int m = sc.nextInt();
            long[] primes = new long[m];
            for(int i=0;i<m;i++)
            {
                primes[i] = sc.nextInt();
            }
            Arrays.sort(primes);

            tr.dfs();

            PriorityQueue<Edge> maxheap = new PriorityQueue<>(Collections.reverseOrder());
            for(Edge e: tr.edgelist)
            {
                e.vcount = tr.nodelist.size()-e.ucount;
                long mulv = e.ucount;
                e.vfactor = (mulv* e.vcount);
                maxheap.add(e);
            }

            long first = 1;
            if(m>=n)
            {
                for(int i=n-2;i<m;i++)
                {
                    first = mul(first , primes[i])%mod;
                }
                primes[n-2] = first;
                m = n-1;
            }

            long sum = 0;
            for(int i=0;i<tr.nodelist.size()-1;i++)
            {
                Edge e = maxheap.remove();
                long mulv = 1;
                if(i<m) mulv = primes[m-i-1];

                long s = e.vfactor;
                s = mul(s, mulv)%mod;
                sum = (sum + s%mod)%mod;
                // System.out.println("test: "+(e.vfactor*mulv)+" "+e.vfactor);
            }

            System.out.println(sum);
        }
    }
}
