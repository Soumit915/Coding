package Codeforces;

import java.io.*;
import java.util.*;

public class Domino_Hard {
    static char get(char[][] arrangements, int i, int j){
        int n = arrangements.length;
        int m = arrangements[0].length;

        if(0<=i && i<n && 0<=j && j<m){
            return arrangements[i][j];
        }
        else{
            return '\u0000';
        }
    }

    static char getCur(Set<Character> set){
        for(char ch = 'a';ch <= 'z';ch++){
            if(!set.contains(ch)) {
                return ch;
            }
        }

        return '\u0000';
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();

            char[][] arrangements = new char[n][m];
            if(n%2==1){
                int tobeDone = k - m/2;
                if(tobeDone<0 || tobeDone%2==1){
                    sb.append("NO\n");
                }
                else{
                    sb.append("YES\n");
                    for(int i=0;i<m;i+=2){
                        arrangements[0][i] = ((char) (((i/2)%20) + 'a'));
                        arrangements[0][i+1] = arrangements[0][i];
                    }

                    for(int j=0;j<m;j+=2){
                        if(tobeDone <= 0)
                            break;

                        for(int i=1;i<n;i++){
                            if(tobeDone <= 0)
                                break;

                            Set<Character> set = new HashSet<>();
                            set.add(get(arrangements, i-1, j));
                            set.add(get(arrangements, i-1, j+1));
                            set.add(get(arrangements, i, j-1));
                            set.add(get(arrangements, i, j+2));
                            set.add(get(arrangements, i+1, j));
                            set.add(get(arrangements, i+1, j+1));

                            arrangements[i][j] = getCur(set);
                            arrangements[i][j+1] = arrangements[i][j];
                            tobeDone--;
                        }
                    }

                    for(int i=0;i<n;i++){
                        for(int j=0;j<m;j++){
                            if(arrangements[i][j]=='\u0000'){
                                Set<Character> set = new HashSet<>();
                                set.add(get(arrangements, i-1, j));
                                set.add(get(arrangements, i, j-1));
                                set.add(get(arrangements, i, j+1));
                                set.add(get(arrangements, i+1, j-2));
                                set.add(get(arrangements, i+1, j+1));
                                set.add(get(arrangements, i+2, j));

                                arrangements[i][j] = getCur(set);
                                arrangements[i+1][j] = arrangements[i][j];
                            }
                        }
                    }

                    for(int i=0;i<n;i++){
                        for(int j=0;j<m;j++){
                            sb.append(arrangements[i][j]);
                        }
                        sb.append("\n");
                    }
                }
            }
            else if(m%2==1){
                int tobeDone = (n*m)/2 - k - n/2;
                if(tobeDone<0 || tobeDone%2==1){
                    sb.append("NO\n");
                }
                else{
                    sb.append("YES\n");

                    tobeDone = k;
                    for(int i=0;i<n;i+=2){
                        arrangements[i][0] = ((char) (((i/2)%20) + 'a'));
                        arrangements[i+1][0] = arrangements[i][0];
                    }

                    for(int j=1;j<m;j+=2){
                        if(tobeDone <= 0)
                            break;

                        for(int i=0;i<n;i++){
                            if(tobeDone <= 0)
                                break;

                            Set<Character> set = new HashSet<>();
                            set.add(get(arrangements, i-1, j));
                            set.add(get(arrangements, i-1, j+1));
                            set.add(get(arrangements, i, j-1));
                            set.add(get(arrangements, i, j+2));
                            set.add(get(arrangements, i+1, j));
                            set.add(get(arrangements, i+1, j+1));

                            arrangements[i][j] = getCur(set);
                            arrangements[i][j+1] = arrangements[i][j];
                            tobeDone--;
                        }
                    }

                    for(int i=0;i<n;i++){
                        for(int j=0;j<m;j++){
                            if(arrangements[i][j]=='\u0000'){
                                Set<Character> set = new HashSet<>();
                                set.add(get(arrangements, i-1, j));
                                set.add(get(arrangements, i, j-1));
                                set.add(get(arrangements, i, j+1));
                                set.add(get(arrangements, i+1, j-2));
                                set.add(get(arrangements, i+1, j+1));
                                set.add(get(arrangements, i+2, j));

                                arrangements[i][j] = getCur(set);
                                arrangements[i+1][j] = arrangements[i][j];
                            }
                        }
                    }

                    for(int i=0;i<n;i++){
                        for(int j=0;j<m;j++){
                            sb.append(arrangements[i][j]);
                        }
                        sb.append("\n");
                    }
                }
            }
            else{
                if(k%2==1){
                    sb.append("NO\n");
                }
                else{
                    sb.append("YES\n");
                    int tobeDone = k;

                    for(int j=0;j<m;j+=2){
                        if(tobeDone <= 0)
                            break;

                        for(int i=0;i<n;i++){
                            if(tobeDone <= 0)
                                break;

                            Set<Character> set = new HashSet<>();
                            set.add(get(arrangements, i-1, j));
                            set.add(get(arrangements, i-1, j+1));
                            set.add(get(arrangements, i, j-1));
                            set.add(get(arrangements, i, j+2));
                            set.add(get(arrangements, i+1, j));
                            set.add(get(arrangements, i+1, j+1));

                            arrangements[i][j] = getCur(set);
                            arrangements[i][j+1] = arrangements[i][j];
                            tobeDone--;
                        }
                    }

                    for(int i=0;i<n;i++){
                        for(int j=0;j<m;j++){
                            if(arrangements[i][j]=='\u0000'){
                                Set<Character> set = new HashSet<>();
                                set.add(get(arrangements, i-1, j));
                                set.add(get(arrangements, i, j-1));
                                set.add(get(arrangements, i, j+1));
                                set.add(get(arrangements, i+1, j-2));
                                set.add(get(arrangements, i+1, j+1));
                                set.add(get(arrangements, i+2, j));

                                arrangements[i][j] = getCur(set);
                                arrangements[i+1][j] = arrangements[i][j];
                            }
                        }
                    }

                    for(int i=0;i<n;i++){
                        for(int j=0;j<m;j++){
                            sb.append(arrangements[i][j]);
                        }
                        sb.append("\n");
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
