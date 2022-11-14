package Hackerrank.Mathematics.NumberTheory;

import java.util.*;

public class EasyGCD {
    public static long gcd(long a, long b)
    {
        if(a==0)
            return b;
        else if(b==0)
            return a;
        if(a%b==0)
            return b;
        return gcd(b, a%b);
    }
    public static long findLowestFactor(long n)
    {
        long lim = (long)Math.ceil(Math.sqrt(n));
        for(int i=2;i<lim;i++)
        {
            if(n%i==0)
                return i;
        }
        return n;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextInt();

        long[] arr = new long[n];
        long g=0;
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextLong();
            g = gcd(g, arr[i]);
        }

        long v = findLowestFactor(g);
        System.out.println(k/v*v);
    }
}
