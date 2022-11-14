package Codeforces.ITMO_PilotCourse.SegmentTrees1.Step4;

import java.io.*;
import java.util.*;

public class SignAlternation {
    static int nextPowerOf2(int n){
        n |= n>>1;
        n |= n>>2;
        n |= n>>4;
        n |= n>>8;
        n |= n>>16;
        n |= n>>30;
        return n+1;
    }
    static void update(long[] segTree, int si, int index, long val, int ll, int ul){
        if(ll==ul){
            segTree[si] = val;
            return;
        }

        int mid = (ll+ul)/2;
        if(index<=mid)
            update(segTree, 2*si+1, index, val, ll, mid);
        else update(segTree, 2*si+2, index, val, mid+1, ul);
        segTree[si] = segTree[2*si+1] + segTree[2*si+2];
    }
    static long query(long[] segTree, int si, int ll, int ul, int start, int end){
        //no overlap
        if(ul<start || ll>end)
            return 0;

        //total overlap
        if(ll>=start && ul<=end)
            return segTree[si];

        int mid = (ll+ul)/2;
        return query(segTree, 2*si+1, ll, mid, start, end) +
                query(segTree, 2*si+2, mid+1, ul, start, end);
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        long[] arr = sc.nextLongArray(n);

        int sn = 2*nextPowerOf2(n)-1;
        long[] oddTree = new long[sn];
        long[] evenTree = new long[sn];
        for(int i=0;i<n;i++){
            if(i%2==0)
                update(oddTree, 0, i, arr[i], 0, n-1);
            else update(evenTree, 0, i, arr[i], 0, n-1);
        }

        StringBuilder sb = new StringBuilder();
        int m = sc.nextInt();
        while (m-->0){
            int type = sc.nextInt();
            switch (type){
                case 0: int ind = sc.nextInt()-1;
                        long val = sc.nextLong();
                        if(ind%2==0)
                            update(oddTree, 0, ind, val, 0, n-1);
                        else update(evenTree, 0, ind, val, 0, n-1);
                    break;

                case 1: int l = sc.nextInt()-1;
                        int r = sc.nextInt()-1;
                        long odd = query(oddTree, 0, 0, n-1, l, r);
                        long even = query(evenTree, 0, 0, n-1, l, r);
                        long ans;
                        if(l%2==0)
                            ans = odd-even;
                        else ans = even-odd;
                        sb.append(ans).append("\n");
                    break;
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
