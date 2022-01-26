package Spoj;

import java.io.*;
import java.util.*;

public class PrimeOrNot {

    static ArrayList<Boolean> isPrime;
    static ArrayList<Integer> primes;
    static ArrayList<Integer> spf;
    static void preComputePrimes(int n){
        n += 10;
        isPrime = new ArrayList<>(n);
        primes = new ArrayList<>();
        spf = new ArrayList<>(n);

        for(int i=0;i<n;i++){
            isPrime.add(true);
            spf.add(2);
        }

        isPrime.set(0, false);
        isPrime.set(1, false);

        for(int i=2;i<n;i++){
            if(isPrime.get(i)){
                spf.set(i, i);
                primes.add(i);
            }

            for(int j=0;j<primes.size() && primes.get(j) <= spf.get(i) && primes.get(j)*i<n;j++){
                isPrime.set(primes.get(j)*i, false);
                spf.set(primes.get(j)*i, primes.get(j));
            }
        }
    }

    static long mul(long a, long b, long mod){
        long s = 0;
        while(b > 0){
            if(b%2==1){
                s = (s + a)%mod;
            }
            a = (a + a)%mod;
            b /= 2;
        }

        return s;
    }

    static long pow(long a, long b, long mod){
        long p = 1;
        while(b > 0){
            if(b%2==1){
                p = mul(p, a, mod);
            }
            a = mul(a, a, mod);
            b /= 2;
        }

        return p;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        long[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            long n = sc.nextLong();

            if(n==1){
                sb.append("NO\n");
                continue;
            }
            else if(n==2){
                sb.append("YES\n");
                continue;
            }
            else if(n%2==0){
                sb.append("NO\n");
                continue;
            }
            else{
                boolean flag = false;
                for(long l: primes){
                    if(l == n){
                        sb.append("YES\n");
                        flag = true;
                        break;
                    }
                }

                if(flag)
                    continue;
            }

            int s = 0;
            long d = n-1;
            while(d%2==0){
                d /= 2;
                s++;
            }

            boolean isPrime = true;
            for(int test = 0;test < 1000;test++){
                long l = (long) (Math.random()*10000);
                if(l > n-2)
                    break;

                boolean flag = false;
                long x0 = pow(l, d, n);
                if(x0 == 1 || x0 == n-1){
                    break;
                }

                for(int i=1;i<s;i++){
                    d = d * 2;
                    if(pow(l, d, n) == (n-1)){
                        flag = true;
                        break;
                    }
                }

                if(!flag){
                    isPrime = false;
                    break;
                }
            }

            if(isPrime){
                sb.append("YES\n");
            }
            else {
                sb.append("NO\n");
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
