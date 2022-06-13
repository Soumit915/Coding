package Leetcode;

import java.io.*;
import java.util.*;

public class BookingConcertTicketsInGroups {

    static class BookMyShow {

        static class Node{
            long maxLeft;
            long sumLeft;

            Node(long max, long left){
                this.maxLeft = max;
                this.sumLeft = left;
            }
        }

        static int getNextPowerOf2(int n){
            n |= n>>1;
            n |= n>>2;
            n |= n>>4;
            n |= n>>8;
            n |= n>>16;
            n |= n>>31;
            return n;
        }

        int n, sn;
        long m;
        Node[] segTree;

        static void build(Node[] segTree, int si, int m, int ll, int ul){
            if(ll == ul){
                segTree[si] = new Node(m, m);
                return;
            }

            int mid = (ll + ul) / 2;
            build(segTree, 2*si + 1, m, ll, mid);
            build(segTree, 2*si + 2, m, mid+1, ul);

            segTree[si] = new Node(Math.max(segTree[2*si+1].maxLeft, segTree[2*si+2].maxLeft),
                    segTree[2*si+1].sumLeft + segTree[2*si+2].sumLeft);
        }

        static int[] gatherQuery(Node[] segTree, int si, long m, int k, int maxRow, int ll, int ul){
            //no-overlap
            if(ll > maxRow){
                return new int[]{-1, -1};
            }

            //total-overlap
            if(ul <= maxRow){

                if(segTree[si].maxLeft >= k){
                    if(ll == ul){
                        int[] ans = {ll, (int) (m - segTree[si].maxLeft)};
                        segTree[si].sumLeft -= k;
                        segTree[si].maxLeft -= k;

                        return ans;
                    }

                    int mid = (ll + ul) / 2;

                    int[] ans;
                    if(segTree[2*si + 1].maxLeft >= k){
                        ans = gatherQuery(segTree, 2 * si + 1, m, k, maxRow, ll, mid);
                    }
                    else{
                        ans = gatherQuery(segTree, 2 * si + 2, m, k, maxRow, mid + 1, ul);

                    }
                    segTree[si] = new Node(Math.max(segTree[2*si+1].maxLeft, segTree[2*si+2].maxLeft),
                            segTree[2*si+1].sumLeft + segTree[2*si+2].sumLeft);
                    return ans;
                }
                else{
                    return new int[]{-1, -1};
                }
            }

            //partial overlap
            int mid = (ll + ul)/2;
            int[] ans = gatherQuery(segTree, 2*si+1, m, k, maxRow, ll, mid);
            if(ans[0] == -1){
                ans = gatherQuery(segTree, 2*si+2, m, k, maxRow, mid+1, ul);
            }

            segTree[si] = new Node(Math.max(segTree[2*si+1].maxLeft, segTree[2*si+2].maxLeft),
                    segTree[2*si+1].sumLeft + segTree[2*si+2].sumLeft);

            return ans;
        }

        static boolean isPossible(Node[] segTree, int si, int k, int maxRow, int ll, int ul){
            //no-overlap
            if(ll > maxRow){
                return k == 0;
            }

            //total-overlap
            if(ul <= maxRow){

                if(segTree[si].sumLeft >= k){
                    if(ll == ul){
                        return true;
                    }

                    int mid = (ll + ul) / 2;

                    boolean ans;
                    int maxLeft = (int) Math.min(segTree[2*si+1].sumLeft, k);

                    ans = isPossible(segTree, 2*si + 1, maxLeft, maxRow, ll, mid);
                    ans = ans & isPossible(segTree, 2*si + 2, k-maxLeft, maxRow, mid+1, ul);

                    if(ans) {
                        segTree[si] = new Node(Math.max(segTree[2 * si + 1].maxLeft, segTree[2 * si + 2].maxLeft),
                                segTree[2 * si + 1].sumLeft + segTree[2 * si + 2].sumLeft);
                    }
                    return ans;
                }
                else{
                    return false;
                }
            }

            int mid = (ll + ul) / 2;
            boolean ans;
            if(mid <= maxRow){
                int maxLeft = (int) Math.min(segTree[2*si+1].sumLeft, k);
                ans = isPossible(segTree, 2*si + 2, k-maxLeft, maxRow, mid+1, ul);
            }
            else{
                ans = isPossible(segTree, 2*si+1, k, maxRow, ll, mid);
            }

            if(ans) {
                segTree[si] = new Node(Math.max(segTree[2 * si + 1].maxLeft, segTree[2 * si + 2].maxLeft),
                        segTree[2 * si + 1].sumLeft + segTree[2 * si + 2].sumLeft);
            }

            return ans;
        }

