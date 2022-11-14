package Codeforces;

import java.io.*;
import java.util.*;

public class NearestBeautifulNumber_Hard {

    static int getSmallest(int[] arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i] > 0)
                return i;
        }

        return -1;
    }

    static String recurse(String sn, int index, String cur, int[] count, int c, int k, boolean flag){
        if(index >= sn.length())
            return cur;

        if(flag){
            if(c == k){
                int smallest = getSmallest(count);
                char ch = (char) (smallest + '0');
                return recurse(sn, index + 1, cur + ch, count, c, k, true);
            }
            else{
                return recurse(sn, index + 1, cur + '0', count, c, k, true);
            }
        }

        char sn_ch = sn.charAt(index);

        for(char ch = '0';ch <= '9';ch++){

            String s;

            if(sn_ch == ch){
                if(count[ch - '0'] > 0){
                    count[ch - '0']++;
                    s = recurse(sn, index + 1, cur + ch, count, c, k, false);

                    if(!s.equals(""))
                        return s;

                    count[ch - '0']--;
                }
                else{
                    if(c < k){
                        count[ch - '0']++;
                        c++;
                        s = recurse(sn, index + 1, cur + ch, count, c, k, false);

                        if(!s.equals(""))
                            return s;

                        count[ch - '0']--;
                        c--;
                    }
                }
            }
            else if(sn_ch < ch){
                if(count[ch - '0'] > 0){
                    count[ch - '0']++;
                    s = recurse(sn, index + 1, cur + ch, count, c, k, true);

                    if(!s.equals(""))
                        return s;

                    count[ch - '0']--;
                }
                else{
                    if(c < k){
                        count[ch - '0']++;
                        c++;
                        s = recurse(sn, index + 1, cur + ch, count, c, k, true);

                        if(!s.equals(""))
                            return s;

                        count[ch - '0']--;
                        c--;
                    }
                }
            }
        }

        return "";
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();

            if(n == 1000000000){
                if(k == 1){
                    sb.append("1111111111\n");
                }
                else{
                    sb.append("1000000000\n");
                }
                continue;
            }

            String sn = Integer.toString(n);

            int x = Integer.MAX_VALUE;
            for(int i=1;i<=k;i++){
                int[] count = new int[10];
                String ans = recurse(sn, 0, "", count, 0, k, false);

                if(ans.equals(""))
                    continue;

                int cur_x = Integer.parseInt(ans);
                x = Math.min(x, cur_x);
            }

            sb.append(x).append("\n");
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
