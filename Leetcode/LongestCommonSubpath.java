package Leetcode;

import java.io.*;
import java.util.*;

public class LongestCommonSubpath {

    static long mod1 = (long) 1e11 + 7;
    static long base1 = 100001;

    static long mod2 = 119218851371L;
    static long base2 = (long) 1e5 + 3;

    static long[] pow1;
    static long[] pow2;
    public void preComputePow(int n){
        pow1 = new long[n];
        pow1[0] = 1;
        for(int i=1;i<n;i++){
            pow1[i] = (pow1[i-1] * base1)%mod1;
        }

        pow2 = new long[n];
        pow2[0] = 1;
        for(int i=1;i<n;i++){
            pow2[i] = (pow2[i-1] * base2)%mod2;
        }
    }

    static boolean isValid(int[][] path, int size, long base, long mod, long[] pow){
        int m = path.length;

        long hash = 0;
        Set<Long> commonSet = new HashSet<>();
        for(int i=0;i<size;i++){
            hash = ((hash*base)%mod + path[0][i])%mod;
        }
        commonSet.add(hash);

        for(int i=size;i<path[0].length;i++){
            hash = ((hash*base)%mod - (path[0][i - size] * pow[size])%mod + mod)%mod;
            hash = (hash + path[0][i])%mod;
            commonSet.add(hash);
        }

        for(int i=1;i<m;i++){
            hash = 0;
            Set<Long> set = new HashSet<>();
            for(int j=0;j<size;j++){
                hash = ((hash*base)%mod + path[i][j])%mod;
            }
            if(commonSet.contains(hash))
                set.add(hash);

            for(int j=size;j<path[i].length;j++){
                hash = ((hash*base)%mod - (path[i][j - size] * pow[size])%mod + mod)%mod;
                hash = (hash + path[i][j])%mod;

                if(commonSet.contains(hash))
                    set.add(hash);
            }

            commonSet = set;
            if(commonSet.size() == 0)
                return false;
        }

        return commonSet.size() > 0;
    }

    public int longestCommonSubpath(int n, int[][] paths) {
        preComputePow(100005);

        int min = 10000000;
        for (int i=0;i<paths.length;i++) {
            for(int j=0;j<paths[i].length;j++){
                paths[i][j] = paths[i][j] + 1;
            }
            min = Math.min(min, paths[i].length);
        }


        int ll = 0, ul = min;
        while(ll < ul){
            int mid = (ll + ul + 1)/2;
            if(isValid(paths, mid, base1, mod1, pow1)){
                ll = mid;
            }
            else{
                ul = mid - 1;
            }
        }

        return ll;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();


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
