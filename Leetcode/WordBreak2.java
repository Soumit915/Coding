package Leetcode;

import java.io.*;
import java.util.*;

public class WordBreak2 {
    static List<String> arlist;
    static void dfs(boolean[][] dp, List<String> wordDict, int row, int col,
                    List<String> list, int index){
        int n = wordDict.size();

        if(col==0){
            StringBuilder sb = new StringBuilder();
            for(int i=index-1;i>=0;i--){
                sb.append(list.get(i));
                sb.append(" ");
            }
            arlist.add(sb.toString().trim());
            return;
        }

        for(int i=n-1;i>=0;i--){
            if(dp[i][col]){
                list.set(index, wordDict.get(i));
                dfs(dp, wordDict, row-1, col-wordDict.get(i).length(), list, index+1);
                list.set(index, "");
            }
        }
    }
    static List<String> wordBreak(String s, List<String> wordDict){
        arlist = new ArrayList<>();
        int l = s.length();
        int n = wordDict.size();
        boolean[][] dp = new boolean[n][l+1];
        for(int i=0;i<n;i++){
            dp[i][0] = true;
        }

        boolean[] overall_dp = new boolean[l+1];
        overall_dp[0] = true;
        for(int i=1;i<=l;i++){
            for(int j=0;j<n;j++){
                String cur = wordDict.get(j);
                int curl = cur.length();
                if(i-curl>=0 && overall_dp[i-curl]){
                    dp[j][i] = s.substring(i-curl, i).equals(cur);
                }
            }

            boolean flag = false;
            for(int j=0;j<n;j++){
                flag |= dp[j][i];
            }
            overall_dp[i] = flag;
        }

        if(overall_dp[l]){
            List<String> list = new ArrayList<>();
            for(int i=0;i<l;i++)
                list.add("");
            dfs(dp, wordDict, n-1, l, list, 0);
        }

        return arlist;
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
