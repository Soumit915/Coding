package Codeforces.CodeTon3;

import java.io.*;
import java.util.*;

public class D {

    static int gcd(int a, int b){
        if(a%b==0)
            return b;
        else return gcd(b, a%b);
    }

    static boolean hasB(int[] a){
        int n = a.length;

        for(int i=1;i<n;i++){
            if(gcd(a[i-1], a[i]) != a[i])
                return false;
        }

        return true;
    }

    static long mod = 998244353;

    static List<Integer> getAllFactors(int n){
        List<Integer> list = new ArrayList<>();

        for(int i=2;i*i<=n;i++){
            if(n%i != 0)
                continue;

            list.add(i);

            while(n%i == 0){
                n /= i;
            }
        }

        if(n > 1)
            list.add(n);

        return list;
    }

    static boolean isSet(int n, int i){
        return (n & (1<<i)) != 0;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] a = sc.nextIntArray(n);

            if(!hasB(a)){
                sb.append("0\n");
                continue;
            }

            long ans = 1;
            for(int i=1;i<n;i++){
                if(a[i-1] == a[i]){
                    long count = m / a[i];
                    ans = (ans * count) % mod;
                }
                else{

                    int div = a[i-1] / a[i];

                    List<Integer> factorList = getAllFactors(div);

                    int fn = factorList.size();
                    int lim = (1 << fn);

                    int total = 0;
                    for(int j=0;j<lim;j++){
                        int cur_count;

                        int bitcount = 0;
                        int val = 1;

                        for(int bit=0;bit < fn;bit++){
                            if(isSet(j, bit)){
                                bitcount++;
                                val *= factorList.get(bit);
                            }
                        }

                        val *= a[i];
                        cur_count = m / val;

                        if(bitcount%2 == 0)
                            total += cur_count;
                        else total -= cur_count;
                    }

                    ans = (ans * total) %mod;
                }
            }

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
