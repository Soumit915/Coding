package Codeforces.ITMO_PilotCourse.BinarySearch.Step4;

import java.io.*;
import java.util.*;

public class A {
    static int start, end;
    public static boolean isValid(double[] arr, int d, double x)
    {
        int n = arr.length;
        double[] prefsum = new double[n];
        double[] prefmin = new double[n];

        prefsum[0] = arr[0]-x;
        for(int i=1;i<n;i++)
        {
            prefsum[i] = prefsum[i-1] + (arr[i]-x);
        }

        prefmin[0] = Math.min(prefsum[0], 0);
        for(int i=1;i<n;i++)
        {
            prefmin[i] = Math.min(prefmin[i-1], prefsum[i]);
        }

        if(prefsum[d-1]>=0)
            return true;

        for(int i=d;i<n;i++)
        {
            double segdeviation = prefsum[i] - prefmin[i-d];
            if(segdeviation>=0)
            {
                return true;
            }
        }
        return false;
    }
    public static void getIndices(double[] arr, int d, double x)
    {
        int n = arr.length;
        double[] prefsum = new double[n];
        double[] prefmin = new double[n];
        int[] prefminInd = new int[n];

        prefsum[0] = arr[0]-x;
        for(int i=1;i<n;i++)
        {
            prefsum[i] = prefsum[i-1] + (arr[i]-x);
        }

        prefmin[0] = Math.min(prefsum[0], 0);
        if(prefmin[0] != prefsum[0])
        {
            prefminInd[0] = -1;
        }
        else
            prefminInd[0] = 0;
        for(int i=1;i<n;i++)
        {
            prefmin[i] = Math.min(prefmin[i-1], prefsum[i]);
            if(prefmin[i] == prefsum[i])
            {
                prefminInd[i] = i;
            }
            else
            {
                prefminInd[i] = prefminInd[i-1];
            }
        }

        if(prefsum[d-1]>=0) {
            start = 0;
            end = d-1;
            return;
        }

        for(int i=d;i<n;i++)
        {
            double segdeviation = prefsum[i] - prefmin[i-d];
            if(segdeviation >= 0)
            {
                start = prefminInd[i-d]+1;
                end = i;
                return;
            }
        }
    }
    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int d = sc.nextInt();

        double[] arr = sc.nextDoubleArray(n);

        double ll = 0;
        double ul = 1e4;
        while(ul-ll>0.0000001)
        {
            double mid = (ll+ul)/2;
            if(isValid(arr, d, mid))
            {
                ll = mid;
            }
            else
            {
                ul = mid-0.0000001;
            }
        }

        getIndices(arr, d, ll);
        System.out.println((start+1)+" "+(end+1));

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
