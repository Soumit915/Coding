package Codeforces.ITMO_PilotCourse.SegmentTrees1.Step4;

import java.io.*;
import java.util.*;

public class Cryptography {
    static int r = 0;
    static class Matrix{
        int a;
        int b;
        int c;
        int d;
        Matrix(int a, int b, int c, int d){
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }
    static int nextPowerOf2(int n){
        n |= n>>1;
        n |= n>>2;
        n |= n>>4;
        n |= n>>8;
        n |= n>>16;
        n |= n>>30;
        return n+1;
    }
    static Matrix multiply(Matrix A, Matrix B){
        int newa = (A.a*B.a + A.b*B.c)%r;
        int newb = (A.a*B.b + A.b*B.d)%r;
        int newc = (A.c*B.a + A.d*B.c)%r;
        int newd = (A.c*B.b + A.d*B.d)%r;
        return new Matrix(newa, newb, newc, newd);
    }
    static void update(Matrix[] segTree, int sn, int index, Matrix val, int ll, int ul){
        if(ll>ul)
            return;
        if(ll==ul){
            segTree[sn] = val;
            return;
        }

        int mid = (ll+ul)/2;
        if(index<=mid){
            update(segTree, 2*sn+1, index, val, ll, mid);
        }
        else{
            update(segTree, 2*sn+2, index, val, mid+1, ul);
        }
        segTree[sn] = multiply(segTree[2*sn+1], segTree[2*sn+2]);
    }
    static Matrix query(Matrix[] segTree, int sn, int start, int end, int ll, int ul){
        //No overlap
        if(start>ul || end<ll)
            return new Matrix(1, 0, 0, 1);

        //Total overlap
        if(ll>=start && ul<=end)
            return segTree[sn];

        int mid = (ll+ul)/2;
        return multiply(query(segTree, 2*sn+1, start, end, ll, mid),
                query(segTree, 2*sn+2, start, end, mid+1, ul));
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        r = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();

        Matrix[] matrices = new Matrix[n];
        for(int i=0;i<n;i++){
            matrices[i] = new Matrix(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        int sn = 2*nextPowerOf2(n)-1;
        Matrix[] segTree = new Matrix[sn];
        for(int i=0;i<sn;i++){
            segTree[i] = new Matrix(1, 0,  0, 1);
        }
        for(int i=0;i<n;i++){
            update(segTree, 0, i, matrices[i], 0, n-1);
        }

        StringBuilder sb = new StringBuilder();
        while (m-->0){
            int l = sc.nextInt()-1;
            int r = sc.nextInt()-1;

            Matrix q = query(segTree, 0, l, r, 0, n-1);
            sb.append(q.a).append(" ").append(q.b).append("\n")
                    .append(q.c).append(" ").append(q.d).append("\n").append("\n");
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
