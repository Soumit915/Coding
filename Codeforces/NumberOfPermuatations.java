package Codeforces;

import java.util.*;
import java.io.*;

public class NumberOfPermuatations {

    static class Pair {
        int a, b;

        Pair(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    static long mod = 998244353;

    static boolean isValid(Pair[] pairs){
        for(int i=1;i<pairs.length;i++){
            if(pairs[i].a < pairs[i-1].a || pairs[i].b < pairs[i-1].b)
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        Pair[] pairs = new Pair[n];

        int[] ca = new int[n+2];
        int[] cb = new int[n+2];

        for(int i=0;i<n;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            pairs[i] = new Pair(a, b);

            ca[a]++;
            cb[b]++;
        }

        Arrays.sort(pairs, (p1, p2) -> Integer.signum((p1.a - p2.a)==0?p1.b-p2.b:p1.a-p2.a));

        long[] fact = new long[n+5];
        fact[0] = 1;
        for(int i=1;i<fact.length;i++){
            fact[i] = ( fact[i-1] * i ) % mod;
        }

        long totcombs = fact[n];

        long sorted_a = 1, sorted_b = 1;
        for(int i=0;i<=n;i++){
            long cur_combs_a = fact[ca[i]];
            long cur_combs_b = fact[cb[i]];

            sorted_a = (sorted_a * cur_combs_a) % mod;
            sorted_b = (sorted_b * cur_combs_b) % mod;
        }

        long both_sorted = 0;
        if(isValid(pairs)){
            both_sorted = 1;

            int c = 1;
            for(int i=1;i<n;i++){
                if(pairs[i].a == pairs[i-1].a && pairs[i].b == pairs[i-1].b){
                    c++;
                }
                else{
                    both_sorted = (both_sorted * fact[c]) % mod;
                    c = 1;
                }
            }

            both_sorted = (both_sorted * fact[c]) % mod;
        }

        long ans = ((totcombs - sorted_a - sorted_b + both_sorted) % mod + mod) % mod;
        System.out.println(ans);

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
