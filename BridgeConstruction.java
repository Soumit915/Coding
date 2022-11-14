import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class BridgeConstruction {

    static class Interval implements Comparable<Interval>{
        int id;
        int li;
        int ri;
        Interval(int id, int li, int ri){
            this.id = id;
            this.li = li;
            this.ri = ri;
        }
        public int compareTo(Interval interval){
            return Integer.compare(this.li, interval.li);
        }
    }

    static class Pair{
        long val;
        int index;
        Pair(long val, int index){
            this.val = val;
            this.index = index;
        }
    }

    static int nextPowerOf2(int n){
        n |= (n>>1);
        n |= (n>>2);
        n |= (n>>4);
        n |= (n>>8);
        n |= (n>>16);
        n |= (n>>26);
        return n+1;
    }

    static void update(Pair[] segTree, int si, long val, int index, int ll, int ul){
        if(ll==ul){
            segTree[si] = new Pair(val, index);
            return;
        }

        int mid = (ll+ul)/2;
        if(index<=mid){
            update(segTree, 2*si+1, val, index, ll, mid);
        }
        else {
            update(segTree, 2*si+2, val, index, mid+1, ul);
        }

        if(segTree[2*si+1].val<segTree[2*si+2].val){
            segTree[si] = new Pair(segTree[2*si+1].val, segTree[2*si+1].index);
        }
        else{
            segTree[si] = new Pair(segTree[2*si+2].val, segTree[2*si+2].index);
        }
    }

    static Pair query(Pair[] segTree, int si, int start, int end, int ll, int ul){
        //total participation
        if(ll>=start && ul<=end){
            return segTree[si];
        }

        //no participation
        if(ll>end || ul<start){
            return new Pair((long) 1e12, (int) 1e7);
        }

        int mid = (ll+ul)/2;
        Pair left = query(segTree, 2*si+1, start, end, ll, mid);
        Pair right = query(segTree, 2*si+2, start, end, mid+1, ul);
        if(left.val<right.val){
            return new Pair(left.val, left.index);
        }
        else{
            return new Pair(right.val, right.index);
        }
    }

    static int minimumCost(int n, int m, int[][] teams){

        Interval[] intervals = new Interval[m];
        for(int i=0;i<m;i++){
            intervals[i] = new Interval(i, teams[i][0]-1, teams[i][1]-1);
        }

        HashMap<Integer, ArrayList<Interval>> hashinterval = new HashMap<>();
        for(int i=0;i<m;i++){
            int end = intervals[i].ri;

            ArrayList<Interval> interval_list = hashinterval.getOrDefault(end, new ArrayList<>());
            interval_list.add(intervals[i]);

            hashinterval.put(end, interval_list);
        }

        for(int key: hashinterval.keySet()){
            ArrayList<Interval> interval_list = hashinterval.get(key);
            Collections.sort(interval_list);
            hashinterval.put(key, interval_list);
        }

        long[] dp = new long[n];
        for(int i=0;i<n;i++){
            dp[i] = (long) 1e12;
        }

        int sn = 2*nextPowerOf2(n)-1;
        Pair[] segTree = new Pair[sn];
        for(int i=0;i<sn;i++){
            segTree[i] = new Pair((long) 1e12, i);
        }
        for(int i=0;i<n;i++){
            update(segTree, 0, dp[i], i, 0, n-1);
        }

        for(int i=0;i<n;i++){
            ArrayList<Interval> interval_list = hashinterval.getOrDefault(i, new ArrayList<>());

            for(Interval interval: interval_list){
                if(interval.li==0){
                    dp[i] = i-interval.li+1;
                    update(segTree, 0, dp[i], i, 0, n-1);
                    break;
                }
                else{
                    int start = interval.li;
                    Pair pair = query(segTree, 0, start-1, i-1, 0, n-1);
                    Pair thispair = new Pair(pair.val+(i-start+1), pair.index);
                    dp[i] = Math.min(thispair.val, dp[i]);
                }
            }

            update(segTree, 0, dp[i], i, 0, n-1);
        }

        if(dp[n-1]>1e10){
            return -1;
        }
        else{
            return (int) dp[n-1];
        }
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] teams = new int[m][2];
            for(int i=0;i<m;i++){
                teams[i][0] = sc.nextInt();
                teams[i][1] = sc.nextInt();
            }

            sb.append(minimumCost(n, m, teams)).append("\n");
        }

        sc.println(sb.toString());

        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000.0);

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
