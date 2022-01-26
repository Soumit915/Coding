import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class LostRepresentative {
    static class Cell {
        int x;
        int y;
        Cell(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
    public static boolean isValid(int n, int i, int j) {
        return (i>=0 && i<n && j>=0 && j<n);
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int[][] mat = new int[n][n];
            for(int i=0;i<n;i++) {
                mat[i] = sc.nextIntArray(n);
            }

            boolean[][] isVisited = new boolean[n][n];

            HashMap<Integer, Cell> hash = new HashMap<>();
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(!isVisited[i][j] && mat[i][j] == 1) {
                        Cell startcell = new Cell(i, j);

                        Queue<Cell> q = new LinkedList<>();
                        q.add(startcell);
                        isVisited[i][j] = true;

                        int count = 1;
                        while(!q.isEmpty()) {
                            Cell thiscell = q.remove();
                            if(isValid(n, thiscell.x+1, thiscell.y) && mat[thiscell.x+1][thiscell.y]==1 && !isVisited[thiscell.x+1][thiscell.y]) {
                                q.add(new Cell(thiscell.x + 1, thiscell.y));
                                count++;
                                isVisited[thiscell.x+1][thiscell.y] = true;
                            }
                            if(isValid(n, thiscell.x-1, thiscell.y) && mat[thiscell.x-1][thiscell.y]==1 && !isVisited[thiscell.x-1][thiscell.y]) {
                                q.add(new Cell(thiscell.x - 1, thiscell.y));
                                count++;
                                isVisited[thiscell.x-1][thiscell.y] = true;
                            }
                            if(isValid(n, thiscell.x, thiscell.y+1) && mat[thiscell.x][thiscell.y+1]==1 && !isVisited[thiscell.x][thiscell.y+1]) {
                                q.add(new Cell(thiscell.x, thiscell.y+1));
                                count++;
                                isVisited[thiscell.x][thiscell.y+1] = true;
                            }
                            if(isValid(n, thiscell.x, thiscell.y-1) && mat[thiscell.x][thiscell.y-1]==1 && !isVisited[thiscell.x][thiscell.y-1]) {
                                q.add(new Cell(thiscell.x, thiscell.y-1));
                                count++;
                                isVisited[thiscell.x][thiscell.y-1] = true;
                            }
                        }

                        hash.put(count, startcell);
                    }
                }
            }

            int r = sc.nextInt();
            if(hash.containsKey(r))
                System.out.println((hash.get(r).x) +" "+(hash.get(r).y));
            else System.out.println("-1 -1");
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
