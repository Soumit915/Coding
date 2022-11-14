package DynamicProgramming;

import java.util.*;

public class PaintingFence {
    public static int findsols(int n, int k)
    {
        int mod = (int) 1e9+7;
        long ans = (long) Math.pow(k, n);
        ans = ans%mod;
        if(n==1 || n==2)
        {
            return (int) ans;
        }

        int[] dp = new int[n];
        dp[0] = k;dp[1] = (k*k) %mod;
        for(int i=2;i<n;i++)
        {
            ans = (k)*(k-1)+(dp[i-1]-k)*k;
            ans = ans%mod;
            dp[i] = (int) ans;
        }

        return dp[n-1];
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(findsols(n, k));
    }
}
