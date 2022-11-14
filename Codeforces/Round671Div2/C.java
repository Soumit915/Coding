package Codeforces.Round671Div2;

import java.util.*;

public class C {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int x = sc.nextInt();

            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
            }

            boolean flag = true;
            for(int i=0;i<n;i++)
            {
                if(arr[i]!=x)
                {
                    flag = false;
                    break;
                }
            }

            if(flag)
            {
                System.out.println(0);
                continue;
            }

            int[] diff = new int[n];
            for(int i=0;i<n;i++)
            {
                diff[i] = arr[i]-x;
            }

            int sum = 0;
            for(int i=0;i<n;i++)
            {
                sum += diff[i];
            }

            if(sum==0)
            {
                System.out.println(1);
            }
            else
            {
                int count = 0;
                for(int i=0;i<n;i++)
                {
                    if(diff[i]!=0)
                        count++;
                }

                if(count==1)
                    System.out.println(1);
                else if(n-count>0)
                    System.out.println(1);
                else System.out.println(2);
            }
        }
    }
}
