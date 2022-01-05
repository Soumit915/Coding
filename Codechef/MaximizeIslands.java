package Codechef;

import java.io.*;
import java.util.*;

public class MaximizeIslands {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            char[][] grid = new char[n][m];
            for(int i=0;i<n;i++)
                grid[i] = sc.next().toCharArray();

            int count1=0, count2=0;
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    if(i%2==0 && j%2==0)
                    {
                        if(grid[i][j]!='*')
                            count1++;
                    }
                    else if(i%2==1 && j%2==1)
                    {
                        if(grid[i][j]!='*')
                            count1++;
                    }
                    else{
                        if(grid[i][j]!='.')
                            count1++;
                    }
                }
            }

            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    if(i%2==0 && j%2==0)
                    {
                        if(grid[i][j]!='.')
                            count2++;
                    }
                    else if(i%2==1 && j%2==1)
                    {
                        if(grid[i][j]!='.')
                            count2++;
                    }
                    else{
                        if(grid[i][j]!='*')
                            count2++;
                    }
                }
            }

            if(n%2==1 && m%2==1)
                sb.append(count1).append("\n");
            else sb.append(Math.min(count1, count2)).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
