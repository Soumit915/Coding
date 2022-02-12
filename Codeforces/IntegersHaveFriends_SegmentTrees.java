package Codeforces;

import java.io.*;
import java.util.*;

public class IntegersHaveFriends_SegmentTrees {

    static long gcd(long a, long b){
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

    static void build(long[] segTree, int si, long[] arr, int ll, int ul){
        if(ll==ul){
            segTree[si] = arr[ll];
            return;
        }

        int mid = (ll + ul)/2;
        build(segTree, 2*si+1, arr, ll, mid);
        build(segTree, 2*si+2, arr, mid+1, ul);
        segTree[si] = gcd(segTree[2*si + 1], segTree[2*si+2]);
    }

    static class Pair{
        int index;
        long val;
        Pair(int index, long val){
            this.index = index;
            this.val = val;
        }
    }

    static Pair query(long[] segTree, int si, long gcd, int start, int end, int ll, int ul){

        //no overlap
        if(start>ul || end<ll)
            return new Pair(-1, 1);

        int mid = (ll + ul)/2;

        //total overlap
        if(ll>=start && ul<=end){
            if(gcd(gcd, segTree[si])==1){
                if(ll==ul){
                    return new Pair(ul+1, 1);
                }

                long si2gcd = gcd(gcd, segTree[2*si+2]);
                if(si2gcd==1){
                    return query(segTree, 2*si+2, gcd, start, end, mid+1, ul);
                }
                else{
                    return query(segTree, 2*si+1, si2gcd, start, end, ll, mid);
                }
            }
            else return new Pair(ll, gcd(gcd, segTree[si]));
        }

        //partial overlap
        Pair right = query(segTree, 2*si+2, gcd, start, end, mid+1, ul);
        if(right.index==-1){
            return query(segTree, 2*si+1, gcd, start, end, ll, mid);
        }

        if(right.val==1)
            return right;

        Pair left = query(segTree, 2*si+1, right.val, start, end, ll, mid);
        if(left.index==-1)
            return right;
        else return left;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            long[] arr = sc.nextLongArray(n);

            if(n==1){
                sb.append("1\n");
                continue;
            }

            long[] diff = new long[n];
            diff[0] = 1;
            for(int i=1;i<n;i++){
                diff[i] = Math.abs(arr[i] - arr[i-1]);
            }

            int sn = 2*getNextPowerOf2(n)+1;
            long[] segTree = new long[sn];
            build(segTree, 0, diff, 0, n-1);

            int max = 1;
            int startMax = 1;
            for(int i=1;i<n;i++){
                Pair p = query(segTree, 0, diff[i], startMax, i, 0, n-1);
                int start = p.index-1;

                max = Math.max(max, Math.max(0, i - start + 1));
                startMax = Math.max(startMax, p.index);
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
