package Spoj;

import java.io.*;
import java.util.*;

public class SmallestInverseTotientFunction {

    static ArrayList<Boolean> isPrime;
    static ArrayList<Integer> primes;
    static ArrayList<Integer> spf;
    static void preComputePrimes(int n){
        n += 10;
        isPrime = new ArrayList<>(n);
        primes = new ArrayList<>();
        spf = new ArrayList<>(n);

        for(int i=0;i<n;i++){
            isPrime.add(true);
            spf.add(2);
        }

        isPrime.set(0, false);
        isPrime.set(1, false);

        for(int i=2;i<n;i++){
            if(isPrime.get(i)){
                spf.set(i, i);
                primes.add(i);
            }

            for(int j=0;j<primes.size() && primes.get(j) <= spf.get(i) && primes.get(j)*i<n;j++){
                isPrime.set(primes.get(j)*i, false);
                spf.set(primes.get(j)*i, primes.get(j));
            }
        }
    }

    static void preCompute(){
        int n = 1001000;
        preComputePrimes(n);
    }

    static boolean isPrime(int n){
        if(n < isPrime.size())
            return isPrime.get(n);

        int sqrtN = (int) (Math.sqrt(n));
        for(int i=2;i<=sqrtN;i++){
            if(n%i==0)
                return false;
        }

        return true;
    }

    static long gcd(long a, long b){
        if(a%b==0)
            return b;
        else return gcd(b, a%b);
    }

    static HashMap<Integer, ArrayList<Long>> inversePHI_memo = new HashMap<>();
    static ArrayList<Long> getInverseETF(int n){
        if(inversePHI_memo.containsKey(n))
            return inversePHI_memo.get(n);
        if(n==1) {
            ArrayList<Long> list = new ArrayList<>();
            list.add(1L);
            return list;
        }
        if(n%2==1)
            return new ArrayList<>();

        int sqrtN = (int) (Math.sqrt(n));
        ArrayList<Long> inverseList = new ArrayList<>();
        List<Integer> divisible_pfactors = new ArrayList<>();
        for(int i=1;i<=sqrtN;i++) {
            if (n % i == 0) {
                int factor1 = i;
                int factor2 = n / i;

                if (isPrime(factor1 + 1)) {
                    if(n % (factor1 + 1) == 0){
                        divisible_pfactors.add(factor1 + 1);
                    }
                    else{
                        ArrayList<Long> phi_rem = getInverseETF(n / factor1);
                        for(long vals: phi_rem){
                            if(gcd(vals, factor1+1)==1){
                                inverseList.add(vals * (factor1 + 1));
                            }
                        }
                    }
                }

                if (factor1!=factor2 && isPrime(factor2 + 1)) {
                    if(n % (factor2 + 1) == 0){
                        divisible_pfactors.add(factor2 + 1);
                    }
                    else{
                        ArrayList<Long> phi_rem = getInverseETF(n / factor2);
                        for(long vals: phi_rem){
                            if(gcd(vals, factor2+1)==1){
                                inverseList.add(vals * (factor2 + 1));
                            }
                        }
                    }
                }
            }
        }

        for(int bit=1;bit<(1<<divisible_pfactors.size());bit++){

            List<Integer> divisible_pfactors_local = new ArrayList<>();
            for(int i=0;i<divisible_pfactors.size();i++){
                if((bit & (1<<i))!=0)
                    divisible_pfactors_local.add(divisible_pfactors.get(i));
            }

            long copyN = n;
            long inverseN = n;
            boolean flag = true;
            for(int i: divisible_pfactors_local){
                if(copyN % (i-1) == 0)
                    copyN /= (i-1);
                else {
                    flag = false;
                    break;
                }
            }
            if(flag){
                for(int i: divisible_pfactors_local){
                    if(n % i == 0)
                        inverseN = (inverseN * i) / (i - 1);
                    while(copyN % i == 0){
                        copyN /= i;
                    }
                }

                if(copyN > 1)
                    flag = false;
            }

            if(flag){
                inverseList.add(inverseN);
            }
        }

        inversePHI_memo.put(n, inverseList);

        return inverseList;
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");

        //Soumit sc = new Soumit();

        preCompute();

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();

            if(n%2==1){
                sb.append("-1\n");
                continue;
            }

            ArrayList<Long> inverseList = getInverseETF(n);
            Collections.sort(inverseList);

            long min = -1;
            if(inverseList.size() > 0)
                min = inverseList.get(0);

            sb.append(min).append("\n");
        }

        System.out.println(sb);

        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);

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
