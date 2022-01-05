package Codeforces.GraknForces;

import java.io.*;
import java.util.*;

public class D {
    static class Point
    {
        long x;
        long y;
        Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
    static class xSorter implements Comparator<Point>
    {
        public int compare(Point o1, Point o2) {
            int r =  Double.compare(o1.x,o2.x);
            if(r==0)
                return Double.compare(o1.y,o2.y)*-1;
            else return r*-1;
        }
    }
    static class ySorter implements Comparator<Point>
    {
        public int compare(Point o1, Point o2) {
            int r = Double.compare(o1.y,o2.y);
            if(r==0)
                return Double.compare(o1.x,o2.x);
            else return r;
        }
    }
    public static void main(String[] args) throws IOException {
        //long start = System.currentTimeMillis();
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();

        //TreeSet<Point> xorder = new TreeSet<>(new xSorter());
        //PriorityQueue<Point> yorder1 = new PriorityQueue<>(new ySorter());

        Point[] robbers = new Point[n];
        Point[] lights = new Point[m];
        for(int i=0;i<n;i++)
        {
            int x = sc.nextInt();
            int y = sc.nextInt();
            robbers[i] = new Point(x, y);
        }

        Arrays.sort(robbers, new xSorter());

        for(int i=0;i<m;i++)
        {
            int x = sc.nextInt();
            int y = sc.nextInt();
            lights[i] = new Point(x, y);
            //yorder1.add(lights[i]);
        }

        Arrays.sort(lights, new xSorter());

        long ans = 0;
        long xadd = 0;
        long yadd = 0;
        for(int i=0;i<n;i++)
        {
            Point crob = robbers[i];
            crob.x += xadd;
            crob.y += yadd;
            //System.out.println("Rob: "+crob.x+" "+crob.y);
            for(int j=0;j<m;j++)
            /*while(!yorder1.isEmpty()) */{
                //Point nlight = yorder1.remove();
                Point nlight = lights[j];
                if (nlight.x < crob.x || nlight.y<crob.y) {
                    continue;
                }
                long upv = nlight.y - crob.y;
                long riv = nlight.x - crob.x;
                if (upv > riv) {
                    long inc = (nlight.x - crob.x) + 1;
                    ans += (nlight.x - crob.x) + 1;
                    robbers[i].x += inc;
                    xadd += inc;
                } else {
                    long inc = (nlight.y - crob.y) + 1;
                    ans += (nlight.y - crob.y) + 1;
                    robbers[i].y += inc;
                    yadd += inc;
                }
            }
            //System.out.println(ans);
        }

        System.out.println(ans);

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
            byte[] buf = new byte[100064]; // line length
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
