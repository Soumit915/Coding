package Codeforces.Round666Div2;

import java.util.*;

public class A {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            String[] arr = new String[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.next();
            }

            int[] hash = new int[26];
            for(int i=0;i<n;i++)
            {
                int l = arr[i].length();
                for(int j=0;j<l;j++)
                {
                    hash[arr[i].charAt(j)-97]++;
                }
            }

            boolean flag = true;
            for(int i=0;i<26;i++)
            {
                if(hash[i]%n!=0)
                {
                    flag = false;
                    break;
                }
            }

            if(flag)
                System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
