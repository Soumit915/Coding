package Codeforces.ITMO_PilotCourse.SegmentTrees1.Step4;

import java.io.*;
import java.util.*;

public class NumberOfDifferentOnSegment {
    static int getNextPowerOf2(int n){
        n = n | (n >> 1);
        n = n | (n >> 2);
        n = n | (n >> 4);
        n = n | (n >> 8);
        n = n | (n >> 16);
        n = n | (n >> 25);

        return n+1;
    }

    static class Node{
        int[] nums;
        int count_diff;

        Node(){
            this.count_diff = 0;
            this.nums = new int[41];
        }
    }

    static void build(Node[] segTree, int si, int[] arr, int ll, int ul){
        if(ll == ul){
            segTree[si].count_diff = 1;
            segTree[si].nums[arr[ll]]++;
            return;
        }

        int mid = (ll + ul) / 2;
        build(segTree, 2*si + 1, arr, ll, mid);
        build(segTree, 2*si + 2, arr, mid+1, ul);

        int[] left = segTree[2*si + 1].nums;
        int[] right = segTree[2*si + 2].nums;

        segTree[si].count_diff = 0;
        for(int i=left.length-1;i>=0;i--){
            segTree[si].nums[i] = left[i] + right[i];
            segTree[si].count_diff += Math.min(1, left[i] + right[i]);
        }
    }

    static Node query(Node[] segTree, int si, int start, int end, int ll, int ul){
        //no overlap
        if(end<ll || start>ul){
            return new Node();
        }

        //total overlap
        if(start<=ll && end>=ul){
            return segTree[si];
        }

        //partial overlap
        int mid = (ll + ul) / 2;
        Node left = query(segTree, 2*si+1, start, end, ll, mid);
        Node right = query(segTree, 2*si+2, start, end, mid+1, ul);

        Node cur = new Node();
        for(int i=left.nums.length-1;i>=0;i--){
            cur.nums[i] = left.nums[i] + right.nums[i];
            cur.count_diff += Math.min(cur.nums[i], 1);
        }

        return cur;
    }

    static void update(Node[] segTree, int si, int prev, int val, int index, int ll, int ul){
        if(ll == ul){
            segTree[si].count_diff = 1;
            segTree[si].nums[prev]--;
            segTree[si].nums[val]++;
            return;
        }

        int mid = (ll + ul) / 2;
        if(index <= mid){
            update(segTree, 2*si + 1, prev, val, index, ll, mid);
        }
        else{
            update(segTree, 2*si + 2, prev, val, index, mid+1, ul);
        }

        int[] left = segTree[2*si + 1].nums;
        int[] right = segTree[2*si + 2].nums;

        segTree[si].count_diff = 0;
        for(int i=left.length-1;i>=0;i--){
            segTree[si].nums[i] = left[i] + right[i];
            segTree[si].count_diff += Math.min(1, left[i] + right[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] arr = sc.nextIntArray(n);

        int sn = 2 * getNextPowerOf2(n) - 1;
        Node[] segTree = new Node[sn];
        for(int i=0;i<sn;i++){
            segTree[i] = new Node();
        }
        build(segTree, 0, arr, 0, n-1);

        StringBuilder sb = new StringBuilder();
        while(q-->0){
            int type = sc.nextInt();
            int xi = sc.nextInt() - 1;
            int yi = sc.nextInt();

            if(type == 1){
                yi--;

                Node node = query(segTree, 0, xi, yi, 0, n-1);
                sb.append(node.count_diff).append("\n");
            }
            else{
                update(segTree, 0, arr[xi], yi, xi, 0, n-1);
                arr[xi] = yi;
            }
        }

        System.out.print(sb);

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
