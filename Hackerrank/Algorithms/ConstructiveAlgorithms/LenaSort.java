package Hackerrank.Algorithms.ConstructiveAlgorithms;

import java.io.*;
import java.util.*;

public class LenaSort {
    static long[] maxops = new long[100001];
    static long[] minops = new long[100001];
    public static void preComputeMaxVals()
    {
        int n = 100001;
        for(long i=0;i<n;i++)
        {
            maxops[(int) i] = (i*(i-1))/2;
        }

        minops[0] = 0;
        minops[1] = 0;
        int c = 0;
        for(long i=2;i<n;i++)
        {
            if(isPowerOf2(i))
                c++;

            minops[(int) i] = minops[(int) (i-1)]+c;
        }
    }
    public static boolean isPowerOf2(long n)
    {
        return (n&(n-1))==0;
    }

    static ArrayList<Integer> ans;
    public static void createArray(int n, long c, int ll, int ul)
    {
        if(n==0)
            return;
        if(n==1)
        {
            ans.add(ll);
            return;
        }

        for(int i=ll;i<=ul;i++)
        {
            int left_len = i-ll;
            int right_len = ul-i;

            if(c-n+1-maxops[left_len] >= minops[right_len] && c-n+1-maxops[left_len] <= maxops[right_len])
            {
                ans.add(i);
                createArray(left_len, maxops[left_len], ll, i-1);
                createArray(right_len, c-n+1-maxops[left_len], i+1, ul);
                return;
            }
            else if(c-n+1-minops[left_len] <= maxops[right_len] && c-n+1-minops[left_len] >= minops[right_len])
            {
                ans.add(i);
                createArray(left_len, minops[left_len], ll, i-1);
                createArray(right_len, c-n+1-minops[left_len], i+1, ul);
                return;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int q = sc.nextInt();
        preComputeMaxVals();

        StringBuilder sb = new StringBuilder();
        while (q-->0)
        {
            int n = sc.nextInt();
            int c = sc.nextInt();

            ans = new ArrayList<>();

            if(c>maxops[n] || c<minops[n])
            {
                sb.append("-1\n");
            }
            else
            {
                createArray(n, c, 0, n-1);
                for(int i: ans)
                    sb.append(i+1).append(" ");
                sb.append("\n");
            }
        }

        System.out.print(sb);

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
