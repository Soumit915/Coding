import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class CrossSequence {
    static int leftSearch(long[] diff, long val, int left, int right, long rightval, int rightindex){
        if(left<=right){
            int mid = (left+right)/2;
            long midval = rightval-diff[mid-1];
            if(midval>=val){
                return leftSearch(diff, val, mid+1, right, rightval, rightindex);
            }
            else{
                int v = leftSearch(diff, val, left, mid-1, rightval, rightindex);
                if(v!=0){
                    return v;
                }
                else{
                    return rightindex-mid+1;
                }
            }
        }
        else{
            return 0;
        }
    }
    static int rightSearch(long[] diff, long val, int left, int right, long rightval){
        if(left<=right){
            int mid = (left+right)/2;
            long midval = rightval-diff[mid-1];
            if(midval<=val){
                return rightSearch(diff, val, left, mid-1, rightval);
            }
            else{
                int v = rightSearch(diff, val, mid+1, right, rightval);
                if(v!=0){
                    return v;
                }
                else{
                    return mid;
                }
            }
        }
        else{
            return 0;
        }
    }
    static ArrayList<Long> getDivisions(long[] arr, long val){
        int n = arr.length;

        long[] diff = new long[n];
        for(int i=1;i<n;i++){
            diff[i] = arr[i]-arr[i-1];
        }
        for(int i=1;i<n;i++){
            diff[i] = diff[i]+diff[i-1];
        }

        ArrayList<Long> divisionlist = new ArrayList<>();
        divisionlist.add((long) n);
        divisionlist.add(0L);
        divisionlist.add(0L);

        for(int i=1;i<n;i++){
            long less = leftSearch(diff, val, 1, i, diff[i], i);
            long more = rightSearch(diff, val, 1, i, diff[i]);
            long equal = i-less-more;

            less *= 2;
            equal *= 2;
            more *= 2;
            divisionlist.set(0, divisionlist.get(0)+less);
            divisionlist.set(1, divisionlist.get(1)+equal);
            divisionlist.set(2, divisionlist.get(2)+more);
        }

        return divisionlist;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            long[] arr = sc.nextLongArray(n);
            sc.sort(arr);
            long k = sc.nextLong();

            if(k<=n){
                sb.append("0\n");
            }
            else{
                long lans = 0;
                long rans = 2*((long) 1e9);
                while(lans<rans){
                    long mid = lans+(rans-lans)/2;
                    ArrayList<Long> divisions = getDivisions(arr, mid);
                    if(divisions.get(0)>=k){
                        rans = mid-1;
                    }
                    else if(divisions.get(0)+divisions.get(1)>=k){
                        lans = mid;
                        break;
                    }
                    else{
                        lans = mid+1;
                    }
                }

                sb.append(lans).append("\n");
            }
        }

        System.out.println(sb);

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
