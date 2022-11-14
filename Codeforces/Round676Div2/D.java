package Codeforces.Round676Div2;

import java.io.*;
import java.util.*;

public class D {
    public static long findDist(long[] arr, long x, long y, long a, long b)
    {
        if(Math.abs(x-a)==Math.abs(y-b) && a==b)
        {
            long p1;
            long p2;
            if(a-x>0)
            {
                p1 = Math.abs(x - a) * arr[0];
                p2 = Math.abs(x - a) * arr[5] + Math.abs(y - b) * arr[1];
            }
            else
            {
                p1 = Math.abs(x - a) * arr[3];
                p2 = Math.abs(x - a) * arr[2] + Math.abs(y - b) * arr[4];
            }
            return Math.min(p1, p2);
        }

        if(x==a)
        {
            long p1;
            long p2;
            if(b-y>0)
            {
                p1 = Math.abs(y - b) * arr[1];
                p2 = Math.abs(y - b) * arr[2] + Math.abs(y - b) * arr[0];
            }
            else
            {
                p1 = Math.abs(y - b) * arr[4];
                p2 = Math.abs(y - b) * arr[5] + Math.abs(y - b) * arr[3];
            }
            return Math.min(p1, p2);
        }

        if(y==b)
        {
            long p1;
            long p2;
            if(a-x>0)
            {
                p1 = Math.abs(x - a) * arr[5];
                p2 = Math.abs(x - a) * arr[4] + Math.abs(x - a) * arr[0];
            }
            else
            {
                p1 = Math.abs(x - a) * arr[2];
                p2 = Math.abs(x - a) * arr[1] + Math.abs(x - a) * arr[3];
            }
            return Math.min(p1, p2);
        }

        return Math.min(findDist(arr, x, y, a, y) + findDist(arr, a, y, a, b)
                    , Math.min(findDist(arr, x, y, a, a) + findDist(arr, a, a, a, b),
                        findDist(arr, x, y, b, b) + findDist(arr, b, b, a, b)));
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();
        //sc.streamOutput("Output1.txt");

        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            long a = sc.nextLong();
            long b = sc.nextLong();

            long[] arr = sc.nextLongArray(6);

            sb.append(findDist(arr, 0, 0,  a, b)).append("\n");
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
