package Codeforces.ICPC3;

import java.util.*;
import java.io.*;

public class D {

    static class Pair{
        int count;
        int val;

        Pair(int count, int val){
            this.count = count;
            this.val = val;
        }
    }

    static String[] number_string = {"1110111", "0010010", "1011101", "1011011", "0111010", "1101011", "1101111", "1010010", "1111111", "1111011"};
    static int[] number_mask = new int[10];

    static void preCompute(){
        for(int i=0;i<10;i++){
            number_mask[i] = Integer.parseInt(number_string[i], 2);
        }
    }

    static boolean isPossible_to_convert(int n, int m){
        return (n & m) == n;
    }

    static int countBits(int n){
        int c = 0;
        while(n > 0){
            n = n & (n-1);
            c++;
        }

        return c;
    }

    static List<Pair> getAllPossibles(String s){
        int bin = Integer.parseInt(s, 2);

        List<Pair> list = new ArrayList<>();
        for(int i=9;i>=0;i--){
            if(isPossible_to_convert(bin, number_mask[i])){
                int extra_bits = countBits(bin ^ number_mask[i]);
                Pair p = new Pair(extra_bits, i);
                list.add(p);
            }
        }

        return list;
    }

    static boolean[][] isVisited;
    static int[][] dp;

    static boolean recurse(List<List<Pair>> list, int index, int k){

        int n = list.size();

        if(k < 0) {
            return false;
        }

        if(index >= n){
            return k == 0;
        }

        if(isVisited[index][k]){
            return dp[index][k] != -1;
        }

        isVisited[index][k] = true;
        List<Pair> li = list.get(index);
        for(Pair p: li){
            boolean isPossible = recurse(list, index + 1, k - p.count);

            if(isPossible){
                dp[index][k] = p.val;
                return true;
            }
        }

        dp[index][k] = -1;
        return false;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        preCompute();

        int n = sc.nextInt();
        int k = sc.nextInt();

        String[] s = new String[n];
        for(int i=0;i<n;i++){
            s[i] = sc.next();
        }

        List<List<Pair>> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            List<Pair> li = getAllPossibles(s[i]);

            if(li.size() == 0){
                System.out.println(-1);
                System.exit(0);
            }

            list.add(li);
        }

        isVisited = new boolean[n][k + 1];
        dp = new int[n][k + 1];

        boolean isPossible = recurse(list, 0, k);

        if(!isPossible) {
            System.out.println(-1);
            System.exit(0);
        }

        StringBuilder ans = new StringBuilder();
        int kptr = k;
        for(int i=0;i<n;i++){
            int c = dp[i][kptr];
            ans.append(c);

            List<Pair> li = list.get(i);

            for(Pair p: li){
                if(p.val == c){
                    kptr -= p.count;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}
