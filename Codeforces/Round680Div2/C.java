package Codeforces.Round680Div2;

import java.io.*;
import java.util.*;

public class C {
    static ArrayList<Boolean> isPrime;
    static ArrayList<Integer> primes;
    static ArrayList<Integer> spf;
    public static void computePrimes()
    {
        int n = 1000002;
        isPrime = new ArrayList<>(n);
        primes = new ArrayList<>();
        spf = new ArrayList<>(n);

        for(int i=0;i<n;i++)
        {
            isPrime.add(true);
            spf.add(2);
        }

        isPrime.set(0, false);
        isPrime.set(1, false);

        for(int i=2;i<n;i++)
        {
            if(isPrime.get(i))
            {
                primes.add(i);
                spf.set(i, i);
            }

            for(int j=0;j<primes.size() && i*primes.get(j)<n && primes.get(j)<=spf.get(i);j++)
            {
                isPrime.set(i*primes.get(j), false);
                spf.set(i*primes.get(j), primes.get(j));
            }
        }
    }
    public static ArrayList<Long> fun(long q)
    {
        ArrayList<Long> factors = new ArrayList<>();
        for(int i: primes)
        {
            while(q>1 && q% (long) i ==0)
            {
                factors.add((long) i);
                q /= i;
            }

            if(q==1)
                break;
        }

        if(q!=1)
            factors.add( q);
        return factors;
    }
    public static void main(String[] args) throws IOException {

        Soumit sc = new Soumit();

        int t = sc.nextInt();
        computePrimes();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            long p = sc.nextLong();
            long q = sc.nextLong();

            if(p<q)
            {
                sb.append(p).append("\n");

            }
            else if(p%q!=0)
            {
                sb.append(p).append("\n");
            }
            else if(p%q==0)
            {
                ArrayList<Long> factorq = fun(q);
                Collections.sort(factorq);
                long coef = p/q;
                ArrayList<Long> factorc = fun(coef);
                Collections.sort(factorc);

                Set<Long> set = new HashSet<>(factorq);

                long maxans = 1;
                for(long key: set)
                {
                    long ans = 1;
                    long others = 1;
                    for(long i: factorq)
                        if(i!=key)
                            ans *= i;
                        else others *= i;
                    for(long i: factorc)
                        if(i!=key)
                            ans *= i;

                    others /= key;
                    ans *= others;

                    maxans = Math.max(ans, maxans);
                }

                sb.append(maxans).append("\n");
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
            byte[] buf = new byte[100064]; // line length
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
