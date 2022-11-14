import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LazyPrimTest {
    static class Vertex
    {
        int node;
        boolean isVisited;
        ArrayList<Edge> edgelist;
        Vertex(int nodeno)
        {
            this.node = nodeno;
            this.isVisited = false;
            edgelist = new ArrayList<>();
        }
    }

    static class Edge implements Comparable<Edge>
    {
        Vertex source;
        Vertex sink;
        long weight;
        Edge(Vertex source, Vertex sink, long weight)
        {
            this.source = source;
            this.sink = sink;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.weight,e.weight);
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
        public void addVertex(int node) {
            this.vertexlist.add(new Vertex(node));
        }
        public void addEdges(Vertex source, Vertex sink, long weight) {
            Edge newedge = new Edge(source, sink, weight);
            edgelist.add(newedge);

            source.edgelist.add(newedge);
            sink.edgelist.add(newedge);
        }
        public void addEdge(Edge e) {
            this.addEdges(e.source,e.sink,e.weight);
        }
    }

    static class LazyPrim
    {
        Graph mst;
        long cost;
        LazyPrim(Graph gr)
        {
            mst = new Graph();
            mst.vertexlist.addAll(gr.vertexlist);
            cost = 0;
        }
        public void makeMST(Graph gr)
        {
            int gsize = gr.vertexlist.size();
            Vertex initSource = gr.vertexlist.get(0);
            initSource.isVisited = true;

            PriorityQueue<Edge> minHeap = new PriorityQueue<>(initSource.edgelist);

            int count=0;
            while(count!=gsize-1 && !minHeap.isEmpty())
            {
                Edge minEdge = minHeap.remove();
                Vertex source = minEdge.source;
                Vertex sink = minEdge.sink;

                if(!source.isVisited)
                {
                    source.isVisited = true;
                    for(Edge e: source.edgelist)
                    {
                        if(!e.source.isVisited || !e.sink.isVisited)
                        {
                            minHeap.add(e);
                        }
                    }
                    mst.addEdge(minEdge);
                    cost+=minEdge.weight;
                    count++;
                }
                else if(!sink.isVisited)
                {
                    sink.isVisited = true;
                    for(Edge e: sink.edgelist)
                    {
                        if(!e.source.isVisited || !e.sink.isVisited)
                        {
                            minHeap.add(e);
                        }
                    }
                    mst.addEdge(minEdge);
                    cost+=minEdge.weight;
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        File file = new File("Input.txt");
        Scanner sc = new Scanner(file);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t-->0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Graph gr = new Graph();
            for (int i = 0; i < n; i++) {
                gr.addVertex(i);
            }

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt() - 1;
                int v = sc.nextInt() - 1;
                long weight = sc.nextLong();
                gr.addEdges(gr.vertexlist.get(u), gr.vertexlist.get(v), weight);
            }

            LazyPrim lp = new LazyPrim(gr);
            lp.makeMST(gr);

            sb.append(lp.cost).append(" ").append(lp.mst.edgelist.size());
            for(Edge e: lp.mst.edgelist) {
                sb.append(e.source.node).append(" ").append(e.sink.node).
                        append(" ").append(e.weight).append("\n");
            }
        }

        /*System.out.println(lp.cost);
        System.out.println("The edgelist of the spanning tree : ");
        for(Edge e: lp.mst.edgelist) {
            System.out.println(e.source.node+" "+e.sink.node+" "+e.weight);
        }*/

        System.out.println(sb);

        long end = System.currentTimeMillis();
        System.out.println(((double) end-start)/1000.0);
    }
}
