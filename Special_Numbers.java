//A special number is one that is a sum of two numbers that are reverse to each other
//ex: 165 is a special number as it is a sum of 78 and 87

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Special_Numbers {
    static boolean isSpecial(int[] digits, int ll, int rr){

        if(ll>rr)
            return true;

        //condition for (l-1) + (l-1)
        if(ll==rr){
            if(ll>0 && digits[ll-1]==1){
                int f4 = digits[ll];

                digits[ll-1] = 0;
                digits[ll] = 0;
                return f4%2==0;
            }

            //condition for l + l
            int f1 = digits[ll];
            digits[ll] = 0;
            return f1%2==0;
        }

        if(ll+1==rr){
            if(ll>0 && digits[ll-1]==1){
                int f2 = digits[ll]-1;
                int f4 = digits[rr];

                digits[ll-1] = 0;
                digits[ll] = 0;
                digits[rr] = 0;
                return f2 == f4;
            }

            //condition for l + l
            int f2 = digits[ll];
            int f4 = digits[rr];

            digits[ll] = 0;
            digits[rr] = 0;
            return f2 == f4;
        }

        if(ll>0 && digits[ll-1]==1){
            int f2 = digits[ll];
            int f4 = digits[rr];

            if(f2==0){
                f2 = 9;
            }
            else{

            }

            if(f4==f2){
                if(f4==9)
                    return false;

                digits[ll-1] = 0;
                digits[ll] = 0;
                digits[rr-1] -= 1;
                digits[rr] = 0;
                System.out.println(ll+", "+rr+" "+Arrays.toString(digits));
                return isSpecial(digits, ll+1, rr-1);
            }
            else if(f4+1==f2){
                digits[ll-1] = 0;
                digits[ll] = 1;
                digits[rr-1] -= 1;
                digits[rr] = 0;
                System.out.println(ll+",, "+rr+" "+Arrays.toString(digits));
                return isSpecial(digits, ll+1, rr-1);
            }
        }

        //condition for l + l
        int f1 = digits[ll];
        int f2 = digits[rr];

        if(f1==f2){
            digits[ll] = 0;
            digits[rr] = 0;
            System.out.println(ll+",,, "+rr+" "+Arrays.toString(digits));
            return isSpecial(digits, ll+1, rr-1);
        }
        else if(f1==f2+1){
            digits[ll] = 1;
            digits[rr] = 0;
            System.out.println(ll+",,,, "+rr+" "+Arrays.toString(digits));
            return isSpecial(digits, ll+1, rr-1);
        }

        return false;
    }
    static boolean allZero(int[] digits){
        System.out.println("Test: "+Arrays.toString(digits));
        for(int i: digits)
            if(i!=0)
                return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output1.txt");

        int n = sc.nextInt();
        String[] numbers = new String[n];
        int c = 0;
        for(int i=0;i<n;i++){
            numbers[i] = sc.next();
            int l = numbers[i].length();
            char[] sarr = numbers[i].toCharArray();
            int[] digits = new int[l];

            for(int j=0;j<l;j++){
                digits[j] = sarr[j]-48;
            }
            if(isSpecial(digits, 1, l-1) && allZero(digits)){
                sc.println(numbers[i]);
                c++;
            }

            for(int j=0;j<l;j++){
                digits[j] = sarr[j]-48;
            }
            if(isSpecial(digits, 0, l-1) && allZero(digits)){
                sc.println(numbers[i]);
                c++;
            }
        }

        sc.println(c+"");

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
