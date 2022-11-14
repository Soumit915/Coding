package ICPC_2020.Preliminary;

import java.io.*;
import java.util.*;

public class F {
    static class Node implements Comparable<Node>{
        int id;
        long value;
        boolean isVisited;
        ArrayList<Edge> edgemap = new ArrayList<>();
        Node(int id, long value){
            this.id = id;
            this.value = value;
            this.isVisited = false;
        }
        public int compareTo(Node node){
            return Long.compare(this.value, node.value);
        }
    }

    static class Edge implements Comparable<Edge>{
        Node u;
        Node v;
        long weight;
        Edge(Node u, Node v, long weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        public int compareTo(Edge e){
            return Long.compare(this.weight, e.weight);
        }
    }

    static class Graph{
        ArrayList<Node> nodelist, sortednodelist;
        ArrayList<Edge> edgelist;
        ArrayList<Edge> mst_edgelist;
        long mincost;
        Graph(int n, long[] value){
            nodelist = new ArrayList<>(n);
            sortednodelist = new ArrayList<>(n);
            edgelist = new ArrayList<>();
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i, value[i]));
                sortednodelist.add(nodelist.get(i));
            }
            Collections.sort(sortednodelist);
        }
        public void addEdge(int u, int v, long weight){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            Edge e = new Edge(nu, nv, weight);

            nu.edgemap.add(e);
            nv.edgemap.add(e);
        }
        public void addOtherPossibleEdges(){
            for(int i=1;i<sortednodelist.size();i++){
                Node u = sortednodelist.get(i-1);
                Node v = sortednodelist.get(i);
                addEdge(u.id, v.id, Math.abs(u.value-v.value));
            }
        }
        public void getMST(){
            this.mst_edgelist = new ArrayList<>();
            PriorityQueue<Edge> minheap = new PriorityQueue<>();
            Node source = nodelist.get(0);
            minheap.addAll(source.edgemap);
            source.isVisited = true;

            int c = 1;
            int n = nodelist.size();
            while(!minheap.isEmpty() && c<n){
                Edge e = minheap.remove();

                if(!e.u.isVisited){
                    mst_edgelist.add(e);
                    mincost+=e.weight;
                    c++;
                    e.u.isVisited = true;
                    for(Edge next_edge: e.u.edgemap){
                        if(!next_edge.u.isVisited || !next_edge.v.isVisited)
                            minheap.add(next_edge);
                    }
                }
                else if(!e.v.isVisited){
                    mst_edgelist.add(e);
                    mincost+=e.weight;
                    c++;
                    e.v.isVisited = true;
                    for(Edge next_edge: e.v.edgemap){
                        if(!next_edge.u.isVisited || !next_edge.v.isVisited)
                            minheap.add(next_edge);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            long[] vals = sc.nextLongArray(n);

            Graph gr = new Graph(n, vals);
            for(int i=0;i<m;i++){
                gr.addEdge(sc.nextInt()-1, sc.nextInt()-1, 0);
            }
            gr.addOtherPossibleEdges();
            gr.getMST();

            sb.append(gr.mincost).append(" ").append(gr.mst_edgelist.size()).append("\n");
            for(Edge e: gr.mst_edgelist){
                sb.append(e.u.id+1).append(" ").append(e.v.id+1).append("\n");
            }
        }

        System.out.println(sb);

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
