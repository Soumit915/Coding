package Codechef;

import java.util.*;

public class MaxMex {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr);

            if(m==1)
            {
                int count=0;
                for(int i=0;i<n;i++)
                {
                    if(arr[i] == 1)
                        count++;
                }
                if(count == n) {
                    System.out.println(n-count);
                    continue;
                }
            }
            else if(m>100000)
            {
                System.out.println(-1);
            }
            else
            {
                int[] hash = new int[100005];
                for(int i=0;i<n;i++)
                {
                    if(arr[i]>m)
                        break;

                    hash[arr[i]]++;
                }

                boolean flag = true;
                for(int i=1;i<m;i++)
                {
                    if(hash[i]==0)
                    {
                        flag = false;
                        break;
                    }
                }

                if(!flag)
                {
                    System.out.println(-1);
                    continue;
                }
                else
                {
                    System.out.println(n-hash[m]);
                }
            }

        }
    }
}
