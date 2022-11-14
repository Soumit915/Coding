package Spoj;

import java.io.*;
import java.util.*;

public class SubsetSums_BruteForce {
    static String pad(String s, int n){
        StringBuilder sb = new StringBuilder();
        for(int i=s.length();i<n;i++){
            sb.append("0");
        }
        sb.append(s);
        return sb.toString();
    }
    static int computeSubsetSums(long[] arr, int start, int n, long a, long b){
        int lim = 1<<n;
        int count = 0;
        for(int i=0;i<lim;i++){
            String bin = Integer.toBinaryString(i);
            bin = pad(bin, n);

            long sum = 0;
            for(int j=0;j<n;j++){
                if(bin.charAt(j)=='1'){
                    sum += arr[start+j];
                }
            }
            if(a<=sum && sum<=b){
                count++;
            }
        }
        return count;
    }
    static int binarySearch_greater(ArrayList<Long> arlist, long key, int ll, int ul){
        if(ll<=ul){
            int mid = (ll+ul)/2;
            System.out.println(mid);
            if(arlist.get(mid)<key){
                //move to the right side
                if(mid==arlist.size()-1){
                    return arlist.size()+10;
                }
                else if(arlist.get(mid+1)>=key){
                    return mid+1;
                }
                else return binarySearch_greater(arlist, key, mid+1, ul);
            }
            else{
                //move to the left side
                if(mid==0 || arlist.get(mid-1)<key)
                    return mid;
                else return binarySearch_greater(arlist, key, ll, mid-1);
            }
        }
        else return arlist.size()+10;
    }
    static int binarySearch_lower(ArrayList<Long> arlist, long key, int ll, int ul){
        if(ll<=ul){
            int mid = (ll+ul)/2;
            if(arlist.get(mid)<=key){
                //move to the right
                if(mid==arlist.size()-1 || arlist.get(mid+1)>key){
                    return mid;
                }
                else return binarySearch_lower(arlist, key, mid+1, ul);
            }
            else {
                //move to the left
                if(mid==0){
                    return -1;
                }
                else if(arlist.get(mid-1)<=key){
                    return mid-1;
                }
                else return binarySearch_lower(arlist, key, ll, mid-1);
            }
        }
        else return -1;
    }
    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit("Input.txt");

        int n = sc.nextInt();
        long a = sc.nextLong();
        long b = sc.nextLong();

        long[] arr = sc.nextLongArray(n);

        ArrayList<Long> arlist = new ArrayList<>();
        int c = computeSubsetSums(arr, 0, n, a, b);
        Collections.sort(arlist);

        int count = 0;
        int l = binarySearch_greater(arlist, a, 0, arlist.size()-1);
        int r = binarySearch_lower(arlist, b, 0, arlist.size()-1);
        System.out.println(arlist);
        System.out.println(l+" "+r);
        if(l<=r){
            count = r-l+1;
        }

        if(c==count)
            System.out.println("Ok");
        else System.out.println("Wrong Answer");
        System.out.println(c);

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
