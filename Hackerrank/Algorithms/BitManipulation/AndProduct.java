package Hackerrank.Algorithms.BitManipulation;

import java.util.*;
public class AndProduct
{
    public static long prevPower2(long n)
    {
        n = n|n>>1;
        n = n|n>>2;
        n = n|n>>4;
        n = n|n>>8;
        n = n|n>>16;
        n = n|n>>32;

        n = n+1;
        return n>>1;
    }
    public static String bin(long n)
    {
        StringBuilder s = new StringBuilder();
        while(n>0)
        {
            s.insert(0, (n % 2));
            n/=2;
        }
        return s.toString();
    }
    public static long convert(String s)
    {
        long ans = 0;
        for(int i=s.length()-1;i>=0;i--)
        {
            ans += (Math.pow(2, s.length()-i-1))*(s.charAt(i)-48);
        }
        return ans;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            long l = sc.nextLong();
            long r = sc.nextLong();

            if(l<prevPower2(r))
                System.out.println(0);
            else
            {
                String s1 = bin(l);
                String s2 = bin(r);

                int i;
                StringBuilder ans = new StringBuilder();
                for(i=0;i<s1.length();i++)
                {
                    if(s1.charAt(i)==s2.charAt(i))
                        ans.append(s1.charAt(i));
                    else break;
                }

                while(i<s1.length())
                {
                    ans.append('0');
                    i++;
                }

                System.out.println(convert(ans.toString()));
            }
        }
    }
}
