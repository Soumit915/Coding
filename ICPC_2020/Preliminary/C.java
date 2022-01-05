package ICPC_2020.Preliminary;

import java.io.*;
import java.util.*;

public class C {
    static int getAns(long n){
        if(n%2==1)
            return -1;

        int k = 0;
        for(int i=0;i<31;i++){
            if(i%2==1){
                if((n&(1L<<i))!=0)
                    k++;
            }
            else{
                if((n&(1L<<i))!=0)
                    k += 2;
            }
        }

        /*if(n==1056){
            System.out.println(k);
        }*/
        return k;
    }
    public static void main(String[] args) throws IOException {
        /*Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");*/

        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            long n = sc.nextLong();
            long x = sc.nextLong();

            int p = getAns(n);

            long xv = 0;
            for(int i=0;i<31;i++){
                if((n&(1L<<i))!=0 && ((xv+(1L<<i))<=x)){
                    xv += (1L<<i);
                    n -= (1L<<i);
                    //System.out.println(i);
                }
            }

            //System.out.println(n+" "+xv);
            for(int i=1;i<31;i+=2){
                if((n&(1L<<i))==0 && ((xv+(1L<<i))<=x) && (n&(1L<<(i+1)))!=0){
                    xv += (1L<<i);
                    n -= (1L<<i);
                }
                /*if((n&(1<<i))!=0 && ((xv+(1<<i))<=x)){
                    xv += (1<<i);
                    n -= (1<<i);
                    System.out.println(i+" "+n+" "+xv);
                }*/
            }

            //System.out.println(xv+" "+n);
            //n -= xv;

            if(n%2==1){
                sb.append("-1\n");
            }
            else{
                int k = 1;
                for(int i=0;i<31;i++){
                    if(i%2==0){
                        if((n&(1L<<i))!=0)
                            k+=2;
                    }
                    else{
                        if((n&(1<<i))!=0)
                            k++;
                    }
                }
                if(p==-1)
                    sb.append(k).append("\n");
                else sb.append(Math.min(k, p)).append("\n");
            }
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
