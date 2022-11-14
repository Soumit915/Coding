package Codeforces;

import java.io.*;
import java.util.*;

public class OldFloppyDrive {

    static int getNextPowerOf2(int n){
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n |= n >> 25;

        return n + 1;
    }

    static void build(long[] segTree, int si, long[] arr, int l, int r){
        if(l == r){
            segTree[si] = arr[l];
            return;
        }

        int mid = (l + r) / 2;
        build(segTree, 2*si + 1, arr, l, mid);
        build(segTree, 2*si + 2, arr, mid + 1, r);
        segTree[si] = Math.max(segTree[2*si + 1], segTree[2*si + 2]);
    }

    static int query(long[] segTree, int si, long x, int l, int r){

        if(segTree[si] < x)
            return -1;

        if(l == r)
            return l;

        int mid = (l + r) / 2;
        if(segTree[2*si + 1] >= x){
            return query(segTree, 2*si + 1, x, l, mid);
        }
        else {
            return query(segTree, 2*si + 2, x, mid + 1, r);
        }
    }

    public static void main(String[] args) throws IOException{
        Soumit sc = new Soumit("Input1.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            long[] arr = sc.nextLongArray(n);

            long[] dp = new long[n];
            dp[0] = arr[0];
            for(int i=1;i<n;i++){
                dp[i] = dp[i-1] + arr[i];
            }

            int sn = 2 * getNextPowerOf2(n) - 1;
            long[] segTree = new long[sn];
            for(int i=0;i<sn;i++)
                segTree[i] = Long.MIN_VALUE;
            build(segTree, 0, dp, 0, n-1);

            long loop_cost = dp[n-1];
            for(int i=0;i<m;i++){
                long x = sc.nextLong();

                long first_loop_ops = query(segTree, 0, x, 0, n-1);

                if(first_loop_ops == -1){
                    if(loop_cost <= 0){
                        sb.append("-1 ");
                    }
                    else{
                        long looped_x = x - segTree[0];
                        long times = (looped_x + loop_cost - 1) / loop_cost;

                        long sum_collected_after_times = times * loop_cost;
                        long ops = times * n;

                        x -= sum_collected_after_times;

                        long last_loop_ops = query(segTree, 0, x, 0, n-1) + ops;
                        sb.append(last_loop_ops).append(" ");
                    }
                }
                else{
                    sb.append(first_loop_ops).append(" ");
                }
            }

            sb.append("\n");
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
