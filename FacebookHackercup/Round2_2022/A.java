package FacebookHackercup.Round2_2022;

import java.util.*;
import java.io.*;

public class A {

    static boolean isValid(int[] a, int[] b){
        for(int i=0;i<26;i++){
            if(a[i] != b[i])
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output.txt");

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int ti=1;ti<=tc;ti++) {

            sb.append("Case #").append(ti).append(": ");

            String s = sc.next();
            int n = s.length();

            int[][] count = new int[n][26];
            for(int i=0;i<n;i++){
                int ch = s.charAt(i) - 'a';
                count[i][ch] = 1;
            }

            for(int i=1;i<n;i++){
                for(int j=0;j<26;j++){
                    count[i][j] += count[i-1][j];
                }
            }

            int q = sc.nextInt();
            int ans = 0;
            for(int i=0;i<q;i++){
                int l = sc.nextInt() - 1;
                int r = sc.nextInt() - 1;

                if(l == r){
                    ans++;
                    continue;
                }

                int[] freqlr = new int[26];
                for(int j=0;j<26;j++){
                    freqlr[j] = count[r][j] - ((l==0)?0:count[l-1][j]);
                }

                int odd_freqs = 0;
                int odd_freq_ind = 0;
                for(int j=0;j<26;j++){
                    if(freqlr[j]%2 == 1) {
                        odd_freqs++;
                        odd_freq_ind = j;
                    }
                }

                if(odd_freqs != 1)
                    continue;

                int[] freq1 = new int[26];
                int[] freq2 = new int[26];

                int[] freq3 = new int[26];
                int[] freq4 = new int[26];

                int mid = (l + r) / 2;
                for(int j=0;j<26;j++){
                    freq1[j] = count[mid-1][j] - ((l==0)?0:count[l-1][j]);
                    freq2[j] = count[r][j] - count[mid-1][j];

                    freq3[j] = count[mid][j] - ((l==0)?0:count[l-1][j]);
                    freq4[j] = count[r][j] - count[mid][j];
                }

                freq2[odd_freq_ind]--;
                freq3[odd_freq_ind]--;

                if(isValid(freq1, freq2) || isValid(freq3, freq4)){
                    ans++;
                }
            }

            sb.append(ans).append("\n");
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
