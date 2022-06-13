package Leetcode;

import java.io.*;
import java.util.*;

public class ReversePairs {

    static int getNextPowerOf2(int n){
        n = n | (n>>1);
        n = n | (n>>2);
        n = n | (n>>4);
        n = n | (n>>8);
        n = n | (n>>16);
        n = n | (n>>25);

        return n + 1;
    }

    static void update(int[] segTree, int si, int index, int l, int r){
        if(l == r){
            segTree[si]++;
            return;
        }

        int mid = (l + r) / 2;
        if(index <= mid)
            update(segTree, 2*si + 1, index, l, mid);
        else update(segTree, 2*si + 2, index, mid+1, r);

        segTree[si] = segTree[2*si+1] + segTree[2*si+2];
    }

    static int query(int[] segTree, int si, int start, int end, int l, int r){

        if(r < l)
            return 0;

        //no-overlap
        if(l > end || r < start)
            return 0;

        //total-overlap
        if(start <= l && end >= r){
            return segTree[si];
        }

        //partial-overlap
        int mid = (l + r) / 2;
        return query(segTree, 2*si+1, start, end, l, mid) + query(segTree, 2*si+2, start, end, mid+1, r);
    }

    static int reversePairs(int[] nums) {

        int n = nums.length;

        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }

        List<Integer> arlist = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        while(!set.isEmpty()){
            int v = set.first();
            arlist.add(v);
            set.remove(v);
        }

        for(int i=0;i<arlist.size();i++){
            map.put(arlist.get(i), i);
        }

        int sn = 2 * getNextPowerOf2(n) - 1;
        int[] segTree = new int[sn];

        System.out.println(arlist+" "+map);

        int ans = 0;
        for(int i=n-1;i>=0;i--){
            int v = (nums[i] - 1) / 2;

            if(map.floorKey(v) != null){
                int index = map.get(map.floorKey(v));

                int q = query(segTree, 0, 0, index, 0, arlist.size()-1);
                ans += q;
            }

            update(segTree, 0, map.get(nums[i]), 0, arlist.size() - 1);

            System.out.println(i+" "+Arrays.toString(segTree));
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int[] arr = {0,0};

        System.out.println(reversePairs(arr));

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
