package Codeforces.EducationalRound93;

import java.util.*;

public class A {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int[] a = new int[n];

            for(int i=0;i<n;i++)
            {
                a[i] = sc.nextInt();
            }

            if(a[0]+a[1]>a[n-1])
            {
                System.out.println("1 2 "+(n-1));
            }
            else System.out.println(-1);
        }
    }
}
