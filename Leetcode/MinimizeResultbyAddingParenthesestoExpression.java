package Leetcode;

import java.io.*;
import java.util.*;

public class MinimizeResultbyAddingParenthesestoExpression {

    static long getVal(String s){
        int n = s.length();

        int start = -1, end = -1;
        for(int i=0;i<n;i++){
            if(s.charAt(i) == '('){
                start = i;
            }

            if(s.charAt(i) == ')'){
                end = i;
                break;
            }
        }

        String brackets = s.substring(start+1, end);

        long sum = 0;
        String[] numbers = brackets.split("\\+");
        for(String number: numbers){
            if(number.equals(""))
                return Long.MAX_VALUE;
            sum += Long.parseLong(number);
        }
        if(numbers.length < 2)
            return Long.MAX_VALUE;

        long[] start_nums = new long[1];
        start_nums[0] = 1;
        if(start != 0){
            numbers[0] = s.substring(0, start);
            start_nums[0] = Long.parseLong(numbers[0]);
        }

        long[] end_nums = new long[1];
        end_nums[0] = 1;
        if(end != n-1){
            numbers[0] = s.substring(end+1);
            end_nums[0] = Long.parseLong(numbers[0]);
        }

        sum = (start_nums[0] * sum * end_nums[0]);

        return sum;
    }

    static String pad(String ter, int n){
        return "0".repeat(Math.max(0, n - (ter.length() + 1) + 1)) + ter;
    }

    static int countBits(int n){
        int c = 0;
        while(n > 0){
            n = n & (n-1);
            c++;
        }

        return c;
    }

    public String minimizeResult(String expression) {
        int n = expression.length();

        int lim = (1 << (n+1));
        long max = Long.MAX_VALUE;
        String ans = "";
        for(int i=0;i<lim;i++){
            if(countBits(i) != 2)
                continue;

            String bin = Integer.toBinaryString(i);
            bin = pad(bin, n+1);

            StringBuilder sb = new StringBuilder();
            boolean flag = true;
            for(int j=0;j<n;j++){
                if(bin.charAt(j) == '1' && flag){
                    sb.append("(");
                    flag = false;
                }
                else if(bin.charAt(j) == '1'){
                    sb.append(")");
                }
                sb.append(expression.charAt(j));
            }

            if(bin.charAt(n) == '1')
                sb.append(")");

            String poss = sb.toString();
            long val = getVal(poss);

            if(val < max){
                max = val;
                ans = poss;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();


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
