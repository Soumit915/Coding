package Codechef;

import java.io.*;
import java.util.*;

public class MaximumGCD {
    public static int getnextPowerOf2(int n)
    {
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>30);

        return n+1;
    }
    static long gcd(long a, long b){
        if(a%b==0){
            return b;
        }
        else return gcd(b, a%b);
    }
    public static void built(long[] segTree, int sn, long[] arr, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn] = arr[ll];
            return;
        }

        int mid = (ll+ul)/2;
        built(segTree, 2*sn+1, arr, ll, mid);
        built(segTree, 2*sn+2, arr, mid+1, ul);
        segTree[sn] = gcd(segTree[2*sn+1], segTree[2*sn+2]);
    }
    public static void update(long[] segTree, int sn, long val, int ind, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn] = val;
            return;
        }

        int mid = (ll+ul)/2;
        if(ind<=mid)
        {
            update(segTree, 2*sn+1, val, ind, ll, mid);
        }
        else
        {
            update(segTree, 2*sn+2, val, ind, mid+1, ul);
        }

        segTree[sn] = gcd(segTree[2*sn+1], segTree[2*sn+2]);
    }
    public static long query(long[] segTree, int sn, int start, int end, int ll, int ul)
    {
        //for no overlap
        if(start>ul || end<ll)
        {
            return Long.MAX_VALUE;
        }

        //for total overlap
        if(start<=ll && end>=ul)
        {
            return segTree[sn];
        }

        int mid = (ll+ul)/2;
        long ql = query(segTree, 2*sn+1, start, end, ll, mid);
        long qr = query(segTree, 2*sn+2, start, end, mid+1, ul);
        if(ql==Long.MAX_VALUE)
            return qr;
        if(qr==Long.MAX_VALUE)
            return ql;
        return gcd(ql, qr);
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            long[] arr = sc.nextLongArray(n);

            int sn = 2*getnextPowerOf2(n)-1;
            long[] segTree = new long[sn];
            Arrays.fill(segTree, Long.MAX_VALUE);
            built(segTree, 0, arr, 0, n-1);

            long max = 0;
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    long ql = query(segTree, 0, 0, i-1, 0, n-1);
                    long qmid = query(segTree, 0, i+1, j-1, 0, n-1);
                    long qr = query(segTree, 0, j+1, n-1, 0, n-1);

                    if(ql==Long.MAX_VALUE){
                        if(qmid==Long.MAX_VALUE){
                            if(qr==Long.MAX_VALUE){
                                max = Math.max(max, arr[i]*arr[j]);
                            }
                            else {
                                max = Math.max(max, gcd(arr[i]*arr[j], qr));
                            }
                        }
                        else{
                            if(qr==Long.MAX_VALUE){
                                max = Math.max(max, gcd(arr[i]*arr[j], qmid));
                            }
                            else {
                                max = Math.max(max, gcd(arr[i]*arr[j], gcd(qmid, qr)));
                            }
                        }
                    }
                    else{
                        if(qmid==Long.MAX_VALUE){
                            if(qr==Long.MAX_VALUE){
                                max = Math.max(max, gcd(arr[i]*arr[j], ql));
                            }
                            else {
                                max = Math.max(max, gcd(arr[i]*arr[j], gcd(ql, qr)));
                            }
                        }
                        else{
                            if(qr==Long.MAX_VALUE){
                                max = Math.max(max, gcd(arr[i]*arr[j], gcd(ql, qmid)));
                            }
                            else {
                                max = Math.max(max, gcd(arr[i]*arr[j], gcd(ql, gcd(qmid, qr))));
                            }
                        }
                    }
                }
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
