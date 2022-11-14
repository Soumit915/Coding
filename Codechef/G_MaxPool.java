package Codechef;

import java.io.*;
import java.util.*;

public class G_MaxPool {
    static class Cell implements Comparable<Cell>{
        int x;
        int y;
        Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int compareTo(Cell cell){
            int c = Integer.compare(this.x, cell.x);
            if(c==0){
                return Integer.compare(this.y, cell.y);
            }
            else return c;
        }
    }

    static boolean isValid(int n, int i, int j){
        return 0<=i && i<n && 0<=j && j<n;
    }

    static boolean isOk(int[][] original, int x, int y, int[][] toget){
        int n = original.length;

        if(isValid(n, x-1, y-1) && isValid(n, x-1, y) && isValid(n, x, y-1) && isValid(n, x, y)){
            int max = Math.max(Math.max(original[x-1][y-1], original[x-1][y]), Math.max(original[x][y-1], original[x][y]));
            if(max!=toget[x-1][y-1])
                return true;
        }

        if(isValid(n, x-1, y) && isValid(n, x-1, y+1) && isValid(n, x, y) && isValid(n, x, y+1)){
            int max = Math.max(Math.max(original[x-1][y], original[x-1][y+1]), Math.max(original[x][y], original[x][y+1]));
            if(max!=toget[x-1][y])
                return true;
        }

        if(isValid(n, x, y-1) && isValid(n, x, y) && isValid(n, x+1, y-1) && isValid(n, x+1, y)){
            int max = Math.max(Math.max(original[x][y-1], original[x][y]), Math.max(original[x+1][y-1], original[x+1][y]));
            if(max!=toget[x][y-1])
                return true;
        }

        if(isValid(n, x, y) && isValid(n, x, y+1) && isValid(n, x+1, y) && isValid(n, x+1, y+1)){
            int max = Math.max(Math.max(original[x][y], original[x][y+1]), Math.max(original[x+1][y], original[x+1][y+1]));
            return max != toget[x][y];
        }

        return false;
    }

    static ArrayList<Cell> getMerged(ArrayList<Cell> list){
        ArrayList<Cell> mergedcells = new ArrayList<>();

        if(list.size()==2){
            Cell c1 = list.get(0);
            Cell c2 = list.get(1);

            if(c1.y>c2.y){
                mergedcells.add(new Cell(c1.x+1, c1.y));
                mergedcells.add(new Cell(c1.x, c1.y));
            }
            else if(c1.x==c2.x){
                mergedcells.add(new Cell(c1.x, c1.y+1));
                mergedcells.add(new Cell(c1.x+1, c1.y+1));
            }
            else if(c1.y==c2.y){
                mergedcells.add(new Cell(c1.x+1, c1.y));
                mergedcells.add(new Cell(c1.x+1, c1.y+1));
            }
            else{
                mergedcells.add(new Cell(c1.x+1, c1.y+1));
                mergedcells.add(new Cell(c1.x, c1.y));
            }

            return mergedcells;
        }
        else if(list.size()==3){
            Cell c1 = list.get(0);
            Cell c2 = list.get(1);

            if(c1.x==c2.x){
                mergedcells.add(new Cell(c1.x+1, c1.y+1));
            }
            else if(c1.y==c2.y){
                mergedcells.add(new Cell(c1.x+1, c1.y+1));
            }
            else{
                mergedcells.add(new Cell(c1.x+1, c1.y));
            }

            return mergedcells;
        }
        else{
            Cell c1 = list.get(0);
            mergedcells.add(new Cell(c1.x+1, c1.y+1));
            return mergedcells;
        }
    }

    static boolean combsCheck(int[][] original, ArrayList<Cell> list, int v){
        if(list.size()==1){
            int x = list.get(0).x;
            int y = list.get(0).y;
            return original[x][y] != v && original[x][y + 1] != v
                    && original[x + 1][y] != v && original[x + 1][y + 1] != v;
        }
        else if(list.size()==2){
            ArrayList<Cell> merged_cell = getMerged(list);
            return original[merged_cell.get(0).x][merged_cell.get(0).y] != v
                    && original[merged_cell.get(1).x][merged_cell.get(1).y] != v;
        }
        else if(list.size()==3){
            Cell merged_cell = getMerged(list).get(0);
            return original[merged_cell.x][merged_cell.y] != v;
        }
        else{
            Cell merged_cell = getMerged(list).get(0);
            return original[merged_cell.x][merged_cell.y] != v;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int[][] mat = new int[n-1][n-1];
            for(int i=0;i<n-1;i++){
                mat[i] = sc.nextIntArray(n-1);
            }

            HashMap<Integer, ArrayList<Cell>> hash = new HashMap<>();
            for(int i=0;i<n-1;i++){
                for(int j=0;j<n-1;j++){
                    Cell cell = new Cell(i, j);
                    ArrayList<Cell> list = hash.getOrDefault(mat[i][j], new ArrayList<>());
                    list.add(cell);
                    hash.put(mat[i][j], list);
                }
            }

            int[][] original = new int[n][n];
            Stack<Integer> stk = new Stack<>();
            for(int i=n*n;i>=1;i--){
                if(hash.containsKey(i)){
                    ArrayList<Cell> list = hash.get(i);
                    for(Cell cells: list){
                        original[cells.x][cells.y] = i;
                        original[cells.x][cells.y+1] = i;
                        original[cells.x+1][cells.y] = i;
                        original[cells.x+1][cells.y+1] = i;
                    }
                }
                else {
                    stk.push(i);
                }
            }

            for(int i=1;i<=n*n;i++){
                if(stk.isEmpty())
                    break;
                if(hash.containsKey(i)){
                    ArrayList<Cell> list = hash.get(i);
                    Collections.sort(list);
                    for(Cell cells: list){
                        //for i, j
                        if(i==original[cells.x][cells.y]){
                            int x = cells.x;
                            int y = cells.y;
                            int v = stk.pop();

                            original[x][y] = v;
                            if(isOk(original, x, y, mat) || combsCheck(original, list, i)){
                                original[x][y] = i;
                                stk.push(v);
                            }
                        }
                        if(stk.isEmpty())
                            break;

                        //for i, j+1
                        if(i==original[cells.x][cells.y+1]){
                            int x = cells.x;
                            int y = cells.y+1;
                            int v = stk.pop();

                            original[x][y] = v;
                            if(isOk(original, x, y, mat) || combsCheck(original, list, i)){
                                original[x][y] = i;
                                stk.push(v);
                            }
                        }
                        if(stk.isEmpty())
                            break;

                        //for i+1, j
                        if(i==original[cells.x+1][cells.y]){
                            int x = cells.x+1;
                            int y = cells.y;
                            int v = stk.pop();

                            original[x][y] = v;
                            if(isOk(original, x, y, mat) || combsCheck(original, list, i)){
                                original[x][y] = i;
                                stk.push(v);
                            }
                        }
                        if(stk.isEmpty())
                            break;

                        //for i+1, j+1
                        if(i==original[cells.x+1][cells.y+1]){
                            int x = cells.x+1;
                            int y = cells.y+1;
                            int v = stk.pop();

                            original[x][y] = v;
                            if(isOk(original, x, y, mat) || combsCheck(original, list, i)){
                                original[x][y] = i;
                                stk.push(v);
                            }
                        }
                        if(stk.isEmpty())
                            break;
                    }
                }
            }

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    sb.append(original[i][j]).append(" ");
                }
                sb.append("\n");
            }
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
