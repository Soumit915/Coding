package Codeforces;

import java.io.*;
import java.util.*;

public class TreeTag {

    static class Node{
        int id;
        List<Node> adlist = new ArrayList<>();

        int diameter_crossing_through;
        int diameter_till_here;
        int dist;

        boolean isVisited;

        Node(int id){
            this.id = id;
            this.isVisited = false;
            this.diameter_crossing_through = 0;
            this.diameter_till_here = 0;
            this.dist = 0;
        }
    }

    static class Tree{
        List<Node> nodelist = new ArrayList<>();

        Tree(int n){
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

        public void getDiameter(){
            Node source = nodelist.get(0);

            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.push(source);
            ptrstk.push(-1);
            source.isVisited = true;

            while(!stk.isEmpty()){
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr < cur.adlist.size() - 1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adlist.get(ptr);
                    if(!next.isVisited){
                        stk.push(next);
                        ptrstk.push(-1);
                        next.isVisited = true;
                    }
                }
                else{
                    int m1 = 0, m2 = 0;
                    for(Node node: cur.adlist){
                        if(stk.isEmpty() || stk.peek()!=node){
                            if(node.diameter_till_here > m1){
                                m2 = m1;
                                m1 = node.diameter_till_here;
                            }
                            else if(node.diameter_till_here > m2){
                                m2 = node.diameter_till_here;
                            }
                        }
                    }

                    cur.diameter_till_here = m1 + 1;
                    cur.diameter_crossing_through = m1 + m2 + 1;
                }
            }
        }

        public int getDist(int s, int t){
            Node source = nodelist.get(s);
            Node sink = nodelist.get(t);

            for(Node node: nodelist)
                node.isVisited = false;

            Queue<Node> q = new LinkedList<>();
            q.add(source);
            source.isVisited = true;

            while(!q.isEmpty()){
                Node cur = q.remove();

                for(Node node: cur.adlist){
                    if(!node.isVisited){
                        node.dist = cur.dist + 1;
                        node.isVisited = true;
                        q.add(node);
                    }
                }
            }

            return sink.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int da = sc.nextInt();
            int db = sc.nextInt();

            Tree tr = new Tree(n);

            for(int i=0;i<n-1;i++){
                tr.addEdge(sc.nextInt()-1, sc.nextInt()-1);
            }

            tr.getDiameter();

            int diameter = 0;
            for(Node node: tr.nodelist){
                diameter = Math.max(diameter, node.diameter_crossing_through);
            }
            diameter -= 1;

            int dist = tr.getDist(a, b);

            if(dist <= da || (Math.min(db, diameter) <= 2*da)){
                sb.append("Alice\n");
            }
            else{
                sb.append("Bob\n");
            }
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
