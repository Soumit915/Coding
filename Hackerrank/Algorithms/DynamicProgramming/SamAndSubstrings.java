package Hackerrank.Algorithms.DynamicProgramming;

import java.util.*;

public class SamAndSubstrings
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();

        long ans = 0;
        long mod = (long) 1e9+7;
        long prev = 0;
        for(int i=0;i<n;i++)
        {
            char ch = s.charAt(i);
            prev = (prev*10)%mod;
            long val = ((int)ch-48)*(i+1);
            prev = prev + val;
            ans = (ans+prev)%mod;
        }

        System.out.println(ans);
    }
}