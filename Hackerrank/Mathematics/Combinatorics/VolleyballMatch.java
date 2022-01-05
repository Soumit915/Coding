package Hackerrank.Mathematics.Combinatorics;

import java.util.*;
public class VolleyballMatch
{
    static long mod = (long) 1e9+7;
    public static long pow(long a, long b)
    {
        long p = 1;
        while(b>0)
        {
            if(b%2==1)
                p = (p*a)%mod;
            a = (a*a)%mod;
            b /= 2;
        }
        return p;
    }
    static long x, y;
    public static void gcdExtended(long a, long b)
    {
        if(a%b==0)
        {
            x = 1;
            y = 1-(a/b);
            return;
        }
        gcdExtended(b, a%b);
        long temp = y;
        y = x-((a/b)*y)%mod;
        x = temp;
    }
    public static long inverseModulo(long a)
    {
        gcdExtended(a, mod);
        x = (x%mod+mod)%mod;
        return x;
    }
    public static long nCr(long n, long r)
    {
        long num = facts[(int) n];
        long deno = (facts[(int) (n-r)] * facts[(int) r])%mod;
        deno = inverseModulo(deno);
        num = (num * deno)%mod;
        return num;
    }
    static long[] facts = new long[40];
    public static void preComputeFactorials()
    {
        facts[0] = 1;
        for(int i=1;i<40;i++)
        {
            facts[i] = (facts[i-1] * i)%mod;
        }
    }
    public static long findCombs(long n)
    {
        if(n==0) return 1;
        preComputeFactorials();
        long sum = 0;
        for(int i=1;i<=n;i++)
        {
            long ncr = nCr(25, i);
            long ic = nCr(n-1, i-1);
            ncr = (ncr * ic)%mod;
            sum = (sum + ncr)%mod;
        }

        return sum;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        if(a<b)
        {
            a = (a+b)-(b=a);
        }

        if(a<=24 || (a!=25 && (a-b)!=2) || (a==25 && (b==24 || b==25)))
        {
            System.out.println(0);
            System.exit(0);
        }

        long fc;
        if(a>25)
        {
            fc = findCombs(24);
            long p = pow(2, b-24);
            fc = (fc*p)%mod;
        }
        else
        {
            fc = findCombs(b);
        }
        System.out.println(fc);
    }
}

