package Hackerrank.DataStructures.Advanced;

import java.util.*;

public class KindergartenAdventures
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            int val = sc.nextInt();
            int start = (i+1)%(n);
            int end = ((i-val)+(n))%(n);

            if(start == ((end+1)%n))
            {
                arr[0] += 1;
            }
            else if(start>end)
            {
                arr[start] += 1;
                arr[0] += 1;
                if(end<n-1)
                    arr[end+1] -= 1;
            }
            else
            {
                arr[start] += 1;
                if(end<n-1)
                    arr[end+1] -= 1;
            }
        }

        for(int i=1;i<n;i++)
        {
            arr[i] += arr[i-1];
        }

        int max = Integer.MIN_VALUE;
        int ind = -1;
        for(int i=0;i<n;i++)
        {
            if(arr[i]>max)
            {
                max = arr[i];
                ind = i;
            }
        }
        System.out.println(ind+1);
    }
}