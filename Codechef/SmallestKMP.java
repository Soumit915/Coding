package Codechef;

import java.util.*;

public class SmallestKMP {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            String s = sc.next();
            int sl = s.length();
            int[] shash = new int[26];
            for(int i=0;i<sl;i++)
            {
                shash[s.charAt(i)-97]++;
            }

            String p = sc.next();
            int pl = p.length();
            for(int i=0;i<pl;i++)
            {
                shash[p.charAt(i)-97]--;
            }

            StringBuilder sb = new StringBuilder();
            int firstchar = p.charAt(0)-97;
            for(int i=0;i<firstchar;i++)
            {
                for(int j=1;j<=shash[i];i++)
                {
                    sb.append((char) i);
                }
            }

            boolean flag = false;
            for(int i=0;i<pl;i++)
            {
                if(p.charAt(i)-97!=firstchar)
                {
                    flag = (p.charAt(i)-97<firstchar);
                    break;
                }
            }

            if(!flag)
            {
                sb.append(p);
                for(int i=0;i<shash[firstchar];i++)
                {
                    sb.append((char) firstchar);
                }
            }
            else {
                for(int i=0;i<shash[firstchar];i++)
                {
                    sb.append((char) firstchar);
                }
                sb.append(p);
            }

            for(int i=firstchar+1;i<26;i++)
            {
                for(int j=1;j<=shash[i];i++)
                {
                    sb.append((char) i);
                }
            }

            System.out.println(sb);
        }
    }
}
