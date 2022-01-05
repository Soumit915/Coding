package Codechef;

import java.io.*;
import java.util.*;

public class ChefAndRailwayStations_NrootN {
    static ArrayList<Integer> primes = new ArrayList<>();
    static ArrayList<Boolean> isPrime;
    static ArrayList<Integer> spf;
    static void permutePrime(){
        int n = 100100;
        isPrime = new ArrayList<>(n);
        spf = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            isPrime.add(true);
            spf.add(2);
        }

        isPrime.set(0, false);
        isPrime.set(1, false);

        for(int i=2;i<n;i++){
            if(isPrime.get(i)) {
                spf.set(i, i);
                primes.add(i);
            }

            for(int j=0;j<primes.size() && i*primes.get(j)<n && primes.get(j)<=spf.get(i);j++){
                isPrime.set(i*primes.get(j), false);
                spf.set(i*primes.get(j), primes.get(j));
            }
        }
    }
    static void recurse(HashMap<Integer, Integer> prime_map,
                  ArrayList<Integer> primelist, long curVal, ArrayList<Integer> list, int index){

        if(curVal>100000)
            return;
        else if(index==primelist.size()){
            list.add((int) curVal);
            return;
        }

        for(int i=0;i<=prime_map.get(primelist.get(index));i++){
            recurse(prime_map, primelist, curVal, list, index+1);
            curVal *= primelist.get(index);
        }
    }
    static long countTriangles(int x, int[] hash_pos, int[] hash_neg){

        HashMap<Integer, Integer> prime_map = new HashMap<>();

        x = Math.abs(x);
        int copy_x = x;
        while(copy_x>1){
            int v = spf.get(copy_x);
            prime_map.put(v, prime_map.getOrDefault(v, 0)+1);
            copy_x /= v;
        }

        prime_map.replaceAll((i, v) -> v * 2);

        ArrayList<Integer> primelist = new ArrayList<>(prime_map.keySet());
        Collections.sort(primelist);
        ArrayList<Integer> alldivlist = new ArrayList<>();
        recurse(prime_map, primelist, 1, alldivlist, 0);
        Collections.sort(alldivlist);

        //System.out.println(alldivlist+" "+x);
        long count = 0;
        for(int i: alldivlist){
            long x1 = x;
            x1 *= x1;
            //System.out.println((x1/i)+" "+x1+" "+i);
            if(x1%i==0){
                if(hash_pos[i]>0 && (x1/i)<hash_neg.length && hash_neg[(int) (x1/i)]>0){
                    count += ((long) hash_pos[i])*hash_neg[(int) (x1/i)];
                }
                else if(hash_neg[i]>0 && (x1/i)<hash_pos.length && hash_pos[(int) (x1/i)]>0){
                    count += ((long) hash_pos[i])*hash_neg[(int) (x1/i)];
                }
            }
        }

        return count;
    }
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");
        sc.streamOutput("Output2.txt");

        //Soumit sc = new Soumit();

        permutePrime();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] x = sc.nextIntArray(n);
            int[] y = sc.nextIntArray(m);

            int[] hashx_pos = new int[100100];
            int[] hashx_neg = new int[100100];
            int[] hashy_pos = new int[100100];
            int[] hashy_neg = new int[100100];
            for(int i: x){
                if(i>=0)
                    hashx_pos[i]++;
                else hashx_neg[i*-1]++;
            }
            for(int i: y){
                if(i>=0)
                    hashy_pos[i]++;
                else hashy_neg[i*-1]++;
            }

            long ans = 0;
            for(int i: x){
                ans += countTriangles(i, hashy_pos, hashy_neg);
            }

            for(int i: y){
                ans += countTriangles(i, hashx_pos, hashx_neg);
            }

            if(hashx_pos[0]>0 || hashy_pos[0]>0){
                ans += ((long)n-hashx_pos[0])*((long) m-hashy_pos[0]);
            }

            sb.append(ans).append("\n");
        }

        sc.println(sb.toString());

        System.out.println((System.currentTimeMillis()-start)/1000.0);

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
