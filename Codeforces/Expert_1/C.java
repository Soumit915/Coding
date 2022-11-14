package Codeforces.Expert_1;

import java.io.*;
import java.util.*;

public class C {

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
        long min;
        long max;
    }

    static void build(Node[] segTree, int si, long[] arr, int l, int r){
        if(l == r){
            segTree[si] = new Node();
            segTree[si].min = segTree[si].max = arr[l];
            return;
        }

        int mid = (l + r) / 2;
        build(segTree, 2*si + 1, arr, l, mid);
        build(segTree, 2*si + 2, arr, mid + 1, r);
        segTree[si] = new Node();
        segTree[si].min = Math.min(segTree[2*si + 1].min, segTree[2*si + 2].min);
        segTree[si].max = Math.max(segTree[2*si + 1].max, segTree[2*si + 2].max);
    }

    static long queryMin(Node[] segTree, int si, int start, int end, int l, int r){

        if(l>end || r<start){
            return (long) 1e15;
        }

        if(l >= start && r <= end){
            return segTree[si].min;
        }

        int mid = (l + r) / 2;
        return Math.min(queryMin(segTree, 2*si + 1, start, end, l, mid) ,
                queryMin(segTree, 2*si + 2, start, end, mid + 1, r) );
    }

    static long queryMax(Node[] segTree, int si, int start, int end, int l, int r){

        if(l>end || r<start){
            return (long) -1e15;
        }

        if(l >= start && r <= end){
            return segTree[si].max;
        }

        int mid = (l + r) / 2;
        return Math.max(queryMax(segTree, 2*si + 1, start, end, l, mid) ,
                queryMax(segTree, 2*si + 2, start, end, mid + 1, r) );
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(testcases-->0){
            int n = sc.nextInt();
            long[] arr = new long[n];
            for(int i=0;i<n;i++){
                arr[i] = sc.nextLong();
            }

            long[] prefix = new long[n];
            prefix[0] = arr[0];
            for(int i=1;i<n;i++){
                prefix[i] = prefix[i-1] + arr[i];
            }

            int sn = 2 * getNextPowerOf2(n) - 1;
            Node[] segTree = new Node[sn];
            build(segTree, 0, prefix, 0, n-1);

            Stack<Integer> stk = new Stack<>();

            int[] previousGreater = new int[n];
            int[] nextGreater = new int[n];

            Arrays.fill(nextGreater, n);
            Arrays.fill(previousGreater, -1);

            for(int i=0;i<n;i++){
                while(!stk.isEmpty() && arr[i]>arr[stk.peek()]){
                    nextGreater[stk.pop()] = i;
                }
                stk.push(i);
            }

            for(int i=n-1;i>=0;i--){
                while(!stk.isEmpty() && arr[i]>arr[stk.peek()]){
                    previousGreater[stk.pop()] = i;
                }
                stk.push(i);
            }

            boolean flag = true;
            for(int i=0;i<n;i++){
                int prev = previousGreater[i];
                int next = nextGreater[i];

                long prefix_val = 0;
                if(i > 0 && prev + 1 < i){
                    if(prev == -1){
                        prefix_val = prefix[i-1] - Math.min(0, queryMin(segTree, 0, prev, i-1, 0, n-1));
                    }
                    else{
                        prefix_val = prefix[i-1] - queryMin(segTree, 0, prev+1, i-1, 0, n-1);
                    }
                }

                long suffix_val = 0;
                if(i < n-1 && next-1 > i)
                    suffix_val = queryMax(segTree, 0, i, next-1, 0, n-1) - prefix[i];

                if(prefix_val + suffix_val + arr[i] > arr[i]){
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
