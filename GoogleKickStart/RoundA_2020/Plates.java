package GoogleKickStart.RoundA_2020;

import java.io.*;
import java.util.*;

public class Plates {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t   = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for(int testi = 1;testi<=t;testi++)
        {
            sb.append("Case #").append(testi).append(": ");

            int n = sc.nextInt();
            int k = sc.nextInt();
            int p = sc.nextInt();

            int[][] mat = new int[n][k];
            for(int i=0;i<n;i++)
                mat[i] = sc.nextIntArray(k);

            int[][] summat = new int[n][k];
            for(int i=0;i<n;i++)
            {
                summat[i][0] = mat[i][0];
                for(int j=1;j<k;j++)
                    summat[i][j] = summat[i][j-1] + mat[i][j];
            }

            int[][] dp = new int[n][p];

            dp[0][0] = mat[0][0];
            for(int i=1;i<Math.min(k, p);i++)
                dp[0][i] = dp[0][i-1] + mat[0][i];
            for(int i=k;i<p;i++)
                dp[0][i] = dp[0][i-1];

            for(int i=1;i<n;i++)
            {
                for(int j=0;j<Math.min(k*(i+1), p);j++)
                {
                    int max = dp[i-1][j];
                    for(int j1=1;j1<=Math.min(k, j+1);j1++)
                    {
                        if(j-j1<0)
                            max = Math.max(max, summat[i][j1-1]);
                        else max = Math.max(max, summat[i][j1-1] + dp[i-1][j-j1]);
                    }
                    dp[i][j] = max;

                }

                for(int j=k*(i+1);j<p;j++)
                {
                    dp[i][j] = dp[i][j-1];
                }
            }

            sb.append(dp[n-1][p-1]).append("\n");
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
            byte[] buf = new byte[100064]; // line length
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
