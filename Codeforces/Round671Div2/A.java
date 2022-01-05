package Codeforces.Round671Div2;

import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            String s = sc.next();

            if(n%2==0)
            {
                int even=0, odd=0;
                for(int i=1;i<n;i+=2)
                {
                    char ch = s.charAt(i);
                    if((ch-48)%2==0)
                    {
                        even++;
                    }
                    else
                    {
                        odd++;
                    }
                }

                if(even>0)
                    System.out.println(2);
                else System.out.println(1);
            }
            else
            {
                int even=0, odd=0;
                for(int i=1;i<n;i+=2)
                {
                    char ch = s.charAt(i);
                    if((ch-48)%2==0)
                    {
                        even++;
                    }
                    else
                    {
                        odd++;
                    }
                }

                if(odd>0)
                    System.out.println(1);
                else System.out.println(2);

            }
        }
    }
}
