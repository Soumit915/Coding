package Codeforces.Round668Div2;

import java.util.*;

public class A {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            long[] arr = new long[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextLong();
            }

            for(int i=n-1;i>=0;i--)
            {
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
    }
}
