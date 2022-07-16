package Codeforces;

import java.io.*;
import java.util.*;

public class TokitsukazeAndGoodString_Hard {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            int n = sc.nextInt();
            String s = sc.next();

            int ops = 0;
            char last = '2';
            int c = 0;
            for(int i=0;i<n;i+=2){
                if(s.charAt(i) != s.charAt(i+1)){
                    ops++;
                }
                else{
                    if(s.charAt(i) !=  last){
                        c++;
                    }
                    last = s.charAt(i);
                }
            }

            sb.append(ops).append(" ").append(Math.max(c, 1)).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
