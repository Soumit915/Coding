package DynamicProgramming;

import java.util.*;

public class LongestCommonSubsequence {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first string : ");
        String a = sc.next();
        int al = a.length();
        a = a.toUpperCase();

        System.out.print("Enter the second string : ");
        String b = sc.next();
        int bl = b.length();
        b = b.toUpperCase();

        StringBuilder lcs = new StringBuilder();
        int[][] dp = new int[al+1][bl+1];
        for(int i=al-1;i>=0;i--)
        {
            char chr = a.charAt(i);
            for(int j=bl-1;j>=0;j--)
            {
                char chc = b.charAt(j);
                if(chc == chr)
                {
                    dp[i][j] = 1+dp[i+1][j+1];
                }
                else
                {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j+1]);
                }
            }
        }

        /*for(int i=0;i<al+1;i++)
        {
            for(int j=0;j<bl+1;j++)
            {
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }*/

        int row = 0;
        int col = 0;
        while(row!=al || col!=bl)
        {
            if(dp[row][col] == dp[row][col+1])
            {
                col = col+1;
            }
            else if(dp[row][col] == dp[row+1][col])
            {
                row = row+1;
            }
            else if(dp[row][col] == 1+dp[row+1][col+1])
            {
                row = row+1;
                col = col+1;
                lcs.append(a.charAt(row-1));
            }
        }

        System.out.println("The length of the longest common substring is : "+lcs.length());
        System.out.println("The longest common subsequence : "+lcs);
    }
}
