package Hackerrank.Algorithms.Strings;

import java.util.*;
public class SpecialStringsAgain_InterviewPrep
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String s = sc.next();

        //Calculating the substrings of type 1
        long count = 1, ans = 0;
        for(int i=1;i<n;i++)
        {
            if(s.charAt(i)==s.charAt(i-1))
            {
                count++;
            }
            else
            {
                ans += count*(count+1)/2;
                count = 1;
            }
        }
        ans += count * (count+1) / 2;

        //Calculating the substrings of type 2
        int i=1;
        while(i<n-1)
        {
            char ch = s.charAt(i);
            char other = s.charAt(i+1);
            if(ch==other)
            {
                i+=2;
                continue;
            }
            int ptr = 1;
            while(i+ptr<n && s.charAt(i+ptr)==other && i-ptr>=0 && s.charAt(i-ptr)==other)
            {
                ptr++;
            }
            ptr-=1;
            ans += ptr;
            i += ptr;
            if(ptr==0)
                i++;
        }

        System.out.println(ans);
    }
}
