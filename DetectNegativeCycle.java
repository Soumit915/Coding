import java.util.*;

public class DetectNegativeCycle {
    static class Vertex
    {
        int node;
        boolean isVisited;
        ArrayList<Integer> edgelist;
        ArrayList<Integer> weightlist;
        Vertex(int node)
        {
            this.node = node;
            isVisited = false;
            edgelist = new ArrayList<>();
            weightlist = new ArrayList<>();
        }
    }

    static class Edge
    {
        int source;
        int sink;
        int weight;
        Edge(int source, int sink, int weight)
        {
            this.source = source;
            this.sink = sink;
            this.weight = weight;
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
            Vertex newv = new Vertex(node);
            vertexlist.add(newv);
        }
        public void addEdges(int source, int sink, int weight)
        {
            Edge newedge = new Edge(source, sink, weight);
            edgelist.add(newedge);

            vertexlist.get(source).edgelist.add(sink);
            vertexlist.get(source).weightlist.add(weight);
        }
    }

    static class NegativeCycleDetector
    {
        ArrayList<Integer> cyclelist;
        boolean hasNegativeCycle;
        NegativeCycleDetector()
        {
            hasNegativeCycle = false;
            cyclelist = new ArrayList<>();
        }
        public void detectNegativeCycle(Graph gr)
        {
            int nov = gr.vertexlist.size();
            int global_source = 0;
            int[] distance = new int[nov];
            int[] previous = new int[nov];
            Arrays.fill(previous,-1);
            Arrays.fill(distance,Integer.MAX_VALUE);
            distance[global_source] = 0;

            for(int i=0;i<gr.vertexlist.size()-1;i++)
            {
                int count=0;
                for(Edge e: gr.edgelist)
                {
                    int source = e.source;
                    int sink = e.sink;
                    int weight = e.weight;
                    if(distance[source]==Integer.MAX_VALUE)
                        continue;
                    int newdist = distance[source]+weight;
                    if(newdist<distance[sink])
                    {
                        distance[sink] = newdist;
                        previous[sink] = source;
                        count++;
                    }
                }
                if(count==0)
                {
                    hasNegativeCycle = false;
                    return;
                }
            }

            //For detecting negative cycle
            for(Edge e: gr.edgelist)
            {
                int source = e.source;
                int sink = e.sink;
                int weight = e.weight;
                if(distance[source]==Integer.MAX_VALUE)
                    continue;
                int newdist = distance[source]+weight;
                if(newdist<distance[sink])
                {
                    makeCycleList(source, previous);
                    hasNegativeCycle = true;
                    display();
                    break;
                }
            }
        }
        public void makeCycleList(int source, int[] previous)
        {
            Set<Integer> isPresent = new HashSet<>();
            ArrayList<Integer> locCycle = new ArrayList<>();

            int pre = source;
            while(!isPresent.contains(pre))
            {
                locCycle.add(pre);
                isPresent.add(pre);
                pre = previous[pre];
            }
            locCycle.add(pre);
            Collections.reverse(locCycle);

            int start = pre;
            boolean flag = false;
            for(int i:locCycle)
            {
                if(i==start)
                {
                    if(flag)
                        break;
                    else
                        flag = true;
                }
                cyclelist.add(i);
            }
        }
        public void display()
        {
            if(!hasNegativeCycle)
            {
                System.out.println("There is no negative cycle in the graph.");
                return;
            }

            System.out.println("There is a negative cycle present in the graph.");
            System.out.print("The negative cycle is : ");
            for(int i=0;i<cyclelist.size()-1;i++)
            {
                System.out.print(cyclelist.get(i)+"-->");
            }
            System.out.println(cyclelist.get(cyclelist.size()-1));
        }
    }
    public static void main(String[] args)
    {
        Graph gr = new Graph();
        for(int i=0;i<4;i++)
        {
            gr.addVertex(i);
        }

        //There are 8 edges
        /*gr.addEdges(0,1,4);
        gr.addEdges(0,2,4);
        gr.addEdges(2,4,4);
        gr.addEdges(2,5,-2);
        gr.addEdges(3,2,2);
        gr.addEdges(3,0,3);
        gr.addEdges(4,3,1);
        gr.addEdges(4,6,-2);
        gr.addEdges(5,4,-3);
        gr.addEdges(5,1,3);
        gr.addEdges(6,5,2);
        gr.addEdges(6,7,2);
        gr.addEdges(7,4,-2);*/

        gr.addEdges(0,1,5);
        gr.addEdges(0,2,4);
        gr.addEdges(2,1,-6);
        gr.addEdges(1,3,3);
        gr.addEdges(3,2,2);

        NegativeCycleDetector dnc = new NegativeCycleDetector();
        dnc.detectNegativeCycle(gr);
    }
}
