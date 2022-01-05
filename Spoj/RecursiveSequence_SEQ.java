package Spoj;

import java.io.*;
import java.util.*;

public class RecursiveSequence_SEQ {
    static long mod = (long) 1e9;
    static long[][] multiply(long[][] a, long[][] b){
        int k = a.length;
        long[][] prod = new long[k][k];

        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                long p = 0;
                for(int j1=0;j1<k;j1++){
                    p = (p+(a[i][j1]*b[j1][j])%mod)%mod;
                }
                prod[i][j] = p;
            }
        }
        return prod;
    }
    static long[][] pow(long[][] mat, long b){
        int k = mat.length;
        long[][] p = new long[k][k];
        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                if(i==j)
                    p[i][j] = 1;
                else p[i][j] = 0;
            }
        }

        while(b>0){
            if(b%2==1){
                p = multiply(p, mat);
            }
            mat = multiply(mat, mat);
            b /= 2;
        }
        return p;
    }
    static long getNth(long[][] transformation_mat, long[] b, long n, int k){
        long[][] tn = pow(transformation_mat, n-k);

        long ans = 0;
        for(int i=0;i<k;i++){
            ans = (ans+(tn[k-1][i]*b[i])%mod)%mod;
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int k = sc.nextInt();
            long[] b = sc.nextLongArray(k);
            long[] c = sc.nextLongArray(k);
            long n = sc.nextLong();

            if(n<=k){
                sb.append(b[(int) (n-1)]).append("\n");
                continue;
            }

            long[][] transformation_mat = new long[k][k];
            for(int i=0;i<k-1;i++){
                for(int j=0;j<k;j++){
                    if(j==i+1)
                        transformation_mat[i][j] = 1;
                    else transformation_mat[i][j] = 0;
                }
            }
            for(int i=0;i<k;i++){
                transformation_mat[k-1][i] = c[k-i-1];
            }

            long ans = getNth(transformation_mat, b, n, k);
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
