package Codeforces.Expert_3;

import java.util.*;
import java.io.*;

public class Traps{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(tc-->0){
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int k = Integer.parseInt(line[1]);

            line = br.readLine().split(" ");
            long[] a = new long[n];
            for(int i=0;i<n;i++){
                a[i] = Integer.parseInt(line[i]);
            }

            long[] dp = new long[n];
            long sum = 0;
            for(int i=0;i<n;i++){
                dp[i] = (n - i - 1) - a[i];
                sum += a[i];
            }

            //System.out.println(Arrays.toString(dp));

            int c = 0;
            Arrays.sort(dp);
            for(int i=0;i<k;i++){
                //if(dp[i] <= 0){
                sum += dp[i];
                c++;
                //}
            }

            for(int i=0;i<c;i++){
                sum -= (i);
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb);
    }
}