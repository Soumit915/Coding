import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Epam3Test {
    static int N = (int) 2e5;
    static int V = 500;
    static int solve(int n, int d, int[] input)
    {
        int []a = new int[N];

        // Stores frequencies
        int []cnt = new int[V + 1];

        // Stores the array elements
        if (n >= 0) System.arraycopy(input, 0, a, 0, n);

        int answer = 0;

        // Count the frequencies of the
        // array elements
        for (int i = 0; i < d; ++i)
            cnt[a[i]]++;

        // Iterating from d to n-1 index
        // means (d+1)th element to nth element
        for (int i = d; i <= n - 1; ++i)
        {

            // To check the median
            int acc = 0;

            int low_median = -1, high_median = -1;

            // Iterate over the frequencies
            // of the elements
            for (int v = 0; v <= V; ++v)
            {

                // Add the frequencies
                acc += cnt[v];

                // Check if the low_median value is
                // obtained or not, if yes then do
                // not change as it will be minimum
                if (low_median == -1
                        && acc >= (int)(Math.floor((d + 1) / 2.0)))
                    low_median = v;

                // Check if the high_median value is
                // obtained or not, if yes then do not
                // change it as it will be maximum
                if (high_median == -1
                        && acc >= (int)(Math.ceil((d + 1) / 2.0)))
                    high_median = v;
            }

            // Store 2 * median of K trailing elements
            int double_median = low_median + high_median;

            // If the current >= 2 * median
            if (a[i] >= double_median) {
                answer++;
                System.out.println(i+" "+a[i]+" "+double_median);
            }

            // Decrease the frequency for (k-1)-th element
            cnt[a[i - d]]--;

            // Increase the frequency of the
            // current element
            cnt[a[i]]++;
        }

        // Print the count
        return answer;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");

        int t = sc.nextInt();
        while(t-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] input = sc.nextIntArray(n);

            sc.println(solve(n, k, input)+"");
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
