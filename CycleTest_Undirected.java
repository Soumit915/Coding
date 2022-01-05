import java.util.*;

public class CycleTest_Undirected
{
    static class Vertex
    {
        int node;
        boolean isVisited;
        int previous;
        ArrayList<Integer> edgelist;
        Vertex(int nodeno)
        {
            node = nodeno;
            isVisited = false;
            previous = -1;
            edgelist = new ArrayList<>();
        }
    }

    static class Graph
    {
        Vertex[] gr;
        Graph(int n)
        {
            gr = new Vertex[n];
            int i;
            for(i=0;i<n;i++)
            {
                gr[i] = new Vertex(i);
            }
        }
        public void addEdge(int i, int j)
        {
            gr[i].edgelist.add(j);
            gr[j].edgelist.add(i);
        }
        public void hasCycle()
        {
            for(Vertex v: gr)
            {
                if(!v.isVisited)
                {
                    dfs(v);
                }
            }
        }
        public void dfs(Vertex source)
        {
            Stack<Vertex> stk = new Stack<>();
            stk.push(source);

            while(!stk.isEmpty())
            {
                Vertex cur = stk.pop();
                cur.isVisited = true;
                for(int i: cur.edgelist)
                {
                    if(gr[i].isVisited && i!=cur.previous)
                    {
                        System.out.println("There is a cycle in the graph.");
                        return;
                    }
                    else if(!gr[i].isVisited)
                    {
                        gr[i].previous = cur.node;
                        gr[i].isVisited = true;
                        stk.push(gr[i]);
                    }
                }
            }
            System.out.println("There is no cycle in the graph");
        }
    }

    public static void main(String[] args)
    {
        int n = 5;
        Graph gr = new Graph(n);
        gr.addEdge(0,1);
        gr.addEdge(0,2);
        gr.addEdge(1,2);
        gr.addEdge(0,3);
        gr.addEdge(3,4);

        gr.hasCycle();
    }
}
