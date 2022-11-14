/*
You are given a dictionary of words. A group is defined if any 2 strings in the group has atmost one
unique character different. Find the length of the maximum group and the number of groups.
* */

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class NameGame_BruteForce {

    static class Node{
        int id;
        boolean isVisited;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id){
            this.id = id;
            this.isVisited = false;
        }
    }

    static class Graph{
        ArrayList<Node> nodelist;
        Graph(int n){
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                this.nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v){
            Node nu = this.nodelist.get(u);
            Node nv = this.nodelist.get(v);

            nu.adlist.add(nv);
            nv.adlist.add(nu);
        }
        public int[] getCC(){
            int[] ans = new int[2];
            for(Node node: this.nodelist){
                if(!node.isVisited){
                    ans[1]++;
                    ans[0] = Math.max(ans[0], dfs(node));
                }
            }
            return ans;
        }
        public int dfs(Node source){

            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.push(source);
            ptrstk.push(-1);
            source.isVisited = true;
            int c = 1;

            while(!stk.isEmpty()){
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adlist.size()-1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adlist.get(ptr);
                    if(next.isVisited)
                        continue;

                    c++;
                    stk.push(next);
                    ptrstk.push(-1);
                    next.isVisited = true;
                }
            }

            return c;
        }
    }

    static boolean hasEdge(String s, String t){
        int[] hash_s = new int[26];
        for(int i=0;i<s.length();i++){
            hash_s[s.charAt(i)-65] = 1;
        }
        int us = 0;
        for(int i: hash_s)
            if(i==1) us++;

        int[] hash_t = new int[26];
        for(int i=0;i<t.length();i++){
            hash_t[t.charAt(i)-65] = 1;
        }
        int ut = 0;
        for(int i: hash_t)
            if(i==1) ut++;

        int c = 0;
        for(int i=0;i<26;i++){
            if(hash_s[i]==hash_t[i]){
                hash_s[i] -= hash_t[i];
            }
            else {
                hash_s[i] = hash_t[i] | hash_s[i];
                c++;
            }
        }

        if(c==0 || c==1){
            return true;
        }
        else return c == 2 && us == ut;
    }

    static int[] getAns(int n, String[] names){

        Graph gr = new Graph(n);
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(hasEdge(names[i], names[j]))
                    gr.addEdge(i, j);
            }
        }

        return gr.getCC();
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String[] names = new String[n];
            for(int i=0;i<n;i++){
                names[i] = sc.next();
            }

            int[] ans = getAns(n, names);
            for(int i: ans)
                sb.append(i).append(" ");
            sb.append("\n");
        }

        sc.println(sb.toString());

        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000.0);

        sc.close();;
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
