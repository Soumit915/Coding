package Hackerrank.Algorithms.GameTheory;

import java.io.*;
import java.util.*;

public class DigitsSquareBoard {
    static HashSet<Integer> primeSet = new HashSet<>();
    static class Matrix{
        int n;
        int[][] arr;
        Matrix(int n){
            this.n = n;
            this.arr = new int[n][n];
        }
    }
    static boolean isValid(int[][] matrix, int r, int c, int il, int jl){
        int n = matrix.length;
        if(r+il<=n && c+jl<=n){
            boolean flag = false;
            for(int i=r;i<r+il;i++){
                for(int j=c;j<c+jl;j++){
                    flag |= primeSet.contains(matrix[i][j]);
                }
            }
            return flag;
        }
        else return false;
    }
    static int mex(int[] set){
        for(int i=0;i<set.length;i++)
            if(set[i]==0)
                return i;
        return 60;
    }
    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit();
        primeSet.add(1);primeSet.add(4);primeSet.add(6);primeSet.add(8);primeSet.add(9);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int[][] arr = new int[n][n];
            for(int i=0;i<n;i++)
                arr[i] = sc.nextIntArray(n);

            HashMap<Integer, HashMap<Integer, Matrix>> grundy_hash = new HashMap<>();
            for(int r=1;r<=n;r++){
                for(int c=1;c<=n;c++){
                    Matrix matrix = new Matrix(n);
                    for(int i=0;i<n;i++){
                        for(int j=0;j<n;j++){
                            if(isValid(arr, i, j, r, c)){
                                int[] set = new int[60];
                                for(int i1=1;i1<r;i1++){
                                    Matrix gm1 = grundy_hash.get(i1).get(c);
                                    Matrix gm2 = grundy_hash.get(r-i1).get(c);
                                    set[gm1.arr[i][j]^gm2.arr[i+i1][j]] = 1;
                                }
                                for(int i1=1;i1<c;i1++){
                                    Matrix gm1 = grundy_hash.get(r).get(i1);
                                    Matrix gm2 = grundy_hash.get(r).get(c-i1);
                                    set[gm1.arr[i][j]^gm2.arr[i][j+i1]] = 1;
                                }
                                matrix.arr[i][j] = mex(set);
                            }
                        }
                    }
                    HashMap<Integer, Matrix> hash = grundy_hash.getOrDefault(r, new HashMap<>());
                    hash.put(c, matrix);
                    grundy_hash.put(r, hash);
                }
            }
            Matrix mat = grundy_hash.get(n).get(n);
            if(mat.arr[0][0]!=0){
                sb.append("First\n");
            }
            else sb.append("Second\n");
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
