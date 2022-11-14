package Codeforces.EducationalRound41;

import java.io.*;
import java.util.*;

public class C {
    static void fillChessboard(int[][][] mat, int[][] chessboard, int[] perm, int n){
        for(int i=0;i<n;i++){
            System.arraycopy(mat[perm[0]][i], 0, chessboard[i], 0, n);
        }

        for(int i=0;i<n;i++){
            System.arraycopy(mat[perm[1]][i], 0, chessboard[i], n, n);
        }

        for(int i=0;i<n;i++){
            System.arraycopy(mat[perm[2]][i], 0, chessboard[i + n], 0, n);
        }

        for(int i=0;i<n;i++){
            System.arraycopy(mat[perm[3]][i], 0, chessboard[i + n], n, n);
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[][][] mat = new int[4][n][n];
        for(int i=0;i<4;i++){
            for(int j1=0;j1<n;j1++){
                String s = sc.next();
                for(int j2=0;j2<n;j2++){
                    mat[i][j1][j2] = s.charAt(j2) - 48;
                }
            }
        }

        int[][] perms = {
                {0,1,2,3},{0,1,3,2},{0,2,1,3},{0,2,3,1},{0,3,1,2},{0,3,2,1},
                {1,0,2,3},{1,0,3,2},{1,2,0,3},{1,2,3,0},{1,3,0,2},{1,3,2,0},
                {2,1,0,3},{2,1,3,0},{2,0,1,3},{2,0,3,1},{2,3,1,0},{2,3,0,1},
                {3,1,2,0},{3,1,0,2},{3,2,1,0},{3,2,0,1},{3,0,1,2},{3,0,2,1}
        };

        int[][] chessboard = new int[2*n][2*n];
        int min = Integer.MAX_VALUE;
        for(int[] perm: perms){
            fillChessboard(mat, chessboard, perm, n);

            int count1=0, count2=0;
            for(int i=0;i<2*n;i++){
                for(int j=0;j<2*n;j++){
                    if(i%2==0){
                        if(j%2==0){
                            if(chessboard[i][j]==1)
                                count1++;
                            if(chessboard[i][j]==0)
                                count2++;
                        }
                        else{
                            if(chessboard[i][j]==0)
                                count1++;
                            if(chessboard[i][j]==1)
                                count2++;
                        }
                    }
                    else {
                        if(j%2==1){
                            if(chessboard[i][j]==1)
                                count1++;
                            if(chessboard[i][j]==0)
                                count2++;
                        }
                        else{
                            if(chessboard[i][j]==0)
                                count1++;
                            if(chessboard[i][j]==1)
                                count2++;
                        }
                    }
                }
            }

            min = Math.min(min, Math.min(count1, count2));
        }

        System.out.println(min);

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
