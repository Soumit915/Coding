package Codeforces;

import java.util.*;
import java.io.*;

public class MinimizingDifference {

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        long k = sc.nextLong();

        long[] a = sc.nextLongArray(n);
        Arrays.sort(a);

        int lptr = 0, rptr = n-1;
        int lstop = n/2;
        int rstop = n/2;
        if(n%2 == 0)
            lstop--;

        long min = a[0], max = a[n-1];
        while(lptr < lstop && rptr > rstop && k > 0){
            int left_len = lptr + 1;
            int right_len = n - rptr;

            if(left_len < right_len){
                long next_val = a[lptr + 1];
                long ops_needed = (next_val - min) * left_len;
                if (ops_needed <= k){
                    k -= ops_needed;
                    min = next_val;
                }
                else{
                    k = (k / left_len);
                    min = min + k;
                    k = 0;
                }
                lptr++;
            }
            else{
                long next_val = a[rptr - 1];
                long ops_needed = (max - next_val) * right_len;
                if(ops_needed <= k){
                    k -= ops_needed;
                    max = next_val;
                }
                else{
                    k = k / right_len;
                    max = max - k;
                    k = 0;
                }
                rptr--;
            }
        }

        while (lptr < lstop && k > 0){
            int left_len = lptr + 1;

            long next_val = a[lptr + 1];
            long ops_needed = (next_val - min) * left_len;
            if (ops_needed <= k){
                k -= ops_needed;
                min = next_val;
            }
            else{
                k = (k / left_len);
                min = min + k;
                k = 0;
            }
            lptr++;
        }

        while(rptr > rstop && k > 0){
            int right_len = n - rptr;

            long next_val = a[rptr - 1];
            long ops_needed = (max - next_val) * right_len;
            if(ops_needed <= k){
                k -= ops_needed;
                max = next_val;
            }
            else{
                k = k / right_len;
                max = max - k;
                k = 0;
            }
            rptr--;
        }

        if(lstop < rstop && k>0){
            int right_len = n - rptr;

            long next_val = a[rptr - 1];
            long ops_needed = (max - next_val) * right_len;

            if(ops_needed <= k){
                max = next_val;
            }
            else{
                k = k / right_len;
                max = max - k;
            }
        }

        System.out.println(Math.abs(max - min));

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
