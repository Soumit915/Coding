package Codeforces;

import java.io.*;
import java.util.*;

public class TylerAndStrings {

    static int getNextPowerOf2(int n){
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n |= n >> 25;

        return n + 1;
    }

    static class Node{
        long count;
        long combinations;
    }

    static void build(Node[] segTree, int si, int[] arr, int l, int r){
        if(l == r){
            segTree[si] = new Node();

            segTree[si].count = arr[l];
            segTree[si].combinations = inverse[arr[l]];

            return;
        }

        int mid = (l + r) / 2;
        build(segTree, 2*si + 1, arr, l, mid);
        build(segTree, 2*si + 2, arr, mid + 1, r);

        segTree[si] = new Node();
        segTree[si].count = (segTree[2*si + 1].count + segTree[2*si + 2].count) % mod;
        segTree[si].combinations = (segTree[2*si + 1].combinations * segTree[2*si + 2].combinations) % mod;
    }

    static void update(Node[] segTree, int si, int index, int l, int r){
        if(l == r){

            segTree[si].count = segTree[si].count-1;
            segTree[si].combinations = inverse[(int) segTree[si].count];

            return;
        }

        int mid = (l + r) / 2;
        if(index <= mid){
            update(segTree, 2*si + 1, index, l, mid);
        }
        else{
            update(segTree, 2*si + 2, index, mid + 1, r);
        }
        segTree[si].count = (segTree[2*si + 1].count + segTree[2*si + 2].count) % mod;
        segTree[si].combinations = (segTree[2*si + 1].combinations * segTree[2*si + 2].combinations) % mod;
    }

    static long query_count(Node[] segTree, int si, int start, int end, int l, int r){
        //no overlap
        if(r < start || l > end){
            return 0;
        }

        //total overlap
        if(l>=start && r<=end){
            return segTree[si].count;
        }

        //partial overlap
        int mid = (l + r) / 2;
        return (query_count(segTree, 2*si + 1, start, end, l, mid) +
                query_count(segTree, 2*si + 2, start, end, mid + 1, r)) % mod;
    }

    static long mod = 998244353;

    static long x, y;
    static void gcdExtended(long a, long b){
        if(a%b == 0){
            x = 1;
            y = 1 - (a / b);
            return;
        }
        gcdExtended(b, a%b);
        long t = y;
        y = x - ((a / b) * y) % mod;
        x = t;
    }
    static long modInverse(long a){
        gcdExtended(a, mod);
        x = (x%mod + mod)%mod;
        return x;
    }

    static long[] fact;
    static long[] inverse;

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = sc.nextIntArray(n);
        int[] b = sc.nextIntArray(m);

        fact = new long[n+2];
        inverse = new long[n+2];

        fact[0] = 1;
        inverse[0] = 1;

        for(int i=1;i<fact.length;i++){
            fact[i] = (fact[i-1] * i) % mod;
            inverse[i] = (modInverse(i) * inverse[i-1]) % mod;
        }

        int hn = 200010;
        int[] hash = new int[hn];
        for(int i=0;i<n;i++)
            hash[a[i]]++;

        int sn = 2 * getNextPowerOf2(hn) - 1;
        Node[] segTree = new Node[sn];
        build(segTree, 0, hash, 0, hn-1);

        long[] dp = new long[m];
        long sum = 0;
        for(int i=0;i<Math.min(n, m);i++){
            long factorial = fact[(int) segTree[0].count - 1];
            long querycount = query_count(segTree, 0, 0, b[i]-1, 0, hn-1);
            long num = (factorial * querycount) % mod;
            long deno = segTree[0].combinations;

            dp[i] = (num * deno) % mod;
            sum = (sum + dp[i])% mod;

            if(query_count(segTree, 0, b[i], b[i], 0, hn-1) > 0)
                update(segTree, 0, b[i], 0, hn-1);
            else break;
        }

        if(n < m){
            boolean flag = true;
            for(int i=0;i<n;i++){
                if(hash[b[i]] == 0){
                    flag = false;
                    break;
                }
                hash[b[i]]--;
            }

            if(flag)
                sum = (sum + 1) % mod;
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
