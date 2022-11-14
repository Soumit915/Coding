package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week4;

import java.io.*;
import java.util.*;

public class SubstringEquality {
    static long p1 = (long) 1e9+7;
    static long p2 = (long) 1e9+9;
    static long base = 263;
    static long pow(long a, long b, long mod)
    {
        long p = 1;
        while (b>0)
        {
            if(b%2!=0)
            {
                p = (p*a)%mod;
            }
            a = (a*a)%mod;
            b /= 2;
        }
        return p;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        String s = sc.next();
        int n = s.length();

        StringBuilder sb = new StringBuilder();
        int q = sc.nextInt();

        long[] dp1 = new long[n];
        long[] dp2 = new long[n];

        dp1[0] = s.charAt(0);
        dp2[0] = s.charAt(0);
        for(int i=1;i<n;i++)
        {
            long ch = s.charAt(i);
            dp1[i] = (dp1[i-1] * base + ch)%p1;
            dp2[i] = (dp2[i-1] * base + ch)%p2;
        }

        while (q-->0)
        {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int l = sc.nextInt();

            if(a==b)
                sb.append("Yes\n");
            else{
                b = (b<a)?(a+b)-(a=b):b;

                long hash1a = dp1[a+l-1];
                long hash1b = dp1[b+l-1];

                long hash2a = dp2[a+l-1];
                long hash2b = dp2[b+l-1];

                if(a!=0)
                {
                    hash1a = (hash1a - (dp1[a-1]*pow(base, l, p1))%p1 + p1)%p1;
                    hash2a = (hash2a - (dp2[a-1]*pow(base, l, p2))%p2 + p2)%p2;
                }
                hash1b = (hash1b - (dp1[b-1]*pow(base, l, p1))%p1 + p1)%p1;
                hash2b = (hash2b - (dp2[b-1]*pow(base, l, p2))%p2 + p2)%p2;

                if(hash1a==hash1b && hash2a==hash2b)
                    sb.append("Yes\n");
                else sb.append("No\n");
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
