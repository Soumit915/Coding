package Codeforces.Round717Div2;

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[] arr = sc.nextIntArray(n);
        ArrayList<Queue<Integer>> arlist = new ArrayList<>();
        for(int i=0;i<2020;i++){
            arlist.add(new LinkedList<>());
        }
        for(int i=0;i<n;i++){
            arlist.get(arr[i]).add(i);
        }

        int sum = 0;
        for(int i=0;i<n;i++){
            sum += arr[i];
        }
        sc.sort(arr);

        long[][] dp = new long[n+1][sum+5];
        for(int i=0;i<=n;i++){
            for(int j=0;j<dp[0].length;j++){
                dp[i][j] = Long.MAX_VALUE;
            }
        }
        for(int i=0;i<=n;i++){
            dp[i][0] = 0;
        }
        for(int i=1;i<=n;i++){
            for(int j=0;j<dp[0].length;j++){
                if(j-arr[i-1]>=0 && dp[i-1][j-arr[i-1]]!=Long.MAX_VALUE){
                    dp[i][j] = Math.min(dp[i][j-arr[i-1]]+1, dp[i-1][j]);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        if(sum%2==1 || dp[n][sum/2]==Long.MAX_VALUE){
            System.out.println(0);
        }
        else{
            int goodval = 0;
            long goodcount = Long.MAX_VALUE;
            for(int i=1;i<dp[0].length;i++){
                if(dp[n][i]>0 && dp[n][i]!=Long.MAX_VALUE &&
                        ((sum-i)%2!=0 || dp[n][(sum-i)/2]==Long.MAX_VALUE)){
                    if(dp[n][i]<goodcount){
                        goodval = i;
                        goodcount = dp[n][i];
                    }
                }
            }

            System.out.println(goodcount);
            ArrayList<Integer> list = new ArrayList<>();
            int i = dp.length-1;
            int j = goodval;
            while(i!=0){
                if(dp[i][j]==dp[i-1][j]){
                    i--;
                }
                else{
                    j-=arr[i-1];
                    list.add(arr[i-1]);
                    i--;
                }
            }

            Collections.sort(list);
            for(i=0;i<list.size();i++){
                Queue<Integer> q = arlist.get(list.get(i));
                System.out.print((q.remove()+1)+" ");
            }
            System.out.println();
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
