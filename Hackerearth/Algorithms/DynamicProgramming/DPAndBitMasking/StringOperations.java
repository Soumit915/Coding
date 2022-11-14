package Hackerearth.Algorithms.DynamicProgramming.DPAndBitMasking;

import java.io.*;
import java.util.*;

public class StringOperations {
    static boolean isValid(int[][] prefix, int index, int home, int[] hash){
        int[] diff = new int[10];
        for(int i=0;i<prefix.length;i++){
            if(index==0){
                diff[i] = prefix[i][home];
            }
            else{
                diff[i] = prefix[i][home] - prefix[i][index-1];
            }
        }

        for(int i=0;i<hash.length;i++){
            if(hash[i]>0){
                if(diff[i]<=0)
                    return false;
            }
        }
        return true;
    }
    static int binarySearch(int[][] prefix, int[] hash, int i, int ll, int ul){
        if(ll<=ul){
            int mid = (ll+ul)/2;
            if(isValid(prefix, mid, i, hash)){
                if(mid==ul){
                    return mid;
                }
                else if(isValid(prefix, mid+1, i, hash)){
                    return binarySearch(prefix, hash, i, mid+1, ul);
                }
                else return mid;
            }
            else return binarySearch(prefix, hash, i, ll, mid-1);
        }
        else return -1;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int l = s.length();
        int n = sc.nextInt();
        int[] set = new int[10];
        for(int i=0;i<n;i++){
            set[sc.nextInt()] = 1;
        }

        int[][] prefix = new int[10][l];
        prefix[s.charAt(0)-'0'][0] = 1;
        for(int i=1;i<l;i++){
            for(int j=0;j<10;j++){
                if(s.charAt(i)-'0'==j)
                    prefix[j][i] = prefix[j][i-1] + 1;
                else prefix[j][i] = prefix[j][i-1];
            }
        }

        long[] dp = new long[l];
        for(int i=0;i<l;i++){
            long pos = binarySearch(prefix, set, i, 0, i)+1;
            dp[i] = pos;
        }

        long sum = 0;
        for(long i: dp)
            sum += i;
        System.out.println(sum);
    }
}
