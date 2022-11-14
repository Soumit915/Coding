package Coursera.DataStructuresAndAlgorithms_UCSanDiego.GraphAlgorithms.Week4;

import java.io.*;
import java.util.*;

public class MinimumCostOfFlight {
    static class Node implements Comparable<Node>{
        int id;
        long dist;
        Node(int id, long dist){
            this.id = id;
            this.dist = dist;
        }
        public int compareTo(Node node){
            return Long.compare(this.dist, node.dist);
        }
    }
    static class Vertex{
        int id;
        boolean isRelaxed;
        long distance;
        ArrayList<Edge> edgelist = new ArrayList<>();
        Vertex(int id){
            this.id = id;
            this.distance = -1;
            this.isRelaxed = false;
        }
    }
    static class Edge{
        Vertex source;
        Vertex sink;
        long weight;
        Edge(Vertex source, Vertex sink, long weight){
            this.source = source;
            this.sink = sink;
            this.weight = weight;
        }
    }
    static class Graph{
        ArrayList<Vertex> vertices;
        Graph(int n){
            this.vertices = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                vertices.add(new Vertex(i));
            }
        }
        public void addEdge(int u, int v, long weight){
            Vertex nu = vertices.get(u);
            Vertex nv = vertices.get(v);
            Edge e = new Edge(nu, nv, weight);

            nu.edgelist.add(e);
        }
        public void getMinDistance(Vertex source){
            PriorityQueue<Node> heap = new PriorityQueue<>();

            heap.add(new Node(source.id, 0));

            source.distance = 0;
            while (!heap.isEmpty()){
                Node cur = heap.remove();
                if(!vertices.get(cur.id).isRelaxed){
                    Vertex v = vertices.get(cur.id);
                    v.isRelaxed = true;
                    v.distance = cur.dist;

                    for(Edge e: v.edgelist){
                        Vertex adv = e.sink;
                        if(!adv.isRelaxed){
                             heap.add(new Node(adv.id, v.distance+e.weight));
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();
        Graph gr = new Graph(n);
        for(int i=0;i<m;i++){
            gr.addEdge(sc.nextInt()-1, sc.nextInt()-1, sc.nextLong());
        }

        gr.getMinDistance(gr.vertices.get(sc.nextInt()-1));
        System.out.println(gr.vertices.get(sc.nextInt()-1).distance);

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
            if (din != null) din.close();
            if (pw != null) pw.close();
        }
    }
}
