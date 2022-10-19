package Codeforces.EducationalRound132;

import java.util.*;
import java.io.*;

public class C {

    static boolean isOk(char[] s){
        int b = 0;
        for (char c : s) {
            if (c == '(') {
                b++;
            } else {
                b--;
            }

            if (b < 0)
                return false;
        }

        return b==0;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            char[] s = sc.next().toCharArray();
            int n = s.length;

            char[] t = new char[n];
            System.arraycopy(s, 0, t, 0, n);

            if(n%2 == 1){
                sb.append("NO\n");
                continue;
            }

            int open = 0, closed = 0;
            for (char value : s) {
                if (value == '(') {
                    open++;
                } else if (value == ')') {
                    closed++;
                }
            }

            int th = n / 2;
            if(open > th || closed > th){
                sb.append("NO\n");
                continue;
            }

            int openchanges = th - open;
            for(int i=0;i<n;i++){
                if(s[i] == '?'){
                    if(openchanges > 0){
                        s[i] = '(';
                        openchanges--;
                    }
                    else{
                        s[i] = ')';
                    }
                }
            }

            if(isOk(s)){

                int[] dp = new int[n];

                int c = 0;
                for(int i=0;i<n;i++){
                    if(s[i] == '('){
                        c++;
                    }
                    else{
                        c--;
                    }
                    dp[i] = c;
                }

                boolean flag = true;
                c = 0;
                for(int i=0;i<n;i++){

                    if(t[i] == '?'){
                        if(s[i] == '('){
                            if(i > 0 && dp[i-1] > 0){
                                c++;
                            }
                        }
                        else{
                            if(dp[i] >= 1){
                                if(c > 0) {
                                    flag = false;
                                    break;
                                }
                            }
                        }
                    }

                    if(dp[i] <= 1){
                        c = 0;
                    }

                }

                if(flag){
                    sb.append("YES\n");
                }
                else{
                    sb.append("NO\n");
                }
            }
            else{
                sb.append("NO\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }

}
