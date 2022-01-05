package Codeforces.Round713Div3;

import java.io.*;
import java.util.*;

public class B {
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

            int r1=-1, r2=-1, c1=-1, c2=-1;
            int c = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(mat[i][j]=='*'){
                        if(c==0){
                            r1 = i;
                            c1 = j;
                            c++;
                        }
                        else{
                            r2 = i;
                            c2 = j;
                        }
                    }
                }
            }

            int r3, r4, c3, c4;
            if(r1==r2){
                if(r1==0){
                    r3 = 1;
                    c3 = c1;

                    r4 = 1;
                }
                else{
                    r3 = 0;
                    c3 = c1;

                    r4 = 0;
                }
                c4 = c2;
            }
            else if(c1==c2){
                r3 = r1;
                if(c1==0){
                    c3 = 1;

                    r4 = r2;
                    c4 = 1;
                }
                else{
                    c3 = 0;

                    r4 = r2;
                    c4 = 0;
                }
            }
            else{
                r3 = r1;
                c3 = c2;
                r4 = r2;
                c4 = c1;
            }

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(i==r3 && j==c3){
                        mat[i][j] = '*';
                    }

                    if(i==r4 && j==c4){
                        mat[i][j] = '*';
                    }
                }
            }

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    sb.append(mat[i][j]);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
