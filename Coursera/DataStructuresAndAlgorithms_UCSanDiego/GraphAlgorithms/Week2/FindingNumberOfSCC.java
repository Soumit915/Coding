package Coursera.DataStructuresAndAlgorithms_UCSanDiego.GraphAlgorithms.Week2;

import java.io.*;
import java.util.*;

public class FindingNumberOfSCC {
    static class Node{
        int id;
        int lowlink;
        int disc;
        boolean isVisited;
        boolean inStack;
        ArrayList<Node> adList = new ArrayList<>();
        Node(int id){
            this.id = id;
            this.lowlink = -1;
            this.disc = -1;
            this.isVisited = false;
            this.inStack = false;
        }
    }
    static class Graph{
        ArrayList<Node> nodelist;
        Stack<Node> stk;
        int disc;
        int numberOfSCC;
        Graph(int n){
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
            disc = 0;
            this.numberOfSCC = 0;
        }
        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);
            nu.adList.add(nv);
        }
        public void countSCC(){
            for(Node node: nodelist){
                if(!node.isVisited){
                    stk = new Stack<>();
                    stk.push(node);
                    node.isVisited = true;
                    node.inStack = true;
                    dfsSCC(node);
                }
            }
        }
        public void dfsSCC(Node source){

            source.disc = disc;
            source.lowlink = source.disc;
            disc++;

            for(Node node: source.adList){
                if(node.inStack){
                    source.lowlink = Math.min(source.lowlink, node.lowlink);
                }
                else if(!node.isVisited){
                    stk.push(node);
                    node.isVisited = true;
                    node.inStack = true;
                    dfsSCC(node);
                    source.lowlink = Math.min(source.lowlink, node.lowlink);
                }
            }

            if(source.lowlink==source.disc){
                this.numberOfSCC++;
                while(stk.pop()!=source){
                    stk.pop();
                }
                source.inStack = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();
        Graph gr = new Graph(n);
        for(int i=0;i<m;i++){
            gr.addEdge(sc.nextInt()-1, sc.nextInt()-1);
        }

        gr.countSCC();
        System.out.println(gr.numberOfSCC);

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
