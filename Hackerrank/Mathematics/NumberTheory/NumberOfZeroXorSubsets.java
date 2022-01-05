//Hint to the above problem is that number of subsets that xor to a value is always equal

package Hackerrank.Mathematics.NumberTheory;

import java.util.*;

public class NumberOfZeroXorSubsets
{
    static long x;
    static long y;
    public static void gcdExtended(long a, long b, long mod)
    {
        if(a%b==0)
        {
            x = 1;
            y = 1-(a/b);
            return;
        }
        gcdExtended(b, a%b, mod);
        long t = y;
        y = x-((a/b)*y)%mod;
        x = t;
    }
    public static long moduloInverse(long a, long mod)
    {
        gcdExtended(a, mod, mod);
        x = (x%mod+mod)%mod;
        return x;
    }
    public static long power(long a, long b, long mod)
    {
        long p=1;
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
        int t = sc.nextInt();

        long mod = 1000000007;
        while(t-->0)
        {
            long n = sc.nextLong();
            long deno = power(2, n, mod);
            n = power(2, n, mod-1);
            long num = power(2, n, mod);
            num = (num*moduloInverse(deno, mod))%mod;
            System.out.println(num);
        }
    }
}