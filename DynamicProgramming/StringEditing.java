package DynamicProgramming;

import java.util.*;

public class StringEditing {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first string : ");
        String a = sc.next();
        int al = a.length();
        a = a.toLowerCase();

        System.out.print("Enter the second string : ");
        String b = sc.next();
        int bl = b.length();
        b = b.toLowerCase();

        int[][] dp = new int[al+1][bl+1];
        StringBuilder lcs = new StringBuilder();
        for(int i=0;i<=al;i++)
        {
            dp[i][bl] = al-i;
        }
        for(int i=0;i<=bl;i++)
        {
            dp[al][i] = bl-i;
        }

        for(int i=al-1;i>=0;i--)
        {
            char chr = a.charAt(i);
            for(int j=bl-1;j>=0;j--)
            {
                char chc = b.charAt(j);
                if(chr == chc)
                {
                    dp[i][j] = dp[i+1][j+1];
                }
                else
                {
                    dp[i][j] = 1+Math.min(dp[i][j+1],Math.min(dp[i+1][j],dp[i+1][j+1]));
                }
            }
        }

        System.out.println("Total number of changes needed to make is : "+dp[0][0]);

        int row = 0;
        int col = 0;
        while(row<al && col<bl)
        {
            char chr = a.charAt(row);
            char chc = b.charAt(col);
            if(chr == chc)
            {
                row = row+1;
                col = col+1;
            }
            else
            {
                if(dp[row][col] == 1+dp[row+1][col+1])
                {
                    System.out.println("Replace "+chr+" at "+(row+1)+" with "+chc);
                    row = row+1;
                    col = col+1;
                }
                else if(dp[row][col] == 1+dp[row][col+1])
                {
                    System.out.println("Insert "+chc+" at "+(row+1));
                    col = col+1;
                }
                else
                {
                    System.out.println("Delete "+chr+" at "+(row+1));
                    row = row+1;
                }
            }
        }

        while(row<al)
        {
            char chr = a.charAt(row);
            char chc = b.charAt(bl-1);
            System.out.println("Delete "+chr+" at "+(row+1));
            row = row+1;
        }

        while(col<bl)
        {
            char chr = a.charAt(al-1);
            char chc = b.charAt(col);
            System.out.println("Insert "+chc+" at "+(row+1));
            col = col+1;
        }
    }
}
