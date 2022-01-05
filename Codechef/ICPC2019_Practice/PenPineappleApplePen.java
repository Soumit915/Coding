package Codechef.ICPC2019_Practice;

import java.io.*;
import java.util.*;

public class PenPineappleApplePen {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            char[] s = sc.next().toCharArray();

            String[] p = new String[k];
            int lim = 1<<20;
            int[] bitmask = new int[lim];
            Arrays.fill(bitmask, 0, lim, -1);
            for(int i=0;i<k;i++) {
                p[i] = sc.next();
                int mask = 0;
                for (int j = 0; j < p[i].length(); j++) {
                    mask |= (1 << (p[i].charAt(j) - 'a'));
                }
                bitmask[mask] = i;
            }

            for(int i=lim-1;i>0;i--){
                if(bitmask[i]!=-1){
                    for(int j=0;j<20;j++){
                        if((i&(1<<j))>0){
                            bitmask[i^(1<<j)] = bitmask[i];
                        }
                    }
                }
            }

            int mask = 0;
            int[] ans = new int[n];
            int lastptr = -1;
            for(int i=0;i<n;i++){
                int curmask = mask | (1<<(s[i]-'a'));
                if(bitmask[curmask]==-1){
                    for(int j=lastptr+1;j<i;j++){
                        ans[j] = bitmask[mask];
                    }
                    lastptr = i-1;
                    curmask = 0;
                    i--;
                }
                mask = curmask;
            }
            for(int j=lastptr+1;j<n;j++){
                ans[j] = bitmask[mask];
            }

            for(int i: ans)
                sb.append(i+1).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
