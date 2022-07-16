package Codeforces.EducationalRound93;

import java.io.*;
import java.util.*;

public class D {

    static long[][][] dp;

    static long recurse(Long[] rpairs, Long[] gpairs, Long[] bpairs, int ri, int gi, int bi){
        int r = rpairs.length;
        int g = gpairs.length;
        int b = bpairs.length;

        if(dp[ri][gi][bi] != -1)
            return dp[ri][gi][bi];

        if(ri >= r){
            if(gi >= g || bi >= b){
                dp[ri][gi][bi] = 0;
                return dp[ri][gi][bi];
            }
            else{
                dp[ri][gi][bi] = recurse(rpairs, gpairs, bpairs, ri, gi + 1, bi + 1) + (gpairs[gi] * bpairs[bi]);
                return dp[ri][gi][bi];
            }
        }
        else if(gi >= g){
            if(bi >= b){
                dp[ri][gi][bi] = 0;
                return dp[ri][gi][bi];
            }
            else{
                dp[ri][gi][bi] = recurse(rpairs, gpairs, bpairs, ri + 1, gi, bi + 1) + (rpairs[ri] * bpairs[bi]);
                return dp[ri][gi][bi];
            }
        }
        else if(bi >= b){
            dp[ri][gi][bi] = recurse(rpairs, gpairs, bpairs, ri + 1, gi + 1, bi) + (rpairs[ri] * gpairs[gi]);
            return dp[ri][gi][bi];
        }
        else{
            dp[ri][gi][bi] = Math.max(Math.max(recurse(rpairs, gpairs, bpairs, ri, gi + 1, bi + 1) + (gpairs[gi] * bpairs[bi]),
                    recurse(rpairs, gpairs, bpairs, ri + 1, gi, bi + 1) + (rpairs[ri] * bpairs[bi])) ,
                    recurse(rpairs, gpairs, bpairs, ri + 1, gi + 1, bi) + (rpairs[ri] * gpairs[gi]));
            return dp[ri][gi][bi];
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        Long[] rpairs = new Long[r];
        Long[] gpairs = new Long[g];
        Long[] bpairs = new Long[b];

        for(int i=0;i<r;i++){
            rpairs[i] = sc.nextLong();
        }
        for(int i=0;i<g;i++){
            gpairs[i] = sc.nextLong();
        }
        for(int i=0;i<b;i++){
            bpairs[i] = sc.nextLong();
        }

        Arrays.sort(rpairs, Collections.reverseOrder());
        Arrays.sort(gpairs, Collections.reverseOrder());
        Arrays.sort(bpairs, Collections.reverseOrder());

        dp = new long[r+1][g+1][b+1];
        for(int i=0;i<=r;i++){
            for(int j=0;j<=g;j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        long ans = recurse(rpairs, gpairs, bpairs, 0, 0, 0);

        System.out.println(ans);

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
