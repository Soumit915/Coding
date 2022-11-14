package Codeforces.Round832Div2;

import java.io.*;
import java.util.*;

public class D {

    static int binarySearch(List<Integer> list, int k){
        int n = list.size();

        int l = 0, r = n-1;
        while(l < r){
            int mid = (l + r) / 2;

            if(list.get(mid) < k){
                l = mid + 1;
            }
            else{
                r = mid;
            }
        }

        if(l > n-1 || list.get(l) < k)
            return 1000000;

        return list.get(l);
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = 1;
        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            int n = sc.nextInt();
            int q = sc.nextInt();

            int[] a = sc.nextIntArray(n);

            int[] count_zeros = new int[n];
            for(int i=0;i<n;i++){
                if(a[i] == 0){
                    count_zeros[i] = 1;
                }
            }

            for(int i=1;i<n;i++){
                count_zeros[i] += count_zeros[i-1];
            }

            int[] xor_arr = new int[n];

            int xor = 0;
            HashMap<Integer, List<Integer>> odd_hash = new HashMap<>();
            HashMap<Integer, List<Integer>> even_hash = new HashMap<>();

            for(int i=0;i<n;i++){
                xor ^= a[i];

                xor_arr[i] = xor;

                if(i%2 == 0){
                    List<Integer> li = even_hash.getOrDefault(xor, new ArrayList<>());
                    li.add(i);

                    even_hash.put(xor, li);
                }
                else{
                    List<Integer> li = odd_hash.getOrDefault(xor, new ArrayList<>());
                    li.add(i);

                    odd_hash.put(xor, li);
                }
            }

            for(int i=0;i<q;i++){
                int l = sc.nextInt() - 1;
                int r = sc.nextInt() - 1;

                int cz = count_zeros[r];
                if(l > 0){
                    cz -= count_zeros[l - 1];
                }

                if(cz == (r-l+1)){
                    sb.append("0\n");
                    continue;
                }

                int xor_segment = xor_arr[r];
                if(l > 0)
                    xor_segment ^= xor_arr[l - 1];

                if(xor_segment != 0){
                    sb.append("-1\n");
                }
                else{
                    if((r-l+1)%2 == 1){
                        sb.append("1\n");
                    }
                    else{

                        if(a[l] == 0 || a[r] == 0){
                            sb.append("1\n");
                        }
                        else{

                            xor = (l==0)?0:xor_arr[l - 1];

                            List<Integer> list;
                            if(l%2 == 0){
                                list = even_hash.getOrDefault(xor, new ArrayList<>());
                            }
                            else{
                                list = odd_hash.getOrDefault(xor, new ArrayList<>());
                            }

                            int val = binarySearch(list, l);

                            if(val > r){
                                sb.append("-1\n");
                            }
                            else{
                                sb.append("2\n");
                            }
                        }
                    }
                }
            }
        }

        System.out.println(sb.toString());

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
