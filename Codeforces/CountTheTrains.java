package Codeforces;

import java.io.*;
import java.util.*;

public class CountTheTrains {

    static int getNextPowerOf2(int n){
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>25);

        return n+1;
    }

    static class Node{
        int max;
        int min;

        int countDistinct;

        int lazy;

        Node(){
            this.lazy = -1;
        }
    }

    static void build(Node[] segTree, int si, int[] arr, int l, int r){
        if(l == r){
            Node node = new Node();
            node.min = arr[l];
            node.max = arr[r];
            node.countDistinct = 1;

            segTree[si] = node;

            return;
        }

        int mid = (l + r)/2;
        build(segTree, 2*si+1, arr, l, mid);
        build(segTree, 2*si+2, arr, mid+1, r);

        Node node = new Node();
        node.min = segTree[2*si + 2].min;
        node.max = segTree[2*si + 1].max;
        if(segTree[2*si + 1].min == segTree[2*si + 2].max){
            node.countDistinct = segTree[2*si + 1].countDistinct + segTree[2*si + 2].countDistinct - 1;
        }
        else{
            node.countDistinct = segTree[2*si + 1].countDistinct + segTree[2*si + 2].countDistinct;
        }

        segTree[si] = node;
    }

    static void updateRangeToV(Node[] segTree, int si, int start, int end, int v, int l, int r){
        //no overlap
        if(l>end || r<start){
            return;
        }

        //total overlap
        if(l>=start && r<=end){
            segTree[si].min = v;
            segTree[si].max = v;
            segTree[si].countDistinct = 1;

            if(l!=r)
                segTree[si].lazy = v;

            return;
        }

        if(segTree[si].lazy >= 0){
            segTree[2*si + 1].min = segTree[si].lazy;
            segTree[2*si + 1].max = segTree[si].lazy;
            segTree[2*si + 1].countDistinct = 1;
            segTree[2*si + 1].lazy = segTree[si].lazy;

            segTree[2*si + 2].min = segTree[si].lazy;
            segTree[2*si + 2].max = segTree[si].lazy;
            segTree[2*si + 2].countDistinct = 1;
            segTree[2*si + 2].lazy = segTree[si].lazy;

            segTree[si].lazy = -1;
        }

        int mid = (l + r) / 2;
        updateRangeToV(segTree, 2*si + 1, start, end, v, l, mid);
        updateRangeToV(segTree, 2*si + 2, start, end, v, mid + 1, r);

        Node node = new Node();
        node.min = segTree[2*si + 2].min;
        node.max = segTree[2*si + 1].max;
        if(segTree[2*si + 1].min == segTree[2*si + 2].max){
            node.countDistinct = segTree[2*si + 1].countDistinct + segTree[2*si + 2].countDistinct - 1;
        }
        else{
            node.countDistinct = segTree[2*si + 1].countDistinct + segTree[2*si + 2].countDistinct;
        }

        segTree[si] = node;
    }

    static int queryCurVal(Node[] segTree, int si, int index, int l, int r){
        if(l == r){
            return segTree[si].max;
        }

        if(segTree[si].lazy >= 0){
            segTree[2*si + 1].min = segTree[si].lazy;
            segTree[2*si + 1].max = segTree[si].lazy;
            segTree[2*si + 1].countDistinct = 1;
            segTree[2*si + 1].lazy = segTree[si].lazy;

            segTree[2*si + 2].min = segTree[si].lazy;
            segTree[2*si + 2].max = segTree[si].lazy;
            segTree[2*si + 2].countDistinct = 1;
            segTree[2*si + 2].lazy = segTree[si].lazy;

            segTree[si].lazy = -1;
        }

        int mid = (l + r) / 2;
        if(index <= mid){
            return queryCurVal(segTree, 2*si + 1, index, l, mid);
        }
        else{
            return queryCurVal(segTree, 2*si + 2, index, mid + 1, r);
        }
    }

    static int queryMaxIndexGreaterThanEqualV(Node[] segTree, int si, int v, int l, int r){
        if(l == r){
            return l;
        }

        if(segTree[si].lazy >= 0){
            segTree[2*si + 1].min = segTree[si].lazy;
            segTree[2*si + 1].max = segTree[si].lazy;
            segTree[2*si + 1].countDistinct = 1;
            segTree[2*si + 1].lazy = segTree[si].lazy;

            segTree[2*si + 2].min = segTree[si].lazy;
            segTree[2*si + 2].max = segTree[si].lazy;
            segTree[2*si + 2].countDistinct = 1;
            segTree[2*si + 2].lazy = segTree[si].lazy;

            segTree[si].lazy = -1;
        }

        int mid = (l + r) / 2;
        if(segTree[2*si + 2].max >= v){
            return queryMaxIndexGreaterThanEqualV(segTree, 2*si + 2, v, mid + 1, r);
        }
        else{
            return queryMaxIndexGreaterThanEqualV(segTree, 2*si + 1, v, l, mid);
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            int n = sc.nextInt();
            int q = sc.nextInt();

            int[] arr = sc.nextIntArray(n);

            int[] speed = new int[n];
            speed[0] = arr[0];
            for(int i=1;i<n;i++){
                speed[i] = Math.min(arr[i], speed[i-1]);
            }

            int sn = 2 * getNextPowerOf2(n) - 1;
            Node[] segTree = new Node[sn];
            build(segTree, 0, speed, 0, n-1);

            for(int i=0;i<q;i++){
                int k = sc.nextInt() - 1;
                int d = sc.nextInt();

                int v = queryCurVal(segTree, 0, k, 0, n-1);
                arr[k] -= d;

                if(v > arr[k]){
                    v = arr[k];
                    int maxIndexGreaterThanEqualV = queryMaxIndexGreaterThanEqualV(segTree, 0, v, 0, n-1);
                    updateRangeToV(segTree, 0, k, maxIndexGreaterThanEqualV, v, 0, n-1);
                }

                int count = segTree[0].countDistinct;

                sb.append(count).append(" ");
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
