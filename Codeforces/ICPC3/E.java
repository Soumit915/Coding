package Codeforces.ICPC3;

import java.util.*;
import java.io.*;

public class E {

    static class Pair implements Comparable<Pair>{
        int id;
        int val;

        Pair(int id, int val){
            this.id = id;
            this.val = val;
        }

        public int compareTo(Pair p){
            int c = Integer.compare(this.val, p.val);
            if(c == 0){
                return Integer.compare(p.id, this.id);
            }
            return c;
        }
    }

    static class Query implements Comparable<Query>{
        int id;
        int k;
        int pos;

        Query(int id, int k, int pos){
            this.id = id;
            this.k = k;
            this.pos = pos;
        }

        public int compareTo(Query q){
            return Integer.compare(this.k, q.k);
        }
    }

    static int getNextPowerOf2(int n){
        n |= (n >> 1);
        n |= (n >> 2);
        n |= (n >> 4);
        n |= (n >> 8);
        n |= (n >> 16);
        n |= (n >> 25);

        return n + 1;
    }

    static class Node {
        int count;
        int val;
    }

    static void update(Node[] segTree, int si, int index, int val, int l, int r){
        if(l == r){
            segTree[si] = new Node();
            segTree[si].count = 1;
            segTree[si].val = val;
            return;
        }

        int mid = (l + r) / 2;
        if(index <= mid){
            update(segTree, 2*si + 1, index, val, l, mid);
        }
        else{
            update(segTree, 2*si + 2, index, val, mid + 1, r);
        }

        segTree[si] = new Node();
        if(segTree[2*si + 1] != null)
            segTree[si].count += segTree[2*si + 1].count;
        if(segTree[2*si + 2] != null)
            segTree[si].count += segTree[2*si + 2].count;
    }

    static int query(Node[] segTree, int si, int k, int l, int r){
        if(l == r){
            return segTree[si].val;
        }

        int mid = (l + r) / 2;
        if(segTree[2*si + 1].count >= k){
            return query(segTree, 2*si + 1, k, l, mid);
        }
        else{
            return query(segTree, 2*si + 2, k - segTree[2*si + 1].count, mid + 1, r);
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();

        int[] a = sc.nextIntArray(n);

        Pair[] pairs = new Pair[n];
        for(int i=0;i<n;i++){
            pairs[i] = new Pair(i, a[i]);
        }

        Arrays.sort(pairs);

        StringBuilder sb = new StringBuilder();

        int m = sc.nextInt();
        Query[] queries = new Query[m];
        for(int i=0;i<m;i++){
            queries[i] = new Query(i, sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(queries);

        int sn = 2 * getNextPowerOf2(n) - 1;
        Node[] segTree = new Node[sn];
        for(int i=0;i<sn;i++)
            segTree[i] = new Node();

        int ptr = n;
        int[] ans = new int[m];
        for(int i=0;i<m;i++){
            Query q = queries[i];

            while(ptr > n-q.k){
                ptr--;
                update(segTree, 0, pairs[ptr].id, pairs[ptr].val, 0, n-1);
            }

            ans[q.id] = query(segTree, 0, q.pos, 0, n-1);
        }

        for(int i: ans)
            sb.append(i).append("\n");
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
