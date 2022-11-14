import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EagerPrimTest {
    static class Vertex implements Comparable<Vertex> {
        int node;
        boolean inTree;
        Edge mstEdge;
        long minEdgeWeight;
        ArrayList<Edge> edgelist;
        Vertex(int nodeno)
        {
            this.node = nodeno;
            this.inTree = false;
            mstEdge = null;
            minEdgeWeight = Long.MAX_VALUE;
            edgelist = new ArrayList<>();
        }

        @Override
        public int compareTo(Vertex v) {
            return Double.compare(this.minEdgeWeight,v.minEdgeWeight);
        }
    }

    static class Edge {
        Vertex source;
        Vertex sink;
        long weight;
        Edge(Vertex source, Vertex sink, long weight)
        {
            this.source = source;
            this.sink = sink;
            this.weight = weight;
        }
    }

    static class Graph {
        ArrayList<Vertex> vertexlist;
        ArrayList<Edge> edgelist;
        Graph()
        {
            this.vertexlist = new ArrayList<>();
            this.edgelist = new ArrayList<>();
        }
        public void addVertex(int nodeno) {
            this.vertexlist.add(new Vertex(nodeno));
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

    static class EagerPrim {
        Graph mst;
        long cost;
        EagerPrim(Graph gr) {
            mst = new Graph();
            mst.vertexlist.addAll(gr.vertexlist);
            cost = 0;
        }
        public void makeMST(Graph gr) {
            int gsize = gr.vertexlist.size();
            Vertex initSource = gr.vertexlist.get(0);

            TreeSet<Vertex> bst = new TreeSet<>();

            initSource.minEdgeWeight = 0;
            bst.add(initSource);

            int count=0;
            while(count!=gsize && !bst.isEmpty())
            {
                Vertex cur = bst.pollFirst();
                cur.inTree = true;
                if(count!=0)
                {
                    mst.addEdge(cur.mstEdge);
                    cost += cur.minEdgeWeight;
                }
                count++;

                for(Edge e: cur.edgelist)
                {
                    Vertex source = e.source;
                    Vertex sink = e.sink;
                    long weight = e.weight;

                    if(source == cur)
                    {
                        if(sink.inTree)
                        {
                            continue;
                        }
                        if(sink.minEdgeWeight == Integer.MAX_VALUE)
                        {
                            sink.mstEdge = e;
                            sink.minEdgeWeight = weight;
                            bst.add(sink);
                        }
                        else if(sink.minEdgeWeight>weight)
                        {
                            bst.remove(sink);
                            sink.mstEdge = e;
                            sink.minEdgeWeight = weight;
                            bst.add(sink);
                        }
                    }
                    else
                    {
                        if(source.inTree)
                        {
                            continue;
                        }
                        if(source.minEdgeWeight == Integer.MAX_VALUE)
                        {
                            source.mstEdge = e;
                            source.minEdgeWeight = weight;
                            bst.add(source);
                        }
                        else if(source.minEdgeWeight>weight)
                        {
                            bst.remove(source);
                            source.mstEdge = e;
                            source.minEdgeWeight = weight;
                            bst.add(source);
                        }
                    }
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
        while(t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            Graph gr = new Graph();
            for(int i=0;i<n;i++)
            {
                gr.addVertex(i);
            }

            for(int i=0;i<m;i++){
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                long weight = sc.nextLong();
                gr.addEdges(gr.vertexlist.get(u), gr.vertexlist.get(v), weight);
            }

            EagerPrim eap = new EagerPrim(gr);
            eap.makeMST(gr);

            /*System.out.println("The cost of the minimum spanning tree is : " + eap.cost);
            System.out.println("The edgelist of the spanning tree : ");
            for(Edge e: eap.mst.edgelist)
            {
                System.out.println(e.source.node+" "+e.sink.node+" "+e.weight);
            }*/

            sb.append(eap.cost).append(" ").append(eap.mst.edgelist.size());
            for(Edge e: eap.mst.edgelist) {
                sb.append(e.source.node).append(" ").append(e.sink.node).
                        append(" ").append(e.weight).append("\n");
            }
        }

        System.out.println(sb);

        long end = System.currentTimeMillis();
        System.out.println(((double) end-start)/1000.0);
    }
}
