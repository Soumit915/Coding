package Codeforces;

import java.io.*;
import java.util.*;

public class TheFootballSeason {

    static long gcd(long a, long b){
        if(a%b==0)
            return b;
        else return gcd(b, a%b);
    }

    static long x, y;
    static void gcdExtended(long a, long b, long mod){
        if(a%b==0){
            x = 1;
            y = 1 - (a / b);
            return;
        }
        gcdExtended(b, a%b, mod);
        long t = y;
        y = x - ((a / b) * y)%mod;
        x = t;
    }
    static long modInverse(long a, long b){
        gcdExtended(a, b, b);
        x = (x%b + b)%b;
        return x;
    }

    static long mul(long a, long b, long mod){
        long m = 0;
        while (b > 0){
            if(b%2==1){
                m = (m + a)%mod;
            }
            a = (a + a)%mod;
            b /= 2;
        }

        return m;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        long n = sc.nextLong();
        long p = sc.nextLong();
        long w = sc.nextLong();
        long d = sc.nextLong();

        long gcd = gcd(w, d);
        if(p%gcd==0){
            p /= gcd;
            w /= gcd;
            d /= gcd;

            long wins = mul(modInverse(w, d), p, d)%d;
            long draws = (p - wins * w) / d;

            long min_k;
            if(wins < 0){
                min_k = (-wins + d - 1) / d;
            }
            else{
                min_k = ((wins) / d) * -1;
            }

            long max_k;
            if(draws < 0){
                max_k = ((-draws + w - 1) / w) * -1;
            }
            else{
                max_k = (draws / w);
            }

            if(max_k < min_k){
                System.out.println(-1);
            }
            else{
                long ans_wins = wins + (max_k * d);
                long ans_draws = draws - (max_k * w);
                if(ans_draws + ans_wins > n){
                    System.out.println(-1);
                }
                else{
                    System.out.println(ans_wins+" "+ans_draws+" "+(n - ans_draws - ans_wins));
                }
            }
        }
        else{
            System.out.println(-1);
        }

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
