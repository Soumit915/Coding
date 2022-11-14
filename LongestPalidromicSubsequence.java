import java.io.*;
import java.util.Scanner;

public class LongestPalidromicSubsequence {
    public static boolean isValid(int n, int i)
    {
        return i >= 0 && i < n;
    }
    public static int find(int[][] dp, int i, int j)
    {
        if(!isValid(dp.length, i) || !isValid(dp.length, j))
            return 0;
        if(i>j)
            return 0;
        else return dp[i][j];
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the string: ");
        String s = sc.next();
        int n = s.length();

        int[][] dp = new int[n][n];
        for(int i=n-1;i>=0;i--)
        {
            for(int j=i;j<n;j++)
            {
                if(s.charAt(i)==s.charAt(j))
                {
                    if(i==j)
                        dp[i][j] = 1;
                    else dp[i][j] = Math.max(Math.max(find(dp, i+1, j-1)+2, find(dp, i, j-1)),
                                    find(dp, i+1, j));
                }
                else
                {
                    dp[i][j] = Math.max(Math.max(find(dp, i, j-1), find(dp, i+1, j-1)),
                            find(dp, i+1, j));
                }
            }
        }

        System.out.print("  ");
        for(int i=0;i<n;i++)
            System.out.print(s.charAt(i)+" ");
        System.out.println();
        for(int i=0;i<n;i++)
        {
            System.out.print(s.charAt(i)+" ");
            for(int j=0;j<n;j++)
                System.out.print(dp[i][j]+" ");
            System.out.println();
        }

        System.out.println(dp[0][n-1]);

        sc.close();
    }
}
