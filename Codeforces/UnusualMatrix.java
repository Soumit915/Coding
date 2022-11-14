package Codeforces;

import java.util.*;
import java.io.*;

public class UnusualMatrix {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();

            char[][] a = new char[n][n];
            for(int i=0;i<n;i++){
                a[i] = sc.next().toCharArray();
            }

            char[][] b = new char[n][n];
            for(int i=0;i<n;i++){
                b[i] = sc.next().toCharArray();
            }

            for(int i=0;i<n;i++){
                if(a[0][i] != b[0][i]){
                    for(int j=0;j<n;j++){
                        if(a[j][i] == '0'){
                            a[j][i] = '1';
                        }
                        else{
                            a[j][i] = '0';
                        }
                    }
                }
            }

            for(int i=0;i<n;i++){
                if(a[i][0] != b[i][0]){
                    for(int j=0;j<n;j++){
                        if(a[i][j] == '0'){
                            a[i][j] = '1';
                        }
                        else{
                            a[i][j] = '0';
                        }
                    }
                }
            }

            boolean flag = true;
            for(int i=0;i<n && flag;i++){
                for(int j=0;j<n;j++){
                    if(a[i][j] != b[i][j]){
                        flag = false;
                        break;
                    }
                }
            }

            if(flag)
                sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
