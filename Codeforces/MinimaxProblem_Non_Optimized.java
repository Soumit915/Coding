package Codeforces;

import java.util.*;
import java.io.*;

public class MinimaxProblem_Non_Optimized {

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] a = new int[n][m];
        for(int i=0;i<n;i++){
            a[i] = sc.nextIntArray(m);
        }

        int lim = (1 << m);
        int[] suffix_min = new int[lim];

        for(int j=0;j<lim;j++){

            int cur_min = Integer.MAX_VALUE;
            int cur_max = Integer.MIN_VALUE;
            for(int k=0;k<m;k++){
                if((j & (1<<k)) != 0){
                    cur_min = Math.min(cur_min, a[0][k]);
                    cur_max = Math.max(cur_max, a[0][k]);
                }
            }

            suffix_min[j] = cur_min;
        }

        int ans = suffix_min[lim-1];
        int ind1 = 0, ind2 = 0;
        int[] last_index = new int[lim];
        for(int i=1;i<n;i++){

            int[] min = new int[lim];

            for(int j=0;j<lim;j++){

                int cur_min = Integer.MAX_VALUE, cur_min_complement = Integer.MAX_VALUE;
                int cur_max = Integer.MIN_VALUE;
                for(int k=0;k<m;k++){
                    if((j & (1<<k)) != 0){
                        cur_min = Math.min(cur_min, a[i][k]);
                        cur_max = Math.max(cur_max, a[i][k]);
                    }
                    else{
                        cur_min_complement = Math.min(cur_min_complement, a[i][k]);
                    }
                }

                min[j] = cur_min;

                if(suffix_min[j] < min[j]) {
                    suffix_min[j] = min[j];
                    last_index[j] = i;
                    continue;
                }

                if(j==0 || cur_min_complement < cur_max)
                    continue;

                if(Math.min(cur_min_complement, suffix_min[j]) > ans){
                    ans = Math.min(cur_min_complement, suffix_min[j]);
                    ind1 = i; ind2 = last_index[j];
                }
            }

            if(min[lim - 1] > ans){
                ans = min[lim - 1];
                ind1 = i; ind2 = i;
            }
        }

        System.out.println((ind1 + 1)+" "+(ind2 + 1));

        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);

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
