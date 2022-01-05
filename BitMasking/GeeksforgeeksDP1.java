package BitMasking;

import java.util.*;

public class GeeksforgeeksDP1 {
    static int[][] dp;
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] hash = new int[101][n];
        for(int i=0;i<n;i++)
        {
            int k = sc.nextInt();
            for(int j=0;j<k;j++)
            {
                int val = sc.nextInt();
                hash[val][i]=1;
            }
        }

        int totbits = (int) Math.pow(2, n);
        dp = new int[102][totbits];
        for(int i=1;i<102;i++)
        {
            dp[i][0] = 1;
        }
        for(int i=100;i>0;i--)
        {
            for(int j=0;j<totbits;j++)
            {
                int count = 0;
                for(int k=0;k<n;k++)
                {
                    if((j&(1<<k))!=0 && hash[i][n-k-1]==1)
                    {
                        count += dp[i+1][j-(1<<k)];
                    }
                }
                dp[i][j] = dp[i+1][j]+count;
            }
        }

        System.out.println("The total number of ways of persons wearing caps are : "+dp[1][totbits-1]);
    }
}
/*
3
3
5 100 1
1
2
2
5 100
*/