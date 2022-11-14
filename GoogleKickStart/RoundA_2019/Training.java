package GoogleKickStart.RoundA_2019;

import java.util.*;

public class Training {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int testi=1;testi<=t;testi++)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();

            long[] arr = new long[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextLong();
            }
            Arrays.sort(arr);

            long[] dp = new long[n];
            long[] prefixsum = new long[n];
            for(int i=1;i<n;i++)
            {
                dp[i] = arr[i]-arr[i-1];
                prefixsum[i] = dp[i]+prefixsum[i-1];
            }

            long sum = 0;
            for(int i=1;i<k;i++)
            {
                sum += dp[i]*(i);
            }

            long min = sum;
            for(int i=k;i<n;i++)
            {
                sum = sum+((k-1)*dp[i])-(prefixsum[i-1]-prefixsum[i-k]);
                min = Math.min(min, sum);
            }

            System.out.println("Case #"+testi+": "+min);
        }
    }
}
