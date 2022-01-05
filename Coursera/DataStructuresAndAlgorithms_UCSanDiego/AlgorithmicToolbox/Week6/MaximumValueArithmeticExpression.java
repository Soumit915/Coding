package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week6;

import java.io.*;
import java.util.*;

public class MaximumValueArithmeticExpression {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        String exp = sc.next();
        int l = exp.length();

        int ns = l/2;
        int nd = l-ns;
        char[] symbols = new char[ns];
        long[] vals = new long[nd];

        for(int i=0;i<l;i++)
        {
            if(i%2==0)
                vals[i/2] = exp.charAt(i)-48;
            else symbols[i/2] = exp.charAt(i);
        }

        long[][] dpmax = new long[nd][nd];
        long[][] dpmin = new long[nd][nd];
        for(int i=0;i<nd;i++)
            dpmax[i][i] = vals[i];
        for(int i=0;i<nd;i++)
            dpmin[i][i] = vals[i];
        for(int i=1;i<nd;i++)
        {
            for(int j=0;j+i<nd;j++)
            {
                long max = Long.MIN_VALUE;
                long min = Long.MAX_VALUE;
                for(int k=j;k<(j+i);k++)
                {
                    long opmaxval;
                    long opminval;
                    if(symbols[k] == '+') {
                        opmaxval = dpmax[j][k] + dpmax[k + 1][j + i];
                        opminval = dpmin[j][k] + dpmin[k + 1][j + i];
                    }
                    else if(symbols[k] == '*') {
                        opmaxval = dpmax[j][k] * dpmax[k + 1][j + i];
                        long d1 = dpmax[j][k] * dpmin[k+1][j+i];
                        long d2 = dpmin[j][k] * dpmax[k+1][j+i];
                        long d3 = dpmin[j][k] * dpmin[k+1][j+i];
                        opminval = Math.min(d1, Math.min(d2, d3));
                    }
                    else {
                        opmaxval = dpmax[j][k] - dpmin[k + 1][j + i];
                        opminval = dpmin[j][k] - dpmax[k + 1][j + i];
                    }

                    max = Math.max(max, opmaxval);
                    min = Math.min(min, opminval);
                }
                dpmax[j][j+i] = max;
                dpmin[j][j+i] = min;
            }
        }

        System.out.println(dpmax[0][nd-1]);

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
