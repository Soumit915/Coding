package Hackerrank.Mathematics.Combinatorics;

import java.util.*;

public class SuperHumbleMatrix {
    public static long[] preComputeFactorials(int n, long mod)
    {
        long[] facts = new long[n+10];
        facts[0] = 1;
        for(int i=1;i<facts.length;i++)
        {
            facts[i] = (facts[i-1] * i)%mod;
        }

        return facts;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        long mod = (long) 1e9+7;
        long[] facts = preComputeFactorials(Math.max(n, m), mod);

        long ans = 1;
        for(int i=1;i<n;i++)
        {
            int ind = Math.min(i, m);
            ans = (ans * facts[ind])%mod;
        }

        for(int i=1;i<m-1;i++)
        {
            int ind = Math.min(i, n);
            ans = (ans * facts[ind])%mod;
        }

        System.out.println(ans);
    }
}
