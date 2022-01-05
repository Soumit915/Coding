package Hackerrank.Mathematics.Combinatorics;

import java.util.*;
public class AntiPalindromicStrings
{
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

        long mod = (long) 1e9+7;
        while(t-->0)
        {
            long n = sc.nextLong();
            long m = sc.nextLong();

            long ans = 1;
            for(int i=1;i<=Math.min(n, 3);i++)
            {
                if(i==1)
                    ans = m;
                else if(i==2)
                    ans = (ans*(m-1))%mod;
                else
                    ans = (ans*power(m-2, n-2, mod))%mod;
            }
            System.out.println(ans);
        }
    }
}
