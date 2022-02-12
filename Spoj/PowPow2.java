package Spoj;

import java.io.*;
import java.util.*;

public class PowPow2 {

    static long x, y;
    static void gcdExtended(long a, long b, long mod){
        if(a%b==0){
            x = 1;
            y = 1 - (a/b);
            return;
        }
        gcdExtended(b, a%b, mod);
        long t = y;
        y = x - ((a/b) * y)%mod;
        x = t;
    }
    static long modInverse(long a, long b){
        if(b==1681){
            for(int i=1;i<b;i++){
                if((a * i)%b == 1)
                    return i;
            }
        }

        gcdExtended(a, b, b);
        x = (x%b + b)%b;

        return x;
    }

    static long[] fact2;

    static long[] fact1681;
    static long[] fact1681_p;

    static long[] fact148721;

    static void preCompute(int n){
        fact2 = new long[n];
        fact2[0] = 1;

        fact1681 = new long[n];
        fact1681_p = new long[n];
        fact1681[0] = 1;
        fact1681_p[0] = 0;

        fact148721 = new long[n];
        fact148721[0] = 1;

        for(int i=1;i<n;i++){
            fact2[i] = (fact2[i-1] * i)%2;
            //inverse2[i] = modInverse(fact2[i], 2);

            int t = i;
            fact1681_p[i] = fact1681_p[i-1];
            while(t%41==0){
                t /= 41;
                fact1681_p[i]++;
            }
            fact1681[i] = (fact1681[i-1] * t)%1681;
            //inverse1681[i] = modInverse(fact1681[i], 1681);

            fact148721[i] = (fact148721[i-1] * i)%148721;
            //inverse148721[i] = modInverse(fact148721[i], 148721);
        }
    }

    static long getExponent(long num, long deno){
        long rem2 = (fact2[(int) num] * modInverse((fact2[(int) deno] * fact2[(int) deno])%2, 2))%2;
        long rem1681 = (fact1681[(int) num] * modInverse((fact1681[(int) deno] * fact1681[(int) deno])%1681, 1681))%1681;
        rem1681 = ((pow(41, fact1681_p[(int) num] - fact1681_p[(int) deno] - fact1681_p[(int) deno], 1681)) * rem1681)%1681;
        long rem148721 = (fact148721[(int) num] * modInverse((fact148721[(int) deno] * fact148721[(int) deno])%148721, 148721))%148721;

        long prod = (long) 5e8 + 2;

        long exp = (rem2 * (prod / 2) * modInverse(prod / 2, 2))%prod;
        exp = (exp + (rem1681 * (prod / 1681) * modInverse(prod / 1681, 1681))%prod)%prod;
        exp = (exp + (rem148721 * (prod / 148721) * modInverse(prod / 148721, 148721))%prod)%prod;

        return exp;
    }

    static long pow(long a, long b, long mod){
        long p = 1;
        while(b > 0){
            if(b%2==1)
                p = (p * a)%mod;
            a = (a * a)%mod;
            b /= 2;
        }

        return p;
    }

    static long getBPow(long a, long b){
        long rem2 = pow(a, 0, 2);
        long rem5108 = pow(a, b, 500000003);

        long prod = (long) 1e9 + 6;

        long exp = (rem2 * (prod / 2) * modInverse(prod / 2, 2))%prod;
        exp = (exp + (rem5108 *
                (prod / 500000003) * modInverse(prod / 500000003, 500000003))%prod)%prod;

        return exp;
    }

    public static void main(String[] args) throws IOException {
        /*Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");*/

        Soumit sc = new Soumit();

        preCompute(200100);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t-->0){
            long a = sc.nextLong();
            long b = sc.nextLong();
            long n = sc.nextLong();

            if(a==0 && b==0){
                sb.append("1\n");
                continue;
            }

            if(b==0){
                sb.append("1\n");
                continue;
            }

            long exp = getExponent(2L*n, n);
            long bPow = getBPow(b, exp);
            long ans = pow(a, bPow, 1000000007);

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());

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
