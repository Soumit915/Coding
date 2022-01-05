package Codeforces.Round677Div3;

import java.io.*;
import java.util.*;

public class G {
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
        ArrayList<Edge> adjacencyList;
        Vertex(int id)
        {
            this.id = id;
            this.distance = Integer.MAX_VALUE;
            adjacencyList = new ArrayList<>();
        }
    }

    static class Edge
    {
        Vertex u;
        Vertex v;
        int count;
        long weight;
        Edge(Vertex u, Vertex v, long weight)
        {
            this.u = u;
            this.v = v;
            this.weight = weight;
            this.count = 0;
        }
    }

    static class Graph
    {
        ArrayList<Vertex> vertexlist;
        ArrayList<Edge> edgelist;
        long[][] mapping;
        Graph(int n, int m)
        {
            this.vertexlist = new ArrayList<>(n);
            this.edgelist = new ArrayList<>(m);
            mapping = new long[n][n];
            for(int i=0;i<n;i++)
            {
                this.vertexlist.add(new Vertex(i));
            }
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    mapping[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        public void addEdge(Vertex source, Vertex sink, int weight)
        {
            System.out.println("soun: "+source.id+" "+sink.id);
            Edge e = new Edge(source, sink, weight);
            source.adjacencyList.add(e);
            sink.adjacencyList.add(e);
            this.edgelist.add(e);
        }
    }
    public static int shortestReach(Graph gr, int source, int sink, int n) {

        gr.vertexlist.get(source).distance = 0;
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        Set<Integer> isRelaxed = new HashSet<>();
        for(Vertex v: gr.vertexlist)
        {
            minHeap.add(new Node(v.id, v.distance));
        }

        Edge[] previous = new Edge[n];
        previous[source] = null;

        while(!minHeap.isEmpty())
        {
            Node node = minHeap.remove();
            if(isRelaxed.contains(node.id))
                continue;
            isRelaxed.add(node.id);
            Vertex cur = gr.vertexlist.get(node.id);
            if(cur.distance == Integer.MAX_VALUE)
                continue;
            //System.out.println("test "+cur.adjacencyList);
            for(Edge e: cur.adjacencyList)
            {
                //System.out.println("soumit");
                Vertex v;
                if(e.u==cur)
                    v = e.v;
                else v = e.u;
                if(isRelaxed.contains(v.id))
                    continue;
                if(v.distance>cur.distance+e.weight)
                {
                    v.distance = (int) (cur.distance+e.weight);
                    Node newnode = new Node(v.id, v.distance);
                    minHeap.add(newnode);

                    previous[v.id] = e;
                }
            }
            //System.out.println(gr.vertexlist.get(1).distance);
        }

        Edge e = previous[sink];
        for(int i=0;i<n;i++)
        {
            if(previous[i]!=null)
                System.out.println(i+" "+previous[i].u.id+" "+previous[i].v.id);
            else System.out.println("null");
        }
        System.out.println();

        int prev = sink;
        while(e!=null)
        {
            e.count++;
            if(e.u.id==prev)
                prev = e.v.id;
            else prev = e.u.id;
           // System.out.println(e.u.id+" "+e.v.id);
            e = previous[prev];
        }

        /*System.out.println(isRelaxed.contains(sink));
        for(Vertex v: gr.vertexlist)
        {
            System.out.print(v.distance+" ");
        }
        System.out.println();*/

        return gr.vertexlist.get(sink).distance;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        Graph gr = new Graph(n, m);

        for(int i=0;i<m;i++)
        {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            if(u==v)
                continue;

            if(gr.mapping[u-1][v-1]<w)
                continue;

            gr.addEdge(gr.vertexlist.get(u - 1), gr.vertexlist.get(v - 1), w);
            gr.mapping[u-1][v-1] = w;
            gr.mapping[v-1][u-1] = w;
        }

        long sum = 0;
        for(int i=0;i<k;i++)
        {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;

            for(int j=0;j<n;j++)
            {
                gr.vertexlist.get(j).distance = Integer.MAX_VALUE;
            }
            int s1 = shortestReach(gr, u, v, n);
            sum += s1;
            System.out.println("Test: "+s1);
        }

        long max = -1;
        for(Edge e: gr.edgelist)
        {
            max = Math.max(max, e.count*e.weight);
        }

        System.out.println(sum - max);

        sc.close();
    }

    static class Soumit {
        final private int BUFFER_SIZE = 1 << 18;
        final private DataInputStream din;
        final private byte[] buffer;
        private PrintWriter pw;
        private int bufferPointer, bytesRead;
        StringTokenizer st;

        public Soumit() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Soumit(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public void streamOutput(String file) throws IOException {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
        }

        public void println(String a) {
            pw.println(a);
        }

        public void print(String a) {
            pw.print(a);
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[100064]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }

            return arr;
        }

        public long[] nextLongArray(int n) throws IOException {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }

            return arr;
        }

        public double[] nextDoubleArray(int n) throws IOException {
            double[] arr = new double[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextDouble();
            }

            return arr;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            /*if (din == null)
                return;*/
            if (din != null) din.close();
            if (pw != null) pw.close();
        }
    }
}
