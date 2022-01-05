package Leetcode;

import java.io.*;
import java.util.*;

public class PaintingGridWith3Colors {
    static long mod = (long) 1e9+7;

    static String pad(String ter, int n){
        StringBuilder sb = new StringBuilder();
        for(int i=ter.length()+1;i<=n;i++){
            sb.append("0");
        }
        sb.append(ter);
        return sb.toString();
    }

    static boolean isValid(String s){
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==s.charAt(i-1))
                return false;
        }
        return true;
    }

    static boolean isValidPair(String s, String t){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==t.charAt(i))
                return false;
        }
        return true;
    }

    public static int colorTheGrid(int m, int n) {
        int lim = (int) (Math.pow(3, m));

        ArrayList<Integer> validArrangements = new ArrayList<>();
        Map<Integer, String> ternaryMap = new HashMap<>();
        for(int i=0;i<lim;i++){
            String ter = Integer.toString(i, 3);
            ter = pad(ter, m);

            if(isValid(ter)){
                validArrangements.add(i);
                ternaryMap.put(i, ter);
            }
        }

        if(n==1)
            return validArrangements.size();

        Map<Integer, ArrayList<Integer>> map_list = new HashMap<>();
        for(int i: validArrangements){
            ArrayList<Integer> list = new ArrayList<>();
            for(int j: validArrangements){
                if(isValidPair(ternaryMap.get(i), ternaryMap.get(j))){
                    list.add(j);
                }
            }
            map_list.put(i, list);
        }

        Map<Integer, Long> count_map = new HashMap<>();
        for(int i: validArrangements){
            count_map.put(i, 1L);
        }
        for(int i=2;i<=n;i++){
            Map<Integer, Long> local_countMap = new HashMap<>();
            for(int j: validArrangements){
                ArrayList<Integer> list = map_list.get(j);
                for(int k: list){
                    local_countMap.put(k, (local_countMap.getOrDefault(k, 0L)+count_map.getOrDefault(j, 0L))%mod);
                }
            }

            count_map = local_countMap;
        }

        long sum = 0;
        for(int i: count_map.keySet()){
            sum = (sum + count_map.get(i))%mod;
        }

        return (int) (sum%mod);
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        System.out.println(colorTheGrid(sc.nextInt(), sc.nextInt()));

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
