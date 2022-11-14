package Codechef;

import java.util.*;

public class ChefAndWeddingArrangements {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
            }

            int[][] dp = new int[n][2];
            dp[0][0] = k;
            dp[0][1] = k;
            int[][] hasharr = new int[2][105];
            hasharr[0][arr[0]] = 1;
            hasharr[1][arr[0]] = 1;
            for(int i=1;i<n;i++)
            {
                int val1=-1, val2=-1;
                if(hasharr[0][arr[i]]==1)
                {
                    val1 = dp[i-1][0]+2;
                }
                else if(hasharr[0][arr[i]]>1)
                {
                    val1 = dp[i-1][0]+1;
                }
                hasharr[0][arr[i]]++;

                if(hasharr[1][arr[i]]==1)
                {
                    val2 = dp[i-1][1]+2;
                }
                else if(hasharr[1][arr[i]]>1)
                {
                    val2 = dp[i-1][1]+1;
                }
                hasharr[1][arr[i]]++;

                if(val1<val2)
                {
                    dp[i][0] = val1;
                }
                else
                {
                    dp[i][1] = val2;
                    System.arraycopy(hasharr[1], 0, hasharr[0], 0, hasharr[0].length);
                }

                dp[1][i] = dp[0][i-1]+k;
                hasharr[1][arr[i]] = 1;
            }

            System.out.println(Math.min(dp[0][n-1], dp[1][n-1]));
        }
    }
}
