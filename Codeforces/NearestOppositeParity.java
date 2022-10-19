package Codeforces;

import java.util.*;
import java.io.*;

public class NearestOppositeParity {

    static class Node{
        int id;
        int val;

        int dist;

        boolean isVisited;

        List<Node> adlist = new ArrayList<>();

        Node(int id, int val){
            this.id = id;
            this.val = val;
            this.isVisited = false;
            this.dist = -1;
        }
    }

    static class Graph {
        List<Node> nodelist = new ArrayList<>();

        Graph(int[] a){
            int n = a.length;
            for(int i=0;i<n;i++)
                nodelist.add(new Node(i, a[i]));
        }

        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adlist.add(nv);
        }

        public void bfs(List<Integer> source_index){

            Queue<Node> q = new LinkedList<>();

            for(int i: source_index){
                q.add(nodelist.get(i));
                nodelist.get(i).isVisited = true;
                nodelist.get(i).dist = 0;
            }

            while(!q.isEmpty()){
                Node cur = q.remove();

                for(Node node: cur.adlist){
                    if(!node.isVisited){
                        q.add(node);
                        node.dist = cur.dist + 1;
                        node.isVisited = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[] a = sc.nextIntArray(n);

        Graph gr_even = new Graph(a);
        Graph gr_odd = new Graph(a);

        int[] dist = new int[n];

        List<Integer> oddlist = new ArrayList<>();
        List<Integer> evenlist = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(a[i]%2 == 0){
                if(i - a[i] >= 0)
                    gr_even.addEdge(i-a[i], i);

                if(i + a[i] < n)
                    gr_even.addEdge(i+a[i], i);
            }
            else{
                if(i - a[i] >= 0)
                    gr_odd.addEdge(i-a[i], i);

                if(i + a[i] < n)
                    gr_odd.addEdge(i+a[i], i);
            }

            if(a[i]%2 == 1){
                oddlist.add(i);
            }
            else{
                evenlist.add(i);
            }
        }

        gr_even.bfs(oddlist);
        gr_odd.bfs(evenlist);

        for(Node node: gr_even.nodelist){
            if(node.val%2 == 0)
                dist[node.id] = node.dist;
        }

        for(Node node: gr_odd.nodelist){
            if(node.val%2 == 1)
                dist[node.id] = node.dist;
        }

        StringBuilder sb = new StringBuilder();
        for(int i: dist){
            sb.append(i).append(" ");
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
