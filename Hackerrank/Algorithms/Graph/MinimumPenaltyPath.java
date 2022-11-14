package Hackerrank.Algorithms.Graph;

import java.util.*;

public class MinimumPenaltyPath {
    static class Node
    {
        int id;
        int[] distOr = new int[2049];
        ArrayList<Edge> adjacentlist = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
        }
    }
    static class Edge
    {
        Node u;
        Node v;
        int weight;
        Edge(Node u, Node v, int weight)
        {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
    static class Graph
    {
        ArrayList<Node> nodelist;
        ArrayList<Edge> edgelist;
        Graph(int n, int m)
        {
            this.nodelist = new ArrayList<>(n);
            this.edgelist = new ArrayList<>(m);
            for(int i=0;i<n;i++)
            {
                nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v, int weight)
        {
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            Edge e = new Edge(nu, nv, weight);
            nu.adjacentlist.add(e);
            nv.adjacentlist.add(e);
        }
        public void dfs(int a)
        {
            Node source = nodelist.get(a);
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            Stack<Integer> curdist = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);
            curdist.push(0);

            while (!stk.isEmpty()){
                Node cur = stk.pop();
                int ptr = ptrstk.pop();
                int cd = curdist.pop();

                if (ptr<cur.adjacentlist.size()-1)
                {
                    ptr++;

                    Edge e = cur.adjacentlist.get(ptr);
                    Node next;
                    if(e.u == cur)
                        next = e.v;
                    else next = e.u;

                    stk.push(cur);
                    ptrstk.push(ptr);
                    curdist.push(cd);

                    if(next.distOr[cd|e.weight]!=1)
                    {
                        int newcd = cd|e.weight;
                        next.distOr[cd|e.weight] = 1;
                        stk.push(next);
                        ptrstk.push(-1);
                        curdist.push(newcd);
                    }
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph gr = new Graph(n, m);
        for(int i=0;i<m;i++)
        {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            int weight = sc.nextInt();
            gr.addEdge(u, v, weight);
        }

        int a = sc.nextInt()-1;
        int b = sc.nextInt()-1;

        gr.dfs(a);

        int counter = 0;
        int min = Integer.MAX_VALUE;
        for(int i: gr.nodelist.get(b).distOr)
        {
            if(i==1)
                min = Math.min(counter, min);
            counter++;
        }

        if(min == Integer.MAX_VALUE)
            System.out.println(-1);
        else System.out.println(min);
    }
}
