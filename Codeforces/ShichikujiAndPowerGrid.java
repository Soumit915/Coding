package Codeforces;

import java.util.*;
import java.io.*;

public class ShichikujiAndPowerGrid {

    static class Node{
        int id;
        long ci;

        long xi, yi;

        Node parent;

        Node(int id, long xi, long yi, long ci){
            this.id = id;
            this.xi = xi;
            this.yi = yi;
            this.ci = ci;
            this.parent = this;
        }

        public long getDist(Node node){
            return Math.abs(this.xi - node.xi) + Math.abs(this.yi - node.yi);
        }

        public Node getParent(){
            if(this.parent == this)
                return this;

            this.parent = this.parent.getParent();
            return this.parent;
        }

        public void merge(Node node){
            node = node.getParent();
            Node thisparent = this.getParent();
            if(thisparent.ci < node.ci){
                node.parent = this.parent;
            }
            else{
                thisparent.parent = node.parent;
            }
        }
    }

    static class Edge{
        Node u, v;
        long cost;

        Edge(Node u, Node v, long cost){
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        long[][] x = new long[n][2];
        for(int i=0;i<n;i++){
            x[i] = sc.nextLongArray(2);
        }

        long[] c = sc.nextLongArray(n);

        Node[] nodes = new Node[n];
        for(int i=0;i<n;i++){
            nodes[i] = new Node(i, x[i][0], x[i][1], c[i]);
        }

        long[] k = sc.nextLongArray(n);

        List<Edge> edgelist = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                edgelist.add(new Edge(nodes[i], nodes[j], nodes[i].getDist(nodes[j]) * (k[i] + k[j])));
            }
        }

        edgelist.sort((e1, e2) -> Long.signum(e1.cost - e2.cost));

        StringBuilder sb = new StringBuilder();
        long connecting_cost = 0;
        int connections = 0;
        for(Edge edge: edgelist){
            Node u = edge.u;
            Node v = edge.v;

            Node pu = u.getParent();
            Node pv = v.getParent();

            if(pu != pv){
                long maxcost = Math.max(pu.ci, pv.ci);

                if(maxcost > edge.cost){
                    pu.merge(pv);
                    sb.append(u.id+1).append(" ").append(v.id+1).append("\n");
                    connecting_cost += edge.cost;
                    connections++;
                }
            }
        }

        HashSet<Node> set = new HashSet<>();
        for(Node node: nodes)
            set.add(node.getParent());

        for(Node node: set)
            connecting_cost += node.ci;

        System.out.println(connecting_cost);
        System.out.println(set.size());

        StringBuilder nodesb = new StringBuilder();
        for(Node node: set)
            nodesb.append(node.id+1).append(" ");
        System.out.println(nodesb);
        System.out.println(connections);
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
