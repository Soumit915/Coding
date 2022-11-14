package Codechef;

import java.io.*;
import java.util.*;

public class TooMuchXor {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int[] arr = sc.nextIntArray(n);

            if(n==1){
                sb.append("0\n");
            }
            else if(n==2){
                if(arr[0]==arr[1]){
                    sb.append("-1\n");
                }
                else{
                    sb.append("0\n");
                }
            }
            else if(n==3){
                if(arr[0]==arr[2] && arr[0]!=arr[1]){
                    sb.append("0\n");
                }
                else if(arr[0]==arr[1] && arr[1]==arr[2] && arr[0]!=0){
                    sb.append("1\n");
                    sb.append("1 3 2\n");
                }
                else if(arr[1]==0 && (arr[0]!=0 || arr[2]!=0)){
                    sb.append("1\n");
                    if(arr[0]!=0) {
                        sb.append("1 2 3\n");
                    }
                    else{
                        sb.append("3 2 1\n");
                    }
                }
                else{
                    sb.append("-1\n");
                }
            }
            else{
                int diff = -1;
                for(int i=1;i<n;i++){
                    if(arr[i]!=arr[0]){
                        diff = i;
                        break;
                    }
                }

                if(diff==-1 && arr[0]==0){
                    sb.append("-1\n");
                }
                else if(diff==-1){
                    sb.append(n/2).append("\n");
                    for(int i=2;i<=n;i+=2){
                        sb.append("1 3 ").append(i).append("\n");
                    }
                }
                else{
                    if(diff==1){
                        if(arr[1]!=0){
                            sb.append(n-4+5).append("\n");
                            sb.append("1 2 3\n");
                            sb.append("1 2 4\n");
                            sb.append("3 4 1\n");
                            sb.append("2 1 4\n");
                            sb.append("2 4 3\n");
                            for(int i=5;i<=n;i++){
                                if(i%2==1){
                                    sb.append("1 3 ").append(i).append("\n");
                                }
                                else{
                                    sb.append("1 2 ").append(i).append("\n");
                                }
                            }
                        }
                        else{
                            sb.append(n-4+2).append("\n");
                            sb.append("1 2 3\n");
                            sb.append("1 3 4\n");
                            for(int i=5;i<=n;i++){
                                if(i%2==1){
                                    sb.append("1 2 ").append(i).append("\n");
                                }
                                else{
                                    sb.append("1 3 ").append(i).append("\n");
                                }
                            }
                        }
                    }
                    else{
                        int temp;
                        if(diff==2){
                            temp = 3;
                        }
                        else{
                            temp = 2;
                        }

                        if(arr[diff]!=0){
                            sb.append(n-4+7).append("\n");
                            sb.append(1 + " ").append(diff + 1).append(" ").append(temp + 1).append("\n");
                            sb.append(1 + " ").append(temp + 1).append(" ").append(2).append("\n");
                            sb.append("1 2 3\n");
                            sb.append("1 2 4\n");
                            sb.append("3 4 1\n");
                            sb.append("2 1 4\n");
                            sb.append("2 4 3\n");
                            for(int i=5;i<=n;i++){
                                if(i%2==1){
                                    sb.append("1 3 ").append(i).append("\n");
                                }
                                else{
                                    sb.append("1 2 ").append(i).append("\n");
                                }
                            }
                        }
                        else{
                            sb.append(n-4+4).append("\n");
                            sb.append(1 + " ").append(diff + 1).append(" ").append(temp + 1).append("\n");
                            sb.append(1 + " ").append(temp + 1).append(" ").append(2).append("\n");
                            sb.append("1 2 3\n");
                            sb.append("1 3 4\n");
                            for(int i=5;i<=n;i++){
                                if(i%2==1){
                                    sb.append("1 2 ").append(i).append("\n");
                                }
                                else{
                                    sb.append("1 3 ").append(i).append("\n");
                                }
                            }
                        }
                    }
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
