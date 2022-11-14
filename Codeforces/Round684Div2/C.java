package Codeforces.Round684Div2;

import java.io.*;
import java.util.*;

public class C {
    public static void fun4(StringBuilder sb, int i, int j)
    {
        sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        sb.append(i + 1).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        sb.append(i).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j).append("\n");
    }
    public static void fun3(StringBuilder sb, char ch00, char ch01, char ch10, int i, int j)
    {
        if(ch00=='0')
        {
            sb.append(i + 1).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        }
        else if(ch01=='0')
        {
            sb.append(i).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        }
        else if(ch10=='0')
        {
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        }
        else
        {
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j).append("\n");
        }
    }
    public static void fun2(StringBuilder sb, char ch00, char ch01, char ch10, char ch11,
                            int i, int j)
    {
        if(ch00=='0' && ch01=='0')
        {
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j).append("\n");
        }
        else if(ch01=='0' && ch11=='0')
        {
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i + 1).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        }
        else if(ch11=='0' && ch10=='0')
        {
            sb.append(i + 1).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        }
        else if(ch00=='0' && ch10=='0')
        {
            sb.append(i).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j).append("\n");
        }
        else if(ch00=='0' && ch11=='0')
        {
            sb.append(i).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        }
        else {
            sb.append(i + 1).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j).append("\n");
        }
    }
    public static void fun1(StringBuilder sb, char ch00, char ch01, char ch10, int i, int j)
    {
        if(ch00=='1')
        {
            sb.append(i).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        }
        else if(ch01=='1')
        {
            sb.append(i + 1).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j).append("\n");
        }
        else if(ch10=='1')
        {
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j).append("\n");
            sb.append(i + 1).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        }
        else
        {
            sb.append(i).append(" ").append(j).append(" ").append(i + 1).append(" ").append(j).append(" ").append(i+1).append(" ").append(j + 1).append("\n");
            sb.append(i).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
            sb.append(i + 1).append(" ").append(j).append(" ").append(i).append(" ").append(j + 1).append(" ").append(i + 1).append(" ").append(j + 1).append("\n");
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();

            char[][] mat = new char[n][m];
            for(int i=0;i<n;i++)
                mat[i] = sc.next().toCharArray();

            StringBuilder ops = new StringBuilder();
            int countops = 0;

            for(int i=0;i<2*(n/2);i+=2)
            {
                for(int j=0;j<m-2;j++)
                {
                    int ones = 0;
                    StringBuilder temp = new StringBuilder();
                    if(mat[i][j] == '1')
                    {
                        temp.append(i+1).append(" ").append(j+1).append(" ");
                        mat[i][j] = '0';
                        ones++;
                    }
                    if(mat[i+1][j] == '1')
                    {
                        temp.append(i + 1+1).append(" ").append(j+1).append(" ");
                        mat[i+1][j] = '0';
                        ones++;
                    }

                    if(ones==0)
                        continue;

                    if(ones==1)
                    {
                        temp.append(i+1).append(" ").append(j +1+1).append(" ");
                        if(mat[i][j+1]=='0')
                            mat[i][j+1]='1';
                        else mat[i][j+1]='0';
                    }
                    temp.append(i + 1+1).append(" ").append(j + 1+1).append("\n");
                    if(mat[i+1][j+1]=='0')
                        mat[i+1][j+1]='1';
                    else mat[i+1][j+1]='0';

                    countops++;
                    ops.append(temp);
                }
            }

            for(int i=0;i<n-2;i++)
            {
                int ones = 0;
                StringBuilder temp = new StringBuilder();
                if(mat[i][m-2] == '1')
                {
                    temp.append(i+1).append(" ").append(m-2+1).append(" ");
                    mat[i][m-2] = '0';
                    ones++;
                }
                if(mat[i][m-2+1] == '1')
                {
                    temp.append(i + 1).append(" ").append(m-2+1+1).append(" ");
                    mat[i][m-2+1] = '0';
                    ones++;
                }

                if(ones==0)
                    continue;

                if(ones==1)
                {
                    temp.append(i+1+1).append(" ").append(m-2 + 1).append(" ");
                    if(mat[i+1][m-2]=='0')
                        mat[i+1][m-2]='1';
                    else mat[i+1][m-2]='0';
                }
                temp.append(i + 1+1).append(" ").append(m-2 + 1+1).append("\n");
                if(mat[i+1][m-2+1]=='0')
                    mat[i+1][m-2+1]='1';
                else mat[i+1][m-2+1]='0';

                countops++;
                ops.append(temp);
            }

            for(int j=0;j<m-2;j++)
            {
                int ones = 0;
                StringBuilder temp = new StringBuilder();
                if(mat[n-2][j] == '1')
                {
                    temp.append(n-2+1).append(" ").append(j+1).append(" ");
                    mat[n-2][j] = '0';
                    ones++;
                }
                if(mat[n-2+1][j] == '1')
                {
                    temp.append(n-2 + 1+1).append(" ").append(j+1).append(" ");
                    mat[n-2+1][j] = '0';
                    ones++;
                }

                if(ones==0)
                    continue;

                if(ones==1)
                {
                    temp.append(n-2+1).append(" ").append(j +1+1).append(" ");
                    if(mat[n-2][j+1]=='0')
                        mat[n-2][j+1]='1';
                    else mat[n-2][j+1]='0';
                }
                temp.append(n-2 + 1+1).append(" ").append(j + 1+1).append("\n");
                if(mat[n-2+1][j+1]=='0')
                    mat[n-2+1][j+1]='1';
                else mat[n-2+1][j+1]='0';

                countops++;
                ops.append(temp);
            }

            int iend = n-2;
            char ch00 = mat[iend][m-2];
            char ch01 = mat[iend][m-2+1];
            char ch10 = mat[iend+1][m-2];
            char ch11 = mat[iend+1][m-2+1];

            int ones = 0;
            if(ch00=='1')
                ones++;
            if(ch01=='1')
                ones++;
            if(ch10=='1')
                ones++;
            if(ch11=='1')
                ones++;

            if(ones==4) {
                fun4(ops, iend+1, m-2+1);
                countops += 4;
            }
            else if(ones==3) {
                fun3(ops, ch00, ch01, ch10, iend+1, m-2+1);
                countops += 1;
            }
            else if(ones==2) {
                fun2(ops, ch00, ch01, ch10, ch11, iend+1, m-2+1);
                countops += 2;
            }
            else if(ones==1) {
                fun1(ops, ch00, ch01, ch10, iend+1, m-2+1);
                countops += 3;
            }

            mat[iend][m-2] = '0';
            mat[iend][m-2+1] = '0';
            mat[iend+1][m-2] = '0';
            mat[iend+1][m-2+1] = '0';

            sb.append(countops).append("\n");
            sb.append(ops);
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
