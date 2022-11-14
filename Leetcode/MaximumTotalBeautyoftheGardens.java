package Leetcode;

import java.io.*;
import java.util.*;

public class MaximumTotalBeautyoftheGardens {

    static boolean isValid(int[] hashcount, long[] prefSum, int target, long flowers, int needed){
        int count = hashcount[target-1];

        count = Math.min(count, needed);

        if(count == 0)
            return true;

        return (((long) target * (count)) - prefSum[count - 1]) <= flowers;
    }

    static long getFullPartial(int[] hashCount, long[] prefsum, int target, long left, int partial){
        int l = 1, r = target - 1;
        while(l < r){
            int mid = (1 + l + r)/2;

            if(isValid(hashCount, prefsum, mid, left, prefsum.length)){
                l = mid;
            }
            else{
                r = mid - 1;
            }
        }

        return (long) l * partial;
    }

    public static long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        int n = flowers.length;

        long[] prefsum = new long[n];
        int[] hashCount = new int[100100];
        prefsum[0] = flowers[0];
        hashCount[flowers[0]]++;
        for(int i=1;i<n;i++){
            prefsum[i] = prefsum[i-1] + flowers[i];
            hashCount[flowers[i]]++;
        }

        for(int i=1;i<hashCount.length;i++){
            hashCount[i] = hashCount[i-1] + hashCount[i];
        }

        if(flowers[0] >= target)
            return (long) n * full;

        long req = 0;
        int i;
        for(i=n-1;i>=0;i--){
            if(flowers[i] < target){
                i++;
                break;
            }
        }
        long max = 0;
        if(flowers[n-1] < target)
            max = getFullPartial(hashCount, prefsum, target, newFlowers, partial);
        for(i=Math.min(i, n-1);i>=0;i--){

            req += Math.max(0, target - flowers[i]);
            if(req > newFlowers)
                break;

            long left = newFlowers - req;

            if(i == 0){
                max = Math.max(max, (long) n * full);
                break;
            }

            int l = 1, r = target - 1;
            while(l < r){
                int mid = (1 + l + r)/2;

                if(isValid(hashCount, prefsum, mid, left, i)){
                    l = mid;
                }
                else{
                    r = mid - 1;
                }
            }

            max = Math.max(max, (long) l * partial + (long) (n - i) * full);
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        int[] flowers = sc.nextIntArray(n);

        long newFlowers = sc.nextLong();
        int target = sc.nextInt();
        int full = sc.nextInt();
        int partial = sc.nextInt();

        System.out.println(maximumBeauty(flowers, newFlowers, target, full, partial));

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
