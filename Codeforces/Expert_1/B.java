package Codeforces.Expert_1;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            int n = sc.nextInt();

            String[] ops = new String[2*n+1];
            for(int i=0;i<2*n+1;i++){
                ops[i] = sc.next();
            }

            int[] hash = new int[26];
            for(int i=0;i<2*n + 1;i++){
                for(int j=0;j<ops[i].length();j++){
                    hash[ops[i].charAt(j)-'a']++;
                }
            }

            String ans = "";
            for(int i=0;i<26;i++){
                if(hash[i] % 2 == 1){
                    char ch = (char) (i + 'a');
                    ans = ch+"";
                    break;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
