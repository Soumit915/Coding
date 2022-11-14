package Codeforces.Round669Div2;

import java.util.*;

public class A {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];

            int one = 0, zero = 0;
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
                if(arr[i] == 1)
                    one++;
                else zero++;
            }

            if(zero >= (n/2))
            {
                System.out.println(zero);
                for(int i=1;i<=zero;i++)
                {
                    System.out.print(0+" ");
                }
                System.out.println();
            }
            else {
                if(one%2==1)
                {
                    System.out.println(one+1);
                    for(int i=0;i<=one;i++)
                    {
                        System.out.print(1+" ");
                    }
                }
                else
                {
                    System.out.println(one);
                    for(int i=0;i<one;i++)
                        System.out.print(1+" ");
                }
                System.out.println();
            }
        }
    }
}
