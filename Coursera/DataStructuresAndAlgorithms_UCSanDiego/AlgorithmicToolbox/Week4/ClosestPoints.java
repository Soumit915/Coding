package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week4;

import java.io.*;
import java.util.*;

public class ClosestPoints {
    static class Point
    {
        double x;
        double y;
        Point(double x, double y)
        {
            this.x = x;
            this.y = y;
        }
    }

    static class xSorter implements Comparator<Point>
    {
        public int compare(Point o1, Point o2) {
            return Double.compare(o1.x,o2.x);
        }
    }

    static class ySorter implements Comparator<Point>
    {
        public int compare(Point a, Point b)
        {
            return Double.compare(a.y,b.y);
        }
    }

    public static double calcDist(Point a, Point b)
    {
        return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }

    public static Point[] findClosestStrip(Point[] points, int ll, int mid, int ul, double dm)
    {
        Point[] stripPoints;
        Point[] closestPair = new Point[2];
        double lr = points[mid].x-dm;
        double rr = points[mid].x+dm;
        int lindex = ll;
        int rindex = ul;
        for(int i=mid;i>=ll;i--)
        {
            if(points[i].x<lr)
            {
                lindex = i+1;
                break;
            }
        }
        for(int i=mid+1;i<ul;i++)
        {
            if(points[i].x>rr)
            {
                rindex = i-1;
                break;
            }
        }

        if(rindex-lindex+1<=2)
        {
            closestPair[0] = points[lindex];
            closestPair[1] = points[lindex+1];
            return closestPair;
        }
        stripPoints = new Point[rindex-lindex+1];
        System.arraycopy(points, lindex, stripPoints, 0, rindex + 1 - lindex);
        Arrays.sort(stripPoints, new ySorter());

        double mind = dm;
        for(int i=0;i<stripPoints.length;i++)
        {
            Point pPoint = stripPoints[i];
            double ry = pPoint.y+dm;
            for(int j=i+1;j<Math.min(i+6, stripPoints.length);j++)
            {
                Point cP = stripPoints[j];
                if(cP.y<ry)
                {
                    double d = calcDist(cP,pPoint);
                    mind = Math.min(d,mind);
                    if(Double.compare(mind,d)==0)
                    {
                        closestPair[0] = pPoint;
                        closestPair[1] = cP;
                    }
                }
                else
                    break;
            }
        }

        if(mind==dm)
        {
            return null;
        }

        return closestPair;
    }
    public static Point[] bruteForce(Point[] points, int ll)
    {
        Point[] closestPair = new Point[2];
        double d1 = calcDist(points[ll],points[ll+1]);
        double d2 = calcDist(points[ll],points[ll+2]);
        double d3 = calcDist(points[ll+1],points[ll+2]);
        if(d1<d2)
        {
            closestPair[1] = points[ll+1];
            if(d1<d3)
            {
                closestPair[0] = points[ll];
            }
            else
            {
                closestPair[0] = points[ll+2];
            }
        }
        else
        {
            closestPair[1] = points[ll+2];
            if(d2<d3)
            {
                closestPair[0] = points[ll];
            }
            else
            {
                closestPair[0] = points[ll+1];
            }
        }

        return closestPair;
    }
    public static Point[] findClosest(Point[] points, int ll, int ul)
    {
        if(ul-ll == 2)
        {
            return bruteForce(points, ll);
        }
        else if(ul-ll==1)
        {
            Point[] closestPair = new Point[2];
            closestPair[0] = points[ll];
            closestPair[1] = points[ll+1];
            return closestPair;
        }
        int mid = (ll+ul)/2;
        Point[] lP = findClosest(points,ll,mid);
        Point[] rP = findClosest(points,mid+1,ul);
        double lm = calcDist(lP[0],lP[1]);
        double rm = calcDist(rP[0],rP[1]);
        double dm = Math.min(lm, rm);
        Point[] dP;
        if(Double.compare(dm,lm) == 0)
            dP = lP;
        else
            dP = rP;
        Point[] minstrip = findClosestStrip(points,ll, mid, ul, dm);
        double minstripm = Double.MAX_VALUE;
        if(minstrip!=null)
            minstripm = calcDist(minstrip[0],minstrip[1]);
        dm = Math.min(dm,minstripm);
        if(Double.compare(dm,minstripm)==0)
            return minstrip;
        else
            return dP;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();
        int n = sc.nextInt();

        Point[] Points = new Point[n];
        for(int i=0;i<n;i++)
        {
            int x = sc.nextInt();
            int y = sc.nextInt();
            Points[i] = new Point(x,y);
        }

        Arrays.sort(Points, new xSorter());

        Point[] closestPoints = findClosest(Points,0,n-1);
        System.out.println(calcDist(closestPoints[0],closestPoints[1]));
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
