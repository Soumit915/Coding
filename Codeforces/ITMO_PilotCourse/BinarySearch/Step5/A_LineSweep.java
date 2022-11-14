package Codeforces.ITMO_PilotCourse.BinarySearch.Step5;

import java.io.*;
import java.util.*;

public class A_LineSweep {

    static class Segment{
        long start;
        long end;

        Segment(long start, long end){
            this.start = start;
            this.end = end;
        }
    }

    static class Start_Sorter implements Comparator<Segment>{
        public int compare(Segment s1, Segment s2){
            return Long.compare(s1.start, s2.start);
        }
    }

    static class End_Sorter implements Comparator<Segment>{
        public int compare(Segment s1, Segment s2){
            return Long.compare(s1.end, s2.end);
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();
        int n = sc.nextInt();
        long k = sc.nextLong()+1;

        Segment[] segments = new Segment[n];
        for(int i=0;i<n;i++){
            segments[i] = new Segment(sc.nextLong(), sc.nextLong());
        }

        PriorityQueue<Segment> start = new PriorityQueue<>(new Start_Sorter());
        PriorityQueue<Segment> end = new PriorityQueue<>(new End_Sorter());
        for(int i=0;i<n;i++){
            start.add(segments[i]);
            end.add(segments[i]);
        }

        long ans = Integer.MIN_VALUE;
        long tot = 0;
        long count = 0;
        assert start.peek() != null;
        long last = start.peek().start;
        while(!start.isEmpty()){
            assert end.peek() != null;
            if(start.peek().start <= end.peek().end){
                Segment si = start.remove();

                long cur = (si.start - last) * count;
                if(cur + tot >= k){
                    long wasLeft = k - tot;
                    ans = last - 1 + (wasLeft + count - 1) / count;
                    break;
                }

                tot += cur;
                count++;
                last = si.start;
            }
            else{
                Segment ei = end.remove();

                long cur = (ei.end - last + 1) * count;
                if(cur + tot >= k){
                    long wasLeft = k - tot;
                    ans = last - 1 + (wasLeft + count - 1) / count;
                    break;
                }

                tot += cur;
                count--;
                last = ei.end + 1;
            }
        }

        if(ans == Integer.MIN_VALUE){
            while(!end.isEmpty()){
                Segment ei = end.remove();

                long cur = (ei.end - last + 1) * count;
                if(cur + tot >= k){
                    long wasLeft = k - tot;
                    ans = last - 1 + (wasLeft + count - 1) / count;
                    break;
                }

                tot += cur;
                count--;
                last = ei.end + 1;
            }
        }

        System.out.println(ans+"");

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
