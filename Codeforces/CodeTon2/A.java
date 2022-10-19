package Codeforces.CodeTon2;

import java.util.*;
import java.io.*;

public class A {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            String a = sc.next();
            String b = sc.next();

            String sufa = a.substring(n-m+1);
            String sufb = b.substring(1);

            if(sufa.equals(sufb)){
                boolean flag = false;
                for(int i=0;i<=n-m;i++){
                    if(a.charAt(i) == b.charAt(0)){
                        flag = true;
                        break;
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
