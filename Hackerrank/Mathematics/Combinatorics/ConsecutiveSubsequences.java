package Hackerrank.Mathematics.Combinatorics;

import java.util.*;

public class ConsecutiveSubsequences {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();
            long[] arr = new long[n];

            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextLong();
            }

            long[] prefix = new long[n];
            prefix[0] = arr[0];
            for(int i=1;i<n;i++)
            {
                prefix[i] = prefix[i-1]+arr[i];
            }

            int[] cum = new int[k];
            for(int i=0;i<n;i++)
            {
                cum[(int)(prefix[i]%k)]++;
            }

            long ans = 0;
            for(int i=0;i<k;i++)
            {
                if(i==0)
                {
                    ans += (((long) cum[i]+1)*cum[i])/2;
                }
                else
                {
                    ans += (((long) cum[i]-1)*cum[i])/2;
                }
            }

            System.out.println(ans);
        }

    }
}