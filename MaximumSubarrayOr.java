import java.util.*;

public class MaximumSubarrayOr {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
            }

            int[] dp = new int[n];
            int[] count = new int[n];
            dp[0] = arr[0];
            count[0] = 1;
            for(int i=1;i<n;i++)
            {
                dp[i] = Math.max(dp[i-1] | arr[i], arr[i]);
                if(dp[i]==arr[i])
                {
                    count[i] = 1;
                }
                else
                {
                    count[i] = 1+count[i-1];
                }
            }

            System.out.println(count[n-1]);
        }
    }
}
