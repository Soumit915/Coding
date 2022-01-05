import java.util.*;

public class ArticulationPointTest
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
            this.inStack = false;
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
            sink.neighbourlist.add(source);
        }
        public void addEdge(int source, int sink)
        {
            this.addEdge(this.vertexlist.get(source),this.vertexlist.get(sink));
        }
    }

    static class ArticulationPoint
    {
        private int discCount;
        ArrayList<Vertex> articulationPoint;
        ArrayList<ArrayList<Edge>> biconnectedComponents;
        ArticulationPoint()
        {
            articulationPoint = new ArrayList<>();
            biconnectedComponents = new ArrayList<>();
        }
        public void findArticulationPoint(Graph gr)
        {
            for(Vertex v: gr.vertexlist)
            {
                if(!v.isVisited)
                {
                    discCount = 0;
                    v.isVisited = true;
                    v.inStack = true;
                    dfsArticulation(v);
                }
            }
        }
        public void dfsArticulation(Vertex source)
        {
            source.disc = discCount;
            discCount++;
            int rootCount=0;

            for(Vertex v: source.neighbourlist)
            {
                if(v.inStack)
                {
                    source.lowlink = Math.min(v.disc,source.lowlink);
                }
                else if(!v.isVisited)
                {
                    if(source.disc == 0)
                        rootCount++;
                    v.isVisited = true;
                    v.inStack = true;
                    dfsArticulation(v);
                    source.lowlink = Math.min(v.lowlink,source.lowlink);
                }
            }

            if(source.disc == 0)
            {
                if(rootCount>1)
                {
                    articulationPoint.add(source);
                    source.inStack = false;
                    return;
                }
            }

            for(Vertex v: source.neighbourlist)
            {
                if(!v.inStack && v.lowlink>=source.disc)
                {
                    articulationPoint.add(source);
                    break;
                }
            }

            source.inStack = false;
        }
    }
    public static void main(String[] args)
    {
        int n=5;
        Graph gr = new Graph(n);
        for(int i=0;i<n;i++)
        {
            gr.addVertex(i);
        }

        gr.addEdge(gr.vertexlist.get(0),gr.vertexlist.get(1));
        gr.addEdge(gr.vertexlist.get(0),gr.vertexlist.get(2));
        gr.addEdge(gr.vertexlist.get(1),gr.vertexlist.get(2));
        gr.addEdge(gr.vertexlist.get(0),gr.vertexlist.get(3));
        gr.addEdge(gr.vertexlist.get(4),gr.vertexlist.get(3));

        ArticulationPoint ap = new ArticulationPoint();
        ap.findArticulationPoint(gr);

        if(ap.articulationPoint.size()!=0)
        {
            System.out.println("The articulation points of the graph are : ");
            for(Vertex v: ap.articulationPoint)
                System.out.println(v.node);
        }
        else
        {
            System.out.println("The graph has no articulation points");
        }
    }
}