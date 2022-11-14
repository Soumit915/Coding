package Leetcode;

import java.util.*;
import java.io.*;

public class SplitMessageBasedOnLimit {

    static int len(int n){
        int c = 0;
        while(n > 0){
            n /= 10;
            c++;
        }

        return c;
    }

    static boolean isValid(String message, int total_len, int limit, int count_len){
        int tl = len(total_len);

        limit -= tl + 3;

        if(limit <= 0)
            return false;

        int n = message.length();
        for(int i=1;i<=total_len;i++){
            int left = limit - len(i);

            if(left <= 0)
                return false;

            n -= left;
        }

        return n <= 0;
    }

    static String[] splitString(String message, int token_size, int total_tokens){

        String[] ans = new String[total_tokens];

        int index = 0;

        for(int i=0;i<total_tokens;i++){
            String suffix = "<" + (i + 1) + "/" + (total_tokens) + ">";

            int left = token_size - suffix.length();

            StringBuilder sb = new StringBuilder();
            while(left > 0 && index < message.length()){
                sb.append(message.charAt(index));
                left--;
                index++;
            }

            ans[i] = sb + suffix;
        }

        return ans;
    }

    public static String[] splitMessage(String message, int limit) {
        int n = message.length();

        int valid = -1;
        int count = 0;
        for(int i=1;i<=n;i++){
            count += len(i);
            if(isValid(message, i, limit, count)){
                valid = i;
                break;
            }
        }

        if(valid == -1){
            return new String[0];
        }

        return splitString(message, limit, valid);
    }

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        int n = 20000;
        for(int i=0;i<n;i++){
            int type = (int) (Math.random() * 10);

            if(type == 0){
                sb.append(" ");
                continue;
            }
            char ch = (char) ((int) (Math.random() * 10) + 'a');
            sb.append(ch);
        }
        String s = sb.toString();
        s = s.trim();

        long start = System.currentTimeMillis();

        System.out.println(s);
        String[] ans = splitMessage(s, 13);
        System.out.println(ans.length);

        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);

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
