import java.util.*;

public class DijkstraTest {
    static class Vertex implements Comparable<Vertex>
    {
        int node;
        int distance;
        boolean isRelaxed;
        ArrayList<Integer> edgelist;
        ArrayList<Integer> weightlist;
        Vertex(int node)
        {
            this.node = node;
            this.distance = Integer.MAX_VALUE;
            this.isRelaxed = false;
            edgelist = new ArrayList<>();
            weightlist = new ArrayList<>();
        }
        public void addNeighbour(int nbour, int weight)
        {
            this.edgelist.add(nbour);
            this.weightlist.add(weight);
        }

        @Override
        public int compareTo(Vertex o) {
            return Double.compare(this.distance,o.distance);
        }
    }

    static class Dijkstra
    {
        int n;
        int source;
        Vertex[] gr;
        int[] previous;
        Dijkstra(Vertex[] gr,int n,int source)
        {
            this.source = source;
            this.gr = new Vertex[n];
            for(int i=0;i<n;i++)
            {
                this.gr[i] = new Vertex(i);
                this.gr[i].edgelist = gr[i].edgelist;
                this.gr[i].weightlist = gr[i].weightlist;
                this.gr[i].distance = Integer.MAX_VALUE;
                this.gr[i].isRelaxed = false;
            }
            this.n = n;
            previous = new int[n];
            previous[source] =  -1;
            this.gr[source].distance = 0;
        }
        public void shortestPath() // Try to perform treemap operation inorder to reduce the operation to nlogn
        {
            int i;
            PriorityQueue<Vertex> minheap = new PriorityQueue<>();
            for(i=0;i<n;i++)
            {
                minheap.add(gr[i]);
            }

            while (!minheap.isEmpty())
            {
                Vertex relaxed = minheap.poll();
                relaxed.isRelaxed = true;
                for(i=0;i<relaxed.edgelist.size();i++)
                {
                    int sinknode = relaxed.edgelist.get(i);
                    if(gr[sinknode].isRelaxed)
                        continue;
                    int weight = relaxed.weightlist.get(i);
                    int newdist = relaxed.distance+weight;
                    if(newdist<gr[sinknode].distance)
                    {
                        minheap.remove(gr[sinknode]);
                        gr[sinknode].distance = newdist;
                        minheap.add(gr[sinknode]);
                        previous[sinknode] = relaxed.node;
                    }
                }
            }
        }
        public void display()
        {
            System.out.println();
            System.out.println("The source vertex is : "+source+".\n");
            for(Vertex v: gr)
            {
                if(v.node == source)
                    continue;
                System.out.println("The distance from "+source+" to "+v.node+" is: "+v.distance);
                System.out.print("The route is : ");
                Stack<Integer> path = new Stack<>();

                int pre = previous[v.node];
                while(pre!=-1)
                {
                    path.push(pre);
                    pre = previous[pre];
                }

                while(!path.isEmpty())
                {
                    int p = path.pop();
                    System.out.print(p+" --> ");
                }
                System.out.println(v.node);
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int n=6,i;
        Vertex[] gr = new Vertex[n];
        for(i=0;i<n;i++)
        {
            gr[i] = new Vertex(i);
        }

        gr[0].addNeighbour(1,4);
        gr[0].addNeighbour(2,3);
        gr[1].addNeighbour(2,5);
        gr[1].addNeighbour(3,2);
        gr[2].addNeighbour(3,7);
        gr[3].addNeighbour(4,2);
        gr[4].addNeighbour(0,4);
        gr[4].addNeighbour(1,4);
        gr[4].addNeighbour(5,6);

        while(true)
        {
            System.out.print("Enter the source node : ");
            int source = sc.nextInt();

            if(source == -1 || source > 5)
                System.exit(0);

            Dijkstra djk = new Dijkstra(gr,n,source);
            djk.shortestPath();
            djk.display();
        }
    }
}
