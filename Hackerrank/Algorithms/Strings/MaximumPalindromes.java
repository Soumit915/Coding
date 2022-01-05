package Hackerrank.Algorithms.Strings;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class MaximumPalindromes
{
    static long[] facts;
    static long[] inverses;
    static long mod = (long) 1e9+7;
    static class Query implements Comparable<Query>
    {
        int id;
        int l;
        int r;
        int leftblock;
        Query(int id, int l, int r)
        {
            this.id = id;
            this.l = l;
            this.r = r;
        }
        public int compareTo(Query q)
        {
            int c = Integer.compare(this.leftblock, q.leftblock);
            if(c==0)
            {
                c = Integer.compare(this.r, q.r);
                if(c==0)
                {
                    return Integer.compare(this.l, q.l);
                }
                else return c;
            }
            else return c;
        }
    }
    public static void preComputeFactorials(int n)
    {
        facts = new long[n+1];
        facts[0] = 1;
        for(int i=1;i<=n;i++)
        {
            facts[i] = (facts[i-1]*i)%mod;
        }

        inverses = new long[n+1];
        for(int i=0;i<=n;i++)
        {
            inverses[i] = inverseModulo(facts[i]);
        }
    }
    static long x,y;
    public static void gcdExtended(long a, long b)
    {
        if(a%b==0)
        {
            x = 1;
            y = 1-(a/b);
            return;
        }

        gcdExtended(b, a%b);
        long t = y;
        y = x-((a/b)*y)%mod;
        x = t;
    }
    public static long inverseModulo(long a)
    {
        gcdExtended(a, mod);
        x = (x%mod+mod)%mod;
        return x;
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        String s = sc.readLine();
        char[] str = s.toCharArray();

        preComputeFactorials(s.length());

        int q = sc.nextInt();
        int blocksize = (int) Math.sqrt(s.length());
        ArrayList<Query> querylist = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        long[] ansarr = new long[q];
        for(int qi=0;qi<q;qi++)
        {
            int l = sc.nextInt();
            int r = sc.nextInt();
            Query query = new Query(qi, l-1, r-1);
            query.leftblock = (l-1/blocksize);
            querylist.add(query);
        }
        Collections.sort(querylist);

        int[] hash = new int[26];
        int gl=0, gr=0;
        hash[str[0]-97]++;
        for(Query query: querylist)
        {
            while(gl<query.l)
            {
                hash[str[gl]-97]--;
                gl++;
            }
            while(gl>query.l)
            {
                gl--;
                hash[str[gl]-97]++;
            }

            while(gr<query.r)
            {
                gr++;
                hash[str[gr]-97]++;
            }
            while(gr>query.r)
            {
                hash[str[gr]-97]--;
                gr--;
            }

            long oddcount = 0;
            long totlen = 0;
            long deno = 1;
            for(int i=0;i<26;i++)
            {
                if(hash[i]%2==1)
                {
                    oddcount++;
                }
                totlen += hash[i]/2;
                deno = (deno*inverses[hash[i]/2])%mod;
            }

            long num = facts[(int) totlen];
            num = (num*deno)%mod;
            long ans = num;
            if(oddcount>0) {
                ans = (ans * oddcount) % mod;
            }
            ansarr[query.id] = ans;
        }

        for(long ans: ansarr)
        {
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
            byte[] buf = new byte[100010]; // line length
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