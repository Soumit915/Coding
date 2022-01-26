package Hackerearth.DataStructures.FenwickTree.GCDSum;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class GCDSum {

    public int minEatingSpeed(int[] piles, int h) {
        int ll = 1, ul = 1000000000;
        while(ll<ul){
            int mid = (ll + ul)/2;

            int hcopy = h;
            for (int pile : piles) {
                hcopy -= (pile + mid - 1) / mid;
            }

            if(hcopy>=0){
                ul = mid;
            }
            else{
                ll = mid+1;
            }
        }

        return ll;
    }


    static ArrayList<Integer> primes = new ArrayList<>();
    static int mod = (int) 1e9+7;
    static int[] phi = new int[500100];
    static int[] gcdSumHash = new int[500100];
    public static void preComputePrimes(int n)
    {
        int[] p = new int[n+1];
        for(int i=0;i<=n;i++)
            p[i] = i;
        p[0] = 1;
        for(int i=0;i<=n;i++)
        {
            if(p[i]==1)
                continue;
            for(int j=2*i;j<=n;j+=i)
            {
                p[j] = 1;
            }
        }

        for(int i=0;i<=n;i++)
        {
            if(p[i]!=1)
                primes.add(i);
        }
    }
    public static int eulerTotient(int n)
    {
        int result=n;
        for(int i=0;i<primes.size() && n>1;i++)
        {
            int v = primes.get(i);
            if(n%v==0)
            {
                while (n%v==0)
                {
                    n = n/v;
                }
                result -= (result/v);
            }
        }

        if(n>1)
            result -= (result/n);
        return result;
    }
    public static void preComputePhi(int n)
    {
        for(int i=0;i<n;i++)
        {
            phi[i] = eulerTotient(i+1);
        }
    }
    public static HashSet<Integer> getDivisors(int val)
    {
        HashSet<Integer> divisors = new HashSet<>();
        int vsqrt = (int) Math.sqrt(val);
        for(int i=1;i<=vsqrt;i++)
        {
            if(val%i==0)
            {
                divisors.add(i);
                divisors.add(val/i);
            }
        }
        return divisors;
    }
    public static int gcdSum(int val)
    {
        if(gcdSumHash[val]!=0)
            return gcdSumHash[val];

        HashSet<Integer> divisors = getDivisors(val);
        int sum = 0;
        for (int v : divisors)
        {
            sum += (((long) v) * phi[(val / v)-1]) % mod;
            //System.out.println(v+" "+phi[val/v-1]);
        }

        gcdSumHash[val] = sum;
        return sum;
    }
    public static int query(int[] BITree, int l, int r)
    {
        int esum = 0;
        int i = r;
        while(i>0)
        {
            esum = (int) (((long)esum)+BITree[i])%mod;
            i -= (i&(-i));
        }

        int ssum = 0;
        i = l-1;
        while(i>0)
        {
            ssum = (int) (((long)ssum)+BITree[i])%mod;
            i -= (i&(-i));
        }

        return (int) ((((long)esum-ssum)%mod+mod)%mod);
    }
    public static void update(int[] BITree, int ind, int val)
    {
        int n = BITree.length;
        while(ind<n)
        {
            BITree[ind] = (int) ((((long)val)+BITree[ind])%mod);
            ind += (ind&(-ind));
        }
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();

        int range = 500000;
        preComputePrimes(((int) Math.sqrt(range)));
        preComputePhi(range);

        int[] BITree = new int[n+1];
        int[] arr = new int[n+1];
        BITree[0] = 0;
        for(int i=1;i<=n;i++)
        {
            int val = sc.nextInt();
            val = gcdSum(val);
            arr[i] = val;
            update(BITree, i, val);
        }

        int q = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++)
        {
            byte choice = sc.read();
            switch (choice)
            {
                case 'C': int l = sc.nextInt();
                    int r = sc.nextInt();
                    //System.out.println(query(BITree, l, r));
                    sb.append(query(BITree, l, r)).append("\n");
                    break;

                case 'U': int x = sc.nextInt();
                    int y = sc.nextInt();
                    y = gcdSum(y);
                    int up = y-arr[x];
                    update(BITree, x, up);
                    arr[x] = y;
                    break;
            }
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
