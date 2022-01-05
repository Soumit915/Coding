import java.util.*;

public class EdmondsKarpTest
{
    static class Vertex
    {
        int node;
        boolean isVisited;
        Vertex previous;
        int bfsflow;
        ArrayList<Vertex> neighbourlist;
        Map<Vertex, Edge> adjacencylist;
        Vertex(int nodeno)
        {
            this.node = nodeno;
            this.isVisited = false;
            this.previous = null;
            this.neighbourlist = new ArrayList<>();
            this.adjacencylist = new HashMap<>();
        }
    }

    static class Edge
    {
        Vertex fromVertex;
        Vertex targetVertex;
        int capacity;
        Edge(Vertex fromVertex, Vertex targetVertex, int capacity)
        {
            this.fromVertex = fromVertex;
            this.targetVertex = targetVertex;
            this.capacity = capacity;
        }
    }

    static class FlowNetwork
    {
        ArrayList<Vertex> vertexlist;
        ArrayList<Edge> edgelist;
        FlowNetwork(int n)
        {
            this.vertexlist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                this.vertexlist.add(new Vertex(i));
            }
            this.edgelist = new ArrayList<>();
        }
        public void addEdge(Vertex fromVertex, Vertex targetVertex, int capacity)
        {
            Edge e = new Edge(fromVertex, targetVertex, capacity);
            this.edgelist.add(e);

            fromVertex.adjacencylist.put(targetVertex, e);
            fromVertex.neighbourlist.add(targetVertex);
        }
        public void addEdge(int fromVertex, int targetVertex, int capacity)
        {
            this.addEdge(this.vertexlist.get(fromVertex), this.vertexlist.get(targetVertex), capacity);
        }
    }

    static class EdmondsKarp
    {
        int maxFlow;
        EdmondsKarp()
        {
            this.maxFlow = 0;
        }
        public void maxFlow(FlowNetwork fn, Vertex source, Vertex sink)
        {
            while(hasAugmentingPath(source, sink))
            {
                //find minimum flow from the path   O(n)
                Vertex pre = sink;
                int min = Integer.MAX_VALUE;
                while(pre.previous != null)
                {
                    min = Math.min(min, pre.bfsflow);
                    pre = pre.previous;
                }

                //then increase the flow of each edge by the minimum value O(n) and have an augmenting path
                Vertex target = sink;
                while((pre = target.previous)!=null)
                {
                    Edge e = pre.adjacencylist.get(target);
                    //e.flow += min;
                    e.capacity -= min;
                    target = pre;
                }

                //initialize previous, bfsFlow and Visited to their default values  O(n)
                for(Vertex v: fn.vertexlist)
                {
                    v.previous = null;
                    v.bfsflow = 0;
                    v.isVisited = false;
                }

                //increase the value of the maxFlow by the minimum value    O(1)
                this.maxFlow += min;
            }
        }
        public boolean hasAugmentingPath(Vertex source, Vertex sink)
        {
            Queue<Vertex> queue = new LinkedList<>();
            queue.add(source);
            source.isVisited = true;

            while(!queue.isEmpty())
            {
                Vertex cur = queue.remove();
                for(Vertex v: cur.neighbourlist)
                {
                    Edge e = cur.adjacencylist.get(v);
                    Vertex neighbour = e.targetVertex;
                    int curCapacity = e.capacity;
                    if(curCapacity == 0)
                        continue;
                    if(neighbour == sink)
                    {
                        neighbour.isVisited = true;
                        neighbour.previous = cur;
                        neighbour.bfsflow = curCapacity;
                        return true;
                    }
                    else if(!neighbour.isVisited)
                    {
                        queue.add(neighbour);
                        neighbour.isVisited = true;
                        neighbour.previous = cur;
                        neighbour.bfsflow = curCapacity;
                    }
                }
            }

            return false;
        }
        public void findMinCut(Vertex source)
        {
            Stack<Vertex> stk = new Stack<>();
            Stack<Integer> indexstk = new Stack<>();
            ArrayList<Edge> cutSet = new ArrayList<>();
            stk.push(source);
            indexstk.push(-1);
            source.isVisited = true;

            while(!stk.isEmpty())
            {
                Vertex cur = stk.pop();
                int ind = indexstk.pop();

                if(cur.neighbourlist.size()>ind+1)
                {
                    ind++;
                    stk.push(cur);
                    indexstk.push(ind);
                    Vertex togo = cur.neighbourlist.get(ind);
                    if(togo.isVisited)
                        continue;
                    Edge e = cur.adjacencylist.get(togo);
                    if(e.capacity != 0)
                    {
                        togo.isVisited = true;
                        stk.push(togo);
                        indexstk.push(-1);
                    }
                    else
                    {
                        cutSet.add(e);
                    }
                }
            }

            System.out.println("\nThe minimum cut-set is : ");
            for(Edge e: cutSet)
            {
                if(e.fromVertex.isVisited && !e.targetVertex.isVisited)
                    System.out.println(e.fromVertex.node+" --> "+e.targetVertex.node + "(" +e.capacity + ")");
            }
        }
    }

    public static void main(String[] args)
    {
        int n = 6;
        FlowNetwork fn = new FlowNetwork(n);

        /*fn.addEdge(0, 1, 2);
        fn.addEdge(0, 2, 3);
        fn.addEdge(0, 5, 5);
        fn.addEdge(1, 3, 3);
        fn.addEdge(5, 4, 4);
        fn.addEdge(3, 6, 3);
        fn.addEdge(4, 6, 2);*/

        /*fn.addEdge(0, 1, 10);
        fn.addEdge(0, 2, 10);
        fn.addEdge(1, 3, 4 );
        fn.addEdge(1, 4, 8 );
        fn.addEdge(1, 2, 2 );
        fn.addEdge(2, 4, 9 );
        fn.addEdge(3, 5, 10 );
        fn.addEdge(4, 3, 6 );
        fn.addEdge(4, 5, 10 );*/

        fn.addEdge(0,1,16);
        fn.addEdge(0,2,13);
        fn.addEdge(1,2,10);
        fn.addEdge(2,1,4);
        fn.addEdge(1,3,12);
        fn.addEdge(3,2,9);
        fn.addEdge(2,4,14);
        fn.addEdge(4,3,7);
        fn.addEdge(3,5,20);
        fn.addEdge(4,5,4);

        EdmondsKarp ek = new EdmondsKarp();
        ek.maxFlow(fn, fn.vertexlist.get(0),fn.vertexlist.get(5));
        System.out.println("The maximum flow is : "+ek.maxFlow);

        for(Vertex v: fn.vertexlist)
        {
            v.isVisited = false;
        }

        ek.findMinCut(fn.vertexlist.get(0));
    }
}
