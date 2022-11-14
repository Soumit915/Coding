package Codeforces;

import java.io.*;
import java.util.*;

public class RiverLocks {
    public static void main(String[] args) throws IOException{
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        long[] arr = sc.nextLongArray(n);

        long[] suffix_sum = new long[n];
        suffix_sum[n-1] = arr[n-1];
        for(int i=n-2;i>=0;i--){
            suffix_sum[i] = suffix_sum[i + 1] + arr[i];
        }

        long[] time = new long[n];
        long[] extra = new long[n];
        long[] time_to_fill_with_k_pipes = new long[n];

        time[0] = arr[0];
        extra[0] = 0;
        time_to_fill_with_k_pipes[0] = suffix_sum[0];

        for(int i=1;i<n;i++){
            if(time[i-1] >= arr[i]){
                time[i] = time[i-1];
                extra[i] = extra[i-1] + (time[i] - arr[i]);
            }
            else{
                long totalfill_at_time_t = extra[i-1] + time[i-1];

                if(totalfill_at_time_t >= arr[i]){
                    time[i] = time[i-1];
                    extra[i] = totalfill_at_time_t - arr[i];
                }
                else{
                    long left = arr[i] - totalfill_at_time_t;
                    long extraTime = ( left + i )/ (i + 1);
                    time[i] = time[i-1] + extraTime;
                    extra[i] = extraTime * (i + 1) - left;
                }
            }

            time_to_fill_with_k_pipes[i] = time[i] +
                    Math.max(0, (i==n-1?0:suffix_sum[i+1]) - extra[i] + i) / (i + 1);
        }

        TreeMap<Long, Integer> map = new TreeMap<>();
        for(int i=n-1;i>=0;i--){
            map.put(time_to_fill_with_k_pipes[i], i+1);
        }
        map.put(0L, -1);

        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++){
            long ti = sc.nextLong();

            int pipes = map.get(map.floorKey(ti));
            sb.append(pipes).append("\n");
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
