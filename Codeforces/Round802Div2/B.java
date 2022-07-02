package Codeforces.Round802Div2;

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

            if(s[0] == '9'){
                int[] ans = new int[n];
                boolean flag = false;
                for(int i=n-1;i>=0;i--){
                    int d = s[i] - '0';

                    if(flag){
                        if(d == 0){
                            ans[i] = 0;
                            flag = !flag;
                        }
                        else{
                            ans[i] = 10 - d;
                        }
                    }
                    else{
                        if(d <= 1){
                            ans[i] = 1 - d;
                        }
                        else{
                            ans[i] = 11 - d;
                            flag = !flag;
                        }
                    }
                }

                for(int i: ans){
                    sb.append(i);
                }
                sb.append("\n");
            }
            else{
                int[] ans = new int[n];
                for(int i=0;i<n;i++){
                    ans[i] = 9 - (s[i] - '0');
                }

                for(int i: ans){
                    sb.append(i);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
