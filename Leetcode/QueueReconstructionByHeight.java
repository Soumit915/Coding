package Leetcode;
import java.io.*;
import java.util.*;

public class QueueReconstructionByHeight {

    static int getNextPowerOf2(int n){
        n |= (n >> 1);
        n |= (n >> 2);
        n |= (n >> 4);
        n |= (n >> 8);
        n |= (n >> 16);

        return n + 1;
    }

    static class People implements Comparable<People>{
        int id;
        int hi;
        int ki;

        People(int id, int hi, int ki){
            this.id = id;
            this.hi = hi;
            this.ki = ki;
        }

        public int compareTo(People people){
            int c = Integer.compare(this.hi, people.hi);
            if(c == 0)
                return Integer.compare(this.ki, people.ki) * -1;
            else return c;
        }
    }

    static void build(int[] segTree, int si, int l, int r){
        if(l == r){
            segTree[si] = 1;
            return;
        }

        int mid = (l + r) / 2;
        build(segTree, 2*si + 1, l, mid);
        build(segTree, 2*si + 2, mid + 1, r);

        segTree[si] = segTree[2*si + 1] + segTree[2*si + 2];
    }

    static void update(int[] segTree, int si, int index, int l, int r){
        if(l == r){
            segTree[si] = 0;
            return;
        }

        int mid = (l + r) / 2;
        if(index <= mid){
            update(segTree, 2*si + 1, index, l, mid);
        }
        else{
            update(segTree, 2*si + 2, index, mid + 1, r);
        }
        segTree[si] = segTree[2*si + 1] + segTree[2*si + 2];
    }

    static int query(int[] segTree, int si, int k, int l, int r){
        if(l == r){
            return l;
        }

        int mid = (l + r) / 2;
        if(segTree[2*si + 1] > k){
            return query(segTree, 2*si + 1, k, l, mid);
        }
        else{
            return query(segTree, 2*si + 2, k - segTree[2*si + 1], mid+1, r);
        }
    }

    public static int[][] reconstructQueue(int[][] people) {
        int n = people.length;

        People[] pi = new People[n];
        for(int i=0;i<n;i++){
            pi[i] = new People(i, people[i][0], people[i][1]);
        }
        Arrays.sort(pi);

        int sn = 2 * getNextPowerOf2(n) - 1;
        int[] segTree = new int[sn];
        build(segTree, 0, 0, n-1);

        int[][] ans = new int[n][2];
        for(int i=0;i<n;i++){
            int index = query(segTree, 0, pi[i].ki, 0, n-1);
            ans[index][0] = pi[i].hi;
            ans[index][1] = pi[i].ki;

            update(segTree, 0, index, 0, n-1);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();
        
        int[][] arr = {{6,0}, {5,0}, {4,0}, {3,2}, {2,2}, {1,4}};

        int[][] ans = reconstructQueue(arr);

        for(int[] i: ans){
            System.out.println(Arrays.toString(i));
        }
        
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
