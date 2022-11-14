package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week6;

import java.io.*;
import java.util.*;

public class PartitioningSouvenirs {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[] arr = sc.nextIntArray(n);
        Arrays.sort(arr);

        int sum = 0;
        for(int i=0;i<n;i++)
        {
            sum += arr[i];
        }

        if(sum%3!=0)
        {
            System.out.println(0);
            System.exit(0);
        }

        int w = sum/3;
        boolean[][] dp = new boolean[n+1][w+1];
        dp[0][0] = true;
        for(int i=1;i<=n;i++)
        {
            for(int j=0;j<=w;j++)
            {
                if(j-arr[i-1]<0)
                    dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] | dp[i-1][j-arr[i-1]];
            }
        }

        Set<Integer> indices = new HashSet<>();
        if(!dp[n][w])
        {
            System.out.println(0);
            System.exit(0);
        }

        int ptr = w;
        int row = n;
        while(ptr>0 && row>0)
        {
            if(ptr-arr[row-1]>=0 && dp[row-1][ptr-arr[row-1]]) {
                indices.add(row);
                ptr = ptr - arr[row-1];
            }
            row--;
        }

        dp = new boolean[n+1][w+1];
        dp[0][0] = true;
        for(int i=1;i<=n;i++)
        {
            for(int j=0;j<=w;j++)
            {
                if(indices.contains(i))
                    dp[i][j] = dp[i-1][j];
                else if(j-arr[i-1]<0)
                    dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] | dp[i-1][j-arr[i-1]];
            }
        }

        if(!dp[n][w])
        {
            System.out.println(0);
        }
        else
        {
            System.out.println(1);
        }

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
