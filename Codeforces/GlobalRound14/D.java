package Codeforces.GlobalRound14;

import java.io.*;
import java.util.*;

public class D {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();

            int[] larr = sc.nextIntArray(l);
            int[] rarr = sc.nextIntArray(r);
            int[] hash_l = new int[n+10];
            int[] hash_r = new int[n+10];
            for(int i=0;i<l;i++){
                hash_l[larr[i]]++;
            }
            for(int i=0;i<r;i++){
                hash_r[rarr[i]]++;
            }

            for(int i=0;i< hash_l.length;i++){
                if(hash_l[i]>=hash_r[i]){
                    hash_l[i] -= hash_r[i];
                    hash_r[i] = 0;
                }
                else{
                    hash_r[i] -= hash_l[i];
                    hash_l[i] = 0;
                }
            }

            int cl = 0, cr = 0;
            for(int i=0;i<hash_l.length;i++){
                cl += hash_l[i];
                cr += hash_r[i];
            }

            if(cl==cr){
                sb.append(cl).append("\n");
            }
            else if(cl<cr){
                int points = cl;
                for(int i=0;i<hash_r.length && cl>0;i++){
                    if(hash_r[i]%2==1){
                        cl--;
                        hash_r[i]--;
                    }
                }

                if(cl>0){
                    for(int i=0;i<hash_l.length;i++){
                        if(cl>hash_r[i]){
                            cl -= hash_r[i];
                            hash_r[i] = 0;
                        }
                        else{
                            hash_r[i] -= cl;
                            cl = 0;
                        }
                    }
                }

                int ones = 0;
                int pairs = 0;
                for(int i=0;i<hash_l.length;i++){
                    ones += hash_r[i]%2;
                    hash_r[i] -= hash_r[i]%2;
                    pairs += hash_r[i]/2;
                }

                points += ones + pairs;
                sb.append(points).append("\n");
            }
            else{
                int points = cr;
                for(int i=0;i< hash_l.length && cr>0;i++){
                    if(hash_l[i]%2==1){
                        hash_l[i]--;
                        cr--;
                    }
                }

                if(cl>0){
                    for(int i=0;i<hash_l.length;i++){
                        if(cr>hash_l[i]){
                            cr -= hash_l[i];
                            hash_l[i] = 0;
                        }
                        else{
                            hash_l[i] -= cr;
                            cr = 0;
                        }
                    }
                }

                int ones = 0;
                int pairs = 0;
                for(int i=0;i<hash_l.length;i++){
                    ones += hash_l[i]%2;
                    hash_l[i] -= hash_l[i]%2;
                    pairs += hash_l[i]/2;
                }

                points += ones + pairs;
                sb.append(points).append("\n");
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
