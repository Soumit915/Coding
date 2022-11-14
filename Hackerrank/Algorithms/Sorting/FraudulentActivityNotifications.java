package Hackerrank.Algorithms.Sorting;

import java.util.*;

public class FraudulentActivityNotifications
{
    public static int findkthlargest(int[] hash, int k)
    {
        int n = hash.length;
        int count=0;
        for(int i=0;i<n;i++)
        {
            count += hash[i];
            if(count>=k)
            {
                return i;
            }
        }
        return -1;
    }
    public static int findmedian(int[] hash, int d)
    {
        if(d%2==0)
        {
            return (findkthlargest(hash, (d/2))+findkthlargest(hash, (d/2+1)));
        }
        else
        {
            return findkthlargest(hash, (d+1)/2)*2;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();

        int[] expenditure = new int[n];
        for(int i=0;i<n;i++)
        {
            expenditure[i] = sc.nextInt();
        }

        int[] hash = new int[202];
        for(int i=0;i<d;i++)
        {
            hash[expenditure[i]]++;
        }
        int count=0;
        for(int i=d;i<n;i++)
        {
            if(i!=d)
            {
                hash[expenditure[i-1]]++;
                hash[expenditure[i-1-d]]--;
            }
            int median = findmedian(hash, d);

            if(expenditure[i]>=median)
            {
                count++;
            }
        }

        System.out.println(count);
    }
}