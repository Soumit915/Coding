package Codeforces.RateUp_Contest_1;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            String s = sc.next();
            int sl = s.length();

            String t = sc.next();
            int tl = t.length();

            int i = sl-1;
            int j = tl-1;

            while(i>=0 && j>=0){
                if(s.charAt(i) == t.charAt(j)){
                    i--;
                    j--;
                }
                else{
                    i-=2;
                }
            }

            if(j<0){
                sb.append("Yes\n");
            }
            else{
                sb.append("No\n");
            }
        }

        System.out.println(sb);
    }
}
