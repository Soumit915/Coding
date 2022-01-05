package Codeforces.Round663Div2;

import java.util.*;

public class B {
    public static void main(String[] arg)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();

            char[][] grid = new char[n][m];
            for(int i=0;i<n;i++)
            {
                String s = sc.next();
                grid[i] = s.toCharArray();
            }

            int count=0;
            for(int i=n-2;i>=0;i--)
            {
                if(grid[i][m-1] == 'R')
                    count++;
            }

            for(int i=m-2;i>=0;i--)
            {
                if(grid[n-1][i] == 'C')
                    count++;
            }

            System.out.println(count);
        }
    }
}
