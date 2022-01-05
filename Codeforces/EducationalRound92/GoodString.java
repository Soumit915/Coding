package Codeforces.EducationalRound92;

import java.util.*;

public class GoodString {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            String s = sc.next();

            long max = 0;
            for(int i=0;i<9;i++)
            {
                char c1 = (char)(i+48);
                for(int j=i+1;j<=9;j++)
                {
                    char c2 = (char)(j+48);
                    char last = '\u0000';
                    long count = 0;
                    for(int ind=0;ind<s.length();ind++)
                    {
                        char ch = s.charAt(ind);
                        if(ch==c1 || ch==c2)
                        {
                            if(last=='\u0000')
                            {
                                last = ch;
                                count++;
                            }
                            else if(last!=ch)
                            {
                                last = ch;
                                count++;
                            }
                        }
                    }
                    if(count%2==0)
                        max = Math.max(max, count);
                    else
                        max = Math.max(max, count-1);
                }
            }

            for(int i=0;i<=9;i++)
            {
                long count = 0;
                char c = (char)(i+48);
                for(int ind=0;ind<s.length();ind++)
                {
                    char ch = s.charAt(ind);
                    if(ch==c)
                    {
                        count++;
                    }
                }
                max = Math.max(max, count);
            }

            System.out.println(s.length()-max);
        }
    }
}