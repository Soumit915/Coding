package Hackerrank.Mathematics.NumberTheory;

import java.util.*;
public class ManasaLovesMaths
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            String n = sc.next();

            int[] hash = new int[10];
            int even=0, odd=0, esp=0;
            for(int i=0;i<n.length();i++)
            {
                hash[n.charAt(i)-48]++;
                if(n.charAt(i)%2==0)
                    even++;
                else odd++;

                if((n.charAt(i)-48)==4 || (n.charAt(i)-48)==0 || (n.charAt(i)-48)==8) esp++;
            }

            if(n.length()==1)
            {
                if(Integer.parseInt(n)%8==0)
                    System.out.println("YES");
                else System.out.println("NO");
                continue;
            }

            boolean flag = false;
            final boolean flag1 = hash[1] > 0 || hash[5] > 0 || hash[9] > 0;
            if(hash[2]>0)
            {
                if(hash[3]>0 || hash[7]>0)
                {
                    if(n.length()==2 || even>1)
                        flag = true;
                }
                if(flag1)
                {
                    if(odd>1)
                        flag = true;
                }
            }
            if(hash[6]>0)
            {
                if(hash[3]>0 || hash[7]>0)
                {
                    if(odd>1)
                        flag = true;
                }
                if(flag1)
                {
                    if(n.length()==2 || even>1)
                        flag = true;
                }
            }

            if(hash[4]>0)
            {
                if(hash[2]>0 || hash[6]>0)
                {
                    if(n.length()==2 || even>2)
                        flag = true;
                }
                if(esp>1)
                {
                    if(odd>0)
                        flag = true;
                }
            }
            if(hash[8]>0 || hash[0]>0)
            {
                if(hash[2]>0 || hash[6]>0)
                {
                    if(odd>0)
                        flag = true;
                }
                if(esp>1)
                {
                    if(n.length()==2 || even>2)
                        flag = true;
                }
            }

            if(flag)
                System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
