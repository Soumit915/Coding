package Hackerrank.Algorithms.DynamicProgramming;

import java.util.*;

public class XorAndSum
{
    static long mod = (long) 1e9+7;
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        /*String abin = sc.next();
        String bbin = sc.next();

        long a = findValue(abin);
        long b = findValue(bbin);*/
        long a=2, b=10;

        int pl = 314159;
        long[] powers = new long[pl+1];
        powers[0] = b;
        for(int i=1;i<=pl;i++)
        {
            powers[i] = (powers[i-1]*2)%mod;
        }

        long sum = 0;
        for(int i=0;i<=pl;i++)
        {
            sum += (a ^ powers[i])%mod;
            sum = sum%mod;
        }

        System.out.println(sum);
    }
}