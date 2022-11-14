import java.util.*;

public class TopologicalSortKahn {
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

    static class TopologicalOrder{
        Queue<Integer> queue = new LinkedList<>();
        public void findTopoOrder(Vertex[] gr)
        {
            int gs = gr.length;
            int[] indegree = new int[gs];
            int i;
            for(i=0;i<gs;i++)
            {
                indegree[i] = 0;
            }

            for(Vertex v:gr)
            {
                for(int j: v.edgelist)
                {
                    indegree[j]++;
                }
            }

            for(i=0;i<gs;i++)
            {
                if(indegree[i]==0)
                {
                    queue.add(i);
                }
            }

            while(!queue.isEmpty())
            {
                int cur = queue.remove();
                System.out.print(cur+" ");
                for(int j: gr[cur].edgelist)
                {
                    indegree[j]--;
                    if(indegree[j] == 0)
                        queue.add(j);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        /*Vertex[] gr = new Vertex[7];
        int i;
        for(i=0;i<7;i++)
        {
            gr[i] = new Vertex(i);
        }

        gr[0].addNeighbour(1);
        gr[1].addNeighbour(2);
        gr[1].addNeighbour(3);
        gr[1].addNeighbour(4);
        gr[4].addNeighbour(5);
        gr[6].addNeighbour(3);
        gr[3].addNeighbour(4);
        gr[2].addNeighbour(4);*/

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
