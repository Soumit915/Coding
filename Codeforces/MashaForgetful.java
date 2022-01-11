package Codeforces;

import java.io.*;
import java.util.*;

public class MashaForgetful {
    static class Triplet{
        int l, r, i;
        Triplet(int l, int r, int i){
            this.l = l+1;
            this.r = r+1;
            this.i = i+1;
        }
        public String toString(){
            return this.l+" "+this.r+" "+this.i;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            Map<String, Triplet> map = new HashMap<>();

            for(int i=0;i<n;i++){
                String s = sc.next();
                for(int j=1;j<s.length();j++){
                    String num = s.substring(j-1, j+1);
                    map.put(num, new Triplet(j-1, j, i));

                    if(j>1){
                        num = s.substring(j-2, j+1);
                        map.put(num, new Triplet(j-2, j, i));
                    }
                }
            }

            String s = sc.next();

            Triplet[] dp = new Triplet[m];
            dp[0] = null;
            if(m>1)
                dp[1] = map.getOrDefault(s.substring(0, 2), null);
            if(m>2)
                dp[2] = map.getOrDefault(s.substring(0, 3), null);
            if(m>3)
                dp[3] = (dp[1]==null)?null:map.getOrDefault(s.substring(2, 4), null);
            for(int i=4;i<m;i++){
                Triplet cur2 = map.getOrDefault(s.substring(i-1, i+1), null);
                Triplet cur3 = map.getOrDefault(s.substring(i-2, i+1), null);

                if(cur2!=null && dp[i-2]!=null)
                    dp[i] = cur2;
                if(cur3!=null && dp[i-3]!=null)
                    dp[i] = cur3;
            }

            boolean flag = true;
            StringBuilder localsb = new StringBuilder();
            if(dp[m-1]==null)
                flag = false;
            else{
                Stack<Triplet> tripletStack = new Stack<>();
                int i = m-1;
                while(i>0 && dp[i]!=null){
                    tripletStack.push(dp[i]);
                    i -= (dp[i].r-dp[i].l+1);
                }

                localsb.append(tripletStack.size()).append("\n");
                while (!tripletStack.isEmpty())
                    localsb.append(tripletStack.pop()).append("\n");
            }

            if(flag)
                sb.append(localsb);
            else sb.append("-1\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
