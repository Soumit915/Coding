package Codeforces.Round780Div3;

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            String s = sc.next();
            int n = s.length();

            int[] previous = new int[n];
            int[] hash = new int[26];
            Arrays.fill(hash, -1);
            for(int i=0;i<n;i++){
                int ch = s.charAt(i) - 'a';
                previous[i] = hash[ch];
                hash[ch] = i;
            }

            int[] dp = new int[n];
            for(int i=0;i<n;i++){
                if(previous[i] == -1){
                    if(i == 0){
                        dp[i] = 1;
                    }
                    else{
                        dp[i] = dp[i-1] + 1;
                    }
                }
                else{
                    dp[i] = dp[i-1] + 1;

                    if(previous[i]-1 < 0){
                        dp[i] = Math.min(dp[i], i - previous[i] - 1);
                    }
                    else{
                        dp[i] = Math.min(dp[i], dp[previous[i] - 1] + (i - previous[i] - 1));
                    }
                }
            }

            sb.append(dp[n-1]).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
