package Hackerrank.DataStructures.Advanced;

import java.util.Scanner;

public class MrXShots {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int range = 1000000;
        int[] a = new int[range];
        int[] b = new int[range];
        for(int i=0;i<n;i++)
        {
            int ai = sc.nextInt();
            a[ai] += 1;
            int bi = sc.nextInt();
            b[bi] += 1;
        }

        for(int i=1;i<range;i++)
        {
            a[i] += a[i-1];
            b[i] += b[i-1];
        }

        int count = 0;
        for(int i=0;i<m;i++)
        {
            int ci = sc.nextInt();
            int di = sc.nextInt();

            count += (a[di]-b[ci-1]);
        }

        System.out.println(count);
    }
}
