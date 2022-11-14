import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class KruskalTest
{
    static class Vertex
    {
        int node;
        UnionFindSet set;
        ArrayList<Integer> edgelist;
        ArrayList<Long> weightlist;
        Vertex(int node)
        {
            this.node = node;
            this.set = new UnionFindSet();
            edgelist = new ArrayList<>();
            weightlist = new ArrayList<>();
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
        public int compareTo(Edge edge)
        {
            return Double.compare(this.weight,edge.weight);
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
        public void addVertex(int node)
        {
            vertexlist.add(new Vertex(node));
        }
        public void addEdges(int source, int sink, long weight)
        {
            Edge newedge = new Edge(this.vertexlist.get(source), this.vertexlist.get(sink), weight);
            edgelist.add(newedge);

            vertexlist.get(source).edgelist.add(sink);
            vertexlist.get(source).weightlist.add(weight);

            vertexlist.get(sink).edgelist.add(source);
            vertexlist.get(sink).weightlist.add(weight);
        }
        public void addEdge(Edge e)
        {
            this.addEdges(e.source.node,e.sink.node,e.weight);
        }
    }

    static class UnionFindSet
    {
        int count;
        UnionFindSet parent;
        UnionFindSet()
        {
            count=1;
            this.parent = this;
        }
        public UnionFindSet getParent()
        {
            if(this.parent == this)
                return this;
            this.parent = this.parent.getParent();
            return this.parent;
        }
        public void union(UnionFindSet setb)
        {
            int ac = this.count;
            int bc = setb.count;
            if(ac>bc)
            {
                setb.parent = this.parent;
                setb.count += this.count;
            }
            else
            {
                this.parent = setb.parent;
                this.count += setb.count;
            }
        }
    }

    static class KruskalMST
    {
        Graph kmst;
        long cost;
        KruskalMST(Graph gr)
        {
            this.kmst = new Graph();
            kmst.vertexlist.addAll(gr.vertexlist);
            cost = 0;
        }
        public void makeMST(Graph gr)
        {
            int gsize = gr.vertexlist.size();
            PriorityQueue<Edge> minHeap = new PriorityQueue<>(gr.edgelist);

            int count=0;
            while(count!=gsize-1 && !minHeap.isEmpty())
            {
                Edge minEdge = minHeap.remove();
                Vertex source = minEdge.source;
                Vertex sink = minEdge.sink;

                if(source.set.getParent() != sink.set.getParent())
                {
                    kmst.addEdge(minEdge);
                    source.set.union(sink.set);
                    count++;
                    cost+=minEdge.weight;
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        /*Graph gr = new Graph();
        int n=4;
        for(int i=0;i<n;i++)
        {
            gr.addVertex(i);
        }

        gr.addEdges(1,0,5);
        gr.addEdges(0,2,3);
        gr.addEdges(3,0,6);
        gr.addEdges(1,3,7);
        gr.addEdges(1,2,4);
        gr.addEdges(2,3,5);

        KruskalMST kMST = new KruskalMST(gr);
        kMST.makeMST(gr);
        System.out.println("The cost of the spanning tree is : " + kMST.cost);
        Graph tree = kMST.kmst;
        System.out.print("The vertextlist of the spanning tree ");
        for(Vertex v:tree.vertexlist)
        {
            System.out.print(v.node+" ");
        }
        System.out.println();

        System.out.println("The edgelist of the spanning tree : ");
        for(Edge e:tree.edgelist)
        {
            System.out.println(e.source.node+" "+e.sink.node+" "+e.weight);
        }*/

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
                gr.addEdges(u, v, weight);
            }

            KruskalMST KMST = new KruskalMST(gr);
            KMST.makeMST(gr);

            /*System.out.println("The cost of the minimum spanning tree is : " + eap.cost);
            System.out.println("The edgelist of the spanning tree : ");
            for(Edge e: eap.mst.edgelist)
            {
                System.out.println(e.source.node+" "+e.sink.node+" "+e.weight);
            }*/

            sb.append(KMST.cost).append(" ").append(KMST.kmst.edgelist.size());
            for(Edge e: KMST.kmst.edgelist) {
                sb.append(e.source.node).append(" ").append(e.sink.node).
                        append(" ").append(e.weight).append("\n");
            }
        }

        System.out.println(sb);

        long end = System.currentTimeMillis();
        System.out.println(((double) end-start)/1000.0);
    }
}
