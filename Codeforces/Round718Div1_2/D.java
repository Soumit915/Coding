package Codeforces.Round718Div1_2;

import java.io.*;
import java.util.*;

public class D {
    static boolean isValid(int n, int m, int i, int j){
        return 0<=i && i<n && 0<=j && j<m;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        if(k%2==1){
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    sb.append("-1 ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        k/=2;

        long[][] horizontaledge = new long[n][m-1];
        long[][] verticaledge = new long[n-1][m];
        for(int i=0;i<n;i++)
            horizontaledge[i] = sc.nextLongArray(m-1);
        for(int i=0;i<n-1;i++)
            verticaledge[i] = sc.nextLongArray(m);

        long[][][] dp = new long[11][n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                dp[0][i][j] = 0;
            }
        }
        for(int i=1;i<=k;i++){
            for(int j1=0;j1<n;j1++){
                for(int j2=0;j2<m;j2++){
                    long min = Long.MAX_VALUE/2000;
                    //for up
                    if(isValid(n, m, j1-1, j2)){
                        min = Math.min(dp[i-1][j1-1][j2]+verticaledge[j1-1][j2], min);
                    }

                    //for down
                    if(isValid(n, m, j1+1, j2)){
                        min = Math.min(min, dp[i-1][j1+1][j2]+verticaledge[j1][j2]);
                    }

                    //for left
                    if(isValid(n, m, j1, j2-1)){
                        min = Math.min(min, dp[i-1][j1][j2-1]+horizontaledge[j1][j2-1]);
                    }

                    //for right
                    if(isValid(n, m, j1, j2+1)){
                        min = Math.min(min, dp[i-1][j1][j2+1]+horizontaledge[j1][j2]);
                    }

                    dp[i][j1][j2] = min;
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                sb.append(dp[k][i][j]*2).append(" ");
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
