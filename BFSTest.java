import java.util.*;

class BFSTest
{
    static class Vertex {
        int data;
        List<Vertex> edgelist;
        Vertex(final int val)
        {
            this.data = val;
            this.edgelist = new ArrayList<>();
        }
        public void addNeighbour(final Vertex neighbour)
        {
            edgelist.add(neighbour);
        }
    }

    static class BFS
    {
        public void bfs(final Vertex source)
        {
            final Queue<Vertex> queue = new LinkedList<>();
            final Map<Vertex,Boolean> visited = new HashMap<>();

            queue.add(source);
            visited.put(source,true);

            while(!queue.isEmpty())
            {
                final Vertex cur = queue.remove();
                for(final Vertex v: cur.edgelist)
                {
                    if(!visited.containsKey(v))
                    {
                        queue.add(v);
                        visited.put(v,true);
                    }
                }
                System.out.print(cur.data+" ");
            }
            System.out.println();
        }
    }

    public static void main(final String[] args)
    {
        final Vertex v1 = new Vertex(1);
        final Vertex v2 = new Vertex(2);
        final Vertex v3 = new Vertex(3);
        final Vertex v4 = new Vertex(4);
        final Vertex v5 = new Vertex(5);
        final Vertex v6 = new Vertex(6);

        v1.addNeighbour(v2);
        v1.addNeighbour(v3);
        v2.addNeighbour(v2);
        v2.addNeighbour(v3);
        v2.addNeighbour(v6);
        v3.addNeighbour(v2);
        v3.addNeighbour(v5);
        //v4.addNeighbour(v1);
        v5.addNeighbour(v6);
        v5.addNeighbour(v1);
        v6.addNeighbour(v2);

        final BFS bfs = new BFS();
        bfs.bfs(v4);
        bfs.bfs(v1);
    }
}