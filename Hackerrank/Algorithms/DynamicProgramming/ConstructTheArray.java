package Hackerrank.Algorithms.DynamicProgramming;

import java.util.*;
public class ConstructTheArray
{
    public static long getNumberOfArrays(int n, long k, long x)
    {
        long[] onedp = new long[n-1];
        long[] nononedp = new long[n-1];

        if(x==1)
        {
            onedp[0] = 1;
            nononedp[0] = 0;

            onedp[1] = 0;
            nononedp[1] = k-1;
        }
        else
        {
            onedp[0] = 0;
            nononedp[0] = 1;

            onedp[1] = 1;
            nononedp[1] = k-2;
        }

        long mod = 1000000007;
        for(int i=2;i<n-1;i++)
        {
            onedp[i] = nononedp[i-1];
            nononedp[i] = ((onedp[i-1]*(k-1))%mod+(nononedp[i-1]*(k-2))%mod)%mod;
        }

        return nononedp[n-2];
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        long x = sc.nextLong();

        System.out.println(getNumberOfArrays(n, k, x));
    }
}