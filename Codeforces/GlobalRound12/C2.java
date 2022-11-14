package Codeforces.GlobalRound12;

import java.io.*;
import java.util.*;

public class C2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            char[][] mat = new char[n][n];
            for(int i=0;i<n;i++){
                mat[i] = sc.next().toCharArray();
            }

            int c12=0, c13=0, c23=0, c21=0, c32=0, c31=0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if((i+j)%3==0){
                        if(mat[i][j]=='X'){
                            c12++;
                            c13++;
                        }
                        else if(mat[i][j]=='O'){
                            c21++;
                            c31++;
                        }
                    }else if((i+j)%3==1){
                        if(mat[i][j]=='X'){
                            c21++;
                            c23++;
                        }
                        else if(mat[i][j]=='O'){
                            c12++;
                            c32++;
                        }
                    }else{
                        if(mat[i][j]=='X'){
                            c31++;
                            c32++;
                        }
                        else if(mat[i][j]=='O'){
                            c13++;
                            c23++;
                        }
                    }
                }
            }

            int min = Math.min(Math.min(Math.min(c12, c13), Math.min(c21, c23)),
                        Math.min(c31, c32));
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(c12==min){
                        if((i+j)%3==0 && mat[i][j]=='X'){
                            mat[i][j] = 'O';
                        }
                        else if((i+j)%3==1 && mat[i][j]=='O'){
                            mat[i][j] = 'X';
                        }
                    }
                    else if(c13==min){
                        if((i+j)%3==0 && mat[i][j]=='X'){
                            mat[i][j] = 'O';
                        }
                        else if((i+j)%3==2 && mat[i][j]=='O'){
                            mat[i][j] = 'X';
                        }
                    }
                    else if(c23==min){
                        if((i+j)%3==1 && mat[i][j]=='X'){
                            mat[i][j] = 'O';
                        }
                        else if((i+j)%3==2 && mat[i][j]=='O'){
                            mat[i][j] = 'X';
                        }
                    }
                    else if(c21==min){
                        if((i+j)%3==1 && mat[i][j]=='X'){
                            mat[i][j] = 'O';
                        }
                        else if((i+j)%3==0 && mat[i][j]=='O'){
                            mat[i][j] = 'X';
                        }
                    }
                    else if(c31==min){
                        if((i+j)%3==2 && mat[i][j]=='X'){
                            mat[i][j] = 'O';
                        }
                        else if((i+j)%3==0 && mat[i][j]=='O'){
                            mat[i][j] = 'X';
                        }
                    }
                    else {
                        if((i+j)%3==2 && mat[i][j]=='X'){
                            mat[i][j] = 'O';
                        }
                        else if((i+j)%3==1 && mat[i][j]=='O'){
                            mat[i][j] = 'X';
                        }
                    }
                }
            }

            for(char[] i: mat){
                for(char ch: i){
                    sb.append(ch);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
