package Codeforces.Round661Div3;

import java.util.*;

public class E1_WeightsDivision_Easy {
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
        long weight;
        long weightby2;
        int ff;
        Edge(Node u, Node v, long weight)
        {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        public int compareTo(Edge e)
        {
            return Long.compare(this.weight-this.weightby2, e.weight-e.weightby2);
        }
    }
    static class Tree
    {
        ArrayList<Node> nodelist;
        ArrayList<Edge> edgelist;
        Tree(int n)
        {
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                nodelist.add(new Node(i));
            }
            this.edgelist = new ArrayList<>(n-1);
        }
        public void addEdge(int xi, int yi, long weight)
        {
            Node nu = nodelist.get(xi);
            Node nv = nodelist.get(yi);
            Edge e = new Edge(nu, nv, weight);

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

            while(!stk.isEmpty())
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
                    else
                    {
                        next = e.u;
                    }

                    if(cur.parent==next)
                    {
                        continue;
                    }
                    next.parent = cur;

                    if(next.adjacentnode.size()==1)
                    {
                        next.child = 1;
                    }
                    else
                    {
                        stk.push(next);
                        ptrstk.push(-1);
                    }
                }
                else
                {
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

                        if(next==cur.parent)
                            continue;

                        cur.child += next.child;
                    }
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            long k = sc.nextLong();

            Tree tr = new Tree(n);
            for(int i=1;i<n;i++)
            {
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                long weight = sc.nextLong();
                tr.addEdge(u, v, weight);
            }

            tr.dfs();

            long sum = 0;
            PriorityQueue<Edge> maxheap = new PriorityQueue<>(Collections.reverseOrder());
            for(Edge e: tr.edgelist)
            {
                if(e.u.parent == e.v)
                {
                    e.ff = e.u.child;
                }
                else
                {
                    e.ff = e.v.child;
                }
                e.weightby2 = e.weight/2;
                e.weight *= e.ff;
                e.weightby2 *= e.ff;
                sum += e.weight;
                maxheap.add(e);
            }

            int count = 0;
            while (sum>k)
            {
                Edge e = maxheap.remove();
                sum -= e.weight-e.weightby2;
                e.weight = e.weightby2;
                e.weightby2 /= e.ff;
                e.weightby2 /= 2;
                e.weightby2 *= e.ff;
                maxheap.add(e);
                count++;
            }

            System.out.println(count);
        }
    }
}
