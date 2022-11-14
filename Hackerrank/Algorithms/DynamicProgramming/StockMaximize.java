package Hackerrank.Algorithms.DynamicProgramming;

import java.util.*;

public class StockMaximize
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();
            long[] stockPrice = new long[n];

            for(int i=0;i<n;i++)
            {
                stockPrice[i] = sc.nextLong();
            }

            long[] suffix = new long[n];
            long cm = stockPrice[n-1];
            for(int i=n-1;i>=0;i--)
            {
                if(stockPrice[i]>cm)
                {
                    cm = stockPrice[i];
                }
                suffix[i] = cm;
            }

            long ans = 0;
            for(int i=0;i<n;i++)
            {
                ans += suffix[i]-stockPrice[i];
            }

            System.out.println(ans);
        }
    }
}