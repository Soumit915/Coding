import java.util.*;

class DFSTest {
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
    static class DFS
    {
        Map<Vertex,Boolean> visited = new HashMap<>();
        public void dfsIterative(final Vertex source)
        {
            final Stack<Vertex> stk = new Stack<>();
            final Map<Vertex,Boolean> visited = new HashMap<>();

            stk.push(source);
            visited.put(source,true);

            while(!stk.empty())
            {
                final Vertex cur = stk.pop();
                for(final Vertex v: cur.edgelist)
                {
                    if(!visited.containsKey(v))
                    {
                        stk.push(v);
                        visited.put(v,true);
                    }
                }
                System.out.print(cur.data+" ");
            }
            System.out.println();
        }
        public void dfsRecursive(final Vertex source)
        {
            if(this.visited.containsKey(source))
                return;
            System.out.print(source.data+" ");
            this.visited.put(source,true);
            for(final Vertex v: source.edgelist)
            {
                if(!this.visited.containsKey(v))
                {
                    this.dfsRecursive(v);
                }
            }
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
        v4.addNeighbour(v1);
        v5.addNeighbour(v6);
        v5.addNeighbour(v1);
        v6.addNeighbour(v2);

        final DFS dfs = new DFS();
        dfs.dfsIterative(v4);
        dfs.dfsRecursive(v4);
        System.out.println();
    }
}
