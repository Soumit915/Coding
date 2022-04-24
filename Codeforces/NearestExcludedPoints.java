package Codeforces;

import java.io.*;
import java.util.*;

public class NearestExcludedPoints {

    static class Point{
        int x;
        int y;

        long hash;

        Point excluded_neighbour = null;
        Point(int x, int y){
            this.x = x;
            this.y = y;

            computeHash();
        }

        public void computeHash(){
            this.hash = x;
            this.hash = (this.hash * ((long) 1e7));
            this.hash += y;
        }

        public Point getUp(){
            return new Point(this.x-1, this.y);
        }

        public Point getDown(){
            return new Point(this.x+1, this.y);
        }

        public Point getLeft(){
            return new Point(this.x, this.y-1);
        }

        public Point getRight(){
            return new Point(this.x, this.y+1);
        }

        public int getDist(Point p){
            return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
        }
    }

    public static void main(String[] args) throws IOException {
        /*Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");*/

        Soumit sc = new Soumit();

        StringBuilder sb = new StringBuilder();

        int t = 1;
        while (t-->0){
            int n = sc.nextInt();

            Point[] points = new Point[n];
            Map<Long, Point> map = new HashMap<>();
            for(int i=0;i<n;i++){
                points[i] = new Point(sc.nextInt(), sc.nextInt());
                map.put(points[i].hash, points[i]);
            }

            Queue<Point> q = new LinkedList<>();
            for(Point p: points){
                Point up = p.getUp();
                Point down = p.getDown();
                Point left = p.getLeft();
                Point right = p.getRight();

                if(!map.containsKey(up.hash)){
                    p.excluded_neighbour = up;
                    q.add(p);
                    continue;
                }

                if(!map.containsKey(down.hash)){
                    p.excluded_neighbour = down;
                    q.add(p);
                    continue;
                }

                if(!map.containsKey(left.hash)){
                    p.excluded_neighbour = left;
                    q.add(p);
                    continue;
                }

                if(!map.containsKey(right.hash)){
                    p.excluded_neighbour = right;
                    q.add(p);
                }
            }

            while(!q.isEmpty()){
                Point p = q.remove();

                Point up = p.getUp();
                Point down = p.getDown();
                Point left = p.getLeft();
                Point right = p.getRight();

                if(map.containsKey(up.hash) && map.get(up.hash).excluded_neighbour==null){
                    Point neighbour = map.get(up.hash);
                    neighbour.excluded_neighbour = p.excluded_neighbour;
                    q.add(neighbour);
                }

                if(map.containsKey(down.hash) && map.get(down.hash).excluded_neighbour==null){
                    Point neighbour = map.get(down.hash);
                    neighbour.excluded_neighbour = p.excluded_neighbour;
                    q.add(neighbour);
                }

                if(map.containsKey(left.hash) && map.get(left.hash).excluded_neighbour==null){
                    Point neighbour = map.get(left.hash);
                    neighbour.excluded_neighbour = p.excluded_neighbour;
                    q.add(neighbour);
                }

                if(map.containsKey(right.hash) && map.get(right.hash).excluded_neighbour==null){
                    Point neighbour = map.get(right.hash);
                    neighbour.excluded_neighbour = p.excluded_neighbour;
                    q.add(neighbour);
                }
            }

            for(Point p: points){
                sb.append(p.excluded_neighbour.x).append(" ").append(p.excluded_neighbour.y).append("\n");
                /*int dist = p.getDist(p.excluded_neighbour);
                sb.append(dist).append("\n");*/
            }
        }

        System.out.println(sb.toString());

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
