package Hackerearth.Algorithms.DynamicProgramming.IntroToDP;

import java.util.HashMap;
import java.util.Scanner;

public class GameOfColors {
    public static int get(int[][][][] mat, int i1, int i2, int i3, int i4, int n)
    {
        if(i3>=0 && i3<n && i4>=0 && i4<n)
            return mat[i1][i2][i3][i4];
        else return 0;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        int[][][] dp = new int[101][n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                dp[0][i][j] = sc.nextInt();
            }
        }

        for(int t=1;t<101;t++)
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    dp[t][i][j] = (t/dp[0][i][j])%3;
                }
            }
        }

        int[][][][] count = new int[3][101][n][n];
        for(int c1 = 0;c1<3;c1++)
        {
            for(int t = 1;t<101;t++)
            {
                for(int i=0;i<n;i++)
                {
                    for(int j=0;j<n;j++)
                    {
                        if(dp[t][i][j]==c1)
                        {
                            count[c1][t][i][j] = get(count, c1, t, i-1, j, n) -
                                    get(count, c1, t, i-1, j-1, n) + get(count, c1, t, i, j-1, n) + 1;
                        }
                        else
                        {
                            count[c1][t][i][j] = get(count, c1, t, i-1, j, n) -
                                    get(count, c1, t, i-1, j-1, n) + get(count, c1, t, i, j-1, n);
                        }
                    }
                }
            }
        }

        HashMap<Character, Integer> hash = new HashMap<>();
        hash.put('R', 0);
        hash.put('G', 1);
        hash.put('B', 2);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++)
        {
            int t = sc.nextInt();
            int x1 = sc.nextInt()-1;
            int y1 = sc.nextInt()-1;
            int x2 = sc.nextInt()-1;
            int y2 = sc.nextInt()-1;
            int color = hash.get(sc.next().charAt(0));

            int a1 = get(count, color, t, x2, y2, n);// count[color][t][x2][y2];
            int a2 = get(count, color, t, x1-1, y1-1, n);//count[color][t][x1-1][y1-1];
            int a3 = get(count, color, t, x1-1, y2, n);//count[color][t][x1-1][y2];
            int a4 = get(count, color, t, x2, y1-1, n);//count[color][t][x2][y1-1];

            int ans = a1+a2-a3-a4;
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }
}
