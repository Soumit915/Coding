package Spoj;

import java.io.*;
import java.util.*;

public class FiboSum {
    static long mod = (long) 1e9+7;
    static long[][] multiply(long[][] a, long[][] b){
        long[][] c = new long[3][3];

        c[0][0] = ((a[0][0]*b[0][0])%mod + (a[0][1]*b[1][0])%mod + (a[0][2]*b[2][0])%mod)%mod;
        c[0][1] = ((a[0][0]*b[0][1])%mod + (a[0][1]*b[1][1])%mod + (a[0][2]*b[2][1])%mod)%mod;
        c[0][2] = ((a[0][0]*b[0][2])%mod + (a[0][1]*b[1][2])%mod + (a[0][2]*b[2][2])%mod)%mod;

        c[1][0] = ((a[1][0]*b[0][0])%mod + (a[1][1]*b[1][0])%mod + (a[1][2]*b[2][0])%mod)%mod;
        c[1][1] = ((a[1][0]*b[0][1])%mod + (a[1][1]*b[1][1])%mod + (a[1][2]*b[2][1])%mod)%mod;
        c[1][2] = ((a[1][0]*b[0][2])%mod + (a[1][1]*b[1][2])%mod + (a[1][2]*b[2][2])%mod)%mod;

        c[2][0] = ((a[2][0]*b[0][0])%mod + (a[2][1]*b[1][0])%mod + (a[2][2]*b[2][0])%mod)%mod;
        c[2][1] = ((a[2][0]*b[0][1])%mod + (a[2][1]*b[1][1])%mod + (a[2][2]*b[2][1])%mod)%mod;
        c[2][2] = ((a[2][0]*b[0][2])%mod + (a[2][1]*b[1][2])%mod + (a[2][2]*b[2][2])%mod)%mod;

        return c;
    }
    static long[][] pow(long[][] a, long b){
        long[][] p = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        while (b>0){
            if(b%2==1){
                p = multiply(p, a);
            }
            a = multiply(a, a);
            b /= 2;
        }
        return p;
    }
    static long getSum(long n){
        if(n==0)
            return 0;
        else if(n==1)
            return 1;
        long[][] mat = {{0, 1, 0}, {1, 1, 0}, {1, 1, 1}};

        mat = pow(mat, n-1);
        return ((mat[2][1]+mat[2][2])%mod)%mod;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            long n = sc.nextLong();
            long m = sc.nextLong();

            long nsum = 0;
            if(n>0)
                nsum = getSum(n-1);
            long msum = getSum(m);

            long ans = (msum-nsum+mod)%mod;
            sb.append(ans).append("\n");
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
