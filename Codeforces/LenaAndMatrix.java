package Codeforces;

import java.util.*;

public class LenaAndMatrix{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(tc-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            char[][] mat = new char[n][m];
            for(int i=0;i<n;i++)
                mat[i] = sc.next().toCharArray();

            int[][] optimalBlacks = new int[4][2];
            int[] mins = new int[4];
            mins[0] = Integer.MAX_VALUE;
            mins[2] = Integer.MAX_VALUE;
            mins[1] = Integer.MIN_VALUE;
            mins[3] = Integer.MIN_VALUE;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(mat[i][j] == 'B'){
                        if(mins[0] > i+j){
                            mins[0] = i + j;
                            optimalBlacks[0][0] = i;
                            optimalBlacks[0][1] = j;
                        }
                    }

                    if(mat[i][j] == 'B'){
                        if(mins[1] < i+j){
                            mins[1] = i + j;
                            optimalBlacks[1][0] = i;
                            optimalBlacks[1][1] = j;
                        }
                    }

                    if(mat[i][j] == 'B'){
                        if(mins[2] > i-j){
                            mins[2] = i - j;
                            optimalBlacks[2][0] = i;
                            optimalBlacks[2][1] = j;
                        }
                    }

                    if(mat[i][j] == 'B'){
                        if(mins[3] < i-j){
                            mins[3] = i - j;
                            optimalBlacks[3][0] = i;
                            optimalBlacks[3][1] = j;
                        }
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            int x=0, y=0;
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    int d1 = Math.abs(i - optimalBlacks[0][0]) + Math.abs(j - optimalBlacks[0][1]);
                    int d2 = Math.abs(i - optimalBlacks[1][0]) + Math.abs(j - optimalBlacks[1][1]);
                    int d3 = Math.abs(i - optimalBlacks[2][0]) + Math.abs(j - optimalBlacks[2][1]);
                    int d4 = Math.abs(i - optimalBlacks[3][0]) + Math.abs(j - optimalBlacks[3][1]);

                    int c = Math.max(Math.max(d1, d2), Math.max(d3, d4));

                    if(c < min){
                        min = c;
                        x = i; y = j;
                    }
                }
            }

            sb.append(x+1).append(" ").append(y+1).append("\n");
        }

        System.out.println(sb);
    }
}