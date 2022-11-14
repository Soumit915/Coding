package Hackerrank.Algorithms.Graph;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class DijskraShortestReach
{
    static class Node implements Comparable<Node>
    {
        int id;
        int distance;
        Node(int id, int distance)
        {
            this.id = id;
            this.distance = distance;
        }
        public int compareTo(Node n)
        {
            return Integer.compare(this.distance, n.distance);
        }
    }

    static class Vertex
    {
        int id;
        int distance;
        ArrayList<Vertex> adjacencyList;
        Map<Vertex, Integer> weightMap;
        Vertex(int id)
        {
            this.id = id;
            this.distance = Integer.MAX_VALUE;
            adjacencyList = new ArrayList<>();
            weightMap = new HashMap<>();
        }
    }

    static class Graph
    {
        ArrayList<Vertex> vertexlist;
        Graph(int n)
        {
            this.vertexlist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                this.vertexlist.add(new Vertex(i));
            }
        }
        public void addEdge(Vertex source, Vertex sink, int weight)
        {
            if(source.weightMap.containsKey(sink))
            {
                if(source.weightMap.get(sink)>weight)
                {
                    source.weightMap.put(sink,weight);
                    sink.weightMap.put(source,weight);
                }
            }
            else
            {
                source.adjacencyList.add(sink);
                sink.adjacencyList.add(source);
                source.weightMap.put(sink,weight);
                sink.weightMap.put(source,weight);
            }
        }
    }
    public static int[] shortestReach(Graph gr, int s, int n) {

        gr.vertexlist.get(s-1).distance = 0;
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        Set<Integer> isRelaxed = new HashSet<>();
        for(Vertex v: gr.vertexlist)
        {
            minHeap.add(new Node(v.id, v.distance));
        }

        while(!minHeap.isEmpty())
        {
            Node node = minHeap.remove();
            if(isRelaxed.contains(node.id))
                continue;
            isRelaxed.add(node.id);
            Vertex cur = gr.vertexlist.get(node.id);
            if(cur.distance == Integer.MAX_VALUE)
                continue;
            for(Vertex v: cur.adjacencyList)
            {
                if(isRelaxed.contains(v.id))
                    continue;
                if(v.distance>cur.distance+cur.weightMap.get(v))
                {
                    v.distance = cur.distance+cur.weightMap.get(v);
                    Node newnode = new Node(v.id, v.distance);
                    minHeap.add(newnode);
                }
            }
        }

        int[] dist = new int[n-1];
        int k = 0;
        for(int i=0;i<n;i++)
        {
            int c = gr.vertexlist.get(i).distance;
            if(c!=0)
            {
                if(c == Integer.MAX_VALUE)
                    dist[k] = -1;
                else
                    dist[k] = c;
                k++;
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int t = sc.nextInt();

        while(t>0)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();

            Graph gr = new Graph(n);

            for(int i=0;i<m;i++)
            {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();

                gr.addEdge(gr.vertexlist.get(u - 1), gr.vertexlist.get(v - 1), w);
            }

            int source = sc.nextInt();
            int[] dist = shortestReach(gr, source, n);

            for(int i=0;i<n-1;i++)
            {
                System.out.print(dist[i]+" ");
            }
            System.out.println();
            t--;
        }
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        final private DataInputStream din;
        final private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}
