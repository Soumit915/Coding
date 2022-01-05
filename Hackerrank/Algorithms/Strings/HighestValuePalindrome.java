package Hackerrank.Algorithms.Strings;

import java.util.*;

public class HighestValuePalindrome
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String s = sc.next();
        char[] str = s.toCharArray();

        boolean[] isChanged = new boolean[n];
        for(int i=0;i<n/2;i++)
        {
            if(str[i]==str[n-i-1])
                continue;
            if(k==0)
            {
                System.out.println(-1);
                System.exit(0);
            }

            k--;
            if(str[i]<str[n-i-1])
            {
                str[i] = str[n-i-1];
                isChanged[i] = true;
            }
            else
            {
                str[n-i-1] = str[i];
                isChanged[n-i-1] = true;
            }
        }

        int i=0, j=n-1;
        while(k>0 && i<j)
        {
            //if str is already 9 then no need to change or update their value
            if(str[i]=='9')
            {
                i++;j--;
                continue;
            }

            //if both needs to be updated then,
            if(!isChanged[i] && !isChanged[j])
            {
                //check if we have left with enough k, if not just continue
                if(k==1)
                {
                    i++;j--;
                }
                //if we have adequate changes left then update the values
                else
                {
                    str[i] = '9';
                    str[j] = '9';
                    i++;j--;
                    k-=2;
                }
            }
            else
            {
                str[i] = '9';
                str[j] = '9';
                i++;j--;
                k--;
            }
        }

        if(n%2==1 && k>0)
        {
            str[n/2] = '9';
        }

        for(char c: str)
            System.out.print(c);
        System.out.println();
    }
}