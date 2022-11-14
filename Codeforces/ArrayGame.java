package Codeforces;

import java.util.*;
import java.io.*;

public class ArrayGame {

    static HashMap<Integer, HashMap<Integer, Boolean>> dp;

    static boolean recurse(List<Integer> front, List<Integer> rear, int i, int j, int last){

        if(i >= front.size() && j >= rear.size()){
            return false;
        }

        if(i >= front.size()){
            if(last >= rear.get(j)){
                return false;
            }
            else {
                return (rear.size() - j) % 2 == 1;
            }
        }

        if(j >= rear.size()){
            if(last >= front.get(i)){
                return false;
            }
            else {
                return (front.size() - i) % 2 == 1;
            }
        }

        HashMap<Integer, Boolean> hash = dp.getOrDefault(i, new HashMap<>());
        if(hash.containsKey(j)){
            return hash.get(j);
        }

        boolean flag;
        if(last < front.get(i) && last < rear.get(j)){

            if(front.get(i) > rear.get(j) && (front.size() - i)%2 == 1){
                flag = true;
            }
            else if(rear.get(j) > front.get(i) && (rear.size() - j)%2 == 1){
                flag = true;
            }
            else{
                boolean flag1 = recurse(front, rear, i+1, j, front.get(i));
                boolean flag2 = recurse(front, rear, i, j+1, front.get(j));

                flag = !flag1 || !flag2;
            }
        }
        else if(last < front.get(i)){
            flag = (front.size() - i) % 2 == 1;
        }
        else{
            flag = (rear.size() - j) % 2 == 1;
        }

        hash.put(j, flag);
        dp.put(i, hash);

        return flag;
    }

    static boolean isMonotonic(int[] a){
        int n = a.length;

        boolean flag = true;
        for(int i=0;i<n-1;i++){
            if(a[i] > a[i+1]){
                flag = false;
                break;
            }
        }

        if(flag)
            return true;

        flag = true;
        for(int i=n-1;i>0;i--){
            if(a[i] > a[i-1]){
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit();

        int n = sc.nextInt();
        int[] a = sc.nextIntArray(n);

        if(n == 1){
            System.out.println("Alice");
            System.exit(0);
        }

        if(isMonotonic(a)){
            System.out.println("Alice");
            System.exit(0);
        }

        dp = new HashMap<>();

        List<Integer> front = new ArrayList<>();
        List<Integer> rear = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(a[i] < a[i+1]){
                front.add(a[i]);
            }
            else{
                front.add(a[i]);
                break;
            }
        }

        for(int i=n-1;i>=0;i--){
            if(a[i] < a[i-1]){
                rear.add(a[i]);
            }
            else{
                rear.add(a[i]);
                break;
            }
        }

        boolean flag = recurse(front, rear, 0, 0, -1);

        if(flag){
            System.out.println("Alice");
        }
        else{
            System.out.println("Bob");
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
