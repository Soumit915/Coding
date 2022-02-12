import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class KratiProg {

    static interface OnlineAccount{
        int basePrice = 120;
        int regularMoviePrice = 45;
        int exclusiveMoviePrice = 80;
    }

    static class Account implements OnlineAccount, Comparable<Account>{
        int noOfRegularMovies, noOfExclusiveMovies;
        String ownerName;

        //1
        Account(String ownerName, int noOfRegularMovies, int noOfExclusiveMovies){
            this.ownerName = ownerName;
            this.noOfRegularMovies = noOfRegularMovies;
            this.noOfExclusiveMovies = noOfExclusiveMovies;
        }

        //2
        public int monthlyCost(){
            return basePrice + regularMoviePrice * noOfRegularMovies
                    + exclusiveMoviePrice * noOfExclusiveMovies;
        }

        //3
        public int compareTo(Account a){
            return Integer.compare(this.monthlyCost(), a.monthlyCost());
        }

        //4
        public String toString(){
            return "Owner is ["+this.ownerName+"] and montly cost is ["+this.monthlyCost()+"] USD";
        }
    }

    static class Shape{
        String type, color;

        Shape(String type, String color){
            this.type = type;
            this.color = color;
        }

        public String getType(){
            return this.type;
        }

        public String getColor(){
            return this.color;
        }

        public void setType(String type){
            this.type = type;
        }

        public void setColor(String color){
            this.color = color;
        }

        public Shape cloneShape(){
            return new Shape(this.type, this.color);
        }
    }

    static String simpleCipher(String encrypted, int k){
        StringBuilder sb = new StringBuilder();
        k%=26;

        for(int i=0;i<encrypted.length();i++){
            int ch = encrypted.charAt(i) - 'A';
            ch = (ch - k + 26)%26;

            char newch = (char) (ch + 'A');
            sb.append(newch);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        String s = sc.next();
        int k = sc.nextInt();

        System.out.println(simpleCipher(s, k));

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
