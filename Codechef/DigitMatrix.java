package Codechef;

import java.io.*;
import java.util.*;

public class DigitMatrix {
    public static boolean isValid(int n, int i, int j)
    {
        return (i>=0 && i<n && j>=0 && j<n);
    }
    public static void normalize(int[][] b, int x, int y, int val)
    {
        int n = b.length;

        Stack<Integer> stkx = new Stack<>();
        Stack<Integer> stky = new Stack<>();
        Stack<Integer> ptrstk = new Stack<>();

        if(isValid(n, x, y+1))
        {
            stkx.push(x);
            stky.push(y+1);
            ptrstk.push(0);
        }
        if(isValid(n, x+1, y))
        {
            stkx.push(x+1);
            stky.push(y);
            ptrstk.push(0);
        }

        while(val > 0)
        {
            int i = stkx.pop();
            int j = stky.pop();
            int ptr = ptrstk.pop();

            if(ptr==0)
            {
                if(isValid(n, i+1, j))
                {
                    stkx.push(i);
                    stky.push(j);
                    ptrstk.push(1);

                    stkx.push(i+1);
                    stky.push(j);
                    ptrstk.push(0);
                }
                else if(isValid(n, i, j+1))
                {
                    stkx.push(i);
                    stky.push(j+1);
                    ptrstk.push(0);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[][] a = new int[n][n];
        for(int i=0;i<n;i++)
        {
            a[i] = sc.nextIntArray(n);
        }

        int[][] b = new int[n+1][n+1];
        for(int i=n;i>=0;i--)
        {
            for(int j=n;j>=0;j--)
            {
                if(i==n && j==n)
                {
                    b[i][j] = Math.min(a[i-1][j-1], 9);
                }
                else if(i==n)
                {
                    int val1 = 100;
                    if(isValid(n+1, i-1, j-1))
                    {
                        val1 = a[i-1][j-1];
                    }

                    int val2 = a[i-1][j] - b[i][j+1] ;

                    b[i][j] = Math.min(9, Math.min(val1, val2));
                }
                else if(j==n)
                {
                    int val1 = 100;
                    if(isValid(n+1, i-1, j-1))
                    {
                        val1 = a[i-1][j-1];
                    }

                    int val2 = a[i-1][j] - b[i+1][j] - b[i][j-1];

                    b[i][j] = Math.min(9, Math.min(val1, val2));
                }
                else
                {
                    int val1 = a[i][j] - b[i+1][j] - b[i][j+1] - b[i+1][j+1];

                    if(val1 > 9)
                    {
                        //normalize();
                    }
                    b[i][j] = 9;
                }
            }
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
