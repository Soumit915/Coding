package GoogleCodeJam.Year_2022.Round_1A;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class EqualSum {
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        for(int testi = 1;testi<=t;testi++){

            int n = sc.nextInt();
            if(n == -1){
                System.exit(0);
            }
            long[] arr = new long[2*n];

            for(int i=0;i<29;i++){
                arr[i] = 1<<i;
            }
            int ind = 1;
            for(int i=29;i<=n;i++){
                while((ind&(ind - 1)) == 0){
                    ind++;
                }
                arr[i] = ind;
            }

            StringBuilder sb = new StringBuilder();
            for(int i=0;i<n;i++){
                sb.append(arr[i]).append(" ");
            }

            System.out.println(sb);
            System.out.flush();

            for(int i=0;i<n;i++){
                arr[i+n] = sc.nextLong();
                if(arr[i+n] == -1){
                    System.exit(0);
                }
            }

            sc.sort(arr);

            long sum = 0;
            for(long l: arr)
                sum += l;

            long target = sum / 2;

            n += n;

            List<Set<Long>> dp = new ArrayList<>();
            for(int i=0;i<n;i++){
                Set<Long> set = new HashSet<>();
                dp.add(set);

                Set<Long> prev;
                if(i == 0)
                    prev = new HashSet<>();
                else prev = dp.get(i-1);

                for(long j: prev){
                    set.add(j);
                    set.add(j+arr[i]);
                }

                set.add(arr[i]);

                if(set.contains(target))
                    break;
            }

            List<Long> list = new ArrayList<>();

            for(int i=dp.size()-1;i>=0;i--){
                if(target == 0)
                    break;

                Set<Long> set = dp.get(i);
                Set<Long> prev;
                if(i == 0)
                    prev = new HashSet<>();
                else prev = dp.get(i-1);

                if(set.contains(target) && (prev.contains(target - arr[i])) || arr[i]==target){
                    list.add(arr[i]);
                    target -= arr[i];
                }
            }

            sb = new StringBuilder();
            for(long l: list){
                sb.append(l).append(" ");
            }
            System.out.println(sb);
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
