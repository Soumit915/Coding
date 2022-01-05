package Leetcode;

import java.io.*;
import java.util.*;

public class BestTimetoBuyAndSellStocks {
    static int maxProfit(int k, int[] prices){
        int n = prices.length;

        if(n==0 || k==0)
            return 0;

        int[][] dp = new int[k+1][n];

        for(int i=1;i<=k;i++){
            int[] prefixmax = new int[n];
            prefixmax[0] = -1 * prices[0];
            for(int j=1;j<n;j++){
                prefixmax[j] = Math.max(prefixmax[j-1], dp[i-1][j-1] - prices[j]);
            }

            for(int j=1;j<n;j++){
                dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j-1], prices[j] + prefixmax[j-1]));
            }

            System.out.println(Arrays.toString(prefixmax));
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[k][n-1];
    }
    public static void main(String[] args) throws IOException {
        int k = 2;
        int[] prices = {};
        System.out.println(maxProfit(k, prices));
    }
}
