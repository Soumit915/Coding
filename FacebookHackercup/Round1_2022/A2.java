package FacebookHackercup.Round1_2022;

import java.util.*;
import java.io.*;

public class A2 {

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output.txt");

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int ti=1;ti<=tc;ti++){

            sb.append("Case #").append(ti).append(": ");

            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] a = sc.nextIntArray(n);
            int[] b = sc.nextIntArray(n);

            if(k == 0){
                boolean flag = true;
                for(int i=0;i<n;i++){
                    if(a[i] != b[i]){
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    sb.append("YES\n");
                }
                else{
                    sb.append("NO\n");
                }

                continue;
            }

            if(n == 2){
                if(a[0] == a[1]){
                    sb.append("YES\n");
                }
                else{
                    int shift;
                    if(a[0] == b[0])
                        shift = 0;
                    else shift = 1;

                    if(shift == k%2){
                        sb.append("YES\n");
                    }
                    else{
                        sb.append("NO\n");
                    }
                }

                continue;
            }

            HashMap<Integer, List<Integer>> index_map = new HashMap<>();
            for(int i=0;i<n;i++){
                List<Integer> list = index_map.getOrDefault(a[i], new ArrayList<>());
                list.add(i);
                index_map.put(a[i], list);
            }

            HashMap<Integer, Integer> b_index = new HashMap<>();
            for(int i=0;i<n;i++){
                b_index.put(b[i], i);
            }

            boolean notstarted = true;
            HashSet<Integer> set = new HashSet<>();
            for(int i : b_index.keySet()){
                int v = b_index.get(i);
                if(notstarted){
                    List<Integer> list = index_map.getOrDefault(i, new ArrayList<>());

                    for(int j : list){
                        int shift;
                        if(v <= j){
                            shift = j - v;
                        }
                        else{
                            shift = j + (n - v);
                        }
                        shift %= n;

                        set.add(shift);
                    }
                    notstarted = false;
                }
                else{
                    List<Integer> list = index_map.getOrDefault(i, new ArrayList<>());
                    HashSet<Integer> curset = new HashSet<>();

                    for(int j : list){
                        int shift;
                        if(v <= j){
                            shift = j - v;
                        }
                        else{
                            shift = j + (n - v);
                        }

                        shift %= n;
                        if(set.contains(shift)){
                            curset.add(shift);
                        }
                    }

                    set = curset;
                }
            }

            int lastval = -1;
            for(int i : set)
                lastval = i;

            if(set.size() == 0 || (set.size() == 1 && lastval == 0 && k == 1)){
                sb.append("NO\n");
            }
            else{
                boolean flag = false;
                for(int shift : set){
                    boolean curflag = true;
                    for(int i = 0;i<n;i++){
                        if(a[(i + shift)%n] != b[i]){
                            curflag = false;
                            break;
                        }
                    }

                    flag = curflag;

                    if(flag)
                        break;
                }

                if(flag){
                    sb.append("YES\n");
                }
                else{
                    sb.append("NO\n");
                }
            }
        }

        sc.println(sb.toString());

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
