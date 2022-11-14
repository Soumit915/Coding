package Codeforces;

import java.util.*;
import java.io.*;

public class BalancedRemovals {

    static class Point{
        int id, x, y, z;

        Point(int id, int x, int y, int z){
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        Point[] points = new Point[n];
        for(int i=0;i<n;i++){
            points[i] = new Point(i+1, sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        HashSet<Point> set = new HashSet<>();

        HashMap<Integer, HashMap<Integer, ArrayList<Point>>> hash = new HashMap<>();
        for(int i=0;i<n;i++){
            int x = points[i].x;
            int y = points[i].y;

            HashMap<Integer, ArrayList<Point>> y_hash = hash.getOrDefault(x, new HashMap<>());
            ArrayList<Point> z_list = y_hash.getOrDefault(y, new ArrayList<>());
            z_list.add(points[i]);

            y_hash.put(y, z_list);
            hash.put(x, y_hash);
        }

        for(int x: hash.keySet()){
            HashMap<Integer, ArrayList<Point>> y_hash = hash.get(x);

            for(int y: y_hash.keySet()){
                ArrayList<Point> point_list = y_hash.get(y);

                point_list.sort((p1, p2) -> Integer.signum(p1.z - p2.z));

                for(int i=0;i<point_list.size();i+=2){
                    if(i+1 >= point_list.size())
                        break;

                    sb.append(point_list.get(i).id).append(" ").append(point_list.get(i + 1).id).append("\n");
                    set.add(point_list.get(i));
                    set.add(point_list.get(i+1));
                }
            }
        }

        HashMap<Integer, ArrayList<Point>> x_hash = new HashMap<>();
        for(Point p: points){
            if(!set.contains(p)){
                int x = p.x;

                ArrayList<Point> y_list = x_hash.getOrDefault(x, new ArrayList<>());
                y_list.add(p);
                x_hash.put(x, y_list);
            }
        }

        for(int x: x_hash.keySet()){
            ArrayList<Point> point_list = x_hash.get(x);

            point_list.sort((p1, p2) -> Integer.signum(p1.y - p2.y));

            for(int i=0;i<point_list.size();i+=2){
                if(i+1 >= point_list.size())
                    break;

                sb.append(point_list.get(i).id).append(" ").append(point_list.get(i+1).id).append("\n");
                set.add(point_list.get(i));
                set.add(point_list.get(i+1));
            }
        }

        ArrayList<Point> x_list = new ArrayList<>();
        for(Point p: points){
            if(!set.contains(p)){
                x_list.add(p);
            }
        }

        x_list.sort((p1, p2) -> Integer.signum(p1.x - p2.x));

        for(int i=0;i<x_list.size();i+=2){
            sb.append(x_list.get(i).id).append(" ").append(x_list.get(i+1).id).append("\n");
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
