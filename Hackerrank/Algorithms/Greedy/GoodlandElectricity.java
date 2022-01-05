package Hackerrank.Algorithms.Greedy;

import java.util.*;

public class GoodlandElectricity
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        int[] hasElectricity = new int[n];
        int count=0;
        for(int i=0;i<n;i++)
        {
            if(hasElectricity[i]==1)
                continue;
            boolean flag = false;
            int ind = -1;
            //System.out.println()
            for(int j=Math.min(i+(k-1), n-1);j>=Math.max(0, i-(k-1));j--)
            {
                if(arr[j]==1)
                {
                    ind = j;
                    count++;
                    flag = true;
                    break;
                }
            }
            if(!flag)
            {
                System.out.println(-1);
                System.exit(0);
            }
            for(int j=Math.min(ind+(k-1), n-1);j>=i;j--)
            {
                hasElectricity[j] = 1;
                if(j==i)
                    break;
            }
        }

        System.out.println(count);
    }
}