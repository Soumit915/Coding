import java.util.*;

public class TopologicalOrderTest {
    static class Vertex
    {
        int data;
        boolean isVisited;
        boolean beingVisited;
        List<Integer> edgelist;
        Vertex(int nodeno)
        {
            data = nodeno;
            isVisited = false;
            beingVisited = false;
            edgelist = new ArrayList<>();
        }
        public void addNeighbour(int nbour)
        {
            this.edgelist.add(nbour);
        }
    }

    static class TopologicalOrder
    {
        Stack<Integer> stk = new Stack<>();
        public void findTopoOrder(Vertex[] gr)
        {
            for(Vertex v: gr)
            {
                if(!v.isVisited)
                {
                    //exploreNode(gr,v.data);
                    explodeNodeRec(gr,v.data);
                }
            }

            System.out.print("The topological ordering of the graph is : ");
            while(!stk.isEmpty())
            {
                System.out.print(stk.pop()+" ");
            }
            System.out.println();
        }
        /*This part of the code has a bug of performing dfs using iterative method
        public void exploreNode(Vertex[] gr, int source)
        {
            Stack<Integer> dfstk = new Stack<Integer>();
            dfstk.push(source);

            int cur;
            while(!dfstk.isEmpty())
            {
                cur = dfstk.peek();
                if(gr[cur].edgelist.size() == 0 || gr[cur].beingVisited)
                {
                    stk.push(cur);
                    gr[cur].isVisited = true;
                    dfstk.pop();
                    gr[cur].beingVisited = false;
                    continue;
                }
                gr[cur].beingVisited = true;
                for(int i: gr[cur].edgelist)
                {
                    if(!gr[i].isVisited)
                    {
                        dfstk.push(i);
                        gr[i].isVisited = true;
                    }
                }
            }

        }*/
        public void explodeNodeRec(Vertex[] gr, int source)
        {
            gr[source].isVisited = true;
            for(int i: gr[source].edgelist)
            {
                if(!gr[i].isVisited)
                {
                    explodeNodeRec(gr,i);
                }
            }
            stk.push(source);
        }
    }

    public static void main(String[] args)
    {
        int n=6;
        Vertex[] gr = new Vertex[n];
        int i;
        for(i=0;i<n;i++)
        {
            gr[i] = new Vertex(i);
        }

        /*for n=7
        gr[0].addNeighbour(1);
        gr[1].addNeighbour(2);
        gr[1].addNeighbour(3);
        gr[1].addNeighbour(4);
        gr[4].addNeighbour(5);
        gr[6].addNeighbour(3);
        gr[3].addNeighbour(4);
        gr[2].addNeighbour(4);*/

        //for n=6
        gr[0].addNeighbour(1);
        gr[1].addNeighbour(2);
        gr[2].addNeighbour(3);
        gr[1].addNeighbour(3);
        gr[1].addNeighbour(5);
        gr[4].addNeighbour(3);
        gr[5].addNeighbour(4);

        TopologicalOrder tpo = new TopologicalOrder();
        tpo.findTopoOrder(gr);
    }
}
