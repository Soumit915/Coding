package Hackerrank.Mathematics.Algebra;

import java.io.DataInputStream;
import java.io.IOException;

public class FindingTanNx {
    static long x, y;
    static long mod = (long) 1e9+7;
    static long gcdExtended(long a, long b)
    {
        if(a%b==0)
        {
            x = 1;
            y = 1-(a/b);
            return b;
        }
        long g = gcdExtended(b, a%b);
        long t = y;
        y = x-((a/b)*y)%mod;
        x = t;
        return g;
    }
    static long moduloInverse(long d)
    {
        long g = gcdExtended(d, mod);
        x = (x%mod+mod)%mod;
        return x;
    }
    static long tansum(long tanAx, long tanBx)
    {
        long num = (tanAx+tanBx)%mod;
        long deno = (tanAx*tanBx)%mod;
        deno = (((1-deno)%mod)+mod)%mod;
        deno = moduloInverse(deno);
        num = (num*deno)%mod;
        return num;
    }
    static int solve(long p, long q, int n) {

        int tanx = (int) ((p*moduloInverse(q))%mod);
        int tanNx = 0;
        while(n>0)
        {
            if(n%2==1)
            {
                tanNx = (int) tansum(tanNx, tanx);
            }
            tanx = (int) tansum(tanx, tanx);
            n/=2;
        }

        return tanNx;

    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (t-->0)
        {
            int p = sc.nextInt();
            int q = sc.nextInt();
            int n = sc.nextInt();
            sb.append(solve(p, q, n));
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
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
            din.close();
        }
    }
}
