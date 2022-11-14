package Hackerrank.Algorithms.Searching;

import java.util.*;

public class ShortPalindrome
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        long[][] dp3 = new long[26][26];
        long[][] dp2 = new long[26][26];
        long[][] dp1 = new long[26][26];
        long[] dp0 = new long[26];
        for(int ind=0;ind<s.length();ind++)
        {
            int i = s.charAt(ind)-97;
            for(int j=0;j<26;j++)
            {
                dp3[i][j] = (dp3[i][j]+dp2[j][i])%1000000007;
                dp2[i][j] = (dp2[i][j]+dp1[i][j])%1000000007;
                dp1[i][j] = (dp1[i][j]+dp0[j])%1000000007;
            }
            dp0[i]++;
        }

        long count=0;
        for(int i=0;i<26;i++)
        {
            for(int j=0;j<26;j++)
            {
                count = (count+dp3[i][j])%1000000007;
            }
        }
        System.out.println(count);
    }
}