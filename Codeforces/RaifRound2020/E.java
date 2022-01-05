package Codeforces.RaifRound2020;

import java.io.*;
import java.util.*;

public class E {
    public static boolean isValid(long[] arr, long mid, int k)
    {
        TreeSet<Long> vals = new TreeSet<>();
        for (long l : arr) {
            vals.add(l);
        }

        for(int i=0;i<k;i++)
        {
            if(vals.size()==0)
                return true;
            long fl = Math.round(((double) mid)/(k-i));
            long sqrt = Math.round(Math.sqrt(fl));
            System.out.println("Test: "+mid+" "+i+" "+fl+" "+sqrt);
            System.out.println(vals);
            if(vals.floor(sqrt)==null)
            {
                long ceil = vals.ceiling(sqrt);
                vals.remove(ceil);
                vals.add(ceil-sqrt);
                mid = mid-((long) Math.pow(sqrt, 2));
                //ans.add((long) Math.pow(sqrt, 2));
            }
            else
            {
                mid = mid-((long) Math.pow(vals.floor(sqrt), 2));
                vals.remove(vals.floor(sqrt));
                //ans.add((long) Math.pow(vals.floor(sqrt), 2));
            }
        }

        return vals.size() == 0 && mid >= 0;
    }
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        int k = sc.nextInt();

        long[] arr = sc.nextLongArray(n);

        long ll = 1, ul = (long) 1e19;
        while(ll<ul)
        {
            long mid = ll+(ul-ll)/2;
            if(isValid(arr, mid, k))
            {
                ul = mid;
            }
            else
            {
                ll = mid+1;
            }

            System.out.println(ll+" "+ul+" "+mid);
        }

        System.out.println(ll);

        long end = System.currentTimeMillis();
        System.out.println(((double) end-start)/1000);

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
