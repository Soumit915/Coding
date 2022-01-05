package Codechef;

import java.util.*;

public class FriendOrGirlfriend {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String s = sc.next();
            char ch = sc.next().charAt(0);

            long[] dp = new long[n];
            int last = -1;
            for(int i=0;i<n;i++)
            {
                if(s.charAt(i)==ch){
                    last = i;
                    dp[i] = i+1;
                }
                else{
                    dp[i] = last+1;
                }
            }

            long ans = 0;
            for(long i: dp)
                ans += i;

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

}
