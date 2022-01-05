package Codeforces.Round754Div2;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            char[] s = sc.next().toCharArray();

            int zeros = 0;
            for(int i=0;i<n;i++){
                if(s[i]=='0')
                    zeros++;
            }

            char[] target = new char[n];
            for(int i=0;i<zeros;i++){
                target[i] = '0';
            }
            for(int i=zeros;i<n;i++){
                target[i] = '1';
            }

            int dis = 0;
            for(int i=0;i<n;i++){
                if(target[i]!=s[i])
                    dis++;
            }

            if(dis==0){
                sb.append("0\n");
            }
            else{
                sb.append("1\n");
                sb.append(dis).append(" ");
                for(int i=0;i<n;i++){
                    if(target[i]!=s[i])
                        sb.append(i+1).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
