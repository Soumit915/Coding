package Leetcode;

import java.io.*;
import java.util.*;

public class PaintHouse3 {

    static int inf = 1000100;

    public static int minCost(int[] houses, int[][] cost, int n, int m, int target) {

        int[][] dp = new int[m][target];

        for(int i=0;i<n;i++){

            int[][] local = new int[m][target];

            for(int j=0;j<m;j++){
                for(int k=0;k<target;k++){

                    if(i == 0){
                        if(houses[i] == 0){
                            if(k == 0)
                                local[j][k] = cost[i][j];
                            else local[j][k] = inf;
                        }
                        else{
                            if(j != houses[i]-1){
                                local[j][k] = inf;
                                continue;
                            }

                            if(k == 0)
                                local[j][k] = 0;
                            else local[j][k] = inf;
                        }
                        continue;
                    }

                    if(houses[i] == 0){

                        int min = inf;
                        for(int l=0;l<m;l++){
                            if(l == j){
                                min = Math.min(min, dp[l][k] + cost[i][j]);
                            }
                            else{
                                if(k != 0){
                                    min = Math.min(min, dp[l][k-1] + cost[i][j]);
                                }
                            }
                        }

                        local[j][k] = min;
                    }
                    else{

                        if(j != houses[i]-1){
                            local[j][k] = inf;
                            continue;
                        }

                        int min = inf;
                        for(int l=0;l<m;l++){
                            if(l == j){
                                min = Math.min(min, dp[l][k]);
                            }
                            else{
                                if(k != 0){
                                    min = Math.min(min, dp[l][k-1]);
                                }
                            }
                        }

                        local[j][k] = min;
                    }

                }
            }

            dp = local;
        }

        int min = inf;
        for(int i=0;i<m;i++){
            min = Math.min(min, dp[i][target-1]);
        }

        if(min >= inf){
            return -1;
        }
        else{
            return min;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int[] houses = {3,1,2,3};
        int[][] cost = {{1,1,1},{1,1,1},{1,1,1},{1,1,1}};
        int target = 3;

        System.out.println(minCost(houses, cost, houses.length, cost[0].length, target));

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
