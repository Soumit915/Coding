package Hackerrank.Mathematics.Combinatorics;

import java.util.*;

public class JourneyToMars {
    public static long firstK(long a, long b)
    {
        double f = a * Math.log10(2.0);
        f -= (long) f;
        f = Math.pow(10.0, f);
        f *= Math.pow(10, b - 1);
        return (long) f;
    }
    public static long pow(long a, long b, long mod)
    {
        long p = 1;
        while (b>0)
        {
            if(b%2==1)
            {
                p = (p*a)%mod;
            }
            a = (a*a)%mod;
            b /= 2;
        }
        return p;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            long n = sc.nextLong();
            long k = sc.nextLong();

            long l = 1000000000;
            long mod = pow(10, k, l);
            long last = pow(2, n-1, mod);

            System.out.println(firstK(n-1, k)+last);
        }
    }
}
