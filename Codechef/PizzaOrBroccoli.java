package Codechef;

import java.io.*;
import java.util.*;

public class PizzaOrBroccoli {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();

            String s = sc.next();

            int[] prefix = new int[n];
            prefix[0] = s.charAt(0) - '0';
            for(int i=1;i<n;i++){
                if(s.charAt(i) == '1'){
                    prefix[i] = prefix[i-1] + 1;
                }
                else{
                    prefix[i] = 0;
                }
            }

            int[] suffix = new int[n];
            suffix[n-1] = s.charAt(n-1) - '0';
            for(int i=n-2;i>=0;i--){
                if(s.charAt(i) == '1'){
                    suffix[i] = suffix[i+1] + 1;
                }
                else{
                    suffix[i] = 0;
                }
            }

            int max = 0;
            for(int i=0;i+k<=n;i++){
                int cur = k;
                if(i!=0)
                    cur += prefix[i-1];
                if(i+k<n)
                    cur += suffix[i+k];

                max = Math.max(max, cur);
            }

            sb.append(max).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
