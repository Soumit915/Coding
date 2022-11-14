package Leetcode;

import java.io.*;
import java.util.*;

public class EvaluateDivision {

    static class Node{
        int id;
        String val;

        boolean isVisited;
        double fraction;

        List<Edge> adlist = new ArrayList<>();

        Node(int id, String val){
            this.id = id;
            this.val = val;
        }

    }

    static class Edge{
        Node source;
        Node sink;
        double value;

        Edge(Node source, Node sink, double value){
            this.source = source;
            this.sink = sink;
            this.value = value;
        }
    }
    
    static class Graph{
        List<Node> nodelist;
        
        Graph(int n, Map<String, Integer> variable_map){
            this.nodelist = new ArrayList<>(n);
            
            for(String s: variable_map.keySet()){
                nodelist.add(new Node(variable_map.get(s), s));
            }
        }

        public void addEdge(int u, int v, double value){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            Edge e = new Edge(nu, nv, value);

            nu.adlist.add(e);
            nv.adlist.add(e);
        }

        public double calulateEquation(int u, int v){

            for(Node node: nodelist){
                node.isVisited = false;
                node.fraction = 1.0;
            }

            Node source = nodelist.get(u);
            Node sink = nodelist.get(v);

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

                    Edge e = cur.adlist.get(ptr);

                    if(e.source == cur){
                        Node next = e.sink;

                        if(!next.isVisited){
                            stk.push(next);
                            ptrstk.push(-1);
                            next.isVisited = true;
                            next.fraction = cur.fraction * e.value;
                        }
                    }
                    else{
                        Node next = e.source;

                        if(!next.isVisited){
                            stk.push(next);
                            ptrstk.push(-1);
                            next.isVisited = true;
                            next.fraction = cur.fraction / e.value;
                        }
                    }
                }
            }

            if(!sink.isVisited)
                return -1;
            else return sink.fraction;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values,
                                 List<List<String>> queries) {

        HashMap<String, Integer> variable_map = new HashMap<>();
        for(List<String> list: equations){
            String num = list.get(0);
            String deno = list.get(1);

            int num_id = variable_map.getOrDefault(num, variable_map.keySet().size());
            variable_map.put(num, num_id);

            int deno_id = variable_map.getOrDefault(deno, variable_map.keySet().size());
            variable_map.put(deno, deno_id);
        }

        Graph gr = new Graph(variable_map.keySet().size(), variable_map);

        for(int i=0;i<equations.size();i++){
            List<String> list = equations.get(i);
            String num = list.get(0);
            String deno = list.get(1);

            int num_id = variable_map.get(num);
            int deno_id = variable_map.get(deno);
            
            gr.addEdge(num_id, deno_id, values[i]);
        }

        int q = queries.size();
        double[] ans = new double[q];
        for(int i=0;i<q;i++){
            List<String> list = queries.get(i);

            String num = list.get(0);
            String deno = list.get(1);

            int num_id = variable_map.getOrDefault(num, -1);
            int deno_id = variable_map.getOrDefault(deno, -1);

            if(num_id == -1 || deno_id == -1){
                ans[i] = -1;
                continue;
            }

            double cur_ans = gr.calulateEquation(num_id, deno_id);
            ans[i] = cur_ans;
        }

        return ans;
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
