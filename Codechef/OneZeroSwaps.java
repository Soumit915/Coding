package Codechef;

import java.util.*;

public class OneZeroSwaps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();
            String s = sc.next();
            String p = sc.next();

            int zero = 0;
            int one = 0;
            for(int i=0;i<n;i++)
            {
                if(s.charAt(i)=='0')
                    zero++;
                else one++;
            }

            for(int i=0;i<n;i++)
            {
                if(p.charAt(i)=='0')
                    zero--;
                else one--;
            }

            if(zero!=0 && one!=0)
            {
                sb.append("No\n");
                continue;
            }

            int ps = 0;
            int pp = 0;
            boolean flag = true;
            for(int i=0;i<n;i++)
            {
                if(s.charAt(i)=='1')
                    ps++;
                if(p.charAt(i)=='1')
                    pp++;
                if(ps<pp)
                {
                    flag= false;
                    break;
                }
            }

            if(flag)
                sb.append("Yes\n");
            else sb.append("No\n");
        }

        System.out.println(sb);
    }
}
