package Hackerrank.Algorithms.DynamicProgramming;

import java.io.*;
import java.util.*;

public class MrKMarsh {
    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit();

        int m = sc.nextInt();
        int n = sc.nextInt();

        char[][] matrix = new char[m][n];
        for(int i=0;i<m;i++)
        {
            matrix[i] = sc.next().toCharArray();
        }

        int[][] down = new int[m][n];
        int[][] right = new int[m][n];
        for(int i=0;i<n;i++)
        {
            if(matrix[m-1][i]=='x')
                down[m-1][i] = -1;
            else down[m-1][i] = 0;
        }
        for(int i=0;i<m;i++)
        {
            if(matrix[i][n-1]=='x')
                right[i][n-1] = -1;
            else right[i][n-1] = 0;
        }
        for(int i=m-2;i>=0;i--)
            for(int j=0;j<n;j++)
            {
                if(matrix[i][j]=='x')
                {
                    down[i][j] = -1;
                }
                else
                {
                    down[i][j] = 1+down[i+1][j];
                }
            }

        for(int i=n-2;i>=0;i--)
            for(int j=0;j<m;j++)
            {
                if(matrix[j][i]=='x')
                    right[j][i] = -1;
                else right[j][i] = 1+right[j][i+1];
            }

        int max = 0;

        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                if(right[0][i]-right[0][j]==(j-i) && right[0][j]!=-1)
                {
                    dp[i][j] = 1;
                }
            }
        }

        for(int i=1;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                for(int k=j+1;k<n;k++)
                {
                    if(matrix[i][j]!='x' && matrix[i][k]!='x')
                    {
                        if(dp[j][k]==0)
                        {
                            if(right[i][j]-right[i][k]==(k-j) && right[i][k]!=-1)
                            {
                                dp[j][k] = 1;
                            }
                        }
                        else
                        {
                            dp[j][k]++;
                        }
                    }
                    else
                    {
                        dp[j][k] = 0;
                    }

                    if(dp[j][k]>1 && right[i][k]!=-1 && right[i][j]-right[i][k]==(k-j))
                    {
                        max = Math.max(max, 2*(dp[j][k]-1)+2*(k-j));
                    }
                }
            }
        }

        if(max==0)
            System.out.println("impossible");
        else System.out.println(max);

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
