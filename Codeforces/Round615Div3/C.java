package Codeforces.Round615Div3;

import java.io.*;
import java.util.*;

public class C {
    static ArrayList<Boolean> isPrime;
    static ArrayList<Integer> primes;
    static ArrayList<Integer> spf;
    static void preComputePrimes(){
        int n = 100000;

        isPrime = new ArrayList<>();
        primes = new ArrayList<>();
        spf = new ArrayList<>();

        for(int i=0;i<n;i++){
            isPrime.add(true);
            spf.add(2);
        }

        isPrime.set(0, false);
        isPrime.set(1, false);
        for(int i=2;i<n;i++){
            if(isPrime.get(i)){
                primes.add(i);
                spf.set(i, i);
            }

            for(int j=0;j<primes.size() && primes.get(j)*i<n && primes.get(j)<=spf.get(i);j++){
                isPrime.set(primes.get(j)*i, false);
                spf.set(primes.get(j)*i, primes.get(j));
            }
        }
    }
    static HashMap<Integer, Integer> getFactorMap(long n){
        HashMap<Integer, Integer> factor_map = new HashMap<>();
        for(int i: primes){
            if(n%i==0){
                int c = 0;
                while(n%i==0){
                    c++;
                    n/=i;
                }
                factor_map.put(i, c);
            }
        }

        if(n>1){
            factor_map.put((int) n, 1);
        }
        return factor_map;
    }
    static void solve(long n, StringBuilder sb){
        HashMap<Integer, Integer> factor_map = getFactorMap(n);

        if(factor_map.keySet().size()>2){
            ArrayList<Integer> factorlist = new ArrayList<>(factor_map.keySet());
            long a = factorlist.get(0);
            long b = factorlist.get(1);
            long c = n/(a*b);
            sb.append("YES\n");
            sb.append(a).append(" ").append(b).append(" ").append(c).append("\n");
        }
        else if(factor_map.keySet().size()==2){
            ArrayList<Integer> factorlist = new ArrayList<>(factor_map.keySet());
            long a = factorlist.get(0);
            long b = factorlist.get(1);
            long c = n/(a*b);
            if(c==1 || c==a || c==b){
                sb.append("NO\n");
            }
            else{
                sb.append("YES\n");
                sb.append(a).append(" ").append(b).append(" ").append(c).append("\n");
            }
        }
        else{
            ArrayList<Integer> factorlist = new ArrayList<>(factor_map.keySet());
            long a = factorlist.get(0);
            int count = factor_map.get((int) a);
            if(count>=6){
                long b = a*a;
                long c = n/(a*b);
                sb.append("YES\n");
                sb.append(a).append(" ").append(b).append(" ").append(c).append("\n");
            }
            else{
                sb.append("NO\n");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();
        preComputePrimes();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            solve(sc.nextLong(), sb);
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
