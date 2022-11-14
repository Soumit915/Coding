package Codechef;

import java.io.*;
import java.util.*;

public class WeirdFullGraph {
    static class Node implements Comparable<Node>
    {
        int id;
        long distance;
        Node(int id, long distance)
        {
            this.id = id;
            this.distance = distance;
        }
        public int compareTo(Node n)
        {
            return Long.compare(this.distance, n.distance);
        }
    }
    static class Vertex
    {
        int id;
        long distance;
        ArrayList<Vertex> adjacencyList;
        Map<Vertex, Long> weightMap;
        Vertex(int id)
        {
            this.id = id;
            this.distance = Long.MAX_VALUE;
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
        public void addEdge(Vertex source, Vertex sink, long weight)
        {
            if(source.weightMap.containsKey(sink))
            {
                source.weightMap.put(sink,weight);
            }
            else
            {
                source.adjacencyList.add(sink);
                source.weightMap.put(sink,weight);
            }
        }
    }
    public static long[] shortestReach(Graph gr, int s, int n) {
        gr.vertexlist.get(s-1).distance = 0;
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        Set<Integer> isRelaxed = new HashSet<>();
        for(Vertex v: gr.vertexlist)
        {
            minHeap.add(new Node(v.id, v.distance));
        }

        int nor = 1;
        while(!minHeap.isEmpty() && nor<n)
        {
            Node node = minHeap.remove();
            if(isRelaxed.contains(node.id))
                continue;
            isRelaxed.add(node.id);
            nor++;
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

        long[] dist = new long[n-1];
        int k = 0;
        for(int i=0;i<n;i++)
        {
            long c = gr.vertexlist.get(i).distance;
            if(c!=0)
            {
                if(c == Long.MAX_VALUE)
                    dist[k] = -1;
                else
                    dist[k] = c;
                k++;
            }
        }

        return dist;
    }
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            long[] a = sc.nextLongArray(n);

            long[][] adMat = new long[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(i==j){
                        adMat[i][j] = 0;
                    }
                    else{
                        adMat[i][j] = a[i];
                    }
                }
            }

            int m = sc.nextInt();
            for(int i=0;i<m;i++){
                int u = sc.nextInt()-1;
                int v = sc.nextInt()-1;
                long c = sc.nextLong();
                adMat[u][v] = c;
            }

            Graph gr = new Graph(n);
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(i==j)
                        continue;
                    gr.addEdge(gr.vertexlist.get(i), gr.vertexlist.get(j), adMat[i][j]);
                }
            }

            long sum = 0;
            for(int i=0;i<n;i++){
                long[] dist = shortestReach(gr, i+1, n);
                //System.out.println(i+" "+Arrays.toString(dist));
                for(long j: dist)
                    sum += j;
                for(int j=0;j<n;j++){
                    gr.vertexlist.get(j).distance = Long.MAX_VALUE;
                }
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb);

        long end = System.currentTimeMillis();
        System.out.println(((double)end-start)/2000.0);

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
            byte[] buf = new byte[3000064]; // line length
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

        public void sort(int[] arr) {
            ArrayList<Integer> arlist = new ArrayList<>();
            for (int i : arr)
                arlist.add(i);

            Collections.sort(arlist);
            for (int i = 0; i < arr.length; i++)
                arr[i] = arlist.get(i);
        }

        public void sort(long[] arr) {
            ArrayList<Long> arlist = new ArrayList<>();
            for (long i : arr)
                arlist.add(i);

            Collections.sort(arlist);
            for (int i = 0; i < arr.length; i++)
                arr[i] = arlist.get(i);
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
