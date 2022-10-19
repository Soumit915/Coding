package Codeforces.Expert_7;

import java.util.*;
import java.io.*;

public class A {

    static class Cell {
        int x, y;

        Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int getNextPowerOf2(int n)
    {
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>25);
        return n+1;
    }

    public static void update(int[] segTree, int sn, int val, int ind, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn] = val;
            return;
        }

        int mid = (ll+ul)/2;
        if(ind<=mid)
        {
            update(segTree, 2*sn+1, val, ind, ll, mid);
        }
        else
        {
            update(segTree, 2*sn+2, val, ind, mid+1, ul);
        }

        segTree[sn] = segTree[2*sn+1] + segTree[2*sn+2];
    }
    public static int query(int[] segTree, int sn, int start, int end, int ll, int ul)
    {
        //for no overlap
        if(start>ul || end<ll)
        {
            return 0;
        }

        //for total overlap
        if(start<=ll && end>=ul)
        {
            return segTree[sn];
        }

        int mid = (ll+ul)/2;
        return query(segTree, 2*sn+1, start, end, ll, mid)
                + query(segTree, 2*sn+2, start, end, mid+1, ul);
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int q = sc.nextInt();

        HashMap<Integer, HashMap<Integer, Cell>> hash = new HashMap<>();

        int[] row = new int[n];
        int[] col = new int[n];

        int sn = 2 * getNextPowerOf2(n) - 1;
        int[] rowTree = new int[sn];
        int[] colTree = new int[sn];

        StringBuilder sb = new StringBuilder();
        while(q-->0){
            int t = sc.nextInt();
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;

            if(t == 1){
                HashMap<Integer, Cell> col_hash = hash.getOrDefault(x, new HashMap<>());

                if(!col_hash.containsKey(y)){

                    Cell cell = new Cell(x, y);

                    row[x]++;
                    col[y]++;

                    col_hash.put(y, cell);
                    hash.put(x, col_hash);

                    update(rowTree, 0, 1, x, 0, n-1);
                    update(colTree, 0, 1, y, 0, n-1);
                }
            }
            else if(t == 2){
                HashMap<Integer, Cell> col_hash = hash.getOrDefault(x, new HashMap<>());

                if(col_hash.containsKey(y)){
                    col_hash.remove(y);

                    row[x]--;
                    col[y]--;

                    if(row[x] == 0)
                        update(rowTree, 0, 0, x, 0, n-1);
                    if(col[y] == 0)
                        update(colTree, 0, 0, y, 0, n-1);
                }
            }
            else{
                int x1 = sc.nextInt() - 1;
                int y1 = sc.nextInt() - 1;

                int count_row_rooks = query(rowTree, 0, x, x1, 0, n-1);
                int count_col_rooks = query(colTree, 0, y, y1, 0, n-1);

                if(count_row_rooks == (x1 - x + 1) || count_col_rooks == (y1 - y + 1))
                    sb.append("Yes\n");
                else sb.append("No\n");
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
