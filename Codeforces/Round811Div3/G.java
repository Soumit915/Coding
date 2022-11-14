package Codeforces.Round811Div3;

import java.util.*;
import java.io.*;

public class G {

    static class Node{
        int id;

        long ai, bi;

        List<Node> adlist;

        Node(int id){
            this.id = id;
            adlist = new ArrayList<>();
        }
    }

    static class Tree{
        List<Node> nodelist;

        Tree(int n){
            nodelist = new ArrayList<>();
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }

        public void addEdge(int u, int v, long a, long b){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nv.adlist.add(nu);

            nu.ai = a;
            nu.bi = b;
        }

        public int binarySearch(List<Long> list, long k, int l, int r){
            while(l < r){
                int mid = (l + r + 1) / 2;

                if(list.get(mid) > k){
                    r = mid - 1;
                }
                else{
                    l = mid;
                }
            }

            if(l == r) {
                if(list.get(l) > k)
                    return -1;
                return l;
            }
            return -1;
        }

        public void dfs(Node source, int[] ans, long sum, int hi, List<Long> list){
            if(hi > -1){
                sum += source.ai;

                if(hi == 0){
                    if(list.size() == 0){
                        list.add(source.bi);
                    }
                    else{
                        list.set(hi, source.bi);
                    }
                }
                else{
                    if(list.size() == hi){
                        list.add(list.get(hi - 1) + source.bi);
                    }
                    else{
                        list.set(hi, list.get(hi - 1) + source.bi);
                    }
                }
            }

            int v = binarySearch(list, sum, 0, hi);
            ans[source.id] = v + 1;

            for(Node node: source.adlist){
                dfs(node, ans, sum, hi+1, list);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();

            Tree tr = new Tree(n);

            for(int i=0;i<n-1;i++){
                int pi = sc.nextInt() - 1;
                long ai = sc.nextLong();
                long bi = sc.nextLong();

                tr.addEdge(i+1, pi, ai, bi);
            }

            int[] ans = new int[n];
            Node root = tr.nodelist.get(0);
            List<Long> list = new ArrayList<>();
            tr.dfs(root, ans, 0, -1, list);

            for(int i=1;i<n;i++){
                sb.append(ans[i]).append(" ");
            }
            sb.append("\n");
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
