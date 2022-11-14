package Codeforces;

import java.util.*;
import java.io.*;

public class Equalizing2Strings {

    static int sort(char[] s){
        int n = s.length;

        int cost = 0;
        for(int i=0;i<n;i++){

            int min = 50;
            int index = -1;

            for(int j=i;j<n;j++){
                int c = s[j]-'a';

                if(c < min){
                    min = c;
                    index = j;
                }
            }

            cost += (index - i);
            char temp = s[index];
            System.arraycopy(s, i, s, i + 1, index - i);

            s[i] = temp;
        }

        return cost;
    }

    static boolean bruteforce(char[] s, char[] t){

        int scost = sort(s);
        int tcost = sort(t);

        return scost % 2 == tcost % 2;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            String s = sc.next();
            String t = sc.next();

            int[] h1 = new int[26];
            int[] h2 = new int[26];

            for(int i=0;i<n;i++){
                h1[s.charAt(i)-'a']++;
                h2[t.charAt(i)-'a']++;
            }

            boolean flag = true;
            for(int i=0;i<26;i++){
                if(h1[i] != h2[i]){
                    flag = false;
                    break;
                }
            }

            if(!flag){
                sb.append("NO\n");
                continue;
            }

            for(int i=0;i<26;i++){
                if(h1[i] > 1){
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                sb.append("YES\n");
                continue;
            }

            flag = bruteforce(s.toCharArray(), t.toCharArray());

            if(flag){
                sb.append("YES\n");
            }
            else{
                sb.append("NO\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
