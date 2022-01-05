package GoogleKickStart.RoundA_2021;

import java.io.*;
import java.util.*;

public class C {
    static long get(long[][] mat, int i, int j){
        int r = mat.length;
        int c = mat[0].length;
        if(i>=0 && i<r && j>=0 && j<c){
            return mat[i][j];
        }
        else return -1;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++){
            sb.append("Case #").append(testi).append(": ");

            int r = sc.nextInt();
            int c = sc.nextInt();
            long[][] mat = new long[r][c];
            for(int i=0;i<r;i++){
                mat[i] = sc.nextLongArray(c);
            }

            long[][] newMat = new long[r][c];
            long max1 = -1, max2 = -1;
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    if(i%2==0){
                        if(j%2==0){
                            max1 = Math.max(max1, mat[i][j]);
                        }
                    }
                    else{
                        if(j%2==1){
                            max1 = Math.max(max1, mat[i][j]);
                        }
                    }
                }
            }
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    if(i%2==0){
                        if(j%2==1){
                            max2 = Math.max(max2, mat[i][j]);
                        }
                    }
                    else{
                        if(j%2==0){
                            max2 = Math.max(max2, mat[i][j]);
                        }
                    }
                }
            }

            if(max1<max2){
                max1 = max2-1;
            }
            else if(max1>max2){
                max2 = max1-1;
            }

            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    if(i%2==0){
                        if(j%2==0){
                            newMat[i][j] = max1;
                        }
                    }
                    else{
                        if(j%2==1){
                            newMat[i][j] = max1;
                        }
                    }
                }
            }
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    if(i%2==0){
                        if(j%2==1){
                            newMat[i][j] = max2;
                        }
                    }
                    else{
                        if(j%2==0){
                            newMat[i][j] = max2;
                        }
                    }
                }
            }

            long prev = -1;
            for(int times=0;times<r*c;times++){
                if(max1>max2){
                    for(int i=0;i<r;i++){
                        for(int j=0;j<c;j++){
                            if(i%2==0){
                                if(j%2==0){
                                    long max = Math.max(Math.max(get(newMat, i-1, j), get(newMat, i+1, j)),
                                            Math.max(get(newMat, i, j-1), get(newMat, i, j+1)))-1;
                                    newMat[i][j] = Math.max(mat[i][j], max);
                                }
                            }
                            else{
                                if(j%2==1){
                                    long max = Math.max(Math.max(get(newMat, i-1, j), get(newMat, i+1, j)),
                                            Math.max(get(newMat, i, j-1), get(newMat, i, j+1)))-1;
                                    newMat[i][j] = Math.max(mat[i][j], max);
                                }
                            }
                        }
                    }
                    for(int i=0;i<r;i++){
                        for(int j=0;j<c;j++){
                            if(i%2==0){
                                if(j%2==1){
                                    long max = Math.max(Math.max(get(newMat, i-1, j), get(newMat, i+1, j)),
                                            Math.max(get(newMat, i, j-1), get(newMat, i, j+1)))-1;
                                    newMat[i][j] = Math.max(mat[i][j], max);
                                }
                            }
                            else{
                                if(j%2==0){
                                    long max = Math.max(Math.max(get(newMat, i-1, j), get(newMat, i+1, j)),
                                            Math.max(get(newMat, i, j-1), get(newMat, i, j+1)))-1;
                                    newMat[i][j] = Math.max(mat[i][j], max);
                                }
                            }
                        }
                    }
                }
                else{
                    for(int i=0;i<r;i++){
                        for(int j=0;j<c;j++){
                            if(i%2==0){
                                if(j%2==1){
                                    long max = Math.max(Math.max(get(newMat, i-1, j), get(newMat, i+1, j)),
                                            Math.max(get(newMat, i, j-1), get(newMat, i, j+1)))-1;
                                    newMat[i][j] = Math.max(mat[i][j], max);
                                }
                            }
                            else{
                                if(j%2==0){
                                    long max = Math.max(Math.max(get(newMat, i-1, j), get(newMat, i+1, j)),
                                            Math.max(get(newMat, i, j-1), get(newMat, i, j+1)))-1;
                                    newMat[i][j] = Math.max(mat[i][j], max);
                                }
                            }
                        }
                    }
                    for(int i=0;i<r;i++){
                        for(int j=0;j<c;j++){
                            if(i%2==0){
                                if(j%2==0){
                                    long max = Math.max(Math.max(get(newMat, i-1, j), get(newMat, i+1, j)),
                                            Math.max(get(newMat, i, j-1), get(newMat, i, j+1)))-1;
                                    newMat[i][j] = Math.max(mat[i][j], max);
                                }
                            }
                            else{
                                if(j%2==1){
                                    long max = Math.max(Math.max(get(newMat, i-1, j), get(newMat, i+1, j)),
                                            Math.max(get(newMat, i, j-1), get(newMat, i, j+1)))-1;
                                    newMat[i][j] = Math.max(mat[i][j], max);
                                }
                            }
                        }
                    }
                }

                long count = 0;
                for(int i=0;i<r;i++){
                    for(int j=0;j<c;j++){
                        count += newMat[i][j] - mat[i][j];
                    }
                }

                if(count==prev){
                    break;
                }

                prev = count;
            }

            long count = 0;
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    count += newMat[i][j] - mat[i][j];
                }
            }

            sb.append(count).append("\n");
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
