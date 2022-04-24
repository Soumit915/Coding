package Leetcode;

import java.io.*;
import java.util.*;

public class LongestPathWithDiffAdjacentChars {

    static class Node{
        int id;
        char ch;
        Node parent;

        int maxPivDist, maxLeafDist;

        List<Node> adlist = new ArrayList<>();
        Node(int id, char ch){
            this.id = id;
            this.ch = ch;
            this.parent = null;
        }
    }

    static class Tree{
        List<Node> nodelist;

        Tree(int n, String s){
            nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i, s.charAt(i)));
            }
        }

        public void addEdge(int child, int parent){
            Node nc = nodelist.get(child);
            Node np = nodelist.get(parent);

            np.adlist.add(nc);
            nc.parent = np;
        }

        public void dfs(){
            Node root = nodelist.get(0);

            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.push(root);
            ptrstk.push(-1);

            while(!stk.isEmpty()){
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr < cur.adlist.size() - 1){
                    ptr++;

                    stk.push(root);
                    ptrstk.push(ptr);

                    Node next = cur.adlist.get(ptr);
                    if(next.adlist.size() == 0){
                        next.maxLeafDist = 1;
                        next.maxPivDist = 1;
                    }
                    else{
                        stk.push(next);
                        ptrstk.push(-1);
                    }
                }
                else{
                    List<Integer> list = new ArrayList<>();
                    for(Node next: cur.adlist){
                        if(next.ch != cur.ch){
                            list.add(next.maxLeafDist);
                        }
                    }

                    list.sort(Collections.reverseOrder());

                    if(list.size() == 0){
                        cur.maxPivDist = 1;
                        cur.maxLeafDist = 1;
                    }
                    else if(list.size() == 1){
                        cur.maxPivDist = list.get(0) + 1;
                        cur.maxLeafDist = list.get(0) + 1;
                    }
                    else{
                        cur.maxLeafDist = list.get(0) + 1;
                        cur.maxPivDist = list.get(0) + list.get(1) + 1;
                    }
                }
            }
        }
    }

    public int longestPath(int[] parent, String s) {
        int n = s.length();

        Tree tr = new Tree(n, s);
        for(int i=1;i<n;i++){
            tr.addEdge(i, parent[i]);
        }

        tr.dfs();

        int max = 0;
        for(Node node: tr.nodelist){
            max = Math.max(node.maxPivDist, max);
        }

        return max;
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
