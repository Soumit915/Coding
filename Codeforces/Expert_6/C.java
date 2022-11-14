package Codeforces.Expert_6;

import java.util.*;
import java.io.*;

public class C {

    static int getCount(int[] a, int l, int r){
        if(l == 0)
            return a[r];
        else return a[r] - a[l - 1];
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int m = sc.nextInt();

        String s = sc.next();

        int[][] a = new int[3][n];
        int[][] b = new int[3][n];
        int[][] c = new int[3][n];

        for(int i=0;i<n;i++){
            if(s.charAt(i) == 'a'){
                a[i%3][i] = 1;
            }
            else if(s.charAt(i) == 'b'){
                b[i%3][i] = 1;
            }
            else{
                c[i%3][i] = 1;
            }
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<3;j++){
                a[j][i] += a[j][i-1];
                b[j][i] += b[j][i-1];
                c[j][i] += c[j][i-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++){
            int l = sc.nextInt()-1;
            int r = sc.nextInt()-1;

            int l_bound = ((l + 2) / 3) * 3;
            int u_bound = (r / 3) * 3;

            int bound_diff = (u_bound - l_bound) / 3;

            int zero = bound_diff, one = bound_diff, two = bound_diff;
            for(int j=l;j<l_bound;j++){
                if(j%3 == 0)
                    zero++;
                else if(j%3 == 1)
                    one++;
                else two++;
            }

            for(int j=u_bound;j<=r;j++){
                if(j%3 == 0)
                    zero++;
                else if(j%3 == 1)
                    one++;
                else two++;
            }

            int ans = Integer.MAX_VALUE;


            int ca0 = getCount(a[0], l, r);
            int ca1 = getCount(a[1], l, r);
            int ca2 = getCount(a[2], l, r);
            int cb0 = getCount(b[0], l, r);
            int cb1 = getCount(b[1], l, r);
            int cb2 = getCount(b[2], l, r);
            int cc0 = getCount(c[0], l, r);
            int cc1 = getCount(c[1], l, r);
            int cc2 = getCount(c[2], l, r);

            int diff = r - l + 1;

            //abc
            ans = Math.min(ans, diff - ca0 - cb1 - cc2);

            //acb
            ans = Math.min(ans, diff - ca0 - cc1 - cb2);

            //bac
            ans = Math.min(ans, diff - cb0 - ca1 - cc2);

            //bca
            ans = Math.min(ans, diff - cb0 - cc1 - ca2);

            //cab
            ans = Math.min(ans, diff - cc0 - ca1 - cb2);

            //cba
            ans = Math.min(ans, diff - cc0 - cb1 - ca2);

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
