import java.util.*;

public class TarjanAlgorithmTest
{
    static class Vertex
    {
        int node;
        int lowlink;
        int disc;
        boolean isVisited;
        boolean inStack;
        ArrayList<Vertex> neighbourlist;
        Vertex(int nodeno)
        {
            this.node = nodeno;
            this.disc = -1;
            this.lowlink = Integer.MAX_VALUE;
            this.isVisited = false;
            neighbourlist = new ArrayList<>();
        }
    }

    static class Edge
    {
        Vertex source;
        Vertex sink;
        Edge(Vertex source, Vertex sink)
        {
            this.source = source;
            this.sink = sink;
        }
    }

    static class Graph
    {
        ArrayList<Vertex> vertexlist;
        ArrayList<Edge> edgelist;
        Graph(int n)
        {
            vertexlist = new ArrayList<>(n);
            edgelist = new ArrayList<>();
        }
        public void addVertex(int nodeno)
        {
            this.vertexlist.add(new Vertex(nodeno));
        }
        public void addEdge(Vertex source, Vertex sink)
        {
            this.edgelist.add(new Edge(source, sink));
            source.neighbourlist.add(sink);
        }
        public void addEdge(int source, int sink)
        {
            this.addEdge(this.vertexlist.get(source),this.vertexlist.get(sink));
        }
    }

    static class TarjanAlgorithm
    {
        Stack<Vertex> sccstack;
        private int discCount;
        int countSCC;
        TarjanAlgorithm()
        {
            this.countSCC=0;
        }
        public void findSCC(Graph gr)
        {
            for(Vertex v: gr.vertexlist)
            {
                if(!v.isVisited) {
                    sccstack = new Stack<>();
                    sccstack.push(v);
                    v.isVisited = true;
                    v.inStack = true;
                    discCount = 0;
                    dfsSCC(v);
                }
            }
        }
        public void dfsSCC(Vertex v)
        {
            discCount++;
            v.disc = discCount;

            for(Vertex i: v.neighbourlist)
            {
                if(i.inStack)
                {
                    v.lowlink = Math.min(i.disc,v.lowlink);
                }
                else if(!i.isVisited)
                {
                    sccstack.push(i);
                    i.inStack = true;
                    i.isVisited = true;
                    dfsSCC(i);
                    v.lowlink = Math.min(i.lowlink,v.lowlink);
                }
            }

            if(v.lowlink > v.disc)
            {
                v.lowlink = v.disc;
            }

            if(v.lowlink == v.disc)
            {
                countSCC++;
                Vertex top;
                while((top = sccstack.pop()) != v)
                {
                    System.out.print(top.node+" ");
                    top.inStack = false;
                }
                System.out.println(v.node);
                v.inStack = false;
            }
        }
    }

    public static void main(String[] args)
    {

        int n=8;
        Graph g5 = new Graph(n);
        for(int i=0;i<n;i++)
        {
            g5.addVertex(i);
        }

        g5.addEdge(0, 1);
        g5.addEdge(1, 2);
        g5.addEdge(2, 0);
        g5.addEdge(6, 2);
        g5.addEdge(6, 0);
        g5.addEdge(6, 4);
        g5.addEdge(5, 0);
        g5.addEdge(5, 6);
        g5.addEdge(4, 5);
        g5.addEdge(7, 3);
        g5.addEdge(3, 7);
        g5.addEdge(3, 4);
        g5.addEdge(7, 5);

        TarjanAlgorithm ta = new TarjanAlgorithm();
        ta.findSCC(g5);
        System.out.println(ta.countSCC);
    }
}