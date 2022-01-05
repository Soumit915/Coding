package Leetcode;

import java.io.*;
import java.util.*;

public class WordBreak {
    static boolean wordBreak(String s, List<String> wordDict) {
        int l = s.length();
        boolean[] dp = new boolean[l+1];
        dp[0] = true;

        for(int i=1;i<=l;i++){
            boolean flag = false;
            for (String cur : wordDict) {
                int curl = cur.length();
                if (i - curl >= 0 && dp[i - curl]) {
                    flag |= (s.substring(i - curl, i).equals(cur));
                }
            }
            dp[i] = flag;
        }
        return dp[l];
    }
    static String extract(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='a' && s.charAt(i)<='z')
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

        String l = sc.next();
        List<String> wordDict = new ArrayList<>();
        String[] dicts = l.split(",");
        for(String str: dicts){
            wordDict.add(extract(str));
        }

        System.out.println(wordBreak(s, wordDict));

        System.out.println(wordDict.size());

        sc.close();
    }
}
