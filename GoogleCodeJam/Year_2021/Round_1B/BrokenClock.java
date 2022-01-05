package GoogleCodeJam.Year_2021.Round_1B;

import java.io.*;
import java.util.*;

public class BrokenClock {
    static class Time{
        long hour; long min; long sec; long nanosec;
        long cumhour; long cummin; long cumsec;
        Time(long hour, long min, long sec, long nanosec){
            this.hour = hour;this.min = min;this.sec = sec;this.nanosec = nanosec;
            this.cumsec = this.sec;
            this.cummin = this.min*60+this.cumsec;
            this.cumhour = this.hour*3600+this.cummin;
        }
    }
    static ArrayList<Time> timelist = new ArrayList<>();
    static void preCompute(){
        for(int i=0;i<12;i++){
            for(int j=0;j<60;j++){
                for(int k=0;k<60;k++){
                    Time time = new Time(i, j, k, 0);
                    timelist.add(time);
                }
            }
        }
    }
    static long hour_a, min_b, sec_c, nanod;
    static boolean isValid(long a, long b, long c){
        long mod = (long) 1e9;

        if(a%mod!=0 || b%(mod*12)!=0 || c%(mod*720)!=0){
            return false;
        }

        long a_nano = a%mod;
        long b_nano = b%(mod*12);
        long c_nano = c%(mod*720);

        if(!(a_nano==b_nano && b_nano==c_nano)){
            return false;
        }

        a /= mod;
        b /= mod;
        c /= mod;

        b /= 12;
        c /= 720;

        hour_a = a/3600;
        min_b = b/60;
        sec_c = c;
        nanod = a_nano;

        return sec_c + min_b * 60 == b && sec_c + min_b * 60 + hour_a * 3600 == a;
    }
    static boolean hasSols(long a, long b, long c){
        if((b-a)%11==0){
            long expectednano = (b-a)/11;
            long shift = a - expectednano;
            long nano = (long) (expectednano/1e9);
            if(720*expectednano+shift==c && nano==0){
                nanod = expectednano;
                return true;
            }
            else return false;
        }
        else return false;
    }
    static boolean isValid2(long a, long b, long c){
        long mod = (long) 1e9;
        long tot = 43200000000000L;
        for(Time time: timelist){
            long diffhour = a-time.cumhour*(mod);
            long diffmin = b-time.cummin*12*(mod);
            long diffsec = c-time.cumsec*720*(mod);
            if(hasSols(diffhour, diffmin, diffsec)){
                hour_a = time.hour;
                min_b = time.min;
                sec_c = time.sec;
                return true;
            }
            diffhour = (diffhour+tot)%tot;
            diffmin = (diffmin+tot)%tot;
            diffsec = (diffsec+tot)%tot;
            if(diffhour==diffsec && diffsec==diffmin) {
                hour_a = time.hour;
                min_b = time.min;
                sec_c = time.sec;
                return true;
            }
            else if(hasSols(diffhour, diffmin, diffsec)){
                hour_a = time.hour;
                min_b = time.min;
                sec_c = time.sec;
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        preCompute();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++){
            sb.append("Case #").append(testi).append(": ");

            long a = sc.nextLong();
            long b = sc.nextLong();
            long c = sc.nextLong();

            if(isValid(a, b, c)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid(a, c, b)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid(b, a, c)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid(b, c, a)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid(c, a, b)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid(c, b, a)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid2(a, b, c)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid2(a, c, b)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid2(b, a, c)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid2(b, c, a)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid2(c, a, b)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
            }
            else if(isValid2(c, b, a)){
                sb.append(hour_a).append(" ").append(min_b).append(" ").append(sec_c).append(" ").append(nanod).append("\n");
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
