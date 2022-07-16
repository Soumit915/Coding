package Leetcode;

import java.io.*;
import java.util.*;

public class MatchsticksToSquare {

    static class Quad implements Comparable<Quad>{
        int a, b, c, d;

        Quad(int a, int b, int c, int d){
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public int compareTo(Quad q){
            int ca = Integer.compare(this.a, q.a);
            if(ca == 0){
                int cb = Integer.compare(this.b, q.b);
                if(cb == 0){
                    int cc = Integer.compare(this.c, q.c);
                    if(cc == 0){
                        return Integer.compare(this.d, q.d);
                    }
                    else return cc;
                }
                else return cb;
            }
            else return ca;
        }
    }

    static String pad(String str, int n){
        StringBuilder sb = new StringBuilder();

        for(int i=str.length();i<n;i++){
            sb.append("0");
        }
        sb.append(str);

        return sb.toString();
    }

    static List<Quad> getSideLengths(List<Integer> list){

        int n = list.size();
        int lim = (1 << (2 * n));

        List<Quad> quadlist = new ArrayList<>();
        for(int i=0;i<lim;i++){
            String str = Integer.toString(i, 4);
            str = pad(str, n);

            int[] arr = new int[4];
            for(int j=0;j<n;j++){
                arr[str.charAt(j) - '0'] += list.get(j);
            }

            Quad q = new Quad(arr[0], arr[1], arr[2], arr[3]);
            quadlist.add(q);
        }

        Collections.sort(quadlist);

        return quadlist;
    }

    static int binsearch(List<Quad> quadlist, int a, int b, int c, int d){
        int l = 0, r = quadlist.size() - 1;

        while(l < r){
            int mid = (l + r) / 2;

            int diff = quadlist.get(mid).compareTo(new Quad(a, b, c, d));
            if(diff == 0){
                return l;
            }
            else if(diff > 0){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return -1;
    }

    public static boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length;

        if(n < 4)
            return false;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        int sum = 0;
        for(int i=0;i<n;i++){
            if(i<n/2)
                list1.add(matchsticks[i]);
            else list2.add(matchsticks[i]);

            sum += matchsticks[i];
        }

        if(sum%4 != 0)
            return false;

        sum /= 4;

        List<Quad> quad1 = getSideLengths(list1);
        List<Quad> quad2 = getSideLengths(list2);

        for(Quad quad : quad1){
            int a = sum - quad.a;
            int b = sum - quad.b;
            int c = sum - quad.c;
            int d = sum - quad.d;

            if(a==3 && b==3 && c==7 && d==7){
                System.out.println("met");
            }

            if(a>=0 && b>=0 && c>=0 && d>=0 && binsearch(quad2, a, b, c, d) != -1){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int[] matchsticks = {97,13,66,41,9,22,1,93,11,65,61,12,41,1,59};
        System.out.println(makesquare(matchsticks));

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
