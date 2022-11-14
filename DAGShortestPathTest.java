import java.util.*;

public class DAGShortestPathTest {
    static class Vertex
    {
        int node;
        boolean isVisited;
        boolean beingVisited;
        ArrayList<Integer> edgelist;
        ArrayList<Integer> weightlist;
        Vertex(int node)
        {
            this.node = node;
            isVisited = false;
            beingVisited = false;
            edgelist = new ArrayList<Integer>();
            weightlist = new ArrayList<Integer>();
        }
        public void addNeighbour(int nbour,int weight)
        {
            this.edgelist.add(nbour);
            this.weightlist.add(weight);
        }
    }

    static class TopologicalOrder
    {
        Stack<Integer> topoStack;
        TopologicalOrder()
        {
            topoStack = new Stack<Integer>();
        }
        public void topoOrder(Vertex[] gr)
        {
            for(Vertex v: gr)
            {
                if(!v.isVisited)
                {
                    //dfsTopo(gr,v);
                    explodeNodeRec(gr,v.node);
                }
            }
        }
        /*public void dfsTopo(Vertex[] gr,Vertex source)
        {
            Stack<Integer> stk = new Stack<Integer>();
            stk.push(source.node);

            while(!stk.isEmpty())
            {
                int cur = stk.peek();
                if(gr[cur].edgelist.size() == 0 || gr[cur].beingVisited)
                {
                    topoStack.push(cur);
                    stk.pop();
                    gr[cur].isVisited = true;
                    gr[cur].beingVisited = false;
                    continue;
                }
                gr[cur].beingVisited = true;
                for(int i:gr[cur].edgelist)
                {
                    if(!gr[i].isVisited)
                    {
                        stk.push(i);
                        gr[i].isVisited = true;
                    }
                }
            }
        }*/
        public void explodeNodeRec(Vertex gr[], int source)
        {
            gr[source].isVisited = true;
            for(int i: gr[source].edgelist)
            {
                if(!gr[i].isVisited)
                {
                    explodeNodeRec(gr,i);
                }
            }
            topoStack.push(source);
        }
    }

    static class DAGShortestPath
    {
        int[] distance;
        int[] previous;
        int source;
        DAGShortestPath(int n, int source)
        {
            distance = new int[n];
            previous = new int[n];
            this.source = source;
            distance[source] = 0;
            previous[source] = -1;
            for(int i=0;i<n;i++)
            {
                if(i==source)
                    continue;
                distance[i] = Integer.MAX_VALUE;
                previous[i] = -1;
            }
        }
        public void shortestPath(Vertex[] gr, Stack<Integer> topoStack)
        {
            int top = topoStack.pop();
            while(top!=source)
            {
                top = topoStack.pop();
            }

            topoStack.push(top);
            while(!topoStack.isEmpty())
            {
                top = topoStack.pop();
                if(distance[top]==Integer.MAX_VALUE)
                    continue;
                for(int i=0;i<gr[top].edgelist.size();i++)
                {
                    int newdist = distance[top]+gr[top].weightlist.get(i);
                    if(newdist < distance[gr[top].edgelist.get(i)])
                    {
                        distance[gr[top].edgelist.get(i)] = newdist;
                        previous[gr[top].edgelist.get(i)] = top;
                    }
                }

                System.out.println("Testing");
                for(int i=0;i<gr.length;i++)
                {
                    System.out.println(distance[i]);
                }
                System.out.println();
            }

            display(gr);
        }
        public void display(Vertex[] gr)
        {
            System.out.println("The source vertex is : "+source);

            for(int i=0;i<gr.length;i++)
            {
                if(distance[i]==Integer.MAX_VALUE || i==source)
                {
                    continue;
                }

                System.out.println("The distance from "+source+" to "+i+" is: "+distance[i]);
                System.out.print("The route is : ");
                Stack<Integer> path = new Stack<Integer>();

                int pre = previous[i];
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
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args)
    {
        Vertex[] gr = new Vertex[7];
        int i;
        for(i=0;i<7;i++)
        {
            gr[i] = new Vertex(i);
        }

        gr[0].addNeighbour(1,5);
        gr[1].addNeighbour(2,2);
        gr[1].addNeighbour(3,1);
        gr[1].addNeighbour(4,4);
        gr[4].addNeighbour(5,3);
        gr[6].addNeighbour(3,4);
        gr[3].addNeighbour(4,2);
        gr[2].addNeighbour(4,5);

        TopologicalOrder tpo = new TopologicalOrder();
        tpo.topoOrder(gr);
        Stack<Integer> topoStack = tpo.topoStack;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the source node of the graph : ");
        int source = sc.nextInt();

        DAGShortestPath dsp = new DAGShortestPath(gr.length,source);
        dsp.shortestPath(gr,topoStack);
    }
}
