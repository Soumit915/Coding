package CUrBrain_Questions;

import java.io.*;
import java.util.*;

public class SplitArray {

    static int getMaxProfit(int[][][][] dp, int[] arr, int i, int p, int q, int r){
        if(i < 0)
            return 0;

        if(dp[i][p][q][r] != Integer.MIN_VALUE){
            return dp[i][p][q][r];
        }

        int max = Integer.MIN_VALUE;
        if(p != 0){
            max = Math.max(max, getMaxProfit(dp, arr, i-1, p-1, q, r) + arr[i]);
        }

        if(q != 0 && i >= 1){
            max = Math.max(max, getMaxProfit(dp, arr, i-2, p, q-1, r) + arr[i] + arr[i-1]);
        }

        if(r != 0 && i >= 2){
            max = Math.max(max, getMaxProfit(dp, arr, i-3, p, q, r-1) + arr[i] + arr[i-1] + arr[i-2]);
        }

        max = Math.max(max, getMaxProfit(dp, arr, i-1, p, q, r));

        dp[i][p][q][r] = max;

        return max;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        int p = sc.nextInt();
        int q = sc.nextInt();
        int r = sc.nextInt();

        int[][][][] dp = new int[n][p+1][q+1][r+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<=p;j++){
                for(int k=0;k<=q;k++){
                    Arrays.fill(dp[i][j][k], Integer.MIN_VALUE);
                }
            }
        }

        System.out.println(getMaxProfit(dp, arr, n-1, p, q, r));
    }

}
