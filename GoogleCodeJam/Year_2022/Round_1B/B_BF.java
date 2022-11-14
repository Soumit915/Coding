package GoogleCodeJam.Year_2022.Round_1B;

import java.io.*;
import java.util.*;

public class B_BF {

    static boolean getNextPermutation(int[] a){
        int n = a.length;
        for(int i=n-1;i>0;i--){
            int l = -1;
            if(a[i]>a[i-1]){
                for(int j=i;j<n;j++){
                    if(a[j]>a[i-1])
                        l = j;
                }

                int t = a[l];
                a[l] = a[i-1];
                a[i-1] = t;

                for(int j=i;j<n-j+i-1;j++){
                    t = a[j];
                    a[j] = a[n-j+i-1];
                    a[n-j+i-1] = t;
                }
                return true;
            }
        }

        return false;
    }

    static int[][] getAllPerms(int n){
        int tot = 1;
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            tot = tot * (i + 1);
            arr[i] = i;
        }

        int[][] perms = new int[tot][n];
        System.arraycopy(arr, 0, perms[0], 0, n);
        for(int i=1;i<tot;i++){
            getNextPermutation(arr);

            System.arraycopy(arr, 0, perms[i], 0, n);
        }

        return perms;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++){
            sb.append("Case #").append(testi).append(": ");

            int n = sc.nextInt();
            int p = sc.nextInt();

            long[][] mat = new long[n][p];
            for(int i=0;i<n;i++){
                mat[i] = sc.nextLongArray(p);
            }

            int[][] allPerms = getAllPerms(p);
            long[] last = new long[p];
            long[] min = new long[p];
            for(int i=0;i<n;i++){

                long[] looplast = new long[p];
                long[] loopmin = new long[p];
                Arrays.fill(loopmin, Long.MAX_VALUE);

                for(int[] perm: allPerms){
                    long cost = Long.MAX_VALUE;
                    long locallast = 0;

                    for(int j=0;j<perm.length;j++){
                        if(j == 0){
                            for(int k=0;k<p;k++){
                                cost = Math.min(cost, min[k] + Math.abs(mat[i][perm[j]] - last[k]));
                            }
                        }
                        else{
                            cost = cost + Math.abs(mat[i][perm[j]] - locallast);
                        }
                        locallast = mat[i][perm[j]];
                    }

                    int perm_last = perm[perm.length - 1];
                    if(cost < loopmin[perm_last]){
                        loopmin[perm_last] = cost;
                        looplast[perm_last] = mat[i][perm_last];
                    }
                }

                last = looplast;
                min = loopmin;
            }

            long ans = min[0];
            for(long v: min){
                ans = Math.min(ans, v);
            }

            sb.append(ans).append("\n");
        }

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
