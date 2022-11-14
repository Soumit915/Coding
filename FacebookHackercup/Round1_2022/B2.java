package FacebookHackercup.Round1_2022;

import java.util.*;
import java.io.*;

public class B2 {

    static long mod = (long) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output.txt");

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int ti=1;ti<=tc;ti++){

            sb.append("Case #").append(ti).append(": ");

            int n = sc.nextInt();

            long[][] trees = new long[n][2];

            long trees_x_sum_square = 0;
            long trees_x_sum = 0;

            long trees_y_sum_square = 0;
            long trees_y_sum = 0;

            for(int i=0;i<n;i++){
                trees[i][0] = sc.nextLong();
                trees[i][1] = sc.nextLong();

                trees_x_sum_square = (trees_x_sum_square + ((trees[i][0] * trees[i][0]) % mod) ) % mod;
                trees_x_sum = (trees_x_sum + trees[i][0]) % mod;

                trees_y_sum_square = (trees_y_sum_square + ((trees[i][1] * trees[i][1]) % mod) ) % mod;
                trees_y_sum = (trees_y_sum + trees[i][1]) % mod;
            }

            int q = sc.nextInt();

            long[][] wells = new long[q][2];
            long tot = 0;
            for(int i=0;i<q;i++){
                wells[i][0] = sc.nextLong();
                wells[i][1] = sc.nextLong();

                long wells_x_sum_square = (n * ((wells[i][0] * wells[i][0]) % mod)) % mod;
                long wells_y_sum_square = (n * ((wells[i][1] * wells[i][1]) % mod)) % mod;

                long sop_x = (2 * ((wells[i][0] * trees_x_sum) % mod)) % mod;
                long sop_y = (2 * ((wells[i][1] * trees_y_sum) % mod)) % mod;

                long ans_x = (wells_x_sum_square - sop_x + trees_x_sum_square + mod) % mod;
                long ans_y = (wells_y_sum_square - sop_y + trees_y_sum_square + mod) % mod;
                long ans = (ans_x + ans_y) % mod;

                tot = (tot + ans) % mod;
            }

            sb.append(tot).append("\n");
        }

        sc.println(sb.toString());

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
