package Codeforces.Round811Div3;

import java.util.*;
import java.io.*;

public class F {

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            int d12 = sc.nextInt();
            int d23 = sc.nextInt();
            int d31 = sc.nextInt();

            StringBuilder cur_sb = new StringBuilder();

            if(d12 + d31 == d23){
                cur_sb.append("YES\n");
                int last = 2;
                int avail = 4;

                for(int i=1;i<d12;i++){
                    cur_sb.append(last).append(" ").append(avail).append("\n");
                    last = avail;
                    avail++;
                }

                cur_sb.append(last).append(" ").append(1).append("\n");
                last = 1;

                for(int i=1;i<d31;i++){
                    cur_sb.append(last).append(" ").append(avail).append("\n");
                    last = avail;
                    avail++;
                }

                cur_sb.append(last).append(" ").append(3).append("\n");

                for(int i=avail;i<=n;i++){
                    cur_sb.append(i).append(" 1\n");
                }
            }
            else if(d12 + d23 == d31){
                cur_sb.append("YES\n");
                int last = 3;
                int avail = 4;

                for(int i=1;i<d23;i++){
                    cur_sb.append(last).append(" ").append(avail).append("\n");
                    last = avail;
                    avail++;
                }

                cur_sb.append(last).append(" ").append(2).append("\n");
                last = 2;

                for(int i=1;i<d12;i++){
                    cur_sb.append(last).append(" ").append(avail).append("\n");
                    last = avail;
                    avail++;
                }

                cur_sb.append(last).append(" ").append(1).append("\n");

                for(int i=avail;i<=n;i++){
                    cur_sb.append(i).append(" 1\n");
                }
            }
            else if(d31 + d23 == d12) {
                cur_sb.append("YES\n");
                int last = 2;
                int avail = 4;

                for(int i=1;i<d23;i++){
                    cur_sb.append(last).append(" ").append(avail).append("\n");
                    last = avail;
                    avail++;
                }

                cur_sb.append(last).append(" ").append(3).append("\n");
                last = 3;

                for(int i=1;i<d31;i++){
                    cur_sb.append(last).append(" ").append(avail).append("\n");
                    last = avail;
                    avail++;
                }

                cur_sb.append(last).append(" ").append(1).append("\n");

                for(int i=avail;i<=n;i++){
                    cur_sb.append(i).append(" 1\n");
                }
            }
            else{
                int odd = d12%2 + d23%2 + d31%2;

                if(odd%2 == 1){
                    sb.append("NO\n");
                    continue;
                }

                int x = (d12 + d31 - d23) / 2;
                int y = (d12 + d23 - d31) / 2;
                int z = (d23 + d31 - d12) / 2;

                if(x < 0 || y <0 || z < 0){
                    sb.append("NO\n");
                    continue;
                }

                int nodes_needed = 4 + (x - 1) + (y - 1) + (z - 1);

                if(nodes_needed > n){
                    sb.append("NO\n");
                    continue;
                }

                cur_sb.append("YES\n");
                int last = 1;
                int avail = 5;

                for(int i=1;i<x;i++){
                    cur_sb.append(last).append(" ").append(avail).append("\n");
                    last = avail;
                    avail++;
                }

                cur_sb.append(last).append(" ").append(4).append("\n");

                last = 2;
                for(int i=1;i<y;i++){
                    cur_sb.append(last).append(" ").append(avail).append("\n");
                    last = avail;
                    avail++;
                }

                cur_sb.append(last).append(" ").append(4).append("\n");

                last = 3;
                for(int i=1;i<z;i++){
                    cur_sb.append(last).append(" ").append(avail).append("\n");
                    last = avail;
                    avail++;
                }

                cur_sb.append(last).append(" ").append(4).append("\n");

                for(int i=avail;i<=n;i++){
                    cur_sb.append(i).append(" 1\n");
                }
            }

            sb.append(cur_sb);
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
