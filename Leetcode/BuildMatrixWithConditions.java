package Leetcode;

import java.util.*;
import java.io.*;

public class BuildMatrixWithConditions {

    static class Node{
        int id;
        int no;
        List<Node> outlist = new ArrayList<>();
        int incount;

        Node(int id){
            this.id = id;
            this.incount = 0;
        }
    }

    static class Graph{
        List<Node> nodelist = new ArrayList<>();

        Graph (int n){
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }

        public void addEdge(int above, int below){
            Node above_node = nodelist.get(above);
            Node below_node = nodelist.get(below);

            below_node.outlist.add(above_node);
            above_node.incount++;
        }

        public boolean toposort(){

            Queue<Node> q = new LinkedList<>();
            int number = nodelist.size() - 1;

            for(Node node: nodelist){
                if(node.incount == 0){
                    q.add(node);
                    node.no = number;
                    number--;
                }
            }

            while(!q.isEmpty()){
                Node cur = q.remove();

                for(Node node : cur.outlist){
                    node.incount--;

                    if (node.incount == 0) {
                        q.add(node);
                        node.no = number;
                        number--;
                    }
                }
            }

            return number < 0;
        }
    }

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] matrix = new int[k][k];

        Graph gr = new Graph(k);

        for (int[] rowCondition : rowConditions) {
            int above = rowCondition[0] - 1;
            int below = rowCondition[1] - 1;

            gr.addEdge(above, below);
        }

        boolean flag = gr.toposort();

        if(!flag){
            matrix = new int[0][0];
            return matrix;
        }

        int[] row_numbers = new int[k];
        for(Node node : gr.nodelist){
            row_numbers[node.id] = node.no;
        }

        gr = new Graph(k);

        for (int[] colCondition : colConditions){
            int above = colCondition[0] - 1;
            int below = colCondition[1] - 1;

            gr.addEdge(above, below);
        }

        flag = gr.toposort();

        if(!flag){
            matrix = new int[0][0];
            return matrix;
        }

        int[] col_numbers = new int[k];
        for(Node node : gr.nodelist){
            col_numbers[node.id] = node.no;
        }

        for(int i=0;i<k;i++){
            matrix[row_numbers[i]][col_numbers[i]] = i + 1;
        }

        return matrix;
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
