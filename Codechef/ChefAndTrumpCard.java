package Codechef;

import java.io.*;

public class ChefAndTrumpCard {
    static long x;
    static long y;
    static long mod = (long) 1e9+7;
    public static void gcdExtended(long a, long b)
    {
        if(a%b==0)
        {
            x = 1;
            y = 1-(a/b);
            return;
        }
        gcdExtended(b,a%b);
        long t = y;
        y = x-((a/b)*y)%mod;
        x = t;
    }
    public static void modInverse(long a)
    {
        gcdExtended(a, mod);
        x = (x%mod+mod)%mod;
    }
    public static long[] nFacts(int n)
    {
        long[] fact = new long[n+1];
        fact[0] = 1;
        for(int i=1;i<=n;i++)
        {
            long v = fact[i-1]%mod;
            v = (v*i)%mod;
            fact[i] = v;
        }

        return fact;
    }
    public static int nCr(long[] fact, int n, int r)
    {
        long num = fact[n];
        long fr = fact[r];
        fr = ((fr)*fact[n-r])%mod;
        long deno =  fr;
        modInverse((int) deno);
        deno = x;
        long v = num*deno;
        v = v%mod;

        return (int) v;
    }
    public static long pow(long a, long b)
    {
        long p = 1;
        while(b>0)
        {
            if(b%2==1)
            {
                p = (p*a)%mod;
            }
            a = (a*a)%mod;
            b/=2;
        }
        return p;
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int t = sc.nextInt();

        long[] facts = nFacts(1000500);

        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();
            long[] arr = new long[n];
            long max = -2;
            for(int i=0;i<n;i++) {
                arr[i] = sc.nextLong();
                max = Math.max(arr[i], max);
            }

            int count = 0;
            for(long i: arr)
                if(i == max)
                    count++;

            long ans = pow(2, n-count);
            long maxcount = pow(2, count);
            if(count%2==0) {
                long ncr = nCr(facts, count, count/2);
                maxcount = ((maxcount-ncr)%mod + mod)%mod;
            }

            ans = (ans * maxcount)%mod;
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 18;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[100064]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
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

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
