package Codeforces.EducationalRound94;

import java.util.*;

public class A {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            String s = sc.next();
            int ind = n/2;
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<n;i++)
            {
                sb.append(s.charAt(ind));
            }
            System.out.println(sb);
        }
    }
}
