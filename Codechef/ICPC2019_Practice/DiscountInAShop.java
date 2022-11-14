package Codechef.ICPC2019_Practice;

import java.io.*;
import java.util.*;

public class DiscountInAShop {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            String s = sc.next();

            int n1 = Integer.parseInt(s.substring(1));

            char ch = '0';
            for(int i=0;i<s.length();i++)
            {
                ch = (char) Math.max(ch, s.charAt(i));
            }

            for(int i=0;i<s.length();i++)
            {
                String s1 = s.substring(0, i) + s.substring(i+1);
                n1 = Math.min(n1, Integer.parseInt(s1));
            }

            sb.append(n1).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
