package Hackerrank.Algorithms.Strings;

import java.util.*;

public class CommonChild
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        int al = a.length();
        int bl = b.length();

        int[][] dp = new int[al+1][bl+1];
        for(int i=al-1;i>=0;i--)
        {
            for(int j=bl-1;j>=0;j--)
            {
                if(a.charAt(i)==b.charAt(j))
                {
                    dp[i][j] = 1+dp[i+1][j+1];
                }
                else
                {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        System.out.println(dp[0][0]);
    }
}