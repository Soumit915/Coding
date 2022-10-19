package Codeforces;

import java.util.*;
import java.io.*;

public class CompleteTripartite {

    static class Node{
        int id;
        List<Node> adlist = new ArrayList<>();

        int group;

        Node(int id){
            this.id = id;
            this.group = -1;
        }
    }

    static class Graph{
        List<Node> nodelist;

        Graph(int n){
            this.nodelist = new ArrayList<>();
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }

        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adlist.add(nv);
            nv.adlist.add(nu);
        }

        public boolean dfs(){
            Node source = nodelist.get(0);

            HashSet<Node> set23 = new HashSet<>(source.adlist);
            HashSet<Node> set1 = new HashSet<>();

            for(Node node: nodelist){
                if(!set23.contains(node)){
                    set1.add(node);
                    node.group = 1;
                }
            }

            if(set23.size() <= 1)
                return false;

            for(Node node: set1){
                if(node.adlist.size() != set23.size())
                    return false;

                for(Node adnode: node.adlist){
                    if(!set23.contains(adnode))
                        return false;
                }
            }

            source = source.adlist.get(0);

            HashSet<Node> set2 = new HashSet<>();
            HashSet<Node> set3 = new HashSet<>();

            for(Node node: source.adlist){
                if(!set1.contains(node)){
                    set3.add(node);
                    node.group = 3;
                }
            }

            if(set3.size() == 0)
                return false;

            for(Node node: set23){
                if(!set3.contains(node)){
                    set2.add(node);
                    node.group = 2;
                }
            }

            for(Node node: set2){
                if(node.adlist.size() != (set1.size() + set3.size())){
                    return false;
                }

                for(Node adnode: node.adlist){
                    if(set2.contains(adnode))
                        return false;
                }
            }

            for(Node node: set3){
                if(node.adlist.size() != (set1.size() + set2.size()))
                    return false;

                for(Node adnode: node.adlist){
                    if(set3.contains(adnode))
                        return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph gr = new Graph(n);

        for(int i=0;i<m;i++){
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            gr.addEdge(u, v);
        }

        boolean flag = gr.dfs();

        if(flag){
            StringBuilder sb = new StringBuilder();
            for(Node node: gr.nodelist){
                sb.append(node.group).append(" ");
            }
            System.out.println(sb);
        }
        else{
            System.out.println("-1");
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
