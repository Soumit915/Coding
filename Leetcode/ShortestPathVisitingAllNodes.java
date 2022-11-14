package Leetcode;

import java.io.*;
import java.util.*;

public class ShortestPathVisitingAllNodes {

    static int countBits(int n){
        int c = 0;
        while(n > 0){
            n = n & (n-1);
            c++;
        }

        return c;
    }

    static boolean isSet(int n, int i){
        return (n&(1<<i))!=0;
    }

    static int unSet(int n, int i){
        return n ^ (1<<i);
    }

    public static int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int[][] admat = new int[n][n];

        for(int i=0;i<n;i++){
            Arrays.fill(admat[i], 100000);
            admat[i][i] = 0;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<graph[i].length;j++){
                admat[i][graph[i][j]] = 1;
            }
        }

        for(int k=0;k<n;k++){
            int[][] admat_local = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(i==k || j==k || i==j)
                        admat_local[i][j] = admat[i][j];

                    admat_local[i][j] = Math.min(admat[i][j], admat[i][k] + admat[k][j]);
                }
            }

            admat = admat_local;
        }

        int[][] dp = new int[1<<n][n];

        int lim = (1 << n);

        for(int i=2;i<=n;i++){

            int[][] dp_local = new int[1<<n][n];

            for(int j=0;j<lim;j++){
                if(countBits(j)!=i)
                    continue;

                for(int k=0;k<n;k++){
                    if(isSet(j, k)){
                        int min = 100000;
                        for(int l=0;l<n;l++){
                            if(l==k || !isSet(j, l))
                                continue;
                            min = Math.min(min, dp[unSet(j, k)][l] + admat[k][l]);
                        }

                        dp_local[j][k] = min;
                    }
                }
            }
            dp = dp_local;
        }

        int min = 100000;
        for(int j=0;j<n;j++){
            min = Math.min(min, dp[(1<<n)-1][j]);
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        int[][] graph = new int[n][];
        for(int i=0;i<n;i++){
            int k = sc.nextInt();
            graph[i] = new int[k];
            for(int j=0;j<k;j++){
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.println(shortestPathLength(graph));

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
