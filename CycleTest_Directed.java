import java.util.*;

public class CycleTest_Directed
{
    static class Vertex{
        int node;
        boolean isVisited;
        boolean beingVisited;
        int previous;
        ArrayList<Integer> edgelist;
        Vertex(int nodeno)
        {
            node = nodeno;
            previous = -1;
            isVisited = false;
            beingVisited = false;
            edgelist = new ArrayList<>();
        }
        public void addEdge(int target)
        {
            this.edgelist.add(target);
        }
    }

    static class DetectCycle
    {
        boolean hasCycle;
        DetectCycle()
        {
            this.hasCycle = true;
        }
        public void hasCycle(Vertex[] gr)
        {
            for(Vertex v: gr)
            {
                if(!v.isVisited)
                {
                    hasCycleDfs(gr,v);
                }
            }
        }
        public void hasCycleDfs(Vertex[] gr, Vertex source)
        {
            source.beingVisited = true;
            for(int i:source.edgelist)
            {
                if(gr[i].beingVisited)
                {
                    this.hasCycle = true;
                    Stack<Integer> cyclestk = new Stack<>();
                    int pre = source.node;
                    while(pre!=i)
                    {
                        cyclestk.push(pre);
                        pre = gr[pre].previous;
                    }
                    cyclestk.push(i);
                    printStack(cyclestk);
                }
                else if(!gr[i].isVisited)
                {
                    gr[i].previous = source.node;
                    hasCycleDfs(gr,gr[i]);
                }
            }
            source.beingVisited = false;
            source.isVisited = true;
        }
        public void printStack(Stack<Integer> stk)
        {
            System.out.print("There is a cycle. The cycle is : "+stk.pop());
            while(!stk.isEmpty())
            {
                System.out.print(" --> "+stk.pop());
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        int n = 6;
        Vertex[] gr = new Vertex[n];
        int i;

        for(i=0;i<n;i++)
        {
            gr[i] = new Vertex(i);
        }

        gr[0].addEdge(1);
        gr[1].addEdge(2);
        gr[2].addEdge(4);
        gr[4].addEdge(5);
        gr[2].addEdge(3);
        gr[3].addEdge(1);
        gr[3].addEdge(5);
        gr[5].addEdge(2);

        DetectCycle dc = new DetectCycle();
        dc.hasCycle(gr);
        //dc.displayGraph(gr);

        if(!dc.hasCycle)
            System.out.println("The graph has no cycle.");
    }
}
