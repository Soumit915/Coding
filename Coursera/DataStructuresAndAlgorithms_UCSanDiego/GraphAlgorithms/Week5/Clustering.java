package Coursera.DataStructuresAndAlgorithms_UCSanDiego.GraphAlgorithms.Week5;

import java.io.*;
import java.util.*;

public class Clustering {
    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getDistance(Point p){
            return (int) (Math.pow(p.x-this.x, 2) + Math.pow(this.y-p.y, 2));
        }
    }
    static class City{
        int id;
        Point pos;
        City parent;
        int count;
        City(int id, Point pos){
            this.id = id;
            this.pos = pos;
            this.parent = this;
            this.count = 1;
        }
        public City get(){
            if(this.parent == this){
                return this;
            }
            this.parent = this.parent.get();
            return this.parent;
        }
        public void union(City c){
            City pthis = this.get();
            City pc = c.get();
            if(pthis.count>pc.count){
                pc.parent = pthis;
                pthis.count += pc.count;
            }
            else{
                pthis.parent = pc;
                pc.count += pthis.count;
            }
        }
    }
    static class Road implements Comparable<Road>{
        City c1;
        City c2;
        int length;
        Road(City c1, City c2, int length){
            this.c1 = c1;
            this.c2 = c2;
            this.length = length;
        }
        public int compareTo(Road r){
            return Integer.compare(this.length, r.length);
        }
    }
    static class Graph{
        ArrayList<City> cities;
        ArrayList<Road> roads = new ArrayList<>();
        ArrayList<Road> mstRoads = new ArrayList<>();
        Graph(int n, Point[] points){
            cities = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                cities.add(new City(i, points[i]));
            }
        }
        public void calcRoads(){
            for(int i=0;i<cities.size();i++){
                for(int j=i+1;j<cities.size();j++){
                    City c1 = cities.get(i);
                    City c2 = cities.get(j);
                    Road road = new Road(c1, c2, c1.pos.getDistance(c2.pos));
                    roads.add(road);
                }
            }
            Collections.sort(roads);
        }
        public double calcMST(int k){
            int count = 0;
            for (Road r : roads) {
                City c1 = r.c1;
                City c2 = r.c2;
                if (c1.get() != c2.get()) {
                    if (count == cities.size() - k) {
                        return Math.sqrt(r.length);
                    }
                    mstRoads.add(r);
                    c1.union(c2);
                    count++;
                }
            }
            return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        Point[] points = new Point[n];
        for(int i=0;i<n;i++){
            points[i] = new Point(sc.nextInt(), sc.nextInt());
        }

        int k = sc.nextInt();

        Graph gr = new Graph(n, points);
        gr.calcRoads();
        System.out.println(gr.calcMST(k));

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
            if (din != null) din.close();
            if (pw != null) pw.close();
        }
    }
}
