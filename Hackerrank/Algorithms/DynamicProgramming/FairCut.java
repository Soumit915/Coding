package Hackerrank.Algorithms.DynamicProgramming;

import java.util.*;

public class FairCut
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        k = Math.min(k, n-k);

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int left = n-2*k;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<k;i++)
        {
            sb.append("01");
        }

        for(int i=(left/2)+1;i<=left;i++)
        {
            sb.append("0");
        }

        StringBuilder s = new StringBuilder();
        for(int i=0;i<(left/2);i++)
        {
            s.append("0");
        }
        s.append(sb.toString());

        ArrayList<Integer> set0 = new ArrayList<>();
        ArrayList<Integer> set1 = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            if(s.charAt(i)=='0')
                set0.add(i);
            else set1.add(i);
        }

        long sum = 0;
        for(int i: set0)
        {
            for(int j: set1)
            {
                sum += Math.abs(arr[i]-arr[j]);
            }
        }

        System.out.println(sum);
    }
}