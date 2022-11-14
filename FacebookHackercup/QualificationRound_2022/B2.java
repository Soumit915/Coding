package FacebookHackercup.QualificationRound_2022;

import java.util.*;
import java.io.*;

public class B2 {

    static class Node{
        int x, y;
        boolean isVisited, inStack;
        char symbol;

        Node(int x, int y, char symbol){
            this.x = x;
            this.y = y;
            this.symbol = symbol;
            this.isVisited = false;
            this.inStack = false;
        }
    }

    static boolean isValid(int r, int c, int x, int y){
        return 0<=x && x<r && 0<=y && y<c;
    }

    static void dfs(Node[][] grid, int x_start, int y_start){
        Node source = grid[x_start][y_start];

        int r = grid.length;
        int c = grid[0].length;

        Stack<Node> stk = new Stack<>();
        Stack<Integer> ptrstk = new Stack<>();
        HashMap<Node, Node> parent = new HashMap<>();

        stk.push(source);
        ptrstk.push(-1);
        source.isVisited = true;

        int[][] steps = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for(int[] step: steps){
            if(isValid(r, c, x_start+step[0], y_start+step[1]) && grid[x_start+step[0]][y_start+step[1]].symbol == '^'){
                parent.put(source, grid[x_start+step[0]][y_start+step[1]]);
                break;
            }
        }

        while(!stk.isEmpty()){
            Node cur = stk.pop();
            int ptr = ptrstk.pop();

            if(ptr < steps.length-1){
                ptr++;

                stk.push(cur);
                ptrstk.push(ptr);

                if(isValid(r, c, cur.x + steps[ptr][0], cur.y + steps[ptr][1])){
                    Node next = grid[cur.x + steps[ptr][0]][cur.y + steps[ptr][1]];

                    if((next.isVisited && !next.inStack) || next.symbol == '#' || parent.get(cur) == next)
                        continue;

                    if(next.inStack){
                        Node node_ptr = cur;
                        while(node_ptr != null){
                            node_ptr.symbol = '^';
                            node_ptr = parent.get(node_ptr);
                        }

                        break;
                    }

                    next.isVisited = true;
                    next.inStack = true;
                    stk.push(next);
                    ptrstk.push(-1);
                    parent.put(next, cur);

                    int count = 0;
                    if(isValid(r, c, next.x-1, next.y) && grid[next.x-1][next.y].symbol == '^' && grid[next.x-1][next.y] != cur) {
                        count++;
                    }

                    if(isValid(r, c, next.x+1, next.y) && grid[next.x+1][next.y].symbol == '^' && grid[next.x+1][next.y] != cur) {
                        count++;
                    }

                    if(isValid(r, c, next.x, next.y-1) && grid[next.x][next.y-1].symbol == '^' && grid[next.x][next.y-1] != cur) {
                        count++;
                    }

                    if(isValid(r, c, next.x, next.y+1) && grid[next.x][next.y+1].symbol == '^' && grid[next.x][next.y+1] != cur) {
                        count++;
                    }

                    if(count > 0){

                        Node node_ptr = next;
                        while(node_ptr != null){
                            node_ptr.symbol = '^';
                            node_ptr = parent.get(node_ptr);
                        }

                        break;
                    }
                }
                else{
                    cur.inStack = false;
                }
            }
        }

        while(!stk.isEmpty()){
            stk.pop().inStack = false;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output.txt");

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int ti=1;ti<=tc;ti++){

            sb.append("Case #").append(ti).append(": ");

            int r = sc.nextInt();
            int c = sc.nextInt();
            char[][] matrix = new char[r][c];

            for(int i=0;i<r;i++){
                matrix[i] = sc.next().toCharArray();
            }

            Node[][] grid = new Node[r][c];
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    grid[i][j] = new Node(i, j, matrix[i][j]);
                }
            }

            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){

                    if(grid[i][j].symbol != '^')
                        continue;

                    int count = 0;
                    if(isValid(r, c, i-1, j) && grid[i-1][j].symbol == '^')
                        count++;

                    if(isValid(r, c, i+1, j) && grid[i+1][j].symbol == '^')
                        count++;

                    if(isValid(r, c, i, j-1) && grid[i][j-1].symbol == '^')
                        count++;

                    if(isValid(r, c, i, j+1) && grid[i][j+1].symbol == '^')
                        count++;

                    if(count == 1){
                        dfs(grid, i, j);
                    }
                    else if(count == 0){
                        //System.out.println(i+" "+j);
                        dfs(grid, i, j);

                        /*for(int i1=0;i1<r;i1++){
                            for(int j1=0;j1<c;j1++){
                                sb.append(grid[i1][j1].symbol);
                            }
                            sb.append("\n");
                        }*/

                        dfs(grid, i, j);

                        /*sb.append("testing 2\n");
                        for(int i1=0;i1<r;i1++){
                            for(int j1=0;j1<c;j1++){
                                sb.append(grid[i1][j1].symbol);
                            }
                            sb.append("\n");
                        }*/
                    }

                }
            }

            boolean flag = true;
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){

                    if(grid[i][j].symbol != '^')
                        continue;

                    int count = 0;
                    if(isValid(r, c, i-1, j) && grid[i-1][j].symbol == '^')
                        count++;

                    if(isValid(r, c, i+1, j) && grid[i+1][j].symbol == '^')
                        count++;

                    if(isValid(r, c, i, j-1) && grid[i][j-1].symbol == '^')
                        count++;

                    if(isValid(r, c, i, j+1) && grid[i][j+1].symbol == '^')
                        count++;

                    if(count <= 1){
                        flag = false;
                        break;
                    }
                }
            }

            if(flag){
                sb.append("Possible\n");
                for(int i=0;i<r;i++){
                    for(int j=0;j<c;j++){
                        sb.append(grid[i][j].symbol);
                    }
                    sb.append("\n");
                }
            }
            else{
                sb.append("Impossible\n");
                /*for(int i=0;i<r;i++){
                    for(int j=0;j<c;j++){
                        sb.append(grid[i][j].symbol);
                    }
                    sb.append("\n");
                }*/
            }
        }

        sc.println(sb.toString());

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
