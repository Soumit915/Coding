package TCS_Codevita9_QualificationRound;

import java.util.*;

public class C {
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
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int low = sc.nextInt();
        int high = sc.nextInt();

        int k = sc.nextInt();
        long[] fact = nFacts(k);

        int diff = high-low+1;
        int even, odd;
        if(diff%2==0)
        {
            even = diff/2;
            odd = diff/2;
        }
        else
        {
            if(low%2==0)
            {
                odd = diff/2;
                even = diff-odd;
            }
            else
            {
                even = diff/2;
                odd = diff-even;
            }
        }

        long sum = 0;
        if(k==0)
            System.out.println(0);
        else
        {
            for(int i=0;i<=k;i+=2)
            {
                long ok = pow(odd, i);
                long ek = pow(even, k-i);
                long nCr = nCr(fact, k, i);
                //System.out.println(ok+" "+ek+" "+nCr);
                long v = (ok*ek)%mod;
                sum = (sum + (nCr*v)%mod)%mod;
            }

            System.out.print(sum);
        }

    }
}
