package Codeforces.Round662Div2;

import java.util.*;

public class B {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] a = new int[n];
        int[] hash = new int[100010];
        for(int i=0;i<n;i++)
        {
            a[i] = sc.nextInt();
            hash[a[i]]++;
        }

        int four = 0, two = 0, six = 0, eight = 0;
        for(int i=0;i<100010;i++)
        {
            if(hash[i]>=8)
            {
                eight++;
            }
            else if(hash[i]>=6)
            {
                six++;
            }
            else if(hash[i]>=4)
            {
                four++;
            }
            else if(hash[i]>=2)
            {
                two++;
            }
        }

        int q = sc.nextInt();
        while (q-->0)
        {
            char c = sc.next().charAt(0);
            int val = sc.nextInt();

            if(c=='+')
            {
                hash[val]++;
                if(hash[val]==8)
                {
                    eight++;six--;
                }
                else if(hash[val]==6)
                {
                    six++;four--;
                }
                else if(hash[val]==4)
                {
                    four++;two--;
                }
                else if(hash[val]==2)
                {
                    two++;
                }
            }
            else
            {
                hash[val]--;
                if(hash[val]==7)
                {
                    eight--;six++;
                }
                else if(hash[val]==5)
                {
                    six--;four++;
                }
                else if(hash[val]==3)
                {
                    four--;two++;
                }
                else if(hash[val]==1)
                {
                    two--;
                }
            }

            if(eight>=1)
                System.out.println("YES");
            else if(six>=1 && (two>=1 || four>=1 || six>=2))
                System.out.println("YES");
            else if(four>=1 && (four>=2 || two>=2))
                System.out.println("YES");
            else System.out.println("NO");
        }
    }
}