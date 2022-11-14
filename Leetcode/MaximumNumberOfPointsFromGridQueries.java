package Leetcode;

import java.util.*;
import java.io.*;

public class MaximumNumberOfPointsFromGridQueries {

    static class Query{
        int id;
        int val;

        Query(int id, int qi){
            this.id = id;
            this.val = qi;
        }
    }

    static class Node{
        int x, y, val;

        List<Node> adlist = new ArrayList<>();

        Node(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    static void addEdge(Node u, Node v){
        u.adlist.add(v);
        v.adlist.add(u);
    }

    static boolean isValid(int n, int m, int i, int j){
        return 0<=i && i<n && 0<=j && j<m;
    }

    public static int[] maxPoints(int[][] grid, int[] queries) {
        int qn = queries.length;

        Query[] query = new Query[qn];
        for(int i=0;i<qn;i++){
            query[i] = new Query(i, queries[i]);
        }

        Arrays.sort(query, (q1, q2) -> Integer.signum(q1.val - q2.val));

        int n = grid.length;
        int m = grid[0].length;

        Node[][] nodes = new Node[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                nodes[i][j] = new Node(i, j, grid[i][j]);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(isValid(n, m, i-1, j)){
                    addEdge(nodes[i][j], nodes[i-1][j]);
                }

                if(isValid(n, m, i, j-1)){
                    addEdge(nodes[i][j], nodes[i][j-1]);
                }
            }
        }

        int count = 0;
        int[] ans = new int[qn];

        Queue<Node> q = new LinkedList<>();
        q.add(nodes[0][0]);

        Set<Node> visited = new HashSet<>();
        visited.add(nodes[0][0]);

        for(int i=0;i<qn;i++){
            Query qi = query[i];

            Queue<Node> localq = new LinkedList<>();
            while(!q.isEmpty()){
                Node cur = q.remove();

                if(cur.val < qi.val){
                    count++;

                    for(Node node : cur.adlist){
                        if(!visited.contains(node)){
                            visited.add(node);
                            q.add(node);
                        }
                    }
                }
                else{
                    localq.add(cur);
                }
            }

            ans[qi.id] = count;
            q = localq;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int n = 745, m = 134, k = 10000;
        int[][] grid = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                grid[i][j] = (int) (Math.random() * 1000000 + 1);
            }
        }

        int[] q = new int[k];
        for(int i=0;i<k;i++){
            q[i] = (int) (Math.random() * 1000000 + 1);
        }

        long start = System.currentTimeMillis();

        System.out.println(Arrays.toString(maxPoints(grid, q)));

        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);

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
