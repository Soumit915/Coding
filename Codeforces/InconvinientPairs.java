package Codeforces;

import java.io.*;
import java.util.*;

public class InconvinientPairs {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();

            int[] vertical = sc.nextIntArray(n);
            int[] horizontal = sc.nextIntArray(m);

            int[][] people = new int[k][2];
            HashMap<Integer, List<Integer>> vertical_hashList = new HashMap<>();
            HashMap<Integer, List<Integer>> horizontal_hashList = new HashMap<>();
            for(int i=0;i<k;i++){
                people[i] = sc.nextIntArray(2);

                if(Arrays.binarySearch(vertical, people[i][0]) >= 0){
                    List<Integer> list = vertical_hashList.getOrDefault(people[i][0], new ArrayList<>());
                    list.add(people[i][1]);
                    vertical_hashList.put(people[i][0], list);
                }

                if(Arrays.binarySearch(horizontal, people[i][1]) >= 0){
                    List<Integer> list = horizontal_hashList.getOrDefault(people[i][1], new ArrayList<>());
                    list.add(people[i][0]);
                    horizontal_hashList.put(people[i][1], list);
                }
            }

            for(int i: vertical_hashList.keySet()){
                List<Integer> list = vertical_hashList.get(i);
                Collections.sort(list);
            }
            for(int i: horizontal_hashList.keySet()){
                List<Integer> list = horizontal_hashList.get(i);
                Collections.sort(list);
            }

            int[] horizontal_hash = new int[k];
            int[] vertical_hash = new int[k];
            for(int i=0;i<k;i++){
                horizontal_hash[i] = people[i][1];
                vertical_hash[i] = people[i][0];
            }

            Arrays.sort(horizontal_hash);
            Arrays.sort(vertical_hash);

            long ans = 0;
            for(int i=0;i<k;i++){
                int x = people[i][0];
                int y = people[i][1];

                if(vertical_hashList.containsKey(x) && horizontal_hashList.containsKey(y)){
                    continue;
                }
                else if(vertical_hashList.containsKey(x)){
                    int up = lower_bound(horizontal, y - 1);
                    int down = upper_bound(horizontal, y + 1);

                    long count = upper_bound_index(horizontal_hash, down) - lower_bound_index(horizontal_hash, up) - 1;

                    List<Integer> in_the_same_sector_line = vertical_hashList.get(x);
                    long in = lower_bound(in_the_same_sector_line, down - 1) - upper_bound(in_the_same_sector_line, up + 1) + 1;
                    long tot = (count - in);
                    ans += tot;
                }
                else{
                    int up = lower_bound(vertical, x - 1);
                    int down = upper_bound(vertical, x + 1);

                    long count = upper_bound_index(vertical_hash, down) - lower_bound_index(vertical_hash, up) - 1;

                    List<Integer> in_the_same_sector_line = horizontal_hashList.get(y);
                    long in = lower_bound(in_the_same_sector_line, down - 1) - upper_bound(in_the_same_sector_line, up + 1) + 1;
                    long tot = (count - in);
                    ans += tot;
                }
            }

            sb.append(ans / 2).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }

    static int lower_bound(int[] arr, int k){
        int l = 0, r = arr.length - 1;

        while(l < r){
            int mid = (l + r + 1) / 2;

            if(arr[mid] > k){
                r = mid - 1;
            }
            else{
                l = mid;
            }
        }

        return arr[l];
    }

    static int upper_bound(int[] arr, int k){
        int l = 0, r = arr.length - 1;

        while(l < r){
            int mid = (l + r) / 2;

            if(arr[mid] < k){
                l = mid + 1;
            }
            else{
                r = mid;
            }
        }

        return arr[l];
    }

    static int lower_bound_index(int[] arr, int k){
        int l = 0, r = arr.length - 1;

        while(l < r){
            int mid = (l + r + 1) / 2;

            if(arr[mid] > k){
                r = mid - 1;
            }
            else{
                l = mid;
            }
        }

        if(l == 0 && arr[l] > k){
            return -1;
        }

        return l;
    }

    static int upper_bound_index(int[] arr, int k){
        int l = 0, r = arr.length - 1;

        while(l < r){
            int mid = (l + r) / 2;

            if(arr[mid] < k){
                l = mid + 1;
            }
            else{
                r = mid;
            }
        }

        if(l == arr.length - 1 && arr[l] < k){
            return arr.length;
        }

        return l;
    }

    static int lower_bound(List<Integer> list, int k){
        int l = 0, r = list.size() - 1;

        while(l < r){
            int mid = (l + r + 1) / 2;

            if(list.get(mid) > k){
                r = mid - 1;
            }
            else{
                l = mid;
            }
        }

        return l;
    }

    static int upper_bound(List<Integer> list, int k){
        int l = 0, r = list.size() - 1;

        while(l < r){
            int mid = (l + r) / 2;

            if(list.get(mid) < k){
                l = mid + 1;
            }
            else{
                r = mid;
            }
        }

        return l;
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
