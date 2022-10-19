package Codeforces;

import java.util.*;
import java.io.*;

public class WorldIsJustAProgrammingTask_Easy {

    static void swap(char[] s, int i, int j){
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
    }

    static int getBeauty(char[] s){
        int n = s.length;

        int[] dp = new int[n];
        int v = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(s[i] == '('){
                v++;
            }
            else{
                v--;
            }

            dp[i] = v;
            min = Math.min(min, v);
        }

        if(dp[n-1] != 0)
            return 0;

        int c = 0;
        for(int i: dp){
            if(i == min){
                c++;
            }
        }

        return c;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        char[] s = sc.next().toCharArray();

        List<Integer> open = new ArrayList<>();
        List<Integer> closed = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(s[i] == '('){
                open.add(i);
            }
            else{
                closed.add(i);
            }
        }

        int max = getBeauty(s);
        int f = 0, e = 0;

        for (int open_ind : open) {
            for (int closed_ind : closed) {

                swap(s, open_ind, closed_ind);

                int b = getBeauty(s);

                if (b > max) {
                    max = b;
                    f = open_ind;
                    e = closed_ind;
                }

                swap(s, open_ind, closed_ind);
            }
        }

        System.out.println(max);
        System.out.println((f + 1)+" "+(e + 1));
    }
}
