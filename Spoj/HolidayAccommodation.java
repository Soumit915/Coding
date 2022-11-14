package Spoj;

import java.io.*;
import java.util.*;

public class HolidayAccommodation {
    static class Node{
        int id;
        ArrayList<Edge> adlist = new ArrayList<>();
        Node(int id){
            this.id = id;
        }
    }
    static class Edge{
        Node u;
        Node v;
        long weight;
        int cu;
        int cv;
        Edge(Node nu, Node nv, long weight){
            this.u = nu;
            this.v = nv;
            this.weight = weight;
        }
    }
    static class Graph{
        ArrayList<Node> nodelist;
        ArrayList<Edge> edgelist;
        Graph(int n){
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }

            this.edgelist = new ArrayList<>(n-1);
        }
        public void addEdge(int u, int v, long weight){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            Edge e = new Edge(nu, nv, weight);
            this.edgelist.add(e);
            nu.adlist.add(e);
            nv.adlist.add(e);
        }
        public void dfs(){
            Node start = this.nodelist.get(0);

            int n = this.nodelist.size();
            boolean[] isVisited = new boolean[n];
            int[] count = new int[n];

            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(start);
            ptrstk.push(-1);
            isVisited[start.id] = true;

            while (!stk.isEmpty()){
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adlist.size()-1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Edge e = cur.adlist.get(ptr);
                    Node next;
                    if(e.u==cur){
                        next = e.v;
                    }
                    else next = e.u;

                    if(isVisited[next.id])
                        continue;
                    isVisited[next.id] = true;

                    stk.push(next);
                    ptrstk.push(-1);
                }
                else{
                    int c = 1;
                    if(stk.isEmpty()){
                        for(Edge e: cur.adlist){
                            Node next;
                            if(e.u==cur)
                                next = e.v;
                            else next = e.u;
                            c += count[next.id];
                            e.cv = count[next.id];
                        }
                    }
                    else{
                        for(Edge e: cur.adlist){
                            Node next;
                            if(e.u==cur)
                                next = e.v;
                            else next = e.u;
                            if(next==stk.peek())
                                continue;
                            c += count[next.id];
                            e.cv = count[next.id];
                        }
                    }

                    count[cur.id] = c;
                }
            }

            for(Edge edge: edgelist){
                edge.cu = n-edge.cv;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=t;tc++){
            sb.append("Case #").append(tc).append(": ");
            int n = sc.nextInt();

            Graph gr = new Graph(n);
            for(int i=0;i<n-1;i++){
                gr.addEdge(sc.nextInt()-1, sc.nextInt()-1, sc.nextLong());
            }
            gr.dfs();

            long ans = 0;
            for(Edge edge: gr.edgelist){
                ans += edge.weight*(2L *(Math.min(edge.cu, edge.cv)));
            }

            sb.append(ans).append("\n");
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
