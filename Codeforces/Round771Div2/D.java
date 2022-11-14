package Codeforces.Round771Div2;

import java.io.*;
import java.util.*;

public class D {

    static class Ops{
        int i, j, k;
        Ops(int i, int j, int k){
            this.i = i;
            this.j = j;
            this.k = k;
        }
        public String toString(){
            return (i+1)+" "+(j+1)+" "+k;
        }
    }

    static class Cell{
        int x, y;
        Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static void convert(int[][] color, int i, int j, int k){
        color[i][j] = k;
        color[i][j+1] = k;
        color[i+1][j+1] = k;
        color[i+1][j] = k;
    }

    static boolean areEqual(int[][] color, int i, int j){
        Set<Integer> set = new HashSet<>();
        set.add(color[i][j]);
        set.add(color[i][j+1]);
        set.add(color[i+1][j]);
        set.add(color[i+1][j+1]);

        if(set.contains(-1)){
            return set.size() == 2;
        }
        else return set.size() == 1;
    }

    public static boolean isValid(int n, int m, int i, int j){
        return (i>=0 && i<n-1 && j>=0 && j<m-1);
    }

    static int getColor(int[][] color, int i, int j){
        if(color[i][j]!=-1)
            return color[i][j];
        if(color[i][j+1]!=-1)
            return color[i][j+1];
        if(color[i+1][j]!=-1)
            return color[i+1][j];

        return color[i+1][j+1];
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] color = new int[n][m];
        int[][] copy_color = new int[n][m];

        for(int i=0;i<n;i++){
            color[i] = sc.nextIntArray(m);
            System.arraycopy(color[i], 0, copy_color[i], 0, m);
        }

        ArrayList<Ops> operations = new ArrayList<>();
        boolean[][] isVisited = new boolean[n][m];
        Queue<Cell> q = new LinkedList<>();
        for(int i=0;i<n-1;i++){
            for(int j=0;j<m-1;j++){
                if(areEqual(color, i, j)){
                    q.add(new Cell(i, j));
                }
            }
        }

        while (!q.isEmpty()){
            Cell c = q.remove();
            if(isVisited[c.x][c.y])
                continue;

            if(areEqual(color, c.x, c.y)){
                isVisited[c.x][c.y] = true;
                operations.add(new Ops(c.x, c.y, getColor(color, c.x, c.y)));
                convert(color, c.x, c.y, -1);

                if(isValid(n, m, c.x-1, c.y-1) && !isVisited[c.x-1][c.y-1]){
                    q.add(new Cell(c.x-1, c.y-1));
                }
                if(isValid(n, m, c.x-1, c.y) && !isVisited[c.x-1][c.y]){
                    q.add(new Cell(c.x-1, c.y));
                }
                if(isValid(n, m, c.x-1, c.y+1) && !isVisited[c.x-1][c.y+1]){
                    q.add(new Cell(c.x-1, c.y+1));
                }

                if(isValid(n, m, c.x, c.y-1) && !isVisited[c.x][c.y-1]){
                    q.add(new Cell(c.x, c.y-1));
                }
                if(isValid(n, m, c.x, c.y) && !isVisited[c.x][c.y]){
                    q.add(new Cell(c.x, c.y));
                }
                if(isValid(n, m, c.x, c.y+1) && !isVisited[c.x][c.y+1]){
                    q.add(new Cell(c.x, c.y+1));
                }

                if(isValid(n, m, c.x+1, c.y-1) && !isVisited[c.x+1][c.y-1]){
                    q.add(new Cell(c.x+1, c.y-1));
                }
                if(isValid(n, m, c.x+1, c.y) && !isVisited[c.x+1][c.y]){
                    q.add(new Cell(c.x+1, c.y));
                }
                if(isValid(n, m, c.x+1, c.y+1) && !isVisited[c.x+1][c.y+1]){
                    q.add(new Cell(c.x+1, c.y+1));
                }
            }
        }

        int[][] mine = new int[n][m];
        for(int i=operations.size()-1;i>=0;i--){
            Ops ops = operations.get(i);
            convert(mine, ops.i, ops.j, ops.k);
        }

        boolean flag = true;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if ((copy_color[i][j] != mine[i][j]) || (color[i][j]!=-1)) {
                    flag = false;
                    break;
                }
            }
        }

        if(flag){
            sb.append(operations.size()).append("\n");
            for(int i=operations.size()-1;i>=0;i--){
                Ops ops = operations.get(i);
                sb.append(ops).append("\n");
            }
        }
        else{
            sb.append("-1\n");
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
