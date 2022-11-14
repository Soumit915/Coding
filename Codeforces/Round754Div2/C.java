package Codeforces.Round754Div2;

import java.io.*;
import java.util.*;

public class C {
    static boolean contains(String s, String t){
        int ns = s.length();
        int nt = t.length();
        if(ns==nt)
            return s.equals(t);

        for(int i=0;i<=ns-nt;i++){
            String sub = s.substring(i, i+nt);
            if(sub.equals(t))
                return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            sc.nextInt();
            String s = sc.next();

            int ans = -1;
            if(contains(s, "aa")){
                ans = 2;
            }
            else if(contains(s, "aba") || contains(s, "aca")){
                ans = 3;
            }
            else if(contains(s, "abca") || contains(s, "acba")){
                ans = 4;
            }
            else if(contains(s, "abbacca") || contains(s, "accabba"))
                ans = 7;

            sb.append(ans).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
