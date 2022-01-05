package Codeforces.EducationalRound94;

import java.util.*;

public class C
{
    public static boolean check(String s, String s1, int x)
    {
        int n = s.length();
        for(int i=0;i<n;i++)
        {
            if((i-x>=0 && s1.charAt(i-x)=='1') || (i+x<n && s1.charAt(i+x)=='1'))
            {
                if(s.charAt(i)=='0')
                {
                    //System.out.println(i);
                    return false;
                }
            }
            else
            {
                if(s.charAt(i)=='1')
                {
                    return false;
                }
            }
        }

        return true;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            String s = sc.next();
            int x = sc.nextInt();
            int n = s.length();

            char[] str = new char[n];
            Arrays.fill(str, '1');
            for(int i=0;i<n;i++)
            {
                if(s.charAt(i)=='0')
                {
                    if(i+x<n)
                        str[i+x] = '0';
                    if(i-x>=0)
                        str[i-x] = '0';
                }
            }

            String s1 = new String(str);

            if(check(s, s1, x))
                System.out.println(s1);
            else System.out.println(-1);
        }
    }
}