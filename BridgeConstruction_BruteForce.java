import com.sun.source.tree.UsesTree;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class BridgeConstruction_BruteForce {
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
    static String format(String s, int n){
        StringBuilder sb = new StringBuilder();
        for(int i=s.length()+1;i<=n;i++){
            sb.append("0");
        }
        sb.append(s);
        return sb.toString();
    }
    static boolean isPossible(ArrayList<Interval> interval_list, int n){
        int ptr = -1;
        for(Interval interval: interval_list){
            if(interval.li>ptr+1)
                return false;
            ptr = Math.max(ptr, interval.ri);
        }
        return ptr==n-1;
    }
    static int minimumCost(int n, int m, int[][] teams){

        Interval[] intervals = new Interval[m];
        for(int i=0;i<m;i++){
            intervals[i] = new Interval(i, teams[i][0]-1, teams[i][1]-1);
        }

        int lim = (int) Math.pow(2, m);
        long min = Long.MAX_VALUE;
        Arrays.sort(intervals);
        for(int i=0;i<lim;i++){
            String s = Integer.toBinaryString(i);
            s = format(s, m);

            ArrayList<Interval> interval_subset = new ArrayList<>();
            for(int j=0;j<m;j++){
                if(s.charAt(j)=='1'){
                    interval_subset.add(intervals[j]);
                }
            }

            if(isPossible(interval_subset, n)){
                long ans = 0;
                for(Interval interval: interval_subset)
                    ans += interval.ri-interval.li+1;
                min = Math.min(min, ans);
            }
        }

        if(min==Long.MAX_VALUE)
            return -1;
        else return (int) min;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");

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
