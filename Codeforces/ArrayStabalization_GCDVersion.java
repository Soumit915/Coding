package Codeforces;

import java.io.*;
import java.util.*;

public class ArrayStabalization_GCDVersion {

    static int gcd(int a, int b){
        if(a%b==0)
            return b;
        else return gcd(b, a%b);
    }

    static int getNextPowerOf2(int n){
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>25);

        return n+1;
    }

    static void build(int[] segTree, int si, int[] arr, int ll, int ul){
        if(ll == ul){
            segTree[si] = arr[ll];
            return;
        }

        int mid = (ll + ul)/2;
        build(segTree, 2*si+1, arr, ll, mid);
        build(segTree, 2*si+2, arr, mid+1, ul);
        segTree[si] = gcd(segTree[2*si+1], segTree[2*si+2]);
    }

    static class Pair{
        int index;
        int val;
        Pair(int index, int val){
            this.index = index;
            this.val = val;
        }
    }

    static Pair query(int[] segTree, int si, int gcd, int start, int end, int ll, int ul){
        //no overlap
        if(ll>end || ul<start)
            return new Pair(-1, reqGCD);

        int mid = (ll + ul)/2;

        //total overlap
        if(ll>=start && ul<=end){
            if(gcd(gcd, segTree[si])==reqGCD){
                if(ll==ul){
                    return new Pair(ll-1, reqGCD);
                }

                if(gcd(gcd, segTree[2*si+1])==reqGCD){
                    return query(segTree, 2*si+1, gcd, start, end, ll, mid);
                }
                else{
                    return query(segTree, 2*si+2, gcd(gcd, segTree[2*si+1]), start, end, mid+1, ul);
                }
            }
            else return new Pair(ul, gcd(gcd, segTree[si]));
        }

        //partial overlap
        Pair left = query(segTree, 2*si+1, gcd, start, end, ll, mid);
        if(left.index==-1){
            return query(segTree, 2*si+2, gcd, start, end, mid+1, ul);
        }

        if(left.val==reqGCD){
            return left;
        }

        Pair right = query(segTree, 2*si+2, left.val, start, end, mid+1, ul);
        if(right.val==-1)
            return left;
        else return right;
    }

    static int reqGCD;

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int[] arr = sc.nextIntArray(n);

            int[] dp = new int[n];
            dp[n-1] = arr[n-1];
            for(int i=n-2;i>=0;i--){
                dp[i] = gcd(dp[i+1], arr[i]);
            }

            reqGCD = dp[0];

            int sn = 2*getNextPowerOf2(n) - 1;
            int[] segTree = new int[sn];
            build(segTree, 0, arr, 0, n-1);

            int max = 0;
            for(int i=0;i<n;i++){
                Pair p;
                if(dp[i]==reqGCD)
                    p = query(segTree, 0, arr[i], i, n-1, 0, n-1);
                else {
                    if(gcd(dp[i], arr[0])!=reqGCD)
                        p = query(segTree, 0, dp[i], 0, i - 1, 0, n - 1);
                    else p = new Pair(-1, 0);
                }

                max = Math.max(max, ((p.index + 1 - i + n)%n));
            }

            sb.append(max).append("\n");
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
