package Codeforces.GlobalRound19;

import java.io.*;
import java.util.*;

public class D {

    static long getSumProduct(long[] a){
        int n = a.length;
        long sum1 = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                sum1 += ((a[i] + a[j]) * (a[i] + a[j]));
            }
        }
        return sum1;
    }

    static long getMinSum(long[] a, long[] b){
        int n = a.length;
        int lim = (int) (Math.pow(2, n));

        long min = Long.MAX_VALUE;
        for(int i=0;i<lim;i++){

            long[] na = new long[n];
            long[] nb = new long[n];

            for(int j=0;j<n;j++){
                if((i&(1<<j))!=0){
                    na[j] = a[j];
                    nb[j] = b[j];
                }
                else{
                    na[j] = b[j];
                    nb[j] = a[j];
                }
            }

            long sum = getSumProduct(na) + getSumProduct(nb);
            min = Math.min(min, sum);
            if(sum==min && sum==35749){
                System.out.println(Integer.toBinaryString(i));
            }
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            long[] a = sc.nextLongArray(n);
            long[] b = sc.nextLongArray(n);

            long bf = getMinSum(a, b);

            long sum1 = 0, sum2 = 0;
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    sum1 += ((a[i] + a[j]) * (a[i] + a[j]));
                    sum2 += ((b[i] + b[j]) * (b[i] + b[j]));
                }
            }

            for(int i=0;i<n;i++){
                long tosub1 = 0, tosub2 = 0;
                long toadd1 = 0, toadd2 = 0;
                for(int j=0;j<n;j++){
                    if(i==j)
                        continue;

                    tosub1 += ((a[i] + a[j]) * (a[i] + a[j]));
                    tosub2 += ((b[i] + b[j]) * (b[i] + b[j]));

                    toadd1 += ((a[i] + b[j]) * (a[i] + b[j]));
                    toadd2 += ((b[i] + a[j]) * (b[i] + a[j]));
                }

                if(toadd1+toadd2 < tosub1+tosub2){
                    long temp = a[i];
                    a[i] = b[i];
                    b[i] = temp;
                    sum1 = sum1 - tosub1 + toadd1;
                    sum2 = sum2 - tosub2 + toadd2;
                }
            }

            if(sum1+sum2!=bf){
                System.out.println("Wrong answer at "+t);
                System.out.println(Arrays.toString(a));
                System.out.println(Arrays.toString(b));
                System.out.println(bf+" "+(sum1+sum2));
                System.exit(0);
            }

            sb.append(sum1+sum2).append("\n");
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
