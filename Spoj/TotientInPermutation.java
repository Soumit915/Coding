package Spoj;

import java.io.*;
import java.util.*;

public class TotientInPermutation {

    static class Pair{
        long n;
        long etf;
        Pair(int n, int etf){
            this.n = n;
            this.etf = etf;
        }
        public Pair compare(Pair p){
            long c = this.n*p.etf - this.etf*p.n;
            if(c >= 0){
                return p;
            }
            else {
                return this;
            }
        }
    }

    static boolean isPermutation(int a, int b){
        int[] hasha = new int[10];
        int[] hashb = new int[10];

        while (a != 0 || b != 0) {

            if (a == 0)
                return false;
            if (b == 0)
                return false;

            hasha[a % 10]++;
            hashb[b % 10]++;

            a /= 10;
            b /= 10;
        }

        for(int i=0;i<10;i++){
            if(hasha[i]!=hashb[i])
                return false;
        }

        return true;
    }

    static Pair[] permut_ETF;
    static void preCompute(int n){
        int[] etf = new int[n];
        for(int i=1;i<n;i++){
            etf[i] += i;
            for(int j=2*i;j<n;j+=i){
                etf[j] -= etf[i];
            }
        }

        permut_ETF = new Pair[n];
        for(int i=2;i<n;i++){
            int cur_etf = etf[i];

            if(isPermutation(i, cur_etf)){
                if(permut_ETF[i-1]==null)
                    permut_ETF[i] = new Pair(i, cur_etf);
                else permut_ETF[i] = permut_ETF[i-1].compare(new Pair(i, cur_etf));
            }
            else{
                permut_ETF[i] = permut_ETF[i-1];
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit();

        Integer[] arr = {75841, 2817, 4198273, 474883, 1288663, 1924891, 20617, 400399, 783169,
                2044501, 2710627, 7507321, 6636841, 3582907, 4696009, 2006737, 1014109, 4435, 21,
                8316907, 69271, 1956103, 3689251, 7357291, 2868469, 7026037, 291, 5380657, 1514419,
                1504051, 778669, 6018163, 2094901, 45421, 2991, 5050429, 8319823, 732031, 5886817,
                162619, 284029, 176569, 63, 2239261};

        TreeSet<Integer> tree = new TreeSet<>(Arrays.asList(arr));

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();

            if(tree.floor(n)==null)
                sb.append("No solution\n");
            else sb.append(tree.floor(n)).append("\n");
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
