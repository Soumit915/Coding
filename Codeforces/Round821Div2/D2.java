package Codeforces.Round821Div2;

import java.util.*;
import java.io.*;

public class D2 {

    static long recurse(long[][] dp, List<Integer> unmatched, int start, int end, long x, long y){
        int n = unmatched.size();

        if(start >= end)
            return 0;

        if(start >= n)
            return 0;

        for(int j=end-1;j>=0;j--){
            for(int i=j+1;i<=end;i++){
                long min = (long) 1e15;
                int count_in_btwn = (i - j - 1);
                if(count_in_btwn%2 == 1) {
                    continue;
                }

                int last = end - ((end-(i+1)+1)%2);
                if(j == i-1){
                    min = Math.min(min, dp[i+1][last] + (x * (unmatched.get(i) - unmatched.get(j))));
                }

                long rec_val = dp[j+1][i-1] + dp[i+1][last];
                min = Math.min(min, rec_val + y);

                dp[j][i] = min;
            }
        }

        return dp[0][end];
    }

    static long getAns(List<Integer> unmatched, long x, long y){
        int n = unmatched.size();
        long[][] dp = new long[n+1][n+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], 0);
        }
        return recurse(dp, unmatched,0, unmatched.size() - 1, x, y);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            long x = sc.nextLong();
            long y = sc.nextLong();

            String a = sc.next();
            String b = sc.next();

            List<Integer> list = new ArrayList<>();
            for(int i=0;i<n;i++){
                if(a.charAt(i) != b.charAt(i)){
                    list.add(i);
                }
            }

            if(list.size() % 2 == 1){
                sb.append("-1\n");
                continue;
            }

            if(list.size() == 0){
                sb.append("0\n");
                continue;
            }

            long ans;
            if(x > y){
                if(list.size() == 2){
                    if(list.get(0)+1 == list.get(1)){
                        if(list.get(1)+2 < n || list.get(0)-2 >=0){
                            ans = Math.min(2*y, x);
                        }
                        else ans = x;
                    }
                    else{
                        ans = y;
                    }
                }
                else{
                    long count = list.size() / 2;
                    ans = y * count;
                }
            }
            else{
                ans = getAns(list, x, y);
            }

            sb.append(ans).append("\n");
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
