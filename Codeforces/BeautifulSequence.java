package Codeforces;

import java.util.*;
import java.io.*;

public class BeautifulSequence {

    static boolean isNotValid(int[] ans) {
        int n = ans.length;

        for(int i=1;i<n;i++){
            if(Math.abs(ans[i-1] - ans[i]) != 1)
                return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        int n = a + b + c + d;

        if(b > (a + c + 1) || c > (b + d + 1) || Math.abs(a+c-b-d) > 1){
            System.out.println("NO");
            System.exit(0);
        }

        int[] ans = new int[n];

        if(a+c == b+d){
            int acopy = a, bcopy = b;
            for(int i=0;i<n;i+=2){
                if(a>0){
                    ans[i] = 0;
                    a--;
                }
                else{
                    ans[i] = 2;
                }
            }

            for(int i=1;i<n;i+=2){
                if(b>0){
                    ans[i] = 1;
                    b--;
                }
                else{
                    ans[i] = 3;
                }
            }

            if(isNotValid(ans)){
                ans = new int[n];
                a = acopy;
                b = bcopy;

                for(int i=0;i<n;i+=2){
                    if(b>0){
                        ans[i] = 1;
                        b--;
                    }
                    else{
                        ans[i] = 3;
                    }
                }

                for(int i=1;i<n;i+=2){
                    if(a>0){
                        ans[i] = 0;
                        a--;
                    }
                    else{
                        ans[i] = 2;
                    }
                }
            }
        }
        else if(a+c > b+d){
            for(int i=0;i<n;i+=2){
                if(a>0){
                    ans[i] = 0;
                    a--;
                }
                else{
                    ans[i] = 2;
                }
            }

            for(int i=1;i<n;i+=2){
                if(b>0){
                    ans[i] = 1;
                    b--;
                }
                else{
                    ans[i] = 3;
                }
            }
        }
        else{
            for(int i=0;i<n;i+=2){
                if(b>0){
                    ans[i] = 1;
                    b--;
                }
                else{
                    ans[i] = 3;
                }
            }

            for(int i=1;i<n;i+=2){
                if(a>0){
                    ans[i] = 0;
                    a--;
                }
                else{
                    ans[i] = 2;
                }
            }
        }

        if(isNotValid(ans)){
            System.out.println("NO");
        }
        else{
            System.out.println("YES");
            StringBuilder sb = new StringBuilder();
            for(int i: ans){
                sb.append(i).append(" ");
            }
            System.out.println(sb);
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
