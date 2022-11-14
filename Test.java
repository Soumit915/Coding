
import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Test {

    public static long alone(long[] sums)
    {
        ArrayList<Long> ls = new ArrayList<Long>();
        for(long x: sums)
            ls.add(x);
        Collections.sort(ls);
        Collections.reverse(ls);
        long sum = 0L;
        for(int i=0; i < Math.min(sums.length, 4); i++)
            sum += ls.get(i);
        return sum;
    }

    private static void solve(Soumit sc) throws IOException {

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] grid = new int[N][M];
        for(int i=0; i < N; i++)
            grid[i] = sc.nextIntArray(M);

        if(N > M)
        {
            int[][] lol = new int[M][N];
            for(int r=0; r < N; r++)
                for(int c=0; c < M; c++)
                    lol[c][r] = grid[r][c];
            N = lol.length;
            M = lol[0].length;
            grid = lol;
        }
        long[] rsums = new long[N];
        long[] csums = new long[M];
        for(int r=0; r < N; r++)
            for(int c=0; c < M; c++)
            {
                rsums[r] += grid[r][c];
                csums[c] += grid[r][c];
            }
        long res = Math.max(alone(rsums), alone(csums));
        if(N > 4)
        {
            for(int r=0; r < N; r++)
            {
                ArrayList<Long> vals = new ArrayList<Long>();
                for(int c=0; c < M; c++)
                    vals.add(csums[c]-grid[r][c]);
                Collections.sort(vals);
                Collections.reverse(vals);
                long temp = rsums[r];
                for(int i=0; i < 3; i++)
                    temp += vals.get(i);
                res = Math.max(res, temp);
            }
            for(int c=0; c < M; c++)
            {
                ArrayList<Long> vals = new ArrayList<Long>();
                for(int r=0; r < N; r++)
                    vals.add(rsums[r]-grid[r][c]);
                Collections.sort(vals);
                Collections.reverse(vals);
                long temp = csums[c];
                for(int i=0; i < 3; i++)
                    temp += vals.get(i);
                res = Math.max(res, temp);
            }
            for(int a=0; a < N; a++)
                for(int b=a+1; b < N; b++)
                {
                    long[] vals = new long[M];
                    for(int c=0; c < M; c++)
                        vals[c] = csums[c]-grid[a][c]-grid[b][c];
                    int dex1 = 0;
                    for(int c=0; c < M; c++)
                        if(vals[dex1] < vals[c])
                            dex1 = c;
                    int dex2 = 0;
                    if(dex1 == 0)
                        dex2 = 1;
                    for(int c=0; c < M; c++)
                        if(vals[dex2] < vals[c] && c != dex1)
                            dex2 = c;
                    long temp = rsums[a]+rsums[b]+vals[dex1]+vals[dex2];
                    res = Math.max(res, temp);
                }
        }

        System.out.println(res);
    }

    public static void main(String[] args) throws IOException{
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");

        int t = sc.nextInt();
        while (t-->0){
            solve(sc);
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
