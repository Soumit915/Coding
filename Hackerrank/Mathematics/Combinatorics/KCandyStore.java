package Hackerrank.Mathematics.Combinatorics;

import java.util.*;

public class KCandyStore
{
    static int mod = (int) 1e9;
    public static int[][] combinations(int n, int r)
    {
        int[][] nCr = new int[n+1][r+1];
        for(int i=0;i<=n;i++)
        {
            nCr[i][0] = 1;
        }
        for(int i=1;i<=r;i++)
        {
            nCr[0][i] = 0;
        }
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=r;j++)
            {
                nCr[i][j] = (nCr[i-1][j-1]%mod + nCr[i-1][j]%mod)%mod;
            }
        }

        return nCr;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        int[][] nCr = combinations(2000,1000);

        while(t>0)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int ans = nCr[k+n-1][n-1];

            System.out.println(ans);

            t--;
        }
    }
}
