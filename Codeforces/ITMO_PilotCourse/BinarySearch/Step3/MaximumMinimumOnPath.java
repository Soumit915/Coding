package Codeforces.ITMO_PilotCourse.BinarySearch.Step3;

import java.io.*;
import java.util.*;

public class MaximumMinimumOnPath {

    static class Node{
        int id;
        boolean isVisited;
        Node parent;
        List<Edge> adlist = new ArrayList<>();

        Node(int id){
            this.id = id;
            this.isVisited = false;
            this.parent = null;
        }
    }

    static class Edge{
        Node u;
        Node v;
        int weight;

        Edge(Node u, Node v, int weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class Graph{
        List<Node> nodelist;

        Graph(int n){
            nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
                nodelist.add(new Node(i));
        }

        public void addEdge(int u, int v, int weight){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            Edge e = new Edge(nu, nv, weight);

            nu.adlist.add(e);
        }

        public int getMinPossible(int max, int k){
            for(Node node: nodelist){
                node.isVisited = false;
                node.parent = null;
            }

            int[] dist = new int[nodelist.size()];

            Queue<Node> q = new LinkedList<>();
            Node source = nodelist.get(0);

            q.add(source);
            source.isVisited = true;
            dist[source.id] = 0;

            while(!q.isEmpty()){
                Node cur = q.remove();

                for(Edge e: cur.adlist){
                    Node node;

                    if(e.u == cur)
                        node = e.v;
                    else node = e.u;

                    if(!node.isVisited && e.weight <= max){
                        q.add(node);
                        node.isVisited = true;
                        dist[node.id] = dist[cur.id] + 1;
                        node.parent = cur;
                    }
                }
            }

            Node dest = nodelist.get(nodelist.size()-1);
            return dest.isVisited? dist[dest.id] : k+5;
        }

        public void findWays(int k){
            int ll = 0, ul = (int) 1e9 + 7;

            while(ll < ul){
                int mid = (ll + ul) / 2;

                if(getMinPossible(mid, k) <= k){
                    ul = mid;
                }
                else{
                    ll = mid + 1;
                }
            }

            if(ll <= 1000000000){
                StringBuilder sb = new StringBuilder();
                int pathLength = getMinPossible(ll, k);
                sb.append(pathLength).append("\n");

                Node dest = nodelist.get(nodelist.size()-1);
                Stack<Node> stk = new Stack<>();
                stk.push(dest);
                while(dest.parent != null){
                    stk.push(dest.parent);
                    dest = dest.parent;
                }

                while(!stk.isEmpty()){
                    sb.append(stk.pop().id + 1).append(" ");
                }

                System.out.println(sb);
            }
            else{
                System.out.println(-1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();

        Graph gr = new Graph(n);

        for(int i=0;i<m;i++){
            gr.addEdge(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt());
        }

        gr.findWays(d);

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
