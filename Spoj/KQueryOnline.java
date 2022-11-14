package Spoj;

import java.io.*;
import java.util.*;

public class KQueryOnline {

    static int getNextPowerOf2(int n){
        n |= n>>1;
        n |= n>>2;
        n |= n>>4;
        n |= n>>8;
        n |= n>>16;
        n |= n>>25;

        return n + 1;
    }

    static class Node{
        List<Integer> list = new ArrayList<>();
    }

    static void build(Node[] segTree, int si, int[] arr, int l, int r){
        if(l == r){
            segTree[si] = new Node();
            segTree[si].list.add(arr[l]);
            return;
        }

        int mid = (l + r) / 2;
        build(segTree, 2*si + 1, arr, l, mid);
        build(segTree, 2*si + 2, arr, mid + 1, r);

        segTree[si] = new Node();
        int n = segTree[2*si + 1].list.size();
        int m = segTree[2*si + 2].list.size();
        int p = 0, q = 0;
        while (p < n && q < m){
            if(segTree[2*si + 1].list.get(p) <= segTree[2*si + 2].list.get(q)){
                segTree[si].list.add(segTree[2*si + 1].list.get(p));
                p++;
            }
            else{
                segTree[si].list.add(segTree[2*si + 2].list.get(q));
                q++;
            }
        }

        while(p < n){
            segTree[si].list.add(segTree[2*si + 1].list.get(p));
            p++;
        }

        while(q < m){
            segTree[si].list.add(segTree[2*si + 2].list.get(q));
            q++;
        }
    }

    static int binarySearch(List<Integer> list, int k){
        int l = 0;
        if(list.get(list.size() - 1) < k)
            return list.size();
        int r = list.size() - 1;

        while(l < r){
            int mid = (l + r) / 2;
            if(list.get(mid) >= k){
                r = mid;
            }
            else l = mid + 1;
        }

        return l;
    }

    static int query(Node[] segTree, int si, int start, int end, int k, int l, int r){
        //no-overlap
        if(r < start || l > end){
            return 0;
        }

        //total overlap
        if(l >= start && r <= end){
            return segTree[si].list.size() - binarySearch(segTree[si].list, k);
        }

        //partial overlap
        int mid = (l + r) / 2;
        return query(segTree, 2*si + 1, start, end, k, l, mid) +
                query(segTree, 2*si + 2, start, end, k, mid + 1, r);
    }

    public static void main(String[] args) throws IOException{

        //long start = System.currentTimeMillis();

        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        int sn = 2 * getNextPowerOf2(n) - 1;
        Node[] segTree = new Node[sn];
        build(segTree, 0, arr, 0, n-1);

        StringBuilder sb = new StringBuilder();

        int q = sc.nextInt();
        int last = 0;
        for (int i=0;i<q;i++){
            int l = sc.nextInt() ^ last;
            int r = sc.nextInt() ^ last;
            int k = sc.nextInt();

            if(l < 1)
                l = 1;

            if(r > n)
                r = n;

            if(l > r){
                sb.append(0).append("\n");
                last = 0;
                continue;
            }

            last = query(segTree, 0, l-1, r-1, k, 0, n-1);
            sb.append(last).append("\n");
        }

        System.out.println(sb);

        /*long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);*/

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
