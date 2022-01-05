package Codeforces.EducationalRound92;

import java.util.*;

public class ArrayWalk {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int z = sc.nextInt();

            long[] arr = new long[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextLong();
            }

            long[] sum = new long[n];
            sum[0] = arr[0];
            for(int i=1;i<n;i++)
                sum[i] = sum[i-1]+arr[i];

            long[] dp = new long[n];
            dp[0] = arr[0];
            for(int i=1;i<=k;i++)
            {
                int movesleft = k-i;
                long valsum = sum[i];

                if(movesleft>2*z)
                {
                    valsum += (arr[i-1] + arr[i])* (z);
                    valsum += (sum[movesleft-2*z+i] - sum[i]);
                }
                else
                {
                    if(movesleft%2==0)
                        valsum += (arr[i-1]+arr[i])*(movesleft/2);
                    else valsum += (arr[i-1]+arr[i])*(movesleft/2) + arr[i-1];
                }
                dp[i] = valsum;
            }

            long max = 0;
            for(int i=0;i<n;i++)
                max = Math.max(dp[i], max);

            System.out.println(max);
        }
    }
}
