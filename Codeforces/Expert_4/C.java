package Codeforces.Expert_4;

import java.util.*;
import java.io.*;

public class C {

    static class Point{
        long x, y;

        Point(long x, long y){
            this.x = x;
            this.y = y;
        }
    }

    static class Line{
        Point p1, p2;
        long len;

        Line(Point p1, Point p2){
            this.p1 = p1;
            this.p2 = p2;

            long dx = Math.abs(p1.x - p2.x);
            long dy = Math.abs(p1.y - p2.y);

            len = dx * dx + dy * dy;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        long x1 = sc.nextLong();
        long y1 = sc.nextLong();

        long x2 = sc.nextLong();
        long y2 = sc.nextLong();

        long x3 = sc.nextLong();
        long y3 = sc.nextLong();

        List<Long> x_list = new ArrayList<>();
        List<Long> y_list = new ArrayList<>();

        x_list.add(x1); x_list.add(x2); x_list.add(x3);
        y_list.add(y1); y_list.add(y2); y_list.add(y3);

        Collections.sort(x_list);
        Collections.sort(y_list);

        long ym = y_list.get(1);

        Line line1 = new Line(new Point(x_list.get(0), ym), new Point(x_list.get(2), ym));
        List<Line> list = new ArrayList<>();
        list.add(line1);

        if(y1 != ym){
            Line line = new Line(new Point(x1, y1), new Point(x1, ym));
            list.add(line);
        }

        if(y2 != ym){
            Line line = new Line(new Point(x2, y2), new Point(x2, ym));
            list.add(line);
        }

        if(y3 != ym){
            Line line = new Line(new Point(x3, y3), new Point(x3, ym));
            list.add(line);
        }

        System.out.println(list.size());
        for(Line line: list){
            System.out.println(line.p1.x+" "+line.p1.y+" "+line.p2.x+" "+line.p2.y);
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