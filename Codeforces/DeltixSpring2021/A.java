package Codeforces.DeltixSpring2021;

import java.io.*;
import java.util.*;

public class A {
    static boolean hasAllZero(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='1')
                return false;
        }
        return true;
    }
    static String getAllOnes(int n){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append("1");
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            String s = sc.next();

            if(hasAllZero(s)){
                sb.append(s).append("\n");
            }
            else if(m>=n){
                char[] str = s.toCharArray();
                for(int i=0;i<Math.min(2*n, m);i++){
                    char[] local = new char[n];
                    for(int j=0;j<n;j++){
                        int c = 0;
                        if(j==0){
                            if(str[j+1]=='1')
                                c++;
                        }
                        else if(j==n-1){
                            if(str[j-1]=='1')
                                c++;
                        }
                        else{
                            if(str[j+1]=='1')
                                c++;
                            if(str[j-1]=='1')
                                c++;
                        }

                        if(str[j]=='0'){
                            if(c==1){
                                local[j] = '1';
                            }
                            else local[j] = '0';
                        }
                        else local[j] = str[j];
                    }
                    str = local;
                }

                for(int i=0;i<n;i++){
                    sb.append(str[i]);
                }
                sb.append("\n");
            }
            else if(n==1){
                sb.append(s).append("\n");
            }
            else{
                char[] str = s.toCharArray();
                for(int i=0;i<m;i++){
                    char[] local = new char[n];
                    for(int j=0;j<n;j++){
                        int c = 0;
                        if(j==0){
                            if(str[j+1]=='1')
                                c++;
                        }
                        else if(j==n-1){
                            if(str[j-1]=='1')
                                c++;
                        }
                        else{
                            if(str[j+1]=='1')
                                c++;
                            if(str[j-1]=='1')
                                c++;
                        }

                        if(str[j]=='0'){
                            if(c==1){
                                local[j] = '1';
                            }
                            else local[j] = '0';
                        }
                        else local[j] = str[j];
                    }
                    str = local;
                }

                for(int i=0;i<n;i++){
                    sb.append(str[i]);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
