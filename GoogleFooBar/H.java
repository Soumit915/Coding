package GoogleFooBar;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Arrays;

public class H {

    static class LocalList implements Comparable<LocalList>{
        List<Integer> list;
        LocalList(List<Integer> list){
            this.list = list;
        }
        public int compareTo(LocalList li){
            for(int i=0;i<this.list.size();i++){
                int c = Integer.compare(this.list.get(i), li.list.get(i));
                if(c!=0)
                    return c;
            }

            return 0;
        }
    }

    static boolean isSet(int n, int i){
        return (n&(1<<i))!=0;
    }

    static int countBits(int n){
        int c = 0;
        while(n > 0){
            n = n & (n-1);
            c++;
        }

        return c;
    }

    static List<List<Integer>> getCombinations(int n, int r){
        int lim = 1 << n;
        List<LocalList> list = new ArrayList<>();
        for(int i=0;i<lim;i++){
            if(countBits(i)!=r)
                continue;

            List<Integer> cur = new ArrayList<>();
            for(int j=0;j<n;j++){
                if(isSet(i, j)){
                    cur.add(j);
                }
            }

            list.add(new LocalList(cur));
        }

        Collections.sort(list);

        List<List<Integer>> combinations = new ArrayList<>();
        for (LocalList localList : list) {
            combinations.add(localList.list);
        }

        return combinations;
    }

    public static int[][] solution(int num_buns, int num_required){

        int copies_per_key = num_buns - num_required + 1;
        List<List<Integer>> combinations = getCombinations(num_buns, copies_per_key);
        List<List<Integer>> ways = new ArrayList<>();
        for(int i=0;i<num_buns;i++){
            ways.add(new ArrayList<>());
        }

        for(int i=0;i<combinations.size();i++){
            for(int j: combinations.get(i)){
                ways.get(j).add(i);
            }
        }

        int[][] correct_scheme = new int[num_buns][ways.get(0).size()];
        for(int i=0;i<num_buns;i++){
            for(int j=0;j<ways.get(0).size();j++){
                correct_scheme[i][j] = ways.get(i).get(j);
            }
        }

        return correct_scheme;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        int r = sc.nextInt();
        int[][] ways = solution(n, r);
        for(int[] i: ways){
            System.out.println(Arrays.toString(i));
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
