package Codechef.SnackDown21.Round1A;

import java.io.*;
import java.util.*;

class YetAnotherFlippingProblem{
    static boolean isNotPossible(int n, int k, int l){
        return k + l > n;
    }
    static int getNextPowerOf2(int n){
        n |= n>>1;
        n |= n>>2;
        n |= n>>4;
        n |= n>>8;
        n |= n>>16;
        n |= n>>31;
        return n;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();

            if(k==0){
                sb.append("YES\n0\n");
            }
            else if(k%2==0){
                sb.append("NO\n");
            }
            else{
                char[] bin = Integer.toBinaryString(k).toCharArray();
                int last = k;

                StringBuilder tsb = new StringBuilder();
                tsb.append("YES");
                tsb.append("\n");
                tsb.append(bin.length).append("\n");

                boolean flag = true;

                for(int i=bin.length-1;i>=0;i--){
                    if(i!=0){
                        char pb = bin[i-1];
                        if(pb=='0'){
                            if(isNotPossible(n, last, (1 << (bin.length - i - 1)))){
                                flag = false;
                                break;
                            }
                            tsb.append(last+1).append("\n");
                            last += (1<<(bin.length-i-1));
                        }
                        else{
                            last = last - (1<<(bin.length-i-1));
                            if(isNotPossible(n, last, (1 << (bin.length - i - 1)))){
                                flag = false;
                                break;
                            }
                            tsb.append(last+1).append("\n");
                        }
                    }
                    else{
                        if(isNotPossible(n, 0, 1 << (bin.length - 1))){
                            flag = false;
                            break;
                        }
                        tsb.append("1").append("\n");
                    }
                }

                if(flag){
                    sb.append(tsb);
                }
                else{
                    int gnp = getNextPowerOf2(n);
                    int bits = k - (3*k - gnp)/2;
                    int[] indices = new int[Integer.toBinaryString(gnp).length()];
                    int start = (1<<Integer.toBinaryString(bits).length()) - bits + 1;
                    for(int i=0;i<indices.length;i++){
                        if((bits&(1<<i))!=0){
                            indices[i] = start;
                            start += (1<<i);
                        }
                    }
                    indices[Integer.toBinaryString(bits).length()] = 1;
                    start -= bits;
                    for(int i=Integer.toBinaryString(bits).length();i<indices.length;i++){
                        if(indices[i]==0){
                            indices[i] = start;
                            start += (1<<i);
                        }
                    }

                    for(int i=0;i<indices.length;i++){
                        if(indices[i]==0){
                            indices[i] = start;
                            start += (1<<i);
                        }
                    }

                    sb.append("YES\n");
                    sb.append(indices.length).append("\n");
                    for(int i: indices)
                        sb.append(i).append("\n");
                }
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
