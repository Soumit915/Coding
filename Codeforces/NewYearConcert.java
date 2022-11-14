package Codeforces;

import java.io.*;
import java.util.*;

public class NewYearConcert {

    static int getNextPowerOf2(int n){
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n |= n >> 22;

        return n + 1;
    }

    static int gcd(int a, int b){
        if(a==0)
            return b;
        else if(b == 0)
            return a;
        else if(a%b == 0)
            return b;
        else return gcd(b, a%b);
    }

    static void build(int[] segTree, int si, int[] arr, int l, int r){
        if(l == r){
            segTree[si] = arr[l];
            return;
        }

        int mid = (l + r) / 2;
        build(segTree, 2*si + 1, arr, l, mid);
        build(segTree, 2*si + 2, arr, mid + 1, r);

        segTree[si] = gcd(segTree[2*si + 1], segTree[2*si + 2]);
    }

    static int query(int[] segTree, int si, int start, int end, int l, int r){
        //no overlap
        if(l > end || r < start)
            return 0;

        //total overlap
        if(l >= start && r <= end)
            return segTree[si];

        //partial overlap
        int mid = (l + r) / 2;
        return gcd(query(segTree, 2*si + 1, start, end, l, mid) ,
                query(segTree, 2*si + 2, start, end, mid + 1, r));
    }

    public static void main(String[] args) throws IOException{
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        int sn = 2 * getNextPowerOf2(n) - 1;
        int[] segTree = new int[sn];
        build(segTree, 0, arr, 0, n-1);

        int[] dp = new int[n];
        StringBuilder sb = new StringBuilder();

        int last = -1;
        for(int i=0;i<n;i++){
            int l = last+1, r = i;

            while (l <= r){
                int mid = (l + r) / 2;

                int q = query(segTree, 0, mid, i, 0, n-1);

                if(q == (i - mid + 1)){
                    l = mid;
                    r = mid;
                    break;
                }
                else if(q > (i - mid + 1)){
                    r = mid - 1;
                }
                else{
                    l = mid + 1;
                }
            }

            if(l==r && l <= i){
                last = i;
                dp[i] = 1;
            }


        }

        for(int i=1;i<n;i++)
            dp[i] += dp[i-1];

        for(int i: dp)
            sb.append(i).append(" ");
        sb.append("\n");

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
