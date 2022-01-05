package Hackerrank.Algorithms.BitManipulation;

import java.util.*;
public class SansaAndXor
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];

            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
            }

            int ans = 0;
            for(int i=0;i<n;i++)
            {
                long next = n-i;
                long prev = i+1;
                if((next*prev)%2!=0)
                    ans = ans^arr[i];
            }
            System.out.println(ans);
        }
    }
}
