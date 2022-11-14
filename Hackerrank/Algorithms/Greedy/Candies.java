package Hackerrank.Algorithms.Greedy;

import java.util.*;

public class Candies
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        int[] left = new int[n];
        left[0] = 1;
        for(int i=1;i<n;i++)
        {
            if(arr[i]>arr[i-1])
            {
                left[i] = left[i-1]+1;
            }
            else left[i] = 1;
        }

        for(int i=n-2;i>=0;i--)
        {
            if(arr[i]>arr[i+1] && left[i]<=left[i+1])
                left[i] = left[i+1]+1;
        }

        long sum=0;
        for(int i=0;i<n;i++)
        {
            sum += left[i];
        }
        System.out.println(sum);
    }
}