package Codechef;

import java.util.*;

public class BinarySubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String s = sc.next();

            int[] dp0 = new int[n];
            int[] dp1 = new int[n];
            for(int i=0;i<n;i++){
                if(s.charAt(i)=='0')
                    dp0[i] = 1;
                else dp1[i] = 1;
            }

            for(int i=1;i<n;i++){
                dp0[i] += dp0[i-1];
                dp1[i] += dp1[i-1];
            }

            int min = 10000000;
            for(int i=0;i<n;i++){
                int v = dp0[n-1]-dp0[i];
                if(i!=0)
                    v += dp1[i-1];
                min = Math.min(v, min);
            }

            if(min==10000000)
                min = 0;

            sb.append(min).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
