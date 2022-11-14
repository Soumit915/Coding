package Codeforces.ITMO_PilotCourse.BinarySearch.Step4;

import java.io.*;
import java.util.*;

public class B_Checker {
    static class Node{
        int id;
        Node next;
        Map<Integer, Edge> adlist = new HashMap<>();
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
        long[] dist;

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

            nu.adlist.put(v, e);
        }

        public long getAvg(int[] path){
            long sum = 0;
            for(int i=1;i<path.length;i++){
                Node source = nodelist.get(path[i-1]-1);
                Node sink = nodelist.get(path[i] - 1);
                if(!source.adlist.containsKey(sink.id)){
                    System.out.println(i);
                    System.out.println("Wrong answer at path-way for : "+Arrays.toString(path));
                    System.exit(0);
                }

                sum += source.adlist.get(sink.id).weight;
            }

            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        Soumit myoutput = new Soumit("Output1.txt");

        Soumit actoutput = new Soumit("Output2.txt");

        int t = sc.nextInt();
        while(t-->0){
            StringBuilder sb = new StringBuilder();
            int n = sc.nextInt();
            int m = sc.nextInt();

            Graph gr = new Graph(n);

            for(int i=0;i<m;i++){
                gr.addEdge(sc.nextInt()-1, sc.nextInt()-1, sc.nextLong());
            }

            int length1 = myoutput.nextInt();
            int[] path1 = myoutput.nextIntArray(length1 + 1);
            long sum1 = gr.getAvg(path1);

            int length2 = actoutput.nextInt();
            int[] path2 = actoutput.nextIntArray(length2 + 1);
            long sum2 = gr.getAvg(path2);

            if(sum1*(length2 + 1) != sum2*(length1 + 1)){
                System.out.println("Wrong answer");
                System.out.println(sum1+" "+sum2);

                for(int i=0;i<=Math.min(length1, length2);i++){
                    if(path1[i] != path2[i]) {
                        System.out.println(i + " " + path1[i] + " " + path2[i]);
                        System.exit(0);
                    }
                }

                System.exit(0);
            }

            sc.println(sb.toString());
        }

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
