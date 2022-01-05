package Codeforces.EducationalRound70;

import java.util.*;

public class A {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            String s1 = sc.next();
            String s2 = sc.next();

            int i;
            for(i=s2.length();i>=0;i--)
            {
                if(s2.charAt(i)=='1')
                {
                    break;
                }
            }

            if(s1.length()<(s2.length()-i))
            {
                System.out.println(0);
            }
            else
            {
                int count=0;
                for(int j=(s1.length()-s2.length()-i);j>=0;j--)
                {
                    if(s1.charAt(j)=='1')
                    {
                        System.out.println(count);
                    }
                    else
                    {
                        count++;
                    }
                }
            }
        }
    }
}
