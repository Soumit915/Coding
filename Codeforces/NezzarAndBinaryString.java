package Codeforces;

import java.util.*;
import java.io.*;

public class NezzarAndBinaryString {

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
        int val, lazy_val;
        boolean hasLazy;

        Node(int val, int lazy_val){
            this.val = val;
            this.lazy_val = lazy_val;
            this.hasLazy = false;
        }
    }

    static void build(Node[] segTree, int si, int[] a, int l, int r){
        if(l == r){
            Node node = new Node(a[l], 0);
            segTree[si] = node;
            return;
        }

        int mid = (l + r) / 2;
        build(segTree, 2*si + 1, a, l, mid);
        build(segTree, 2*si + 2, a, mid+1, r);

        Node node = new Node(segTree[2*si + 1].val + segTree[2*si + 2].val, 0);
        segTree[si] = node;
    }

    static void update(Node[] segTree, int si, int start, int end, int v, int l, int r){
        //no overlap
        if(l>end || r<start){
            return;
        }

        //total overlap
        if(l>=start && r<=end){
            segTree[si].val = (r - l + 1) * v;
            if(l<r) {
                segTree[si].lazy_val = v;
                segTree[si].hasLazy = true;
            }

            return;
        }

        //partial overlap
        int mid = (l + r) / 2;
        if(segTree[si].hasLazy) {
            segTree[2 * si + 1].val = (mid - l + 1) * segTree[si].lazy_val;
            segTree[2 * si + 2].val = (r - mid) * segTree[si].lazy_val;

            segTree[2*si + 1].lazy_val = segTree[si].lazy_val;
            segTree[2*si + 2].lazy_val = segTree[si].lazy_val;

            segTree[2*si + 1].hasLazy = true;
            segTree[2*si + 2].hasLazy = true;

            segTree[si].hasLazy = false;
        }

        update(segTree, 2*si + 1, start, end, v, l, mid);
        update(segTree, 2*si + 2, start, end, v, mid+1, r);
        segTree[si].val = segTree[2*si + 1].val + segTree[2*si + 2].val;
    }

    static int query(Node[] segTree, int si, int start, int end, int l, int r){
        //no overlap
        if(l>end || r<start){
            return 0;
        }

        //total overlap
        if(l>=start && r<=end){
            return segTree[si].val;
        }

        //partial overlap
        int mid = (l + r) / 2;
        if(segTree[si].hasLazy) {
            segTree[2 * si + 1].val = (mid - l + 1) * segTree[si].lazy_val;
            segTree[2 * si + 2].val = (r - mid) * segTree[si].lazy_val;

            segTree[2*si + 1].lazy_val = segTree[si].lazy_val;
            segTree[2*si + 2].lazy_val = segTree[si].lazy_val;

            segTree[2*si + 1].hasLazy = true;
            segTree[2*si + 2].hasLazy = true;

            segTree[si].hasLazy = false;
        }

        return query(segTree, 2*si + 1, start, end, l, mid) + query(segTree, 2*si + 2, start, end, mid+1, r);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            int q = sc.nextInt();

            String s = sc.next();
            String t = sc.next();

            int[] a = new int[n];
            for(int i=0;i<n;i++){
                a[i] = t.charAt(i) - '0';
            }

            int sn = 2 * getNextPowerOf2(n) - 1;
            Node[] segTree = new Node[sn];
            build(segTree, 0, a, 0, n-1);

            int[][] range = new int[q][2];
            for(int i=0;i<q;i++){
                range[i][0] = sc.nextInt() - 1;
                range[i][1] = sc.nextInt() - 1;
            }

            boolean flag = true;
            for(int i=q-1;i>=0;i--){
                int sum = query(segTree, 0, range[i][0], range[i][1], 0, n-1);
                int len = range[i][1] - range[i][0] + 1;

                if(sum < (len+1) / 2){
                    update(segTree, 0, range[i][0], range[i][1], 0, 0, n-1);
                }
                else if(sum > len / 2){
                    update(segTree, 0, range[i][0], range[i][1], 1, 0, n-1);
                }
                else{
                    flag = false;
                    break;
                }
            }

            for(int i=0;i<n && flag;i++){
                int bit = query(segTree, 0, i, i, 0, n-1);
                if(bit != (s.charAt(i) - '0')){
                    flag = false;
                    break;
                }
            }

            if(flag){
                sb.append("YES\n");
            }
            else{
                sb.append("NO\n");
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
