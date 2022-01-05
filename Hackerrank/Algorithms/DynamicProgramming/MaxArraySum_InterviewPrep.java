package Hackerrank.Algorithms.DynamicProgramming;

import java.util.*;

public class MaxArraySum_InterviewPrep
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[] arr = new long[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextLong();
        }

        long[] dp = new long[n];
        dp[0] = arr[0];
        if(n>1) dp[1] = Math.max(dp[0], arr[1]);
        for(int i=2;i<n;i++)
        {
            dp[i] = Math.max(dp[i-2]+ arr[i], Math.max(dp[i-1], arr[i]));
        }

        System.out.println(dp[n-1]);
    }
}