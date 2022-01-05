package Codeforces.EducationalRound94;

import java.util.*;

public class D {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            int[] arr = new int[n];

            int[][] hasharr = new int[n+1][n+1];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
                for(int j=1;j<=n;j++)
                {
                    if(j==arr[i])
                    {
                        hasharr[j][i+1] = 1+hasharr[j][i];
                    }
                    else
                    {
                        hasharr[j][i+1] = hasharr[j][i];
                    }
                }
            }

            int start = 1, end = n-2;
            long count = 0;
            while(start<end)
            {
                for(int i=start;i<end;i++)
                {
                    int y = arr[i];
                    int x = arr[end];
                    count += (hasharr[x][i]-hasharr[x][0])*(hasharr[y][n]-hasharr[y][end+1]);
                }
                end--;
            }

            System.out.println(count);
        }
    }
}
