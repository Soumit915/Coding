package Codeforces;

import java.util.*;
import java.io.*;

public class LetsGoHiking {

    static class Peek implements Comparable<Peek>{
        int id;
        int left, right;

        int max, min;

        Peek(int id, int left, int right){
            this.id = id;
            this.left = left;
            this.right = right;

            this.max = Math.max(left, right);
            this.min = Math.min(left, right);
        }

        public int compareTo(Peek p){
            int c = Integer.compare(this.max, p.max) * -1;

            if(c == 0)
                return Integer.compare(this.min, p.min) * -1;
            else return c;
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[] a = sc.nextIntArray(n);

        List<Peek> list = new ArrayList<>();
        for(int i=1;i<n-1;i++){
            if(a[i]>a[i-1] && a[i]>a[i+1]){

                int left = 0, right = 0;

                for(int j=i-1;j>=0;j--){
                    if(a[j] < a[j+1]){
                        left++;
                    }
                    else {
                        break;
                    }
                }

                for(int j=i+1;j<n;j++){
                    if(a[j] < a[j-1]){
                        right++;
                    }
                    else{
                        break;
                    }
                }

                list.add(new Peek(i, left, right));
            }
        }

        if(n>2 && a[0]>a[1]){
            int right = 0;

            for(int j=1;j<n;j++){
                if(a[j] < a[j-1]){
                    right++;
                }
                else{
                    break;
                }
            }

            list.add(new Peek(0, 0, right));
        }

        if(n>2 && a[n-1]>a[n-2]){
            int left = 0;

            for(int j=n-2;j>=0;j--){
                if(a[j] < a[j+1]){
                    left++;
                }
                else {
                    break;
                }
            }

            list.add(new Peek(n-1, left, 0));
        }

        Collections.sort(list);

        if(list.size() == 0){
            System.out.println(0);
            System.exit(0);
        }

        int maxl = list.get(0).max;
        int countl = 0;
        for(Peek p: list){
            if(p.max == maxl)
                countl++;

            if(p.min == maxl)
                countl++;
        }

        if(countl > 2){
            System.out.println(0);
            System.exit(0);
        }
        else if(countl == 2){
            if(list.get(0).max != list.get(0).min){
                System.out.println(0);
                System.exit(0);
            }
        }

        boolean flag = false;
        if(countl == 2){
            if(maxl%2 == 0)
                flag = true;
        }

        if(flag){
            System.out.println(1);
            System.exit(0);
        }
        else{
            System.out.println(0);
            System.exit(0);
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
