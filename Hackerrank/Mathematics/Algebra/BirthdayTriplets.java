package Hackerrank.Mathematics.Algebra;

import java.io.*;
import java.util.*;

public class BirthdayTriplets {
    static long mod = (long) 1e9+7;
    static long x, y;
    static void gcdExtended(long a, long b){
        if (a % b == 0) {
            x = 1;
            y = 1 - (a/b);
            return ;
        }
        gcdExtended(b, a%b);
        long temp = y;
        y = x - ((a/b)*y)%mod;
        x = temp;
    }
    static long modInverse(long a){
        gcdExtended(a, mod);
        x = (x + mod)%mod;
        return x;
    }
    static long pow(long a, long b){
        long p = 1;
        while(b>0){
            if(b%2==1){
                p = (p*a)%mod;
            }
            a = (a*a)%mod;
            b /= 2;
        }
        return p;
    }
    static long fun(long a, long l, long r){
        if(a==1)
            return r-l+1;
        long ans = pow(a, l);
        ans = (ans * ((pow(a, r-l+1) - 1 + mod)%mod)%mod);
        ans = (ans * modInverse(a-1))%mod;
        return ans;
    }
    static long global_a, global_b, global_c;
    static boolean isValid(long a, long f2, long f3, long f4){
        long k2 = f2 - a*a;
        long k3 = f3 - a*a*a;
        long k4 = f4 - a*a*a*a;
        
        long sb = -2*k2;
        long sa = 2;
        long sc = k2*k2 - k4;

        long discriminant = (long) Math.sqrt(sb*sb - 4*sa*sc);
        discriminant -= sb;
        discriminant /= 2*sa;

        long c = (long) Math.sqrt(discriminant);
        long c_square = c*c;
        long check = sa*c_square*c_square + sb*c_square + sc;

        if(check!=0)
            return false;

        long b_cube = k3 - c*c*c;
        long b = (long) Math.cbrt(b_cube);
        if(b * b * b != b_cube)
            return false;

        global_a = a; global_b = b; global_c = c;
        return true;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            long f2 = sc.nextLong();
            long f3 = sc.nextLong();
            long f4 = sc.nextLong();
            long l = sc.nextLong();
            long r = sc.nextLong();

            long a=0, b=0, c=0;
            for(long i=1;i<5000;i++){
                a = 0; b = 0; c= 0;
                if(isValid(i, f2, f3, f4)){
                    a = global_a; b = global_b; c = global_c;
                    break;
                }
            }

            long ans = (fun(a, l, r)%mod + fun(b, l, r)%mod + fun(c, l, r)%mod)%mod;
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
