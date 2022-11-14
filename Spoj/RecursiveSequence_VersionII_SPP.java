package Spoj;

import java.io.*;
import java.util.*;

public class RecursiveSequence_VersionII_SPP {
    static long mod;
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
    static long getSum(long[][] transformation_mat, long[] b, long pow){
        int k = transformation_mat.length;
        if(pow<=0){
            long sum = 0;
            for(int i = 1; i<=pow+k-1; i++){
                sum = (sum+b[i])%mod;
            }
            return sum;
        }

        long[][] tnmat_copy = new long[k][k];
        for(int i=0;i<k;i++){
            System.arraycopy(transformation_mat[i], 0, tnmat_copy[i], 0, k);
        }

        long[][] tn = pow(tnmat_copy, pow);

        long ans = 0;
        for(int i=0;i<k;i++){
            ans = (ans+(tn[0][i]*b[i])%mod)%mod;
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
            long m = sc.nextLong();
            long n = sc.nextLong();
            mod = sc.nextLong();

            long[] d = new long[k+1];
            long sum = 0;
            for(int i=k;i>=1;i--){
                d[i] = b[i-1];
                sum += d[i];
            }
            d[0] = sum%mod;
            b = d;

            if(n<=k){
                sum = 0;
                for(int i = (int) m; i<=n; i++){
                    sum = (sum+b[i])%mod;
                }
                sb.append(sum).append("\n");
                continue;
            }

            long[][] transformation_mat = new long[k+1][k+1];
            transformation_mat[0][0] = 1;
            for(int i=1;i<k;i++){
                for(int j=0;j<=k;j++){
                    if(j==i+1)
                        transformation_mat[i][j] = 1;
                    else transformation_mat[i][j] = 0;
                }
            }
            for(int i=1;i<=k;i++){
                transformation_mat[k][i] = c[k-i];
                transformation_mat[0][i] = c[k-i];
            }

            long sum_n = getSum(transformation_mat, b, n-k);
            long sum_m = getSum(transformation_mat, b, m-k-1);
            long ans = (sum_n-sum_m+mod)%mod;
            
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
