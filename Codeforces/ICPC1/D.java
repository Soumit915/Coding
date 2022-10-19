package Codeforces.ICPC1;

import java.util.*;
import java.io.*;

public class D {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            int k = sc.nextInt();

            String s = sc.next();

            if(k >= 25){
                sb.append("a".repeat(Math.max(0, n))).append("\n");
                continue;
            }

            int max = 0;
            int l = 0, r = 0;
            char ch = 'a';
            for(int i=0;i<n;i++){
                if(s.charAt(i)-'a' > k){
                    int left = k - max;
                    l = s.charAt(i) - 'a' - left;
                    r = s.charAt(i) - 'a';
                    ch = (char) (l + 'a');
                    break;
                }
                else{
                    max = Math.max(max, s.charAt(i) - 'a');
                }
            }

            for(int i=0;i<n;i++){
                if(s.charAt(i)-'a' <= max){
                    sb.append("a");
                }
                else{
                    if(s.charAt(i)-'a' >= l && s.charAt(i)-'a' <= r){
                        sb.append(ch);
                    }
                    else{
                        sb.append(s.charAt(i));
                    }
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
