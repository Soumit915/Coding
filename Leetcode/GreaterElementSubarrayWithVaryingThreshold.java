package Leetcode;

import java.io.*;
import java.util.*;

public class GreaterElementSubarrayWithVaryingThreshold {

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

    static class Node{
        int id;
        long val;

        Node(int id, long val){
            this.id = id;
            this.val = val;
        }
    }

    public static void built(Node[] segTree, int sn, int[] arr, int ll, int ul)
    {
        if(ll==ul) {
            Node node = new Node(ll, arr[ll]);
            segTree[sn] = node;
            return;
        }

        int mid = (ll+ul)/2;
        built(segTree, 2*sn+1, arr, ll, mid);
        built(segTree, 2*sn+2, arr, mid+1, ul);

        if(segTree[2*sn + 1].val < segTree[2*sn + 2].val){
            segTree[sn] = segTree[2*sn + 1];
        }
        else{
            segTree[sn] = segTree[2*sn + 2];
        }
    }

    public static Node query(Node[] segTree, int sn, int start, int end, int ll, int ul)
    {
        //for no overlap
        if(start>ul || end<ll) {
            return new Node(-1, Integer.MAX_VALUE);
        }

        //for total overlap
        if(start<=ll && end>=ul) {
            return segTree[sn];
        }

        int mid = (ll+ul)/2;
        Node left = query(segTree, 2*sn+1, start, end, ll, mid);
        Node right = query(segTree, 2*sn+2, start, end, mid+1, ul);

        if(left.val < right.val)
            return left;
        else return right;
    }

    static int recurse(Node[] segTree, int threshold, int n, int l, int r){

        if(l>r)
            return -1;

        Node min = query(segTree, 0, l, r, 0, n-1);
        int len = r - l + 1;

        if(min.val * len > threshold){
            return len;
        }

        int left = recurse(segTree, threshold, n, l, min.id - 1);
        int right = recurse(segTree, threshold, n, min.id + 1, r);

        if(left == -1)
            return right;
        else return left;
    }

    public static int validSubarraySize(int[] arr, int threshold) {

        int n = arr.length;

        int sn = 2*getnextPowerOf2(n)-1;
        Node[] segTree = new Node[sn];
        built(segTree, 0, arr, 0, n-1);

        return recurse(segTree, threshold, n, 0, n-1);
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int[] arr = {818,232,595,418,608,229,37,330,876,774,931,939,479,884,354,328};
        System.out.println(validSubarraySize(arr, 3790));

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
