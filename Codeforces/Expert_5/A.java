package Codeforces.Expert_5;

import java.util.*;
import java.io.*;

public class A {

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int[] count = new int[1009];

        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> countq = new LinkedList<>();
        HashSet<Integer> isVisited = new HashSet<>();

        q.add(1);
        countq.add(0);
        isVisited.add(1);
        count[1] = 0;

        while (!q.isEmpty()){
            int cur = q.remove();
            int ops = countq.remove();

            for(int i=1;i<=cur;i++){
                int v = cur + (cur / i);

                if(!isVisited.contains(v) && v < count.length){
                    q.add(v);
                    countq.add(ops + 1);
                    isVisited.add(v);
                    count[v] = ops + 1;
                }
            }
        }

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] b = sc.nextIntArray(n);
            int[] c = sc.nextIntArray(n);

            int[] ops = new int[n];
            int sum_ops = 0;
            for(int i=0;i<n;i++){
                ops[i] = count[b[i]];
                sum_ops += ops[i];
            }

            if(sum_ops <= k){

                int sum_cost = 0;
                for(int i=0;i<n;i++){
                    sum_cost += c[i];
                }

                sb.append(sum_cost).append("\n");
            }
            else{

                int[][] dp = new int[n + 1][k + 1];

                for(int i=1;i<=n;i++){
                    for(int j=0;j<=k;j++){
                        if(j - ops[i-1] >= 0){
                            dp[i][j] = Math.max(dp[i-1][j - ops[i-1]] + c[i-1], dp[i-1][j]);
                        }
                        else{
                            dp[i][j] = dp[i-1][j];
                        }
                    }
                }

                int max = 0;
                for(int i=0;i<=k;i++){
                    max = Math.max(max, dp[n][i]);
                }

                sb.append(max).append("\n");
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
