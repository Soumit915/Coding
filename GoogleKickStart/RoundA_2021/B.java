package GoogleKickStart.RoundA_2021;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++){
            sb.append("Case #").append(testi).append(": ");

            int r = sc.nextInt();
            int c = sc.nextInt();
            int[][] mat = new int[r][c];
            for(int i=0;i<r;i++){
                mat[i] = sc.nextIntArray(c);
            }

            int[][] row_dp = new int[r+1][c+1];
            int[][] col_dp = new int[r+1][c+1];
            for(int i=1;i<=r;i++){
                for(int j=1;j<=c;j++){
                    col_dp[i][j] += col_dp[i][j-1]+mat[i-1][j-1];
                }
            }
            for(int i=1;i<=c;i++){
                for(int j=1;j<=r;j++){
                    row_dp[j][i] += row_dp[j-1][i]+mat[j-1][i-1];
                }
            }

            long count = 0;
            for(int i=1;i<=r;i++){
                for(int j=1;j<=c;j++){
                    for(int k=j-1;k>0;k--){
                        int col_length = j-k+1;
                        int col_length_got = col_dp[i][j]-col_dp[i][k-1];
                        if(col_length_got!=col_length){
                            continue;
                        }

                        if(col_length%2==1 || col_length%2==0){
                            int row_length = col_length*2;
                            int uplimit = i-row_length+1;
                            int downlimit = i+row_length-1;
                            if(uplimit>0){
                                int left = row_dp[i][k]-row_dp[uplimit-1][k];
                                int right = row_dp[i][j]-row_dp[uplimit-1][j];
                                if(left==row_length)
                                    count++;
                                if(right==row_length)
                                    count++;
                            }
                            if(downlimit<r+1){
                                int left = row_dp[downlimit][k]-row_dp[i-1][k];
                                int right = row_dp[downlimit][j]-row_dp[i-1][j];
                                if(left==row_length)
                                    count++;
                                if(right==row_length)
                                    count++;
                            }
                        }

                        if(col_length%2==0 && col_length>=4){
                            int row_length = col_length/2;
                            int uplimit = i-row_length+1;
                            int downlimit = i+row_length-1;
                            if(uplimit>0){
                                int left = row_dp[i][k]-row_dp[uplimit-1][k];
                                int right = row_dp[i][j]-row_dp[uplimit-1][j];
                                if(left==row_length)
                                    count++;
                                if(right==row_length)
                                    count++;
                            }
                            if(downlimit<r+1){
                                int left = row_dp[downlimit][k]-row_dp[i-1][k];
                                int right = row_dp[downlimit][j]-row_dp[i-1][j];
                                if(left==row_length)
                                    count++;
                                if(right==row_length)
                                    count++;
                            }
                        }
                    }
                }
            }

            sb.append(count).append("\n");
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
