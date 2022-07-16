package Codeforces.Round807Div2;

import java.util.*;

public class C{

    static char getKth(String s, long[][] range, long k){
        int n = s.length();

        if(k < n){
            return s.charAt(((int) k));
        }

        int index = 0;

        k -= n;
        while(true){
            long len = range[index][1] - range[index][0] + 1;
            
            if(k < len){
                return getKth(s, range, range[index][0] + k);
            }

            k -= len;
            index++;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            sc.nextInt();
            int c = sc.nextInt();
            int q = sc.nextInt();

            String s = sc.next();

            long[][] range = new long[c][2];
            for(int i=0;i<c;i++){
                range[i][0] = sc.nextLong() - 1;
                range[i][1] = sc.nextLong() - 1;
            }

            while(q-->0){
                long k = sc.nextLong() - 1;

                char ch = getKth(s, range, k);
                sb.append(ch).append("\n");
            }
        }

        System.out.println(sb);
    }
}