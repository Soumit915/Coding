package Codechef;

import java.util.*;

public class PerCapitaIncomeTest {
    static class Vertex
    {
        int id;
        int ai;
        int bi;
        int percapita;
        boolean isVisited;
        ArrayList<Vertex> neighbourlist;
        Vertex(int id)
        {
            this.id = id;
            this.isVisited = false;
            this.neighbourlist = new ArrayList<>();
        }
    }

    static class Edge
    {
        Vertex u;
        Vertex v;
        Edge(Vertex u, Vertex v)
        {
            this.u = u;
            this.v = v;
        }
    }

    static class ChefLand
    {
        ArrayList<Vertex> vertexlist;
        ArrayList<Edge> edgelist;
        ArrayList<ArrayList<Integer>> kingdoms = new ArrayList<>();
        ChefLand()
        {
            vertexlist = new ArrayList<>();
            edgelist = new ArrayList<>();
        }
        public void addEdge(Vertex u, Vertex v)
        {
            Edge e = new Edge(u,v);
            this.edgelist.add(e);
            u.neighbourlist.add(v);
            v.neighbourlist.add(u);
        }
        public void addEdge(int u, int v)
        {
            addEdge(this.vertexlist.get(u),this.vertexlist.get(v));
        }
        public void findMaxKingdom(ArrayList<Vertex> maxProvince)
        {
            for(Vertex v: maxProvince)
            {
                if(!v.isVisited)
                {
                    dfs(v);
                }
            }

            int max = Integer.MIN_VALUE;
            for(ArrayList<Integer> lscc : kingdoms)
            {
                if(lscc.size()>max)
                {
                    max = lscc.size();
                }
            }

            ArrayList<Integer> answer = new ArrayList<>();
            for (ArrayList<Integer> kingdom : kingdoms) {
                if (kingdom.size() == max) {
                    answer = kingdom;
                    break;
                }
            }

            System.out.println(answer.size());
            for (Integer integer : answer) {
                System.out.print((integer + 1) + " ");
            }
            System.out.println();
        }
        public void dfs(Vertex source)
        {
            ArrayList<Integer> lscc = new ArrayList<>();
            Stack<Vertex> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.push(source);
            ptrstk.push(-1);
            source.isVisited = true;

            while(!stk.isEmpty())
            {
                Vertex cur = stk.pop();
                int ptr = ptrstk.pop();

                if(cur.neighbourlist.size()>ptr+1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);
                    Vertex pro = cur.neighbourlist.get(ptr);
                    if(pro.percapita<cur.percapita)
                        continue;
                    if(!pro.isVisited)
                    {
                        stk.push(pro);
                        ptrstk.push(-1);
                        pro.isVisited = true;
                        lscc.add(pro.id);
                    }
                }
            }
            kingdoms.add(lscc);
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            ChefLand cl = new ChefLand();
            int n = sc.nextInt();
            int m = sc.nextInt();

            int maxcapita = Integer.MIN_VALUE;
            for(int i=0;i<n;i++)
            {
                Vertex node = new Vertex(i);
                node.ai = sc.nextInt();
                cl.vertexlist.add(node);
            }
            for(int i=0;i<n;i++)
            {
                Vertex node = cl.vertexlist.get(i);
                node.bi = sc.nextInt();
                node.percapita = (node.ai/node.bi);
                if(node.percapita>maxcapita)
                    maxcapita = node.percapita;
            }

            for(int i=0;i<m;i++)
            {
                int u = sc.nextInt();
                int v = sc.nextInt();
                cl.addEdge(u-1,v-1);
            }

            ArrayList<Vertex> maxProvince = new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                if(cl.vertexlist.get(i).percapita==maxcapita)
                    maxProvince.add(cl.vertexlist.get(i));
            }

            cl.findMaxKingdom(maxProvince);
        }
    }
}
