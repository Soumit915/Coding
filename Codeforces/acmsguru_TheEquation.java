package Codeforces;

import java.io.*;
import java.util.*;

public class acmsguru_TheEquation {

    static long gcd(long a, long b){
        if(a%b==0)
            return b;
        else return gcd(b, a%b);
    }

    static long x, y;
    static void gcdExtended(long a, long b, long mod){
        if(a%b==0) {
            x = 1;
            y = 1 - (a/b);
            return;
        }
        gcdExtended(b, a%b, mod);
        long t = y;
        y = x - ((a/b)*y)%mod;
        x = t;
    }
    static long modInverse(long a, long b){
        gcdExtended(a, b, b);
        x = (x%b + b)%b;
        return x;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = 1;
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=t;i++){
            long a = sc.nextLong();
            long b = sc.nextLong();
            long c = sc.nextLong() * -1;
            long x1 = sc.nextLong();
            long x2 = sc.nextLong();
            long y1 = sc.nextLong();
            long y2 = sc.nextLong();

            if(c<0){
                c = -c;
                a = -a;
                b = -b;
            }

            if(a<0){
                a = -a;
                long temp = x1;
                x1 = -x2;
                x2 = -temp;
            }

            if(b<0){
                b = -b;
                long temp = y1;
                y1 = -y2;
                y2 = -temp;
            }

            if(a==0 && b==0 && c==0){
                sb.append((x2-x1+1)*(y2-y1+1)).append("\n");
                continue;
            }

            if(a==0 && b==0){
                sb.append("0\n");
                continue;
            }

            if(a==0){
                if(c%b==0 && y1<=(c/b) && (c/b)<=y2){
                    sb.append(x2-x1+1).append("\n");
                }
                else{
                    sb.append("0\n");
                }
                continue;
            }
            else if(b==0){
                if(c%a==0 && x1<=(c/a) && (c/a)<=x2){
                    sb.append(y2-y1+1).append("\n");
                }
                else{
                    sb.append("0\n");
                }
                continue;
            }

            long gcd = gcd(a, b);
            if(c%gcd==0){
                a /= gcd;
                b /= gcd;
                c /= gcd;

                long x = (c * modInverse(a, b))%b;
                long y = (c - a*x)/b;

                long minK_x;
                if(x < x1){
                    minK_x = (x1 - x + b - 1) / b;
                }
                else{
                    minK_x = ((x - x1) / b) * -1;
                }

                long maxK_x;
                if(x < x2){
                    maxK_x = (x2 - x) / b;
                }
                else{
                    maxK_x = ((x - x2 + b - 1) / b) * -1;
                }

                long maxK_y;
                if(y < y1){
                    maxK_y = ((y1 - y + a - 1) / a) * -1;
                }
                else{
                    maxK_y = (y - y1) / a;
                }

                long minK_y;
                if(y < y2){
                    minK_y = ((y2 - y) / a) * -1;
                }
                else{
                    minK_y = ((y - y2 + a - 1) / a);
                }

                long start = Math.max(minK_x, minK_y);
                long end = Math.min(maxK_x, maxK_y);
                long ans = Math.max(end - start + 1, 0);
                sb.append(ans).append("\n");
            }
            else{
                sb.append("0\n");
            }
        }

        System.out.print(sb);

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
