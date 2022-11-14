package Codeforces;

import java.io.*;
import java.util.*;

public class The_Intriguing_Obsession {
    static long mod = 998244353;
    static long x, y;
    static void gcdExtended(long a, long b){
        if(a%b==0){
            x = 1;
            y = 1 - (a/b);
            return;
        }
        gcdExtended(b, a%b);
        long t = y;
        y = x - ((a/b)*y)%mod;
        x = t;
    }
    static long moduloInverse(long a){
        gcdExtended(a, mod);
        x = (x%mod + mod)%mod;
        return x;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();

        long[] acr = new long[(int) a+1];
        long[] bcr = new long[(int) b+1];
        long[] ccr = new long[(int) c+1];
        long[] fact = new long[Math.max(acr.length, Math.max(bcr.length, ccr.length))];

        acr[0] = 1; bcr[0] = 1; ccr[0] = 1;
        for(int i=1;i<=a;i++){
            acr[i] = (((acr[i-1] * (a - i + 1))%mod) * moduloInverse(i))%mod;
        }
        for(int i=1;i<=b;i++){
            bcr[i] = (((bcr[i-1] * (b - i + 1))%mod) * moduloInverse(i))%mod;
        }
        for(int i=1;i<=c;i++){
            ccr[i] = (((ccr[i-1] * (c - i + 1))%mod) * moduloInverse(i))%mod;
        }

        fact[0] = 1;
        for(int i=1;i<fact.length;i++){
            fact[i] = (fact[i-1] * i)%mod;
        }

        long edgeab = 0;
        long edgebc = 0;
        long edgeca = 0;
        for(int i=0;i<=Math.min(a, b);i++){
            edgeab = (edgeab + ((acr[i] * bcr[i])%mod * fact[i])%mod)%mod;
        }
        for(int i=0;i<=Math.min(b, c);i++){
            edgebc = (edgebc + ((bcr[i] * ccr[i])%mod * fact[i])%mod)%mod;
        }
        for(int i=0;i<=Math.min(c, a);i++){
            edgeca = (edgeca + ((ccr[i] * acr[i])%mod * fact[i])%mod)%mod;
        }

        long ans = (((edgeab * edgebc)%mod) * edgeca)%mod;
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
