package Codeforces.GlobalRound12;

import java.io.*;
import java.util.*;

public class C1 {
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

            int x1 = 0, x2 = 0, x3 = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(mat[i][j]=='X'){
                        if((i+j)%3==0){
                            x1++;
                        }
                        else if((i+j)%3==1){
                            x2++;
                        }
                        else x3++;
                    }
                }
            }

            int min = Math.min(x1, Math.min(x2, x3));
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(mat[i][j]=='X') {
                        if (x1 == min) {
                            if ((i + j) % 3 == 0) {
                                mat[i][j] = 'O';
                            }
                        } else if (x2 == min) {
                            if ((i + j) % 3 == 1) {
                                mat[i][j] = 'O';
                            }
                        } else {
                            if ((i + j) % 3 == 2) {
                                mat[i][j] = 'O';
                            }
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
