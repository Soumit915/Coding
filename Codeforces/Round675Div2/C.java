package Codeforces.Round675Div2;

import java.io.*;
import java.util.*;

public class C {
    static long mod = (long) 1e9+7;
    static long getSubArraysCount(long n){
        n = (n * (n+1))/2;
        n = n%mod;
        return n;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

        int n = s.length();
        long[] number = new long[n];
        for(int i=0;i<n;i++){
            number[i] = s.charAt(i) - '0';
        }

        long[] dp = new long[n];
        dp[0] = 0;
        long sum = 0;
        for(int i=1;i<n;i++){
            long cur = (number[i] * getSubArraysCount(i))%mod;
            long dplast = (dp[i-1] - (number[i-1]*getSubArraysCount(i-1))%mod - sum + mod)%mod;
            dp[i] = (dplast + cur + (number[i-1]*(n - i))%mod)%mod;

            sum = (sum + number[i-1])%mod;
        }

        long ans = 0;
        for(int i=0;i<n;i++){
            ans = ((ans * 10)%mod + dp[i])%mod;
        }

        System.out.println(ans);
    }
}
