package Codeforces.Round775Div2;

import java.io.*;
import java.util.*;

public class C {

    static class Cell implements Comparable<Cell>{
        int x, y, color;
        Cell(int x, int y, int color){
            this.x = x;
            this.y = y;
            this.color = color;
        }
        public int compareTo(Cell cell){
            int c = Integer.compare(this.x, cell.x);
            if(c==0)
                return Integer.compare(this.y, cell.y);
            else return c;
        }
    }

    public static int getnextPowerOf2(int n)
    {
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>30);

        return n+1;
    }

    public static void update(long[] segTree, int sn, long val, int ind, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[sn] += val;
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
    public static long query(long[] segTree, int sn, int start, int end, int ll, int ul)
    {
        if(start > end)
            return 0;

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

    static long countDistPairs(List<Cell> list){
        Collections.sort(list);

        long rowsum = 0;
        Map<Integer, Integer> columnMap = new HashMap<>();
        List<Integer> col_list = new ArrayList<>();
        for (Cell cell : list) {
            rowsum += cell.x;
            col_list.add(cell.y);
        }

        Collections.sort(col_list);
        int col_index_mapper_count = 0;
        for (Integer integer : col_list) {
            if (!columnMap.containsKey(integer)) {
                columnMap.put(integer, col_index_mapper_count);
                col_index_mapper_count++;
            }
        }

        int sn = 2 * getnextPowerOf2(col_index_mapper_count) - 1;
        long[] segTree_front = new long[sn];
        long[] count_front = new long[sn];
        for (Cell cell : list) {
            update(segTree_front, 0,
                    cell.y, columnMap.get(cell.y), 0, col_index_mapper_count-1);
            update(count_front, 0,
                    1, columnMap.get(cell.y), 0, col_index_mapper_count-1);
        }

        long count = 0;
        for(int i=0;i<list.size()-1;i++){
            rowsum -= list.get(i).x;
            count += (rowsum - ((long) list.get(i).x) *((long) list.size() - i - 1));
            long sumfront = query(segTree_front, 0,
                    0, columnMap.get(list.get(i).y)-1, 0, col_index_mapper_count-1);
            long countfront = query(count_front, 0,
                    0, columnMap.get(list.get(i).y)-1, 0, col_index_mapper_count-1);

            long sumback = query(segTree_front, 0, columnMap.get(list.get(i).y)+1,
                    col_index_mapper_count-1, 0, col_index_mapper_count-1);
            long countback = query(count_front, 0, columnMap.get(list.get(i).y)+1,
                    col_index_mapper_count-1, 0, col_index_mapper_count-1);

            count += (((long)list.get(i).y) * (countfront) - sumfront);
            count += (sumback - (((long)list.get(i).y) * countback));

            Cell cell = list.get(i);
            update(segTree_front, 0,
                    cell.y*-1, columnMap.get(cell.y), 0, col_index_mapper_count-1);
            update(count_front, 0,
                    -1, columnMap.get(cell.y), 0, col_index_mapper_count-1);
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] mat = new int[n][m];

        for(int i=0;i<n;i++){
            mat[i] = sc.nextIntArray(m);
        }

        Map<Integer, List<Cell>> color_map = new HashMap<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(color_map.containsKey(mat[i][j])){
                    List<Cell> colorlist = color_map.get(mat[i][j]);
                    colorlist.add(new Cell(i, j, mat[i][j]));
                }
                else{
                    List<Cell> colorlist = new ArrayList<>();
                    colorlist.add(new Cell(i, j, mat[i][j]));
                    color_map.put(mat[i][j], colorlist);
                }
            }
        }

        long ans = 0;
        for(int i: color_map.keySet()){
            ans += countDistPairs(color_map.get(i));
        }

        sb.append(ans).append("\n");

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
