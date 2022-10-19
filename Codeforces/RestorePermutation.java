package Codeforces;

import java.util.*;
import java.io.*;

public class RestorePermutation {

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
        long val, lazy_val;
        int index;
    }

    static void build(Node[] segTree, int si, long[] a, int l, int r){
        if(l == r){
            Node node = new Node();
            node.val = a[l];
            node.index = l;
            node.lazy_val = 0;
            segTree[si] = node;
            return;
        }

        int mid = (l + r) / 2;
        build(segTree, 2*si + 1, a, l, mid);
        build(segTree, 2*si + 2, a, mid + 1, r);

        Node node = new Node();
        node.val = Long.MAX_VALUE;
        node.index = -1;
        node.lazy_val = 0;

        if(segTree[2*si + 1].val >= 0 && segTree[2*si+1].val < node.val){
            node.val = segTree[2*si + 1].val;
            node.index = segTree[2*si + 1].index;
        }

        if(segTree[2*si + 2].val >= 0 && segTree[2*si+2].val <= node.val){
            node.val = segTree[2*si + 2].val;
            node.index = segTree[2*si + 2].index;
        }

        if(node.val == Long.MAX_VALUE){
            node.val = -1;
        }

        segTree[si] = node;
    }

    static void delete(Node[] segTree, int si, int index, int l, int r){
        if(l == r){
            segTree[si].val = -1;
            return;
        }

        int mid = (l + r) / 2;
        if(segTree[si].lazy_val > 0){
            segTree[2*si + 1].val -= segTree[si].lazy_val;
            segTree[2*si + 1].lazy_val += segTree[si].lazy_val;

            segTree[2*si + 2].val -= segTree[si].lazy_val;
            segTree[2*si + 2].lazy_val += segTree[si].lazy_val;

            segTree[si].lazy_val = 0;
        }

        if(index <= mid){
            delete(segTree, 2*si + 1, index, l, mid);
        }
        else{
            delete(segTree, 2*si + 2, index, mid + 1, r);
        }

        Node node = new Node();
        node.val = Long.MAX_VALUE;
        node.index = -1;
        node.lazy_val = 0;

        if(segTree[2*si + 1].val >= 0 && segTree[2*si+1].val < node.val){
            node.val = segTree[2*si + 1].val;
            node.index = segTree[2*si + 1].index;
        }

        if(segTree[2*si + 2].val >= 0 && segTree[2*si+2].val <= node.val){
            node.val = segTree[2*si + 2].val;
            node.index = segTree[2*si + 2].index;
        }

        if(node.val == Long.MAX_VALUE){
            node.val = -1;
        }

        segTree[si] = node;
    }

    static void update(Node[] segTree, int si, int start, int end, long val, int l, int r){
        //no overlap
        if(l > end || r < start){
            return;
        }

        //total overlap
        if(l >= start && r <= end){

            segTree[si].val -= val;

            if(l == r){
                return;
            }

            segTree[si].lazy_val += val;

            segTree[2*si + 1].val -= segTree[si].lazy_val;
            segTree[2*si + 1].lazy_val += segTree[si].lazy_val;

            segTree[2*si + 2].val -= segTree[si].lazy_val;
            segTree[2*si + 2].lazy_val += segTree[si].lazy_val;

            segTree[si].lazy_val = 0;

            if(segTree[si].val < 0){
                Node node = new Node();
                node.val = Long.MAX_VALUE;
                node.index = -1;
                node.lazy_val = 0;

                if(segTree[2*si + 1].val >= 0 && segTree[2*si+1].val < node.val){
                    node.val = segTree[2*si + 1].val;
                    node.index = segTree[2*si + 1].index;
                }

                if(segTree[2*si + 2].val >= 0 && segTree[2*si+2].val < node.val){
                    node.val = segTree[2*si + 2].val;
                    node.index = segTree[2*si + 2].index;
                }

                if(node.val == Long.MAX_VALUE){
                    node.val = -1;
                }

                segTree[si] = node;
            }

            return;
        }

        int mid = (l + r) / 2;
        if(segTree[si].lazy_val > 0){
            segTree[2*si + 1].val -= segTree[si].lazy_val;
            segTree[2*si + 1].lazy_val += segTree[si].lazy_val;

            segTree[2*si + 2].val -= segTree[si].lazy_val;
            segTree[2*si + 2].lazy_val += segTree[si].lazy_val;

            segTree[si].lazy_val = 0;
        }

        update(segTree, 2*si + 1, start, end, val, l, mid);
        update(segTree, 2*si + 2, start, end, val, mid + 1, r);

        Node node = new Node();
        node.val = Long.MAX_VALUE;
        node.index = -1;
        node.lazy_val = 0;

        if(segTree[2*si + 1].val >= 0 && segTree[2*si+1].val < node.val){
            node.val = segTree[2*si + 1].val;
            node.index = segTree[2*si + 1].index;
        }

        if(segTree[2*si + 2].val >= 0 && segTree[2*si+2].val <= node.val){
            node.val = segTree[2*si + 2].val;
            node.index = segTree[2*si + 2].index;
        }

        if(node.val == Long.MAX_VALUE){
            node.val = -1;
        }

        segTree[si] = node;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        long[] a = sc.nextLongArray(n);

        int sn = 2 * getNextPowerOf2(n) - 1;
        Node[] segTree = new Node[sn];
        build(segTree, 0, a, 0, n-1);

        int[] ans = new int[n];
        for(int i=1;i<=n;i++){
            int index = segTree[0].index;
            ans[index] = i;

            delete(segTree, 0, index, 0, n-1);
            update(segTree, 0, index + 1, n-1, i, 0, n-1);
        }

        for(int i: ans){
            sb.append(i).append(" ");
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
