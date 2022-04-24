package Codeforces.ITMO_PilotCourse.BinarySearch.Step4;

import java.io.*;
import java.util.*;

public class B {

    static class Node{
        int id;
        Node next;
        List<Edge> adlist = new ArrayList<>();
        List<Edge> inedge = new ArrayList<>();

        Node(int id){
            this.id = id;
        }
    }

    static class Edge{
        Node u, v;
        long weight;

        Edge(Node u, Node v, long weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class Graph{
        List<Node> nodelist;
        double[] dist;

        Graph(int n){
            nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }

        public void addEdge(int u, int v, long weight){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            Edge e = new Edge(nu, nv, weight);

            nu.adlist.add(e);
            nv.inedge.add(e);
        }

        public boolean isPossible(double avg){
            int n = nodelist.size();

            for(Node node: nodelist)
                node.next = null;

            dist = new double[n];
            Arrays.fill(dist, 1000000000);
            dist[n-1] = 0;
            for(int i=n-2;i>=0;i--){
                double min = 1000000000;
                Node cur = nodelist.get(i);

                for(Edge e: cur.adlist){
                    Node next;
                    if(e.u == cur){
                        next = e.v;
                    }
                    else{
                        next = e.u;
                    }

                    min = Math.min(min, e.weight + dist[next.id] - avg);
                    if(min == e.weight+dist[next.id]-avg){
                        cur.next = next;
                    }
                }

                dist[i] = min;
            }

            return dist[0] <= 0;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph gr = new Graph(n);

        for(int i=0;i<m;i++){
            gr.addEdge(sc.nextInt()-1, sc.nextInt()-1, sc.nextLong());
        }

        double ll = 0, ul = 1000;
        while(ul-ll > 0.000001){
            double mid = (ll + ul) / 2;

            if(gr.isPossible(mid)){
                ul = mid;
            }
            else{
                ll = mid + 0.000001;
            }
        }

        gr.isPossible(ll);
        Stack<Node> stk = new Stack<>();
        Node start = gr.nodelist.get(0);
        stk.push(start);
        while(start.next != null){
            start = start.next;
            stk.push(start);
        }

        sb.append(stk.size() - 1).append("\n");
        for(Node node: stk){
            sb.append(node.id+1).append(" ");
        }
        sb.append("\n");

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
