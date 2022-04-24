package Leetcode;

import java.io.*;
import java.util.*;

public class MaximumScoreOfNodeSequence {

    static class Node implements Comparable<Node>{
        int id;
        long value;
        ArrayList<Node> adlist = new ArrayList<>();

        Node(int id, long value){
            this.id = id;
            this.value = value;
        }

        public int compareTo(Node node){
            return Long.compare(this.value, node.value) * -1;
        }
    }

    static class Edge{
        Node u;
        Node v;

        Edge(Node u, Node v){
            this.u = u;
            this.v = v;
        }
    }

    static class Graph{
        List<Node> nodelist;
        List<Edge> edgeList;

        Graph(int n, int[] array){
            nodelist = new ArrayList<>(n);
            edgeList = new ArrayList<>();

            for(int i=0;i<n;i++){
                nodelist.add(new Node(i, array[i]));
            }
        }

        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adlist.add(nv);
            nv.adlist.add(nu);

            edgeList.add(new Edge(nu, nv));
        }
    }

    public static int maximumScore(int[] scores, int[][] edges) {
        int n = scores.length;
        Graph gr = new Graph(n, scores);

        for(int[] i : edges){
            gr.addEdge(i[0], i[1]);
        }
        for(int i=0;i<n;i++){
            Collections.sort(gr.nodelist.get(i).adlist);
        }

        long max = 0;

        for(Edge e: gr.edgeList){
            HashSet<Node> nodeSet = new HashSet<>();
            nodeSet.add(e.u);
            nodeSet.add(e.v);

            if(e.u.adlist.size() > 1 && e.v.adlist.size() > 1){
                nodeSet.add(e.u.adlist.get(0));
                nodeSet.add(e.v.adlist.get(0));

                if(nodeSet.size() == 2){
                    nodeSet.add(e.u.adlist.get(1));
                    nodeSet.add(e.v.adlist.get(1));
                }

                if(nodeSet.size() == 3){
                    Node node1 = null, node2 = null;
                    if(e.u.adlist.size() > 2)
                        node1 = e.u.adlist.get(2);
                    if(e.v.adlist.size() > 2)
                        node2 = e.v.adlist.get(2);

                    if(node1 == null){
                        if(node2 != null)
                            nodeSet.add(node2);
                    }
                    else{
                        if(node2 == null)
                            nodeSet.add(node1);
                        else{
                            if(node1.value < node2.value){
                                nodeSet.add(node2);
                            }
                            else{
                                nodeSet.add(node1);
                            }
                        }
                    }
                }

                if(nodeSet.size() == 4){
                    long score = 0;
                    for(Node node: nodeSet){
                        score += node.value;
                    }
                    max = Math.max(max, score);
                }
            }
        }

        return (int) max;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();


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
