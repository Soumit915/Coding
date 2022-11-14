package Hackerrank.Algorithms.Searching;

import java.util.*;

public class CutTheTree {
    static class Node
    {
        int id;
        int dataval;
        Node parent;
        ArrayList<Node> adjacentlist = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
            this.parent = null;
        }
    }
    static class Tree
    {
        ArrayList<Node> nodelist;
        int min;
        Tree(int n)
        {
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                nodelist.add(new Node(i));
            }
            this.min = Integer.MAX_VALUE;
        }
        public void addEdge(int u, int v)
        {
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adjacentlist.add(nv);
            nv.adjacentlist.add(nu);
        }
        public int calcSum()
        {
            int sum = 0;
            for(Node u: nodelist)
                sum += u.dataval;
            return sum;
        }
        public void dfs()
        {
            Node source = nodelist.get(0);
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);

            int sum = calcSum();

            while(!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adjacentlist.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adjacentlist.get(ptr);
                    if(cur.parent!=next)
                    {
                        next.parent = cur;
                        stk.push(next);
                        ptrstk.push(-1);
                    }
                }
                else
                {
                    //System.out.println();
                    for(Node adnode: cur.adjacentlist)
                    {
                        if(adnode!=cur.parent)
                        {
                            min = Math.min(min, Math.abs(sum - 2*adnode.dataval));
                        }
                    }

                    for(Node adnode: cur.adjacentlist)
                    {
                        if(adnode!=cur.parent)
                        {
                            cur.dataval += adnode.dataval;
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Tree tr = new Tree(n);
        for(int i=0;i<n;i++)
        {
            tr.nodelist.get(i).dataval = sc.nextInt();
        }

        for(int i=1;i<=n-1;i++)
        {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            tr.addEdge(u, v);
        }

        tr.dfs();
        System.out.println(tr.min);
    }
}
