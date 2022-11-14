package Codechef;

import java.io.*;
import java.util.*;

public class ChefInHeaven {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String s = sc.next();

            int z = 0;
            int one = 0;
            boolean flag = false;
            for(int i=0;i<n;i++){
                if(s.charAt(i)=='0')
                    z++;
                else one++;
                if(one>=z){
                    flag = true;
                    break;
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
