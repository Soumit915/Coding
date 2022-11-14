package ICPC_2020.Preliminary;

import java.io.*;
import java.util.*;

public class E {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            String s = sc.next();

            StringBuilder newstring = new StringBuilder();
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!='?')
                    newstring.append(s.charAt(i));
            }

            s = newstring.toString();
            boolean flag = false;
            for(int i=1;i<s.length();i++){
                if(s.charAt(i-1)=='M' && s.charAt(i)=='U'){
                    flag = true;
                    break;
                }
            }

            sb.append(flag?"Yes\n":"No\n");
        }

        System.out.println(sb);
    }

}
