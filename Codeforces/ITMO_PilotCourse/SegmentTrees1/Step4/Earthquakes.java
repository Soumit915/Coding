package Codeforces.ITMO_PilotCourse.SegmentTrees1.Step4;

import java.io.*;
import java.util.*;

public class Earthquakes {

    static int getNextPowerOf2(int n){
        n = n | (n >> 1);
        n = n | (n >> 2);
        n = n | (n >> 4);
        n = n | (n >> 8);
        n = n | (n >> 16);
        n = n | (n >> 25);

        return n+1;
    }

    static void update(int[] segTree, int si, int index, int val, int ll, int ul){
        if(ll == ul){
            segTree[si] = val;
            return;
        }

        int mid = (ll + ul)/2;
        if(index <= mid){
            update(segTree, 2*si + 1, index, val, ll, mid);
        }
        else{
            update(segTree, 2*si + 2, index, val, mid+1, ul);
        }

        segTree[si] = Math.min(segTree[2*si+1], segTree[2*si+2]);
    }

    static int query(int[] segTree, int si, int p, int start, int end, int ll, int ul){
        //no overlap
        if(start>ul || end<ll){
            return 0;
        }

        int mid = (ll + ul) / 2;

        //total overlap
        if(start<=ll && end>=ul){
            if(segTree[si] <= p){
                if(ll == ul){
                    segTree[si] = Integer.MAX_VALUE;
                    return 1;
                }
                else{
                    int lc = query(segTree, 2*si + 1, p, start, end, ll, mid);
                    int rc = query(segTree, 2*si + 2, p, start, end, mid+1, ul);

                    segTree[si] = Math.min(segTree[2*si+1], segTree[2*si+2]);

                    return lc + rc;
                }
            }
            else return 0;
        }

        //partial overlap
        int lc = query(segTree, 2*si + 1, p, start, end, ll, mid);
        int rc = query(segTree, 2*si + 2, p, start, end, mid+1, ul);

        segTree[si] = Math.min(segTree[2*si+1], segTree[2*si+2]);

        return lc + rc;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int q = sc.nextInt();

        int sn = 2 * getNextPowerOf2(n) - 1;
        int[] segTree = new int[sn];
        for(int i=0;i<n;i++){
            update(segTree, 0, i, Integer.MAX_VALUE, 0, n-1);
        }

        StringBuilder sb = new StringBuilder();
        while(q-->0){
            int type = sc.nextInt();

            if(type == 1){
                int index = sc.nextInt();
                int h = sc.nextInt();

                update(segTree, 0, index, h, 0, n-1);
            }
            else{
                int l = sc.nextInt();
                int r = sc.nextInt() - 1;
                int p = sc.nextInt();

                int ans = query(segTree, 0, p, l, r, 0, n-1);
                sb.append(ans).append("\n");
            }
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
