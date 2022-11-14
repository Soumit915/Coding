package Hackerrank.Algorithms.DynamicProgramming;

import java.util.*;
public class LongestIncreasingSubsequence
{
    public static int binsearch(int[] arr, int val, int ll, int ul)
    {
        int mid = (ll+ul)/2;
        if(arr[mid]==val)
            return mid;
        else if(arr[mid]>val)
        {
            if(mid==0)
            {
                arr[0] = val;
                return 0;
            }
            else if(arr[mid-1]<val)
            {
                arr[mid] = val;
                return mid;
            }
            else return binsearch(arr, val, ll, mid-1);
        }
        else
        {
            if(mid==ul)
            {
                arr[mid+1] = val;
                return mid+1;
            }
            else return binsearch(arr, val, mid+1, ul);
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        int[] dp = new int[n];
        int[] count = new int[n];
        int k = 0;
        dp[k] = arr[0];
        count[0] = 1;
        k++;
        for(int i=1;i<n;i++)
        {
            if(arr[i]>dp[k-1])
            {
                dp[k] = arr[i];
                count[i] = k+1;
                k++;
            }
            else if(arr[i]==dp[k])
            {
                count[i] = k;
            }
            else
            {
                int ind = binsearch(dp, arr[i], 0, k-1);
                count[i] = ind+1;
            }
        }

        int max = -1;
        for(int i=0;i<n;i++)
        {
            max = Math.max(max, count[i]);
        }

        System.out.println(max);
    }
}
