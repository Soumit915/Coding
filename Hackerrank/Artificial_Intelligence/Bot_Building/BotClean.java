package Hackerrank.Artificial_Intelligence.Bot_Building;

import java.io.*;
import java.util.*;

public class BotClean {
    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static boolean isValid(int n, int x, int y){
        return 0<=x && x<n && 0<=y && y<n;
    }
    static Point findNearestPoint(int r, int c, char[][] mat, int n){
        if(mat[r][c]=='d')
            return new Point(r, c);

        boolean[][] isVisited = new boolean[n][n];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        isVisited[r][c] = true;

        while(!q.isEmpty()){
            Point cur = q.remove();
            if(isValid(n, cur.x+1, cur.y) && !isVisited[cur.x+1][cur.y]){
                isVisited[cur.x+1][cur.y] = true;
                q.add(new Point(cur.x+1, cur.y));
                if(mat[cur.x+1][cur.y]=='d')
                    return new Point(cur.x+1, cur.y);
            }
            if(isValid(n, cur.x, cur.y+1) && !isVisited[cur.x][cur.y+1]){
                isVisited[cur.x][cur.y+1] = true;
                q.add(new Point(cur.x, cur.y+1));
                if(mat[cur.x][cur.y+1]=='d')
                    return new Point(cur.x, cur.y+1);
            }
            if(isValid(n, cur.x-1, cur.y) && !isVisited[cur.x-1][cur.y]){
                isVisited[cur.x-1][cur.y] = true;
                q.add(new Point(cur.x-1, cur.y));
                if(mat[cur.x-1][cur.y]=='d')
                    return new Point(cur.x-1, cur.y);
            }
            if(isValid(n, cur.x, cur.y-1) && !isVisited[cur.x][cur.y-1]){
                isVisited[cur.x][cur.y-1] = true;
                q.add(new Point(cur.x, cur.y-1));
                if(mat[cur.x][cur.y-1]=='d')
                    return new Point(cur.x, cur.y-1);
            }
        }

        return new Point(-1, -1);
    }
    static String getNextMove(int row, int col, int x, int y){

        if(x<row){
            return "UP";
        }
        else if(x>row)
            return "DOWN";
        else {
            if(y<col){
                return "LEFT";
            }
            else if(y>col){
                return "RIGHT";
            }
            else{
                return "CLEAN";
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int r = sc.nextInt();
        int c = sc.nextInt();

        int n = 5;
        char[][] mat = new char[n][n];
        for(int i=0;i<n;i++){
            mat[i] = sc.next().toCharArray();
        }

        Point nearest_point = findNearestPoint(r, c, mat, n);
        System.out.println(getNextMove(r, c, nearest_point.x, nearest_point.y));

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
