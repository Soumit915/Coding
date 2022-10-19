package Codeforces.ICPC3;

import java.util.*;
import java.io.*;

public class D_BF {

    static class Pair{
        int count;
        int val;

        Pair(int count, int val){
            this.count = count;
            this.val = val;
        }
    }

    static String[] number_string = {"1110111", "0010010", "1011101", "1011011", "0111010", "1101011", "1101111", "1010010", "1111111", "1111011"};
    static int[] number_mask = new int[10];

    static void preCompute(){
        for(int i=0;i<10;i++){
            number_mask[i] = Integer.parseInt(number_string[i], 2);
        }
    }

    static boolean isPossible_to_convert(int n, int m){
        return (n & m) == n;
    }

    static int countBits(int n){
        int c = 0;
        while(n > 0){
            n = n & (n-1);
            c++;
        }

        return c;
    }

    static List<Pair> getAllPossibles(String s){
        int bin = Integer.parseInt(s, 2);

        List<Pair> list = new ArrayList<>();
        for(int i=9;i>=0;i--){
            if(isPossible_to_convert(bin, number_mask[i])){
                int extra_bits = countBits(bin ^ number_mask[i]);
                Pair p = new Pair(extra_bits, i);
                list.add(p);
            }
        }

        return list;
    }

    static String recurse(List<List<Pair>> list, int index, int k){

        int n = list.size();

        if(k < 0)
            return "-1";

        if(index >= n){
            if(k == 0)
                return "";
            else return "-1";
        }

        List<Pair> li = list.get(index);
        for(Pair p: li){
            String ans = recurse(list, index + 1, k - p.count);

            if(!ans.equals("-1")){
                return p.val + ans;
            }
        }

        return "-1";
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        preCompute();

        int n = sc.nextInt();
        int k = sc.nextInt();

        String[] s = new String[n];
        for(int i=0;i<n;i++){
            s[i] = sc.next();
        }

        List<List<Pair>> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            List<Pair> li = getAllPossibles(s[i]);

            if(li.size() == 0){
                System.out.println(-1);
                System.exit(0);
            }

            list.add(li);
        }

        String ans = recurse(list, 0, k);

        System.out.println(ans);
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
