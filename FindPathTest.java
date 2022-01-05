import java.util.*;

public class FindPathTest
{
    static class Vertex {
        int node;
        ArrayList<Vertex> neighbourlist;
        Map<Vertex, Edge> connecingMap;
        Vertex(int node)
        {
            this.node = node;
            neighbourlist = new ArrayList<>();
            connecingMap = new HashMap<>();
        }
    }

    static class Edge{
        Vertex fromVertex;
        Vertex targetVertex;
        Edge(Vertex fromVertex, Vertex targetVertex)
        {
            this.fromVertex = fromVertex;
            this.targetVertex = targetVertex;
        }
    }

    static class Tree
    {
        ArrayList<Vertex> vertexlist;
        ArrayList<Edge> edgelist;
        Tree(int n)
        {
            this.vertexlist = new ArrayList<>();
            this.edgelist = new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                addVertex(i);
            }
        }
        public void addVertex(int node) {
            this.vertexlist.add(new Vertex(node));
        }
        public void addEdge(Vertex fromVertex, Vertex targetVertex) {
            Edge e = new Edge(fromVertex, targetVertex);
            this.edgelist.add(e);

            fromVertex.neighbourlist.add(targetVertex);
            fromVertex.connecingMap.put(targetVertex,e);
            targetVertex.neighbourlist.add(fromVertex);
            targetVertex.connecingMap.put(fromVertex,e);
        }
        public void addEdge(int fromVertex, int targetVertex)
        {
            addEdge(this.vertexlist.get(fromVertex-1), this.vertexlist.get(targetVertex-1));
        }
    }

    static class FindPath
    {
        ArrayList<Edge> path;
        boolean flag;
        boolean[] isVisited;
        FindPath(Tree tr)
        {
            path = new ArrayList<>();
            this.flag = false;
            isVisited = new boolean[tr.vertexlist.size()];
        }
        public void findpath(Vertex source, Vertex sink)
        {
            dfs(source, sink);
        /*for(Edge e: path)
        {
            System.out.print(e.fromVertex.node+1 +"-->"+(e.targetVertex.node+1)+" ");
        }*/
        }
        public void dfs(Vertex source, Vertex sink)
        {
            if(source == sink)
            {
                flag = true;
                return;
            }
            isVisited[source.node] = true;
            for(Vertex v: source.neighbourlist)
            {
                if(!isVisited[v.node])
                {
                    path.add(source.connecingMap.get(v));
                    dfs(v,sink);
                    if(flag)
                        return;

                    path.remove(path.size()-1);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        Tree tr = new Tree(n);
        for(int i=0;i<n-1;i++)
        {
            int start = sc.nextInt();
            int end = sc.nextInt();

            tr.addEdge(start, end);
        }

        ArrayList<ArrayList<Edge>> mainpath = new ArrayList<ArrayList<Edge>>();
        for(int i=0;i<m;i++)
        {
            int start = sc.nextInt();
            int end = sc.nextInt();
            FindPath fp = new FindPath(tr);
            fp.findpath(tr.vertexlist.get(start-1), tr.vertexlist.get(end-1));
            mainpath.add(fp.path);
            //System.out.println();
        }

        ArrayList<Edge> path1,path2;
        int common=0;
        int commonedges;
        for(int i=0;i<mainpath.size();i++)
        {
            path1 = mainpath.get(i);
            for(int j=i+1;j<mainpath.size();j++)
            {
                commonedges = 0;
                path2 = mainpath.get(j);
                for (Edge edge : path1) {
                    for (Edge value : path2) {
                        if (edge == value) {
                            commonedges++;
                        }
                    }
                }

                if(commonedges>=k)
                {
                    common++;
                }
            }
        }

        System.out.println(common);
    }
}
