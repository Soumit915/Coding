package Hackerrank.Algorithms.DynamicProgramming;

import java.util.*;

public class Knapsack {
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

            boolean[] dp1 = new boolean[k+1];
            dp1[0] = true;
            for(int i=0;i<n;i++)
            {
                boolean[] dp2 = new boolean[k+1];
                for(int j=0;j<=k;j++)
                {
                    if(j-arr[i]<0)
                        dp2[j] = dp1[j];
                    else
                    {
                        dp2[j] = dp1[j] | dp2[j-arr[i]];
                    }
                }
                dp1 = dp2;
            }

            for(int i=k;i>=0;i--)
            {
                if(dp1[i])
                {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}
