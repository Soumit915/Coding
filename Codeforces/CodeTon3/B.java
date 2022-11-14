package Codeforces.CodeTon3;

import java.io.*;
import java.util.*;

public class B {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();

            long max = 1;
            long count = 1;
            for(int i=1;i<n;i++){
                if(s.charAt(i) == s.charAt(i-1)){
                    count++;
                }
                else{
                    max = Math.max(max, count * count);
                    count = 1;
                }
            }

            max = Math.max(max, count * count);

            count = 0;
            for(int i=0;i<n;i++){
                if(s.charAt(i) == '1')
                    count++;
            }

            max = Math.max(max, count * (n - count));

            sb.append(max).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }

}
