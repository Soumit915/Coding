package Hackerrank.Algorithms.DynamicProgramming;

import java.util.*;

public class LongestCommonSubsequence
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n];
        int[] b = new int[m];

        for(int i=0;i<n;i++)
        {
            a[i] = sc.nextInt();
        }
        for(int i=0;i<m;i++)
        {
            b[i] = sc.nextInt();
        }

        int[][] dp = new int[n+1][m+1];

        for(int i=n-1;i>=0;i--)
        {
            for(int j=m-1;j>=0;j--)
            {
                if(a[i]==b[j])
                    dp[i][j] = 1+dp[i+1][j+1];
                else
                    dp[i][j] = Math.max(dp[i][j+1], dp[i+1][j]);
            }
        }

        int len = dp[0][0];

        int i=0, j=0;
        while(len>0)
        {
            if(dp[i][j]==dp[i][j+1])
            {
                j++;
            }
            else if(dp[i][j]==dp[i+1][j])
            {
                i++;
            }
            else
            {
                System.out.print(b[j] + " ");
                i++;
                j++;
                len--;
            }
        }
        System.out.println();
    }
}
