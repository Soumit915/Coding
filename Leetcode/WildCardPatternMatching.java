package Leetcode;

import java.io.*;
import java.util.*;

public class WildCardPatternMatching {
    public static boolean isMatch(String s, String p) {
        int ls = s.length();
        int lp = p.length();
        boolean[] dp = new boolean[ls+1];
        for(int i=0;i<ls;i++){
            dp[i] = false;
        }
        for(int i=0;i<lp;i++){
            dp[ls] = false;
        }
        dp[ls] = true;

        boolean[] suffixboolean = new boolean[ls+1];
        suffixboolean[ls] = dp[ls];
        for(int j=ls-1;j>=0;j--)
            suffixboolean[j] = suffixboolean[j+1] | dp[j];
        for(int i=lp-1;i>=0;i--){
            boolean[] dpcur = new boolean[ls+1];
            if(p.charAt(i)=='*'){
                dpcur[ls] = dp[ls];
            }
            for(int j=ls-1;j>=0;j--){
                if(p.charAt(i)=='*'){
                    dpcur[j] = suffixboolean[j];
                }
                else if(p.charAt(i)=='?'){
                    dpcur[j] = dp[j+1];
                }
                else{
                    if(p.charAt(i)==s.charAt(j)){
                        dpcur[j] = dp[j+1];
                    }
                    else{
                        dpcur[j] = false;
                    }
                }
            }

            suffixboolean[ls] = dpcur[ls];
            for(int j=ls-1;j>=0;j--)
                suffixboolean[j] = suffixboolean[j+1] | dpcur[j];

            dp = dpcur;
        }

        return dp[0];
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        Scanner sc = new Scanner(new File("Input.txt"));

        int t = sc.nextInt();
        for(int i=0;i<t;i++){
            System.out.println(isMatch(sc.next(), sc.next()));
        }

        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000.0);

        sc.close();
    }
}
