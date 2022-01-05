package Hackerrank.Algorithms.Graph;

import java.util.*;

public class SynchronousShopping {
    static class Node
    {
        int id;
        int fish;
        ArrayList<Node> adjacentlist = new ArrayList<>();
        ArrayList<Integer> weightlist = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
            this.fish = 0;
        }
    }

    static class Graph
    {
        ArrayList<Node> nodelist;
        int[][] dp;
        Graph(int n, int k)
        {
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
                nodelist.add(new Node(i));
            int lim = (int) Math.pow(2, k);
            dp = new int[n][lim];
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<lim;j++)
                    dp[i][j] = Integer.MAX_VALUE/2;
            }
        }
        public void addEdge(int u, int v, int weight)
        {
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);
            nu.adjacentlist.add(nv);
            nu.weightlist.add(weight);
            nv.adjacentlist.add(nu);
            nv.weightlist.add(weight);
        }
        public void traverse()
        {
            Node source = nodelist.get(0);
            Queue<Node> qx = new LinkedList<>();
            Queue<Integer> bits = new LinkedList<>();

            qx.add(source);
            bits.add(source.fish);
            dp[0][source.fish] = 0;

            while(!qx.isEmpty())
            {
                Node cur = qx.remove();
                int fish = bits.remove();

                for(int i=0;i<cur.adjacentlist.size();i++)
                {
                    Node node = cur.adjacentlist.get(i);
                    int weight = cur.weightlist.get(i);

                    if(dp[cur.id][fish]+weight<dp[node.id][fish|node.fish])
                    {
                        qx.add(node);
                        bits.add(fish|node.fish);
                        dp[node.id][fish|node.fish] = dp[cur.id][fish]+weight;
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
        int k = sc.nextInt();

        Graph gr = new Graph(n, k);
        for(int i=0;i<n;i++)
        {
            int t = sc.nextInt();
            Node node = gr.nodelist.get(i);
            while(t-->0)
            {
                int f = sc.nextInt()-1;
                node.fish |= 1<<f;
            }
        }

        for(int i=0;i<m;i++)
        {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            int weight = sc.nextInt();
            gr.addEdge(u, v, weight);
        }
        gr.traverse();

        int lim = (int) Math.pow(2, k);
        int[][] dp = gr.dp;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<lim;j++)
                if(dp[i][j] == Integer.MAX_VALUE/2)
                    dp[i][j] = 0;
        }

        for(int i=0;i<lim;i++)
        {
            for(int j=i+1;j<lim-1;j++)
            {
                if((i|j)==(lim-1))
                {
                    if(dp[n-1][i] == 0 || dp[n-1][j] == 0)
                        continue;
                    min = Math.min(min, Math.max(dp[n-1][i], dp[n-1][j]));
                }
            }
        }

        min = Math.min(min, dp[n-1][lim-1]);

        System.out.println(min);
    }
}
