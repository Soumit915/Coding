package FacebookHackercup.Round1_2019;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GraphsAsAService {
    static class Node
    {
        int id;
        ArrayList<Edge> edgelist;
        Node(int id)
        {
            this.id = id;
            edgelist = new ArrayList<>();
        }
    }

    static class Edge
    {
        Node u;
        Node v;
        long weight;
        Edge(Node u, Node v, long weight)
        {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class DijkstraNode implements Comparable<DijkstraNode>
    {
        int id;
        long dist;
        DijkstraNode(int id, long dist)
        {
            this.id = id;
            this.dist = dist;
        }
        public int compareTo(DijkstraNode dn)
        {
            return Long.compare(this.dist, dn.dist);
        }
    }

    static class Graph
    {
        ArrayList<Node> nodelist;
        long[][] dist;
        Graph(int n)
        {
            nodelist = new ArrayList<>(n);
            dist = new long[n][n];
            for(int i=0;i<n;i++)
            {
                nodelist.add(new Node(i));
            }
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    dist[i][j] = Long.MAX_VALUE;
                    if(i==j)
                        dist[i][j] = 0;
                }
            }
        }
        public void addEdge(int u, int v, long w)
        {
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            Edge e = new Edge(nu, nv, w);
            nu.edgelist.add(e);
            nv.edgelist.add(e);
        }
        public void dijkstra(int si)
        {
            Node source = nodelist.get(si);

            PriorityQueue<DijkstraNode> minHeap = new PriorityQueue<>();
            HashSet<Integer> set = new HashSet<>();
            minHeap.add(new DijkstraNode(source.id, 0));

            while(!minHeap.isEmpty())
            {
                DijkstraNode cur = minHeap.remove();
                if(set.contains(cur.id))
                    continue;

                Node curnd = nodelist.get(cur.id);
                set.add(curnd.id);
                if(dist[source.id][curnd.id]==Long.MAX_VALUE)
                    continue;
                for(Edge e: curnd.edgelist)
                {
                    Node u = e.u;
                    Node v = e.v;
                    if(u==curnd)
                    {
                        if(set.contains(v.id))
                            continue;
                        if(dist[source.id][u.id]+e.weight<dist[source.id][v.id])
                        {
                            dist[source.id][v.id] = dist[source.id][u.id]+e.weight;
                        }
                        minHeap.add(new DijkstraNode(v.id, dist[source.id][v.id]));
                    }
                    else
                    {
                        if(set.contains(u.id))
                            continue;
                        if(dist[source.id][v.id]+e.weight<dist[source.id][u.id])
                        {
                            dist[source.id][u.id] = dist[source.id][v.id]+e.weight;
                        }
                        minHeap.add(new DijkstraNode(u.id, dist[source.id][u.id]));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {

        File file = new File("Input.txt");

        FileWriter fw = new FileWriter("Output.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            int t = sc.nextInt();

            for(int testi=1;testi<=t;testi++)
            {
                int n = sc.nextInt();
                int m = sc.nextInt();

                Graph gr = new Graph(n);
                long[][] conditions = new long[m][3];
                for(int i=0;i<m;i++)
                {
                    int xi = sc.nextInt()-1;
                    int yi = sc.nextInt()-1;
                    long zi = sc.nextLong();

                    gr.addEdge(xi, yi, zi);
                    conditions[i][0] = xi;
                    conditions[i][1] = yi;
                    conditions[i][2] = zi;
                }

                for(int i=0;i<n;i++)
                {
                    gr.dijkstra(i);
                }

                long[][] dist = gr.dist;
                boolean flag = true;
                for(int i=0;i<m;i++)
                {
                    int xi = (int) conditions[i][0];
                    int yi = (int) conditions[i][1];
                    long zi = conditions[i][2];
                    if(dist[xi][yi] != zi)
                    {
                        flag = false;
                        break;
                    }
                }

                pw.print("Case #"+testi+": ");
                if(!flag)
                    pw.println("Impossible");
                else {
                    pw.println(m);
                    for(int i=0;i<m;i++)
                    {
                        pw.println((conditions[i][0]+1)+" "+(conditions[i][1]+1)+" "+conditions[i][2]);
                    }
                }
            }

            pw.close();
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}