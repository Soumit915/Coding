package Codeforces;

import java.util.*;
import java.io.*;

public class YetAnotherDivisionInTeams {

    static class Node implements Comparable<Node>{
        int id;
        long val;

        Node(int id, long val){
            this.id = id;
            this.val = val;
        }

        public int compareTo(Node node){
            return Long.compare(this.val, node.val);
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        long[] a = sc.nextLongArray(n);

        Node[] nodes = new Node[n];
        for(int i=0;i<n;i++){
            nodes[i] = new Node(i, a[i]);
        }
        Arrays.sort(nodes);

        long[] dp = new long[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        dp[2] = nodes[2].val - nodes[0].val;
        prev[2] = -1;

        if(n > 3){
            dp[3] = nodes[3].val - nodes[0].val;
            prev[3] = -1;
        }

        if(n > 4){
            dp[4] = nodes[4].val - nodes[0].val;
            prev[4] = -1;
        }

        for(int i=5;i<n;i++){
            long v3 = dp[i-3] + (nodes[i].val - nodes[i-2].val);
            long v4 = dp[i-4] + (nodes[i].val - nodes[i-3].val);
            long v5 = dp[i-5] + (nodes[i].val - nodes[i-4].val);

            dp[i] = Math.min(v3, Math.min(v4, v5));

            if(dp[i] == v3){
                prev[i] = i-3;
            }
            else if(dp[i] == v4){
                prev[i] = i-4;
            }
            else{
                prev[i] = i-5;
            }
        }

        List<Integer> list = new ArrayList<>();
        int ptr = n-1;
        while(ptr != -1){
            list.add(ptr);
            ptr = prev[ptr];
        }
        Collections.sort(list);

        int[] teams = new int[n];
        ptr = 0;
        for(int i=0;i<n;i++){
            if(list.get(ptr) < i){
                ptr++;
            }

            teams[nodes[i].id] = ptr + 1;
        }

        System.out.println(dp[n-1]+" "+(ptr+1));
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append(teams[i]).append(" ");
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
