package Hackerrank.Artificial_Intelligence.Bot_Building;

import java.io.*;
import java.util.*;

public class BotCleanLarge {
    static class GridPoint {
        int x;
        int y;
        GridPoint(){
            this.x = -1;
            this.y = -1;
        }
        GridPoint(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static boolean isValid(int n, int m, int x, int y){
        return 0<=x && x<n && 0<=y && y<m;
    }
    static String getDirection(GridPoint a, GridPoint b){
        if(a.y+1==b.y)
            return "LEFT";
        if(a.y-1==b.y)
            return "RIGHT";
        if(a.x+1==b.x)
            return "UP";
        else return "DOWN";
    }
    static GridPoint getNearestDirtyCell(char[][] mat, int n, int m, GridPoint start){
        boolean[][] isVisited = new boolean[n][n];

        Queue<GridPoint> q = new LinkedList<>();
        q.add(start);
        isVisited[start.x][start.y] = true;

        while (!q.isEmpty()){
            GridPoint cur = q.remove();
            if(isValid(n, m, cur.x+1, cur.y) && !isVisited[cur.x+1][cur.y]){
                q.add(new GridPoint(cur.x+1, cur.y));
                isVisited[cur.x+1][cur.y] = true;
                if(mat[cur.x+1][cur.y]=='d')
                    return new GridPoint(cur.x+1, cur.y);
            }

            if(isValid(n, m, cur.x-1, cur.y) && !isVisited[cur.x-1][cur.y]){
                q.add(new GridPoint(cur.x-1, cur.y));
                isVisited[cur.x-1][cur.y] = true;
                if(mat[cur.x-1][cur.y]=='d')
                    return new GridPoint(cur.x-1, cur.y);
            }

            if(isValid(n, m, cur.x, cur.y+1) && !isVisited[cur.x][cur.y+1]){
                q.add(new GridPoint(cur.x, cur.y+1));
                isVisited[cur.x][cur.y+1] = true;
                if(mat[cur.x][cur.y+1]=='d')
                    return new GridPoint(cur.x, cur.y+1);
            }

            if(isValid(n, m, cur.x, cur.y-1) && !isVisited[cur.x][cur.y-1]){
                q.add(new GridPoint(cur.x, cur.y-1));
                isVisited[cur.x][cur.y-1] = true;
                if(mat[cur.x][cur.y-1]=='d')
                    return new GridPoint(cur.x, cur.y-1);
            }
        }
        return new GridPoint();
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        GridPoint start = new GridPoint(sc.nextInt(), sc.nextInt());

        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] mat = new char[n][m];
        for(int i=0;i<n;i++){
            String s = sc.next();
            mat[i] = s.toCharArray();
        }

        GridPoint end = getNearestDirtyCell(mat, n, m, start);

        if(mat[start.x][start.y]=='d'){
            System.out.println("CLEAN");
        }
        else{
            boolean[][] isVisited = new boolean[n][m];
            GridPoint[][] previous = new GridPoint[n][m];

            Queue<GridPoint> q = new LinkedList<>();
            q.add(start);
            isVisited[start.x][start.y] = true;
            while (!q.isEmpty()){
                GridPoint cur = q.remove();
                if(isValid(n, m, cur.x+1, cur.y) && !isVisited[cur.x+1][cur.y]){
                    q.add(new GridPoint(cur.x+1, cur.y));
                    isVisited[cur.x+1][cur.y] = true;
                    previous[cur.x+1][cur.y] = cur;
                }

                if(isValid(n, m, cur.x-1, cur.y) && !isVisited[cur.x-1][cur.y]){
                    q.add(new GridPoint(cur.x-1, cur.y));
                    isVisited[cur.x-1][cur.y] = true;
                    previous[cur.x-1][cur.y] = cur;
                }

                if(isValid(n, m, cur.x, cur.y+1) && !isVisited[cur.x][cur.y+1]){
                    q.add(new GridPoint(cur.x, cur.y+1));
                    isVisited[cur.x][cur.y+1] = true;
                    previous[cur.x][cur.y+1] = cur;
                }

                if(isValid(n, m, cur.x, cur.y-1) && !isVisited[cur.x][cur.y-1]){
                    q.add(new GridPoint(cur.x, cur.y-1));
                    isVisited[cur.x][cur.y-1] = true;
                    previous[cur.x][cur.y-1] = cur;
                }
            }

            GridPoint ptr = end;
            Stack<String> stk = new Stack<>();
            while(previous[ptr.x][ptr.y]!=null){
                stk.push(getDirection(ptr, previous[ptr.x][ptr.y]));
                ptr = previous[ptr.x][ptr.y];
            }

            System.out.println(stk.pop());
        }

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
