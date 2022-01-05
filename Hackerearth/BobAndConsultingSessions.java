package Hackerearth;

import java.io.*;
import java.util.*;

public class BobAndConsultingSessions {
    static long mod = (long) 1e9+7;
    static long[] facts;
    static long[] inverse;
    static long x, y;
    static void gcdExtended(long a, long b){
        if(a%b==0){
            x = 1;
            y = 1-(a/b);
            return;
        }
        gcdExtended(b, a%b);
        long t = y;
        y = x - ((a/b)*y)%mod;
        x = t;
    }
    static long modInverse(long a){
        gcdExtended(a, mod);
        x = (x%mod+mod)%mod;
        return x;
    }
    static void preCompute(long n){
        facts = new long[(int) n+10];
        inverse = new long[(int) n+10];

        facts[0] = 1;
        inverse[0] = modInverse(1);

        for(long i=1;i<facts.length;i++){
            facts[(int) i] = (facts[(int) i-1]*i)%mod;
            inverse[(int) i] = modInverse(facts[(int) i])%mod;
        }
    }
    static long pow(long b){
        long pow = 1;
        long a = 2;
        while(b>0){
            if(b%2==1){
                pow = (pow*a)%mod;
            }
            a = (a*a)%mod;
            b /= 2;
        }
        return pow;
    }
    static long nCr(long n, long r){
        if(r==n || r==0)return 1;
        if(n<r)return 0;
        long num = facts[(int) n];
        long deno = (facts[(int) r] * facts[(int) (n-r)])%mod;
        deno = modInverse(deno);
        return (num*deno)%mod;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        long n = sc.nextLong();
        preCompute(n);

        long sum = 0;
        for(long i=n;i>=0;i--){
            long cur = (pow(((i*(i-1))/2)%(mod-1)) * nCr(n, i))%mod;
            if((n-i)%2==0){
                sum = (sum + cur)%mod;
            }
            else{
                sum = (sum - cur + mod)%mod;
            }
        }

        System.out.println(sum);

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
