package Leetcode;
import java.io.*;
import java.util.*;

public class NQueens {

    static List<List<String>> distinctWays;

    static boolean isValid(char[][] board){

        print(board);

        int n = board.length;

        int[] row = new int[n];
        int[] col = new int[n];

        Map<Integer, Integer> left_diagonal = new HashMap<>();
        Map<Integer, Integer> right_diagonal = new HashMap<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == 'Q'){
                    row[i]++;
                    col[j]++;

                    left_diagonal.put(i-j, left_diagonal.getOrDefault(i-j, 0) + 1);
                    right_diagonal.put(i+j, right_diagonal.getOrDefault(i+j, 0) + 1);
                }
            }
        }

        for(int i=0;i<n;i++){
            if(row[i] > 1)
                return false;

            if(col[i] > 1)
                return false;
        }

        for(int i: left_diagonal.keySet()){
            if(left_diagonal.get(i) > 1)
                return false;
        }

        for(int i: right_diagonal.keySet()){
            if(right_diagonal.get(i) > 1)
                return false;
        }

        print(board);
        return true;
    }

    static void add(char[][] board){
        int n = board.length;

        List<String> new_nQueens = new ArrayList<>();

        for(int i=0;i<n;i++){

            StringBuilder sb = new StringBuilder();
            for(int j=0;j<n;j++){
                sb.append(board[i][j]);
            }

            new_nQueens.add(sb.toString());
        }

        distinctWays.add(new_nQueens);
    }

    static void print(char[][] board){
        int n = board.length;

        List<String> new_nQueens = new ArrayList<>();

        for(int i=0;i<n;i++){

            StringBuilder sb = new StringBuilder();
            for(int j=0;j<n;j++){
                sb.append(board[i][j]);
            }

            new_nQueens.add(sb.toString());
        }

        System.out.println(new_nQueens);
    }

    static void recurse(char[][] board, int row){

        int n = board.length;

        if(row == n){
            add(board);
            return;
        }

        for(int i=0;i<n;i++){
            board[row][i] = 'Q';

            if(isValid(board)){
                recurse(board, row + 1);
            }

            board[row][i] = '.';
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i], '.');
        }

        distinctWays = new ArrayList<>();

        recurse(board, 0);

        return distinctWays;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();
        
        int n = 4;
        System.out.println(solveNQueens(n));
        
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
