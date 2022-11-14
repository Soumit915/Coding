package ICPC_2020.Regionals;

import java.io.*;
import java.util.*;

public class K {
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
        segTree[sn] = Math.max(segTree[2*sn+1], segTree[2*sn+2]);
    }
    public static long query(long[] segTree, int sn, int start, int end, int ll, int ul)
    {
        //for no overlap
        if(start>ul || end<ll)
            return Long.MIN_VALUE;

        //for total overlap
        if(start<=ll && end>=ul)
        {
            return segTree[sn];
        }

        int mid = (ll+ul)/2;
        return Math.max(query(segTree, 2*sn+1, start, end, ll, mid)
                , query(segTree, 2*sn+2, start, end, mid+1, ul));
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int test = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (test-->0){
            int n = sc.nextInt();
            long q = sc.nextLong();

            long[] u = sc.nextLongArray(n);
            long[] t = sc.nextLongArray(n);

            long[] dp = new long[n];
            long[] prefix = new long[n];
            long[] suffix = new long[n];
            long[] subarraysum = new long[n];

            prefix[0] = u[0];
            for(int i=1;i<n;i++){
                prefix[i] = prefix[i-1]+u[i];
            }

            suffix[n-1] = u[n-1];
            for(int i=n-2;i>=0;i--){
                suffix[i] = suffix[i+1]+u[i];
            }

            subarraysum[0] = Math.max(u[0], 0);
            long max = subarraysum[0];
            for (int i = 1; i < n; i++) {
                subarraysum[i] = Math.max(subarraysum[i-1] + u[i], 0);
                max = Math.max(max, subarraysum[i]);
            }

            int sn = 2*getnextPowerOf2(n)-1;
            long[] prefix_segTree = new long[sn];
            long[] suffix_segTree = new long[sn];
            built(prefix_segTree, 0, prefix, 0, n-1);
            built(suffix_segTree, 0, suffix, 0, n-1);

            for(int i=0;i<n;i++){
                long trained = q*t[i]+u[i];
                long leftmax = query(suffix_segTree, 0, 0, i-1, 0, n-1);
                long rightmax = query(prefix_segTree, 0, i+1, n-1, 0, n-1);

                if(leftmax==Long.MIN_VALUE)
                    leftmax = 0;
                else {
                    leftmax -= suffix[i];
                    leftmax = Math.max(leftmax, 0);
                }

                if(rightmax==Long.MIN_VALUE)
                    rightmax = 0;
                else{
                    rightmax -= prefix[i];
                    rightmax = Math.max(rightmax, 0);
                }

                dp[i] = trained+leftmax+rightmax;
                max = Math.max(dp[i], max);
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
