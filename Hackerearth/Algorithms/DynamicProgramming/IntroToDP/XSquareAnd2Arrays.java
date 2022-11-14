package Hackerearth.Algorithms.DynamicProgramming.IntroToDP;

import java.io.*;
import java.util.*;

public class XSquareAnd2Arrays {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int q = sc.nextInt();

        long[] a = new long[n];
        long[] b = new long[n];

        for(int i=0;i<n;i++)
        {
            a[i] = sc.nextLong();
        }
        for(int i=0;i<n;i++)
        {
            b[i] = sc.nextLong();
        }

        long[] lsum = new long[n];
        long[] rsum = new long[n];
        for(int i=0;i<n;i++)
        {
            if(i%2==0)
            {
                lsum[i] = a[i];
                rsum[i] = b[i];
            }
            else
            {
                lsum[i] = b[i];
                rsum[i] = a[i];
            }
        }

        for(int i=1;i<n;i++)
        {
            lsum[i] += lsum[i-1];
            rsum[i] += rsum[i-1];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++)
        {
            int type = sc.nextInt();
            int l = sc.nextInt()-1;
            int r = sc.nextInt()-1;

            long val;
            if(type==1 && l%2==0)
            {
                if(l==0)
                    val = lsum[r];
                else val = lsum[r]-lsum[l-1];
                sb.append(val).append("\n");
            }
            else if(type==1 && l%2==1)
            {
                if(l==0)
                    val = rsum[r];
                else val = rsum[r]-rsum[l-1];
                sb.append(val).append("\n");
            }
            else if(type==2 && l%2==0)
            {
                if(l==0)
                    val = rsum[r];
                else val = rsum[r]-rsum[l-1];
                sb.append(val).append("\n");
            }
            else
            {
                if(l==0)
                    val = lsum[r];
                else val = lsum[r]-lsum[l-1];
                sb.append(val).append("\n");
            }
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
