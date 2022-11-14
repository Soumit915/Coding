package Leetcode;

import java.io.*;
import java.util.*;

public class MinimumScoreAfterRemovalsOnTree {

    static class Node{
        int id;
        int val;

        boolean isVisited;

        List<Edge> adlist = new ArrayList<>();

        Node(int id, int val){
            this.id = id;
            this.val = val;
        }
    }

    static class Edge{
        Node u, v;

        int xor;

        Set<Edge> componentEdges = new HashSet<>();

        Edge(Node u, Node v){
            this.u = u;
            this.v = v;
        }
    }

    static class Tree{
        List<Node> nodeList;
        List<Edge> edgelist;

        Tree(int n, int[] arr){
            this.nodeList = new ArrayList<>(n);
            this.edgelist = new ArrayList<>(n-1);
            for(int i=0;i<n;i++){
                this.nodeList.add(new Node(i, arr[i]));
            }
        }

        public void addEdge(int u, int v){
            Node nu = nodeList.get(u);
            Node nv = nodeList.get(v);

            Edge e = new Edge(nu, nv);
            nu.adlist.add(e);
            nv.adlist.add(e);

            this.edgelist.add(e);
        }

        public void computeXors(){
            Node source = nodeList.get(0);

            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);
            source.isVisited = true;

            while(!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adlist.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Edge e = cur.adlist.get(ptr);

                    Node next;
                    if(e.u == cur){
                        next = e.v;
                    }
                    else{
                        next = e.u;
                    }

                    if(!next.isVisited){
                        stk.push(next);
                        ptrstk.push(-1);
                        next.isVisited = true;
                    }
                }
                else{
                    if(stk.isEmpty())
                        continue;

                    int xor = cur.val;
                    Set<Edge> edgeset = new HashSet<>();
                    for(Edge e: cur.adlist){
                        if(e.u == stk.peek() || e.v == stk.peek())
                            continue;
                        xor ^= e.xor;
                        edgeset.addAll(e.componentEdges);
                        edgeset.add(e);
                    }

                    for(Edge e: cur.adlist){
                        if(e.u == stk.peek() || e.v == stk.peek()){
                            e.xor = xor;
                            e.componentEdges = edgeset;
                        }
                    }
                }
            }
        }

        public int getMinScore(){

            int total_xor = 0;
            for(Node node: nodeList)
                total_xor ^= node.val;

            int min = Integer.MAX_VALUE;

            for(int i=0;i<edgelist.size();i++){

                Edge removed = edgelist.get(i);
                int component1_Score = removed.xor;
                int component2_Score = total_xor ^ component1_Score;

                for(int j=i + 1;j<edgelist.size();j++){
                    Edge toBeRemoved = edgelist.get(j);

                    int cur1 = component1_Score;
                    int cur2 = component2_Score;
                    int cur3;

                    if(!removed.componentEdges.contains(toBeRemoved)){
                        if(toBeRemoved.componentEdges.contains(removed)){
                            cur3 = toBeRemoved.xor ^ cur1;
                        }
                        else{
                            cur3 = toBeRemoved.xor;
                        }
                        cur2 = cur2 ^ cur3;
                    }
                    else{
                        cur3 = toBeRemoved.xor;
                        cur1 = cur1 ^ cur3;
                    }

                    min = Math.min(min, Math.max(Math.max(cur1, cur2), cur3)
                            - Math.min(cur1, Math.min(cur2, cur3)));
                }
            }

            return min;
        }
    }

    public static int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        Tree tr = new Tree(n, nums);

        for(int[] edge: edges){
            tr.addEdge(edge[0], edge[1]);
        }

        tr.computeXors();

        return tr.getMinScore();
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int[] nums = {1,5,5,4,11};
        int[][] edges = {{0,1},{1,2},{1,3},{3,4}};

        System.out.println(minimumScore(nums, edges));

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
