package Codeforces;

import java.util.*;
import java.io.*;

public class Harvester {

    static long zeroFull(long[][] mat){
        int m = mat[0].length;

        List<Long> sumlist = new ArrayList<>();
        for (long[] longs : mat) {
            long s = 0;
            for (int j = 0; j < m; j++) {
                s += longs[j];
            }

            sumlist.add(s);
        }

        sumlist.sort(Collections.reverseOrder());

        long s = 0;
        for(int i = 0;i<Math.min(sumlist.size(), 4);i++){
            s += sumlist.get(i);
        }

        return s;
    }

    static long oneSome(long[][] mat){
        int m = mat[0].length;

        if(m < 3)
            return 0;

        long[] a = new long[m];
        for(int i=0;i<m;i++){
            for (long[] longs : mat) {
                a[i] += longs[i];
            }
        }

        long max = 0;
        for(long[] row : mat){
            PriorityQueue<Long> heap = new PriorityQueue<>();

            long rowsum = 0;
            for(int i=0;i<m;i++){
                long v = a[i] - row[i];
                rowsum += row[i];

                if(heap.size() < 3){
                    heap.add(v);
                }
                else{
                    if(heap.peek() < v){
                        heap.remove();
                        heap.add(v);
                    }
                }
            }

            long totsum = 0;
            while(!heap.isEmpty()){
                totsum += heap.remove();
            }
            totsum += rowsum;

            max = Math.max(max, totsum);
        }

        return max;
    }

    static long two2(long[][] mat){
        int n = mat.length;
        int m = mat[0].length;

        if(n < 2 || m < 2)
            return 0;

        long[] a = new long[m];
        for(int i=0;i<m;i++){
            for (long[] longs : mat) {
                a[i] += longs[i];
            }
        }

        long max = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){

                PriorityQueue<Long> heap = new PriorityQueue<>();
                long rowsum = 0;
                for(int k=0;k<m;k++){

                    long v = a[k] - mat[i][k] - mat[j][k];
                    rowsum += mat[i][k] + mat[j][k];

                    if(heap.size() < 2){
                        heap.add(v);
                    }
                    else{
                        if(heap.peek() < v){
                            heap.remove();
                            heap.add(v);
                        }
                    }
                }

                long totsum = 0;
                while(!heap.isEmpty()){
                    totsum += heap.remove();
                }
                totsum += rowsum;

                max = Math.max(max, totsum);

            }
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();

        long[][] mat = new long[n][m];
        for(int i=0;i<n;i++){
            mat[i] = sc.nextLongArray(m);
        }

        long[][] tmat = new long[m][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                tmat[j][i] = mat[i][j];
            }
        }

        long sum = zeroFull(mat);
        sum = Math.max(sum, zeroFull(tmat));

        sum = Math.max(sum, oneSome(mat));
        sum = Math.max(sum, oneSome(tmat));

        if(n <= m)
            sum = Math.max(sum, two2(mat));
        else sum = Math.max(sum, two2(tmat));

        System.out.println(sum);

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
