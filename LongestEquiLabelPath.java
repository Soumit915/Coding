import java.util.*;

public class LongestEquiLabelPath {
    static class Node
    {
        int id;
        int label;
        ArrayList<Node> neighbour = new ArrayList<>();
        int longestpath;
        //ArrayList<Integer> longestpath = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
        }
    }
    static class Tree
    {
        ArrayList<Node> nodelist;
        Tree(int n)
        {
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                this.nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v)
        {
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);
            nu.neighbour.add(nv);
            //nu.longestpath.add(0);
            nv.neighbour.add(nu);
            //nv.longestpath.add(0);
        }
        int[] lp;
        public int findlongestPath()
        {
            for(Node nd: nodelist)
            {
                dfscount(nd);
                int max = 0;
                int secondmax = 0;
                for(Node neightbournode: nd.neighbour)
                {
                    if(nd.label == neightbournode.label)
                    {
                        if(lp[neightbournode.id]>max)
                        {
                            secondmax = max;
                            max = lp[neightbournode.id];
                        }
                        else if(lp[neightbournode.id]>secondmax)
                        {
                            secondmax = lp[neightbournode.id];
                        }
                    }
                }

                nd.longestpath = 1+max+secondmax;
            }

            int max = -1;
            for(Node nd: nodelist)
            {
                max = Math.max(max, nd.longestpath);
            }
            return max;
        }
        public void dfscount(Node source)
        {
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            boolean[] isVisited = new boolean[nodelist.size()];
            stk.push(source);
            ptrstk.push(-1);
            isVisited[source.id] = true;
            lp = new int[nodelist.size()];

            while (!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.neighbour.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.neighbour.get(ptr);
                    if(!isVisited[next.id] && next.neighbour.size()==1)
                    {
                        lp[next.id] = 1;
                        isVisited[next.id] = true;
                    }
                    else if(!isVisited[next.id])
                    {
                        stk.push(next);
                        ptrstk.push(-1);
                        isVisited[next.id] = true;
                    }
                }
                else
                {
                    int max = 0;
                    for(Node node: cur.neighbour)
                    {
                        if(!stk.isEmpty() && node!=stk.peek() && node.label==cur.label)
                        {
                            max = Math.max(max, lp[node.id]);
                        }
                    }
                    lp[cur.id] = max+1;
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] A = new int[n];
        Tree tr = new Tree(n);
        for(int i=0;i<n;i++)
        {
            A[i] = sc.nextInt();
            tr.nodelist.get(i).label = A[i];
        }

        for(int i=0;i<n-1;i++)
        {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            tr.addEdge(u, v);
        }

        System.out.println(tr.findlongestPath()-1);
    }
}
