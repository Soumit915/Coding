package Hackerrank.Algorithms.DynamicProgramming;

import java.util.*;

public class Abbreviation
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            String a = sc.next();
            String b = sc.next();
            int al = a.length();
            int bl = b.length();

            boolean[][] dp = new boolean[al+1][bl+1];
            for(int i=0;i<bl;i++)
            {
                dp[al][i] = false;
            }
            dp[al][bl] = true;

            boolean capFlag = true;
            for(int i=al-1;i>=0;i--)
            {
                if(65<=a.charAt(i )&& a.charAt(i)<=90)
                    capFlag = false;
                dp[i][bl] = capFlag;
            }

            for(int i=bl-1;i>=0;i--)
            {
                char chb = b.charAt(i);
                for(int j=al-1;j>=0;j--)
                {
                    char cha = a.charAt(j);
                    if(cha==chb)
                    {
                        dp[j][i] = dp[j+1][i+1];
                    }
                    else if(cha-32==chb)
                    {
                        dp[j][i] = dp[j+1][i] | dp[j+1][i+1];
                    }
                    else if(65<=cha && cha<=90)
                    {
                        dp[j][i] = false;
                    }
                    else
                    {
                        //System.out.println(i+" "+j);
                        dp[j][i] = dp[j+1][i];
                    }
                }
            }

            if(dp[0][0])
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
