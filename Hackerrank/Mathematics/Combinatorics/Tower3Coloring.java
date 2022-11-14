package Hackerrank.Mathematics.Combinatorics;

import java.util.Scanner;

public class Tower3Coloring {
    public static long power(long a, long b, long mod)
    {
        long p = 1;
        while (b>0)
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
        long n = sc.nextLong();
        long mod = (long) 1e9+7;
        n = power(3,n,mod-1);
        System.out.println(power(3,n,mod));
    }
}
