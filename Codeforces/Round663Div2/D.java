package Codeforces.Round663Div2;

import java.io.*;
import java.util.*;

public class D {
    static boolean[][] validpairs;
    static char[][] transpose(char[][] mat){
        int n = mat.length;
        int m = mat[0].length;
        char[][] transposed_mat = new char[m][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                transposed_mat[i][j] = mat[j][i];
            }
        }
        return transposed_mat;
    }
    static int countbits(int mask){
        int c = 0;
        while(mask!=0){
            mask &= (mask-1);
            c++;
        }
        return c;
    }
    static int countChange(char[][] mat, int row, int mask){
        int thismask = 0;
        for(int i=0;i<mat.length;i++){
            thismask |= (mat[i][row]-48)*(1<<i);
        }

        int changedmask = thismask^mask;
        return countbits(changedmask);
    }
    static boolean isValid(int mask1, int mask2){
        int masklength = 3;
        int[][] hash = new int[masklength][2];
        for(int j=0;j<masklength;j++){
            hash[j][0] = ((mask1&(1<<j))==0)?0:1;
        }
        for(int j=0;j<masklength;j++){
            hash[j][1] = ((mask2&(1<<j))==0)?0:1;
        }

        int c1 = hash[0][0]+hash[0][1]+hash[1][0]+hash[1][1];
        int c2 = hash[1][0]+hash[1][1]+hash[2][0]+hash[2][1];
        return (c1%2==1 & c2%2==1);
    }
    static void preComputeValid(){
        validpairs = new boolean[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                validpairs[i][j] = isValid(i, j);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        preComputeValid();

        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] mat = new char[n][m];
        for(int i=0;i<n;i++){
            mat[i] = sc.next().toCharArray();
        }

        if(Math.min(n, m)>3){
            System.out.println(-1);
            System.exit(0);
        }

        if(n>m){
            mat = transpose(mat);
        }

        if(n==1){
            sb.append(0).append("\n");
        }
        else if(n==2){
            int[] dp = new int[m];
            for(int i=0;i<m;i++){
                dp[i] = (mat[0][i]+mat[1][i])%2;
            }

            int c1 = 0, c2 = 0;
            for(int i=0;i<m;i++){
                c1 += Math.abs(dp[i]-i%2);
                c2 += Math.abs(dp[i]-(i+1)%2);
            }

            sb.append(Math.min(c1, c2)).append("\n");
        }
        else{
            long[][] dp = new long[m][8];
            for(int i=0;i<8;i++){
                dp[0][i] = countChange(mat, 0, i);
            }

            for(int i=1;i<m;i++){
                for(int j=0;j<8;j++){
                    long min = Integer.MAX_VALUE;
                    for(int k=0;k<8;k++){
                        if(validpairs[j][k]){
                            min = Math.min(min, countChange(mat, i, j)+dp[i-1][k]);
                        }
                    }
                    dp[i][j] = min;
                }
            }

            long min = Long.MAX_VALUE;
            for(int i=0;i<8;i++){
                min = Math.min(min, dp[m-1][i]);
            }
            sb.append(min).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
