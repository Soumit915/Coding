package Codeforces;

import java.io.*;
import java.util.*;

public class InteracdiveProblem {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int ll = 1, ul = n-1;
        int sumc = 0, mult = 1;
        while(ll<ul){
            int rl = mult * n - ll - sumc;
            int ru = mult * n - ul - sumc;

            int mid = (rl + ru)/2;
            System.out.println("+ "+mid);
            System.out.flush();
            int q = sc.nextInt();

            sumc += mid;
            if(q==(mult-1)){
                ul = ((q+1) * n - 1) - sumc;
            }
            else{
                ll = (q*n - sumc);
            }

            mult = q + 1;
        }

        System.out.println("! "+(ll+sumc));
        System.out.flush();
    }
}
