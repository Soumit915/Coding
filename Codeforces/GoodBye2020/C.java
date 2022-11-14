package Codeforces.GoodBye2020;

import java.util.*;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            String s = sc.next();
            int n = s.length();

            boolean[] hash = new boolean[n];
            for(int i=1;i<n;i++)
            {
                if(i==1)
                {
                    if(s.charAt(i)==s.charAt(i-1) && !hash[i] && !hash[i-1])
                    {
                        hash[i] = true;
                    }
                }
                else
                {
                    if(s.charAt(i)==s.charAt(i-1) && !hash[i] && !hash[i-1])
                    {
                        hash[i] = true;
                    }
                    if(s.charAt(i)==s.charAt(i-2) && !hash[i] && !hash[i-2])
                    {
                        hash[i] = true;
                    }
                }


            }

            int ans = 0;
            for(int i=0;i<n;i++)
            {
                if(hash[i])
                    ans++;
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
