package Leetcode;

import java.io.*;
import java.util.*;

public class EscapeTheSpreadingFire {

    static class Node{
        int x, y;
        int val;
        int fireTime;

        int lastVal;

        List<Node> adlist = new ArrayList<>();

        boolean isVisited;

        Node(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    static class Graph{
        List<Node> nodelist;

        Graph(int n){
            nodelist = new ArrayList<>(n);
        }

        public void addEdge(Node u, Node v){
            if(u.val == 2 || v.val == 2)
                return;

            u.adlist.add(v);
            v.adlist.add(u);
        }

        public void calcFireTime(List<Node> fireNodes){
            for(Node node: nodelist){
                node.isVisited = false;
                node.fireTime = Integer.MAX_VALUE;
            }

            Queue<Node> q = new LinkedList<>();
            for(Node node: fireNodes){
                node.isVisited = true;
                q.add(node);
                node.fireTime = 0;
            }

            while (!q.isEmpty()){
                Node cur = q.remove();

                for(Node node: cur.adlist){
                    if(!node.isVisited){
                        node.isVisited = true;
                        node.fireTime = cur.fireTime + 1;
                        q.add(node);
                    }
                }
            }
        }

        public boolean isPossible(int startTime){
            for(Node node: nodelist){
                node.isVisited = false;
            }
            Queue<Node> q = new LinkedList<>();

            Node source = nodelist.get(0);
            Node sink = nodelist.get(nodelist.size() - 1);

            q.add(source);
            source.isVisited = true;
            source.lastVal = startTime;

            while (!q.isEmpty()){
                Node cur = q.remove();

                for(Node node: cur.adlist){
                    if(node == sink && sink.fireTime >= cur.lastVal + 1){
                        return true;
                    }
                    if(!node.isVisited && node.fireTime > cur.lastVal + 1){
                        node.isVisited = true;
                        node.lastVal = cur.lastVal + 1;
                        q.add(node);
                    }
                }
            }

            return sink.isVisited;
        }
    }

    static boolean isValid(int n, int m, int i, int j){
        return 0<=i && i<n && 0<=j && j<m;
    }

    public static int maximumMinutes(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Graph gr = new Graph(n * m);

        Node[][] mat = new Node[n][m];
        List<Node> fireNodes = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                mat[i][j] = new Node(i, j, grid[i][j]);
                gr.nodelist.add(mat[i][j]);

                if(grid[i][j] == 1)
                    fireNodes.add(mat[i][j]);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(isValid(n, m, i, j+1))
                    gr.addEdge(mat[i][j], mat[i][j+1]);

                if(isValid(n, m, i+1, j))
                    gr.addEdge(mat[i][j], mat[i+1][j]);
            }
        }

        gr.calcFireTime(fireNodes);

        if(!gr.isPossible(0))
            return -1;

        int ll = 0, ul = (int) 1e9;
        while(ll < ul){
            int mid = ll + (ul - ll + 1) / 2;

            if(gr.isPossible(mid)){
                ll = mid;
            }
            else{
                ul = mid - 1;
            }
        }

        return ll;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int t = sc.nextInt();
        while (t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] grid = new int[n][m];
            for(int i=0;i<n;i++){
                grid[i] = sc.nextIntArray(m);
            }

            System.out.println(maximumMinutes(grid));
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
