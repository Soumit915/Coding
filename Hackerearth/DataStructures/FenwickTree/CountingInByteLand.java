package Hackerearth.DataStructures.FenwickTree;

import java.util.*;
import java.io.*;

public class CountingInByteLand {

    static void update(long[][][] bit, int x, int y, int z, long val){
        int n = bit.length;

        if(x <= 0 || y <= 0 || z <= 0)
            return;

        while(x <= n){
            int y1 = y;
            while(y1 <= n){
                int z1 = z;
                while(z1 <= n){
                    bit[x-1][y1-1][z1-1] += val;
                    z1 += (z1 & (-z1));
                }

                y1 += (y1 & (-y1));
            }

            x += (x & (-x));
        }
    }

    static long query(long[][][] bit, int x, int y, int z){
        long sum = 0;

        if(x <= 0 || y <= 0 || z <= 0)
            return 0;

        while(x > 0){
            int y1 = y;
            while(y1 > 0){
                int z1 = z;
                while(z1 > 0){
                    sum += bit[x-1][y1-1][z1-1];
                    z1 -= (z1 & (-z1));
                }

                y1 -= (y1 & (-y1));
            }

            x -= (x & (-x));
        }

        return sum;
    }

    static long query(long[][][] bit, int x1, int x2, int y1, int y2, int z1, int z2){
        long v1 = query(bit, x2, y2, z2);
        long v2 = query(bit, x2, y1-1, z1-1) + query(bit, x1-1, y2, z1-1) + query(bit, x1-1, y1-1, z2);
        long v3 = query(bit, x2, y2, z1-1) + query(bit, x2, y1-1, z2) + query(bit, x1-1, y2, z2);
        long v4 = query(bit, x1-1, y1-1, z1-1);

        return v1 + v2 - v3 - v4;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        long[][][] bit = new long[n+1][n+1][n+1];

        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        long tot = 0;
        while (q-->0){
            int type = sc.nextInt();

            int x = sc.nextInt() + 1;
            int y = sc.nextInt() + 1;
            int z = sc.nextInt() + 1;

            if(type == 1){
                long val = sc.nextLong();
                tot += val;

                update(bit, x, y, z, val);
            }
            else{
                int X = sc.nextInt() + 1;
                int Y = sc.nextInt() + 1;
                int Z = sc.nextInt() + 1;

                long residing_count = query(bit, x, X, y, Y, z, Z);
                long not_residing_count = tot - residing_count;

                sb.append(not_residing_count).append("\n");
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
