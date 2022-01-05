package Hackerrank.Algorithms.Searching;

import java.util.*;

public class Pairs
{
    public static boolean search(long[] arr, int ll, int ul, long val)
    {
        if(ll==ul)
        {
            return val==arr[ll];
        }
        else if(ll<ul)
        {
            int mid = (ll+ul)/2;
            if(arr[mid]<val)
            {
                return search(arr, mid+1, ul, val);
            }
            else if(arr[mid]>val)
            {
                return search(arr, ll, mid-1, val);
            }
            else return val==arr[mid];
        }
        else return false;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();

        long[] arr = new long[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextLong();
        }

        Arrays.sort(arr);

        int count=0;
        for(int i=0;i<n;i++)
        {
            long val = arr[i]-k;
            if(val<=0)
                continue;
            if(search(arr, 0, i-1, val))
            {
                count++;
            }
        }

        System.out.println(count);
    }
}