package Hackerrank.Algorithms.Graph;

import java.util.*;

public class EvenEdges {
    static class Vertex
    {
        int id;
        ArrayList<Vertex> adjacencyList;
        Vertex(int id)
        {
            this.id = id;
            adjacencyList = new ArrayList<>();
        }
    }

    static class Graph
    {
        ArrayList<Vertex> vertexlist;
        int ans;
        Graph(int n)
        {
            this.vertexlist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                this.vertexlist.add(new Vertex(i));
            }
            this.ans = 0;
        }
        public void addEdge(Vertex source, Vertex sink)
        {
            source.adjacencyList.add(sink);
            sink.adjacencyList.add(source);
        }
        public void removeEdge(Vertex source, Vertex sink)
        {
            source.adjacencyList.remove(sink);
            sink.adjacencyList.remove(source);
        }
        public int dfsCount(Vertex source, Vertex bound)
        {
            int count = 1;
            for(int i=0;i<source.adjacencyList.size();i++)//Vertex s: source.adjacencyList)
            {
                Vertex s = source.adjacencyList.get(i);
                if(s!=bound)
                {
                    int c = dfsCount(s, source);
                    if(c%2==0)
                    {
                        removeEdge(s, source);
                        i--;
                        this.ans++;
                    }
                    count += c;
                }
            }

            return count;
        }
    }

    static int evenForest(int n, int m, List<Integer> t_from, List<Integer> t_to)
    {
        Graph gr = new Graph(n);
        for(int i=0;i<m;i++)
        {
            gr.addEdge(gr.vertexlist.get(t_from.get(i)-1),gr.vertexlist.get(t_to.get(i)-1));
        }

        Vertex source = gr.vertexlist.get(0);
        for(int i=0;i<source.adjacencyList.size();i++)
        {
            Vertex s = source.adjacencyList.get(i);
            int c = gr.dfsCount(s, source);
            if(c%2==0) {
                gr.removeEdge(source, s);
                gr.ans++;
                i--;
            }
        }

        return gr.ans;
    }
    public  static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Integer> t_from = new ArrayList<>(m);
        List<Integer> t_to = new ArrayList<>(m);
        for(int i=0;i<m;i++)
        {
            t_from.add(sc.nextInt());
            t_to.add(sc.nextInt());
        }

        int count = evenForest(n, m, t_from, t_to);
        System.out.println(count);
    }
}
