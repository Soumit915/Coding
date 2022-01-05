package Codeforces.Round719Div3;

import java.io.*;
import java.util.*;

public class G {
    static class Node{
        int x;
        int y;
        long[] dir;
        long val;
        Node(int x, int y, long val){
            this.x = x;
            this.y = y;
            this.val = val;
            this.dir = new long[]{Long.MAX_VALUE/100, Long.MAX_VALUE/100};
        }
    }
    static boolean isValid(int n, int m, int x, int y){
        return 0<=x && x<n && 0<=y && y<m;
    }
    static void bfs(Node[][] nodes, int startx, int starty, int dir, long w){
        Queue<Node> q = new LinkedList<>();

        int n = nodes.length;
        int m = nodes[0].length;
        boolean[][] isVisited = new boolean[n][m];

        Node startnode = nodes[startx][starty];
        isVisited[startx][starty] = true;
        q.add(startnode);
        startnode.dir[dir] = 0;

        while(!q.isEmpty()){
            Node cnode = q.remove();

            if(isValid(n, m, cnode.x+1, cnode.y)
                    && !isVisited[cnode.x+1][cnode.y] && nodes[cnode.x+1][cnode.y].val!=-1){
                Node next = nodes[cnode.x+1][cnode.y];
                next.dir[dir] = cnode.dir[dir]+w;
                isVisited[next.x][next.y] = true;
                q.add(next);
            }

            if(isValid(n, m, cnode.x-1, cnode.y)
                    && !isVisited[cnode.x-1][cnode.y] && nodes[cnode.x-1][cnode.y].val!=-1){
                Node next = nodes[cnode.x-1][cnode.y];
                next.dir[dir] = cnode.dir[dir]+w;
                isVisited[next.x][next.y] = true;
                q.add(next);
            }

            if(isValid(n, m, cnode.x, cnode.y+1)
                    && !isVisited[cnode.x][cnode.y+1] && nodes[cnode.x][cnode.y+1].val!=-1){
                Node next = nodes[cnode.x][cnode.y+1];
                next.dir[dir] = cnode.dir[dir]+w;
                isVisited[next.x][next.y] = true;
                q.add(next);
            }

            if(isValid(n, m, cnode.x, cnode.y-1)
                    && !isVisited[cnode.x][cnode.y-1] && nodes[cnode.x][cnode.y-1].val!=-1){
                Node next = nodes[cnode.x][cnode.y-1];
                next.dir[dir] = cnode.dir[dir]+w;
                isVisited[next.x][next.y] = true;
                q.add(next);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        int w = sc.nextInt();
        long[][] mat = new long[n][m];
        for(int i=0;i<n;i++){
            mat[i] = sc.nextLongArray(m);
        }

        Node[][] nodes = new Node[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                nodes[i][j] = new Node(i, j, mat[i][j]);
            }
        }

        bfs(nodes, 0, 0, 0, w);
        bfs(nodes, n-1, m-1, 1, w);

        long min = nodes[0][0].dir[1];
        long end_min = Long.MAX_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(nodes[i][j].val>0)
                    end_min = Math.min(end_min, nodes[i][j].dir[1]+nodes[i][j].val);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(nodes[i][j].val>0)
                    min = Math.min(min, end_min+nodes[i][j].dir[0]+nodes[i][j].val);
            }
        }

        if(min>1e16)
            System.out.println(-1);
        else System.out.println(min);
        
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
