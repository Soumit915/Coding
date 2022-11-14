import java.util.*;

public class KosarajuAlgorithmTest {
    static class Vertex
    {
        int node;
        ArrayList<Vertex> neighbourlist;
        Vertex(int nodeno)
        {
            this.node = nodeno;
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
        Graph()
        {
            vertexlist = new ArrayList<>();
            edgelist = new ArrayList<>();
        }
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
        public Graph transpose()
        {
            Graph transgr = new Graph();
            for(Vertex v: this.vertexlist)
            {
                transgr.addVertex(v.node);
            }
            for(Edge e: this.edgelist)
            {
                Vertex source = transgr.vertexlist.get(e.source.node);
                Vertex sink = transgr.vertexlist.get(e.sink.node);
                transgr.addEdge(sink,source);
            }
            return transgr;
        }
    }

    static class TopoLogicalOrdering
    {
        Stack<Vertex> topoStack;
        boolean[] isVisited;
        TopoLogicalOrdering(Graph gr)
        {
            int gsize = gr.vertexlist.size();
            topoStack = new Stack<>();
            isVisited = new boolean[gsize];
        }
        public void findTopoOrder(Graph gr)
        {
            for(Vertex v: gr.vertexlist)
            {
                if(!isVisited[v.node])
                    explodeNodeRec(v);
            }
        }
        public void explodeNodeRec(Vertex source)
        {
            isVisited[source.node] = true;
            for(Vertex v: source.neighbourlist)
            {
                if(!isVisited[v.node])
                {
                    explodeNodeRec(v);
                }
            }
            topoStack.push(source);
        }
    }

    static class KosarajuAlgorithm
    {
        ArrayList<ArrayList<Vertex>> scc;
        boolean[] isVisited;
        KosarajuAlgorithm(Graph gr)
        {
            int gsize = gr.vertexlist.size();
            isVisited = new boolean[gsize];
            scc = new ArrayList<>();
        }
        public void findSCC(Graph gr, Stack<Vertex> topoStack)
        {
            while(!topoStack.isEmpty())
            {
                Vertex cur = topoStack.pop();
                Vertex trcur = gr.vertexlist.get(cur.node);
                if(!isVisited[trcur.node])
                    scc.add(dfs(trcur));
            }

            System.out.println("There are "+scc.size()+" strongly connected components.");
            for(ArrayList<Vertex> eachscc: scc)
            {
                for(Vertex v: eachscc)
                {
                    System.out.print(v.node+" ");
                }
                System.out.println();
            }
        }
        public ArrayList<Vertex> dfs(Vertex source)
        {
            ArrayList<Vertex> localscc = new ArrayList<>();
            Stack<Vertex> vertexStack = new Stack<>();
            Stack<Integer> npStack = new Stack<>();

            vertexStack.push(source);
            npStack.push(-1);
            isVisited[source.node] = true;
            localscc.add(source);

            while(!vertexStack.isEmpty())
            {
                Vertex cur = vertexStack.pop();
                int curp = npStack.pop();

                if(cur.neighbourlist.size()>curp+1)
                {
                    curp++;
                    vertexStack.push(cur);
                    npStack.push(curp);
                    Vertex pro = cur.neighbourlist.get(curp);
                    if(!isVisited[pro.node])
                    {
                        vertexStack.push(pro);
                        npStack.push(-1);
                        isVisited[pro.node] = true;
                        localscc.add(pro);
                    }
                }
            }
            return localscc;
        }
    }

    public static void main(String[] args)
    {
        int n=8;
        Graph gr = new Graph(n);
        for(int i=0;i<n;i++)
        {
            gr.addVertex(i);
        }

        gr.addEdge(gr.vertexlist.get(0),gr.vertexlist.get(1));
        gr.addEdge(gr.vertexlist.get(1),gr.vertexlist.get(2));
        gr.addEdge(gr.vertexlist.get(2),gr.vertexlist.get(0));
        gr.addEdge(gr.vertexlist.get(2),gr.vertexlist.get(3));
        gr.addEdge(gr.vertexlist.get(1),gr.vertexlist.get(3));
        gr.addEdge(gr.vertexlist.get(1),gr.vertexlist.get(5));
        gr.addEdge(gr.vertexlist.get(3),gr.vertexlist.get(4));
        gr.addEdge(gr.vertexlist.get(4),gr.vertexlist.get(3));
        gr.addEdge(gr.vertexlist.get(5),gr.vertexlist.get(4));
        gr.addEdge(gr.vertexlist.get(5),gr.vertexlist.get(6));
        gr.addEdge(gr.vertexlist.get(6),gr.vertexlist.get(5));
        gr.addEdge(gr.vertexlist.get(6),gr.vertexlist.get(7));
        gr.addEdge(gr.vertexlist.get(7),gr.vertexlist.get(6));
        gr.addEdge(gr.vertexlist.get(7),gr.vertexlist.get(4));

        TopoLogicalOrdering tpo = new TopoLogicalOrdering(gr);
        tpo.findTopoOrder(gr);
        Stack<Vertex> stk = tpo.topoStack;

        Graph transgr = gr.transpose();

        KosarajuAlgorithm ka = new KosarajuAlgorithm(transgr);
        ka.findSCC(transgr,stk);
    }
}
