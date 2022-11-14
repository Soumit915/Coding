package Codechef;

import java.util.Scanner;

public class CacheHits {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int b = sc.nextInt();
            int m = sc.nextInt();

            int[] arr = new int[m];
            for(int i=0;i<m;i++)
            {
                arr[i] = sc.nextInt();
                arr[i] = arr[i]/b;
            }

            int count = 1;
            for(int i=1;i<m;i++)
            {
                if(arr[i]!=arr[i-1])
                    count++;
            }

            System.out.println(count);
        }
    }
}
