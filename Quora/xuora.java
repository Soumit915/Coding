package Quora;

import java.io.*;
import java.util.StringTokenizer;

public class xuora {
    static int countSetBits(long n){
        int count = 0;
        while(n>0){
            n = n&(n-1);
            count++;
        }
        return count;
    }
    public static long nextPowerof2(long n)
    {
        n = n|(n>>1);
        n = n|(n>>2);
        n = n|(n>>4);
        n = n|(n>>8);
        n = n|(n>>16);
        n = n|(n>>32);
        n = n|(n>>62);

        return (n+1);
    }
    public static String pad(String s, int n){
        StringBuilder sb = new StringBuilder();
        for(int i=s.length();i<n;i++){
            sb.append("0");
        }
        sb.append(s);
        return sb.toString();
    }
    static void bruteForce(int n){

        int min = 1000;
        String s = "";
        for(int nl = n;nl<n+5;nl++){
            int lim = (int) (Math.pow(2, nl+2));
            for(int i=0;i<lim;i++){
                if(countSetBits(i) == n){
                    String bin = Integer.toBinaryString(i);
                    bin = pad(bin, nl);

                    int x = 0;
                    for(int j=0;j<bin.length();j++){
                        if(bin.charAt(j)=='1'){
                            x ^= (j+1);
                        }
                    }

                    if(x==0){
                        min = Math.min(min, bin.length());
                        if(min == bin.length())
                            s = bin;
                    }
                }
            }
        }

        System.out.println(min+" "+s);
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        long n = sc.nextLong();

        //bruteForce((int) n);

        if(n%4==3){
            System.out.println(n);
            System.out.println();
        }
        else if(n%4==2){
            if(countSetBits(n+2)==1){
                System.out.println(n+3);
                System.out.println("3 4 6");
            }
            else{
                System.out.println(n+2);
                long a = nextPowerof2(n+2) / 2;
                long b = n+2 - a;
                System.out.println(a+" "+b);
            }
        }
        else if(n%4==1){
            if(countSetBits(n+3) == 1){
                System.out.println(n+4);
                System.out.println("1 2 5 7");
            }
            else if(countSetBits(n+3)==2){
                System.out.println(n+3);
                long a = nextPowerof2(n+3) / 2;
                long b = n + 3 - a;
                System.out.println(1+" "+(b+1)+" "+a);
            }
            else{
                System.out.println(n+3);
                long a = nextPowerof2(n+3) / 2;
                long b = n + 3 - a;
                long a1 = nextPowerof2(b) / 2;
                long a2 = b - a1;
                System.out.println(a+" "+a1+" "+a2);
            }

        }
        else{
            System.out.println(n+1);
            System.out.println(1);
        }

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
            byte[] buf = new byte[100064]; // line length
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
