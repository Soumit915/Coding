package Hackerrank.Algorithms.GameTheory;

import java.io.*;
import java.util.*;

public class ChessboardGameAgain {
    static int[][] mat = new int[15][15];
    static boolean isValid(int x, int y){
        return 0<=x && x<15 && 0<=y && y<15;
    }
    static int mex(Set<Integer> set){
        for(int i=0;i<10;i++)
            if(!set.contains(i))
                return i;
        return 10;
    }
    static void preCompute(){
        int n = 15;
        mat[0][0] = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                int r = i-j;
                Set<Integer> set = new HashSet<>();
                if(isValid(r-2, j+1))
                    set.add(mat[r-2][j+1]);
                if(isValid(r-2, j-1))
                    set.add(mat[r-2][j-1]);
                if(isValid(r+1, j-2))
                    set.add(mat[r+1][j-2]);
                if(isValid(r-1, j-2))
                    set.add(mat[r-1][j-2]);
                mat[r][j] = mex(set);
            }
        }
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<=i;j++){
                int r = 14-j;
                int c = n-i-1+j;
                Set<Integer> set = new HashSet<>();
                if(isValid(r-2, c+1))
                    set.add(mat[r-2][c+1]);
                if(isValid(r-2, c-1))
                    set.add(mat[r-2][c-1]);
                if(isValid(r+1, c-2))
                    set.add(mat[r+1][c-2]);
                if(isValid(r-1, c-2))
                    set.add(mat[r-1][c-2]);
                mat[r][c] = mex(set);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        preCompute();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int k = sc.nextInt();
            int xor = 0;
            for(int i=0;i<k;i++)
                xor ^= mat[sc.nextInt()-1][sc.nextInt()-1];

            if(xor!=0)
                sb.append("First\n");
            else sb.append("Second\n");
        }

        System.out.println(sb.toString());

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
