package Codeforces;

import java.io.*;
import java.util.*;

public class PolycarpStringTransformation {

    static boolean isOk(String s, String order, String t){
        StringBuilder sb = new StringBuilder();

        sb.append(s);

        boolean[] isVisited = new boolean[26];
        for(int i=0;i<order.length();i++){
            isVisited[order.charAt(i)-'a'] = true;

            for(int j=0;j<s.length();j++){
                int ch = s.charAt(j) - 'a';
                if(!isVisited[ch]){
                    sb.append(s.charAt(j));
                }
            }
        }

        return sb.toString().equals(t);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            String s = sc.next();
            int n = s.length();

            int[] hash = new int[26];
            Arrays.fill(hash, -1);

            int[] count = new int[26];

            int j = 0;
            int distinct = 0;

            for(int i=n-1;i>=0;i--){
                int ch = s.charAt(i) - 'a';

                if(hash[ch] == -1){
                    hash[ch] = j;
                    distinct++;
                    j++;
                }

                count[ch]++;
            }

            int[] order = new int[distinct];
            for(int i=0;i<26;i++){
                if(hash[i] != -1){
                    hash[i] = distinct - hash[i];
                    order[hash[i]-1] = i;
                }
            }

            boolean flag = true;
            int len = 0;
            for(int i=0;i<26;i++){
                if(hash[i] != -1){
                    if(count[i] % hash[i] != 0){
                        flag = false;
                        break;
                    }

                    count[i] /= hash[i];
                    len += count[i];
                }
            }

            if(!flag){
                sb.append("-1\n");
                continue;
            }

            StringBuilder s_org = new StringBuilder();
            for(int i=0;i<len;i++){
                s_org.append(s.charAt(i));
            }

            StringBuilder s_order = new StringBuilder();
            for(int i=0;i<distinct;i++){
                char ch = (char) (order[i] + 'a');
                s_order.append(ch);
            }

            if(isOk(s_org.toString(), s_order.toString(), s)){
                sb.append(s_org).append(" ").append(s_order).append("\n");
            }
            else{
                sb.append("-1\n");
            }
        }

        System.out.println(sb);
    }
}
