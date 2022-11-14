package Hackerrank.Mathematics.Combinatorics;

import java.util.*;

public class ChooseAndCalculate
{
    static int x;
    static int y;
    static int mod = (int) 1e9+7;
    public static void gcdExtended(int a, int b)
    {
        if(a%b==0)
        {
            x = 1;
            y = 1-(a/b);
            return;
        }
        gcdExtended(b,a%b);
        int t = y;
        y = x-((a/b)*y)%mod;
        x = t;
    }
    public static void modInverse(int a)
    {
        gcdExtended(a, mod);
        x = (x%mod+mod)%mod;
    }
    public static int[] nFacts(int n)
    {
        int[] fact = new int[n+1];
        fact[0] = 1;
        for(int i=1;i<=n;i++)
        {
            long v = fact[i-1]%mod;
            v = (v*i)%mod;
            fact[i] = (int) v;
        }

        return fact;
    }
    public static int nCr(int[] fact, int n, int r)
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
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        long max = 0;
        int[] fact = nFacts(n);
        for(int i=k-1;i<n;i++)
        {
            max = (max+((long)nCr(fact, i,k-1)*arr[i])%mod)%mod;
        }

        long min = 0;
        for(int i=0;i<=n-k;i++)
        {
            min = (min+((long)nCr(fact,n-i-1,k-1)*arr[i])%mod)%mod;
        }

        max = max-min;
        max = (max+mod)%mod;
        System.out.println(max);
    }
}
