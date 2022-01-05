package Hackerrank.Mathematics.Combinatorics;

import java.util.*;

public class LexicographicPaths
{
    public static long fact(long a, long b)
    {
        long f=1;
        for(long i=a;i<=b;i++)
        {
            f = f*i;
        }
        return f;
    }
    public static long nCr(long n, long r)
    {
        long num = fact(n-r+1, n);
        long deno = fact(1, r);
        num = num/deno;
        return num;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            long x = sc.nextLong();
            long y = sc.nextLong();
            long k = sc.nextLong();
            k+=1;

            StringBuilder sb = new StringBuilder();
            while(x+y>1)
            {
                long combs = nCr(x+y-1,x-1);
                if(combs>=k && x>0)
                {
                    sb.append("H");
                    x--;
                }
                else
                {
                    sb.append("V");
                    k-=combs;
                    y--;
                }
            }

            if(x>0)
            {
                sb.append("H");
            }
            else
            {
                sb.append("V");
            }

            System.out.println(sb);
        }
    }
}