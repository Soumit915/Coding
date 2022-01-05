package Hackerrank.Mathematics.Algebra;

import java.io.*;
import java.util.*;

public class ManasaAndSubsequences {
    static long mod = (long) 1e9+7;
    static long pow(long b){
        long a = 2;
        long p = 1;
        while (b>0){
            if(b%2==1){
                p = (p*a)%mod;
            }
            a = (a*a)%mod;
            b /= 2;
        }
        return p;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int n = s.length();

        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = s.charAt(i)-48;
        }

        long[] dp = new long[n];
        dp[0] = arr[0];
        for(int i=1;i<n;i++){
            dp[i] = ((dp[i-1]*10)%mod+(pow(i)*arr[i])%mod+dp[i-1])%mod;
        }

        System.out.println(dp[n-1]);
    }
}
