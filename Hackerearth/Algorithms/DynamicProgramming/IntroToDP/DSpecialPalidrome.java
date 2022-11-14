package Hackerearth.Algorithms.DynamicProgramming.IntroToDP;

import java.io.*;
import java.util.*;

public class DSpecialPalidrome {
    public static boolean isValid(int n , int i)
    {
        return i>=0 && i<n;
    }
    public static int find(int[][] dp, int i, int j)
    {
        if(!isValid(dp.length, i) || !isValid(dp.length, j))
            return 0;
        if(i>j)
            return 0;
        else return dp[i][j];
    }
    public static boolean find(boolean[][] dp, int i, int j)
    {
        if(!isValid(dp.length, i) || !isValid(dp.length, j))
            return false;
        if(i==j)
            return false;
        else return dp[i][j];
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t-->0)
        {
            char ch = sc.next().charAt(0);
            String s = sc.next();
            int n = s.length();

            int[][] dp = new int[n][n];
            boolean[][] isPresent = new boolean[n][n];
            int[][] dpwithch = new int[n][n];
            for(int i=n-1;i>=0;i--)
            {
                for(int j=i;j<n;j++)
                {
                    if(s.charAt(i)==s.charAt(j))
                    {
                        isPresent[i][j] = s.charAt(i)==ch;
                        if(i==j) {
                            dp[i][j] = 1;
                        }
                        else {
                            dp[i][j] = Math.max(find(dp, i + 1, j - 1) + 2,
                                    Math.max(find(dp, i + 1, j), find(dp, i, j - 1)));

                            if(dp[i][j]==find(dp, i+1, j-1)+2)
                                isPresent[i][j] |= find(isPresent, i+1, j-1);
                            if(dp[i][j]==find(dp, i+1, j))
                                isPresent[i][j] |= find(isPresent, i+1, j);
                            if(dp[i][j]==find(dp, i, j+1))
                                isPresent[i][j] |= find(isPresent, i, j+1);
                        }
                    }
                    else
                    {
                        dp[i][j] = Math.max(Math.max(find(dp, i+1, j), find(dp, i, j-1)),
                                find(dp, i+1, j-1));

                        if(dp[i][j]==find(dp, i+1, j-1))
                            isPresent[i][j] |= find(isPresent, i+1, j-1);
                        if(dp[i][j]==find(dp, i+1, j))
                            isPresent[i][j] |= find(isPresent, i+1, j);
                        if(dp[i][j]==find(dp, i, j+1))
                            isPresent[i][j] |= find(isPresent, i, j+1);
                    }
                }
            }

            for(int i=n-1;i>=0;i--)
            {
                for(int j=i;j<n;j++)
                {
                    if(s.charAt(i)==s.charAt(j))
                    {
                        if(i==j)
                        {
                            if(s.charAt(i)==ch)
                            {
                                dpwithch[i][j] = 1;
                            }
                            else {
                                dpwithch[i][j] = Math.max(0, Math.max(find(dpwithch, i + 1, j),
                                        find(dpwithch, i, j + 1)));
                            }
                        }
                        else
                        {
                            if(s.charAt(i)==ch)
                            {
                                dpwithch[i][j] = dp[i][j];
                            }
                            else
                            {
                                if(find(isPresent, i+1, j-1))
                                    dpwithch[i][j] = find(dp, i+1, j-1)+2;
                                if(find(isPresent, i, j-1))
                                    dpwithch[i][j] = Math.max(dp[i][j], find(dp, i, j-1));
                                if(find(isPresent, i+1, j))
                                    dpwithch[i][j] = Math.max(dp[i][j], find(dp, i+1, j));

                                if(dpwithch[i+1][j-1]!=0)
                                    dpwithch[i][j] = Math.max(dpwithch[i][j], find(dpwithch, i+1, j-1)+2);
                                dpwithch[i][j] = Math.max(find(dpwithch, i+1, j),
                                        Math.max(dpwithch[i][j], find(dpwithch, i, j-1)));
                            }
                        }
                    }
                    else
                    {
                        dpwithch[i][j] = Math.max(Math.max(find(dpwithch, i+1, j-1),
                                find(dpwithch, i+1, j)),
                                Math.max(dpwithch[i][j], find(dpwithch, i, j-1)));
                    }
                }
            }

            sb.append(dpwithch[0][n-1]).append("\n");
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
