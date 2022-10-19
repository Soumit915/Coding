package Spoj;

import java.util.*;
import java.io.*;

public class EnjoySumWithOperations {

    static void update(long[] bit, int index, long val){
        int n = bit.length;

        while(index <= n){
            bit[index - 1] += val;
            index += (index & (-index));
        }
    }

    static long query(long[] bit, int r){
        long sum = 0;

        if(r <= 0)
            return 0;

        while (r > 0) {
            sum += bit[r - 1];
            r -= (r & (-r));
        }

        return sum;
    }

    static boolean isSet(long n, int i){
        return (n & (1L<<i)) != 0;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        long[] a = new long[n];
        for(int i=0;i<n;i++)
            a[i] = sc.nextLong();

        StringBuilder sb = new StringBuilder();

        long[][] bit = new long[30][n];
        for(int i=0;i<30;i++){
            for(int j=0;j<n;j++){
                if(isSet(a[j], i)){
                    update(bit[i], j + 1, 1);
                }
            }
        }

        while(q-->0){
            int type = sc.nextInt();

            if(type == 1){
                int x = sc.nextInt();
                int index = sc.nextInt();

                for(int i=0;i<30;i++){
                    if (isSet(a[index - 1], i)) {
                        update(bit[i], index, -1);
                    }
                    if (isSet(x, i)) {
                        update(bit[i], index, 1);
                    }
                }

                a[index - 1] = x;
            }
            else{
                String op = sc.next();
                int l = sc.nextInt();
                int r = sc.nextInt();

                long totbits = r - l + 1;
                long totpairs = (totbits * (totbits - 1)) / 2;
                long ans = 0;
                for(int i=0;i<30;i++){
                    long countBits = query(bit[i], r) - query(bit[i], l-1);
                    long zeros = totbits - countBits;

                    long setpairs;
                    if(op.equals("AND")){
                        setpairs = (countBits * (countBits - 1)) / 2;
                    }
                    else if(op.equals("OR")){
                        setpairs = totpairs - ((zeros) * (zeros - 1)) / 2;
                    }
                    else{
                        setpairs = countBits * zeros;
                    }

                    ans += (1L<<i) * setpairs;
                }

                sb.append(ans).append("\n");
            }
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
