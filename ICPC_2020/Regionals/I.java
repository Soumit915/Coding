package ICPC_2020.Regionals;

import java.io.*;
import java.util.*;

public class I {
    static ArrayList<Boolean> isPrime;
    static ArrayList<Integer> primes = new ArrayList<>();
    static ArrayList<Integer> spf;
    public static void computePrimes(int n)
    {
        isPrime = new ArrayList<>(n);
        spf = new ArrayList<>(n);
        for(int i=0;i<n;i++)
        {
            isPrime.add(true);
            spf.add(2);
        }

        isPrime.set(0,false);
        isPrime.set(1,false);

        for(int i=2;i<n;i++)
        {
            if(isPrime.get(i))
            {
                primes.add(i);
                spf.set(i, i);
            }

            for(int j=0;j<primes.size() && i*primes.get(j)<n && primes.get(j)<=spf.get(i);j++)
            {
                isPrime.set(i*primes.get(j), false);
                spf.set(i*primes.get(j), primes.get(j));
            }
        }
    }
    static ArrayList<Integer> primefactors(int n){
        ArrayList<Integer> arlist = new ArrayList<>();
        while(n>1){
            int pf = spf.get(n);
            while(spf.get(n)==pf && n>1){
                n /= pf;
            }
            arlist.add(pf);
        }

        return arlist;
    }

    static class Node{
        int id;
        int rating;
        boolean isVisited;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id, int rating){
            this.id = id;
            this.rating = rating;
            this.isVisited = false;
        }
        public String toString(){
            return this.id+" "+this.rating;
        }
    }

    static class Graph{
        ArrayList<Node> nodelist = new ArrayList<>();
        HashMap<Integer, Node> masternodelist = new HashMap<>();
        long components;
        long spf;
        Graph(int n, int[] ratings){
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i, ratings[i]));
            }

            this.spf = Integer.MAX_VALUE;
        }
        public void addEdge(Node u, Node v){
            u.adlist.add(v);
            v.adlist.add(u);
        }
        public void addEdges(){
            for(Node node: nodelist){
                ArrayList<Integer> pfactors = primefactors(node.rating);
                for(int i: pfactors){
                    if(!masternodelist.containsKey(i)){
                        Node newnode = new Node(-1, i);
                        masternodelist.put(i, newnode);
                    }

                    addEdge(node, masternodelist.get(i));
                    this.spf = Math.min(spf, i);
                }
            }
        }
        public void countComponents(){
            for(Node node: nodelist){
                if(!node.isVisited){
                    dfs(node);
                    this.components++;
                }
            }
        }
        public void dfs(Node source){
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.push(source);
            ptrstk.push(-1);
            source.isVisited = true;

            while(!stk.isEmpty()){
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adlist.size()-1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adlist.get(ptr);
                    if(!next.isVisited){
                        stk.push(next);
                        next.isVisited = true;
                        ptrstk.push(-1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        computePrimes(1000001);

        int test = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (test-->0){
            int n = sc.nextInt();
            int[] r = sc.nextIntArray(n);

            Graph gr = new Graph(n, r);
            gr.addEdges();
            gr.countComponents();

            sb.append(Math.min(2*(gr.components), gr.spf*(gr.components-1))).append("\n");
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
