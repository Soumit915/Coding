import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class SparseTables {
    public static void built(int[][] sparseTable, int[] arr)
    {
        int n = arr.length;
        int cols = sparseTable[0].length;
        for(int i=0;i<n;i++)
        {
            sparseTable[i][0] = arr[i];
        }

        for(int i=1;i<cols;i++)
        {
            for(int j=0;j<n;j++)
            {
                int vl = (int) Math.pow(2, i-1);
                if(vl+j<n)
                    sparseTable[j][i] = Math.min(sparseTable[j][i-1], sparseTable[j+vl][i-1]);
            }
        }
    }
    public static int log(int n)
    {
        if(n==0)
            return n;
        return (int) (Math.log(n)/Math.log(2));
    }
    public static int query(int[][] sparseTable, int l, int r)
    {
        int diff = (int) log(r-l);
        int min = sparseTable[l][diff];

        int left = (r-l+1) - (int) Math.pow(2, diff);
        if(left>0)
            min = Math.min(min, sparseTable[l+left][diff]);

        return min;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");

        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = sc.nextIntArray(n);

        int cols = (int) Math.ceil((Math.log(n)/Math.log(2)));
        int[][] sparseTable = new int[n][cols];
        for(int i=0;i<n;i++)
            for(int j=0;j<cols;j++)
                sparseTable[i][j] = Integer.MIN_VALUE;
        built(sparseTable, arr);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++)
        {
            int type = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            sb.append(query(sparseTable, l, r-1)).append("\n");
        }

        //System.out.println(sb);
        sc.println(sb.toString());
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

        public void println(String a)
        {
            pw.println(a);
        }

        public void print(String a)
        {
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
            if(din!=null) din.close();
            if(pw!=null) pw.close();
        }
    }
}
