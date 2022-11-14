package Codeforces;

import java.io.*;
import java.util.*;

public class GameWithString {
    static double getWinProb(String s, int letter){
        ArrayList<Integer> indices = new ArrayList<>();

        int n = s.length();
        for(int i=0;i<n;i++){
            if(s.charAt(i)-'a'==letter){
                indices.add(i);
            }
        }

        if(indices.size()<=1)
            return 1.0;

        for(int i=1;i<n-1;i++){
            int[] hash = new int[26];
            for(int j: indices){
                hash[s.charAt((i+j)%n)-'a']++;
            }

            boolean sure_success = true;
            for(int j: hash){
                if(j>1){
                    sure_success = false;
                    break;
                }
            }
            if(sure_success){
                return 1.0;
            }
        }

        double maxProb = 0.0;
        for(int i=1;i<=n-1;i++){
            int[] hash = new int[26];
            for(int j: indices){
                hash[s.charAt((i+j)%n)-'a']++;
            }

            double curprob = 0.0;
            for(int j=0;j<26;j++){
                if(hash[j]==1){
                    curprob += (1.0 / indices.size());
                }
            }

            maxProb = Math.max(maxProb , curprob);
        }

        return maxProb;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int n = s.length();

        int[] hash1 = new int[26];
        for(int i=0;i<n;i++){
            hash1[s.charAt(i)-'a']++;
        }

        double prob = 0;
        for(int i=0;i<26;i++){
            prob += getWinProb(s, i) * (((double) hash1[i]) / n);
        }

        System.out.println(prob);

        sc.close();
    }
}