        static boolean scatterQuery(Node[] segTree, int si, int k, int maxRow, int ll, int ul){
            //no-overlap
            if(ll > maxRow){
                return k == 0;
            }

            //total-overlap
            if(ul <= maxRow){

                if(segTree[si].sumLeft >= k){
                    if(ll == ul){
                        segTree[si].sumLeft -= k;
                        segTree[si].maxLeft -= k;

                        return true;
                    }

                    int mid = (ll + ul) / 2;

                    boolean ans;
                    int maxLeft = (int) Math.min(segTree[2*si+1].sumLeft, k);

                    ans = scatterQuery(segTree, 2*si + 1, maxLeft, maxRow, ll, mid);
                    ans = ans & scatterQuery(segTree, 2*si + 2, k-maxLeft, maxRow, mid+1, ul);

                    if(ans) {
                        segTree[si] = new Node(Math.max(segTree[2 * si + 1].maxLeft, segTree[2 * si + 2].maxLeft),
                                segTree[2 * si + 1].sumLeft + segTree[2 * si + 2].sumLeft);
                    }
                    return ans;
                }
                else{
                    return false;
                }
            }

            int mid = (ll + ul) / 2;
            boolean ans;
            if(mid <= maxRow){
                int maxLeft = (int) Math.min(segTree[2*si+1].sumLeft, k);

                ans = scatterQuery(segTree, 2*si + 1, maxLeft, maxRow, ll, mid);
                ans = ans & scatterQuery(segTree, 2*si + 2, k-maxLeft, maxRow, mid+1, ul);
            }
            else{
                ans = scatterQuery(segTree, 2*si+1, k, maxRow, ll, mid);
            }

            if(ans) {
                segTree[si] = new Node(Math.max(segTree[2 * si + 1].maxLeft, segTree[2 * si + 2].maxLeft),
                        segTree[2 * si + 1].sumLeft + segTree[2 * si + 2].sumLeft);
            }

            return ans;
        }

        public BookMyShow(int n, int m) {
            this.n = n;
            this.m = m;

            sn = 2 * getNextPowerOf2(n) - 1;
            this.segTree = new Node[sn];

            build(segTree, 0, m, 0, n-1);
        }

        public int[] gather(int k, int maxRow) {
            int[] ans = gatherQuery(segTree, 0, m, k, maxRow, 0, n-1);
            if(ans[0] != -1)
                return ans;
            else return new int[]{};
        }

        public boolean scatter(int k, int maxRow) {
            if(isPossible(segTree, 0, k, maxRow, 0, n-1))
                return scatterQuery(segTree, 0, k, maxRow, 0, n-1);
            else return false;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        int m = sc.nextInt();

        BookMyShow bms = new BookMyShow(n, m);
        int q = sc.nextInt();
        while(q-->0){
            int type = sc.nextInt();
            int k = sc.nextInt();
            int maxRow = sc.nextInt();

            if(type == 0){
                System.out.println(Arrays.toString(bms.gather(k, maxRow)));
            }
            else{
                System.out.println(bms.scatter(k, maxRow));
            }
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
