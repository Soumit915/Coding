package Codeforces.ITMO_PilotCourse.TwoPointerMethods.Step2;

import java.io.*;
import java.util.*;

public class SegmentWithSmallSpread {
    static class Pair{
        int index;
        long val;
        Pair(int index, long val){
            this.index = index;
            this.val = val;
        }
        public String toString(){
            return "("+this.index+" "+this.val+")";
        }
    }
    static Deque<Pair> insert_in_min(Deque<Pair> min_dq, Pair p){
        if(!min_dq.isEmpty() && min_dq.peekFirst().val>p.val){
            min_dq = new LinkedList<>();
            min_dq.addFirst(p);
            return min_dq;
        }

        while(!min_dq.isEmpty() && min_dq.peekLast().val>p.val){
            min_dq.removeLast();
        }
        min_dq.addLast(p);
        return min_dq;
    }
    static Deque<Pair> insert_in_max(Deque<Pair> max_dq, Pair p){
        if(!max_dq.isEmpty() && max_dq.peekFirst().val<p.val){
            max_dq = new LinkedList<>();
            max_dq.addFirst(p);
            return max_dq;
        }

        while(!max_dq.isEmpty() && max_dq.peekLast().val<p.val){
            max_dq.removeLast();
        }
        max_dq.addLast(p);
        return max_dq;
    }
    static void delete_in_min(Deque<Pair> min_dq, Pair p){
        while(min_dq.peekFirst().index<=p.index){
            min_dq.removeFirst();
        }
    }
    static void delete_in_max(Deque<Pair> max_dq, Pair p){
        while(max_dq.peekFirst().index<=p.index){
            max_dq.removeFirst();
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        long k = sc.nextLong();
        long[] a = sc.nextLongArray(n);

        Deque<Pair> min_dq = new LinkedList<>();
        Deque<Pair> max_dq = new LinkedList<>();
        int ptr = 0;
        long count = 0;
        for(int i=0;i<n;i++){
            min_dq = insert_in_min(min_dq, new Pair(i, a[i]));
            max_dq = insert_in_max(max_dq, new Pair(i, a[i]));

            while(max_dq.peekFirst().val-min_dq.peekFirst().val>k){
                delete_in_min(min_dq, new Pair(ptr, a[ptr]));
                delete_in_max(max_dq, new Pair(ptr, a[ptr]));
                ptr++;
            }

            count += i-ptr+1;
        }

        System.out.println(count);

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
