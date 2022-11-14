package Hackerrank.Mathematics.Combinatorics;

import java.util.*;

public class Coinage {
    public static int solveBruteForce(int n, int[] coins)
    {
        int count = 0;
        int A = coins[0];
        int B = coins[1];
        int C = coins[2];
        int D = coins[3];

        for(int d=0;d<=D;d++)
        {
            if(d*10>=n)
            {
                if(d*10==n)
                    count++;
                break;
            }
            for(int c=0;c<=C;c++)
            {
                if(c*5+d*10>=n)
                {
                    if(c*5+d*10==n)
                        count++;
                    break;
                }

                for(int b=0;b<=B;b++)
                {
                    if(b*2+c*5+d*10>=n)
                    {
                        if(b*2+c*5+d*10==n)
                            count++;
                        break;
                    }

                    if(n-(b*2+c*5+d*10)<=A)
                        count++;
                }
            }
        }

        return count;
    }

    public static int solve(int n, int[] coins)
    {
        int count = 0;
        int A = coins[0];
        int B = coins[1];
        int C = coins[2];
        int D = coins[3];

        int[] ways = new int[n+1];
        for(int d=0;d<=D;d++)
        {
            for(int c=0;c<=C;c++)
            {
                if(c*5+d*10<=n)
                    ways[c*5+d*10]++;
                else
                    break;
            }
        }

        for(int b=0;b<=B;b++)
        {
            for(int i=0;i<=n;i++)
            {
                if(n-i-b*2<=A && n-i-b*2>=0)
                {
                    count += ways[i];
                }
            }
        }

        return count;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t>0)
        {
            int n = sc.nextInt();       //Sum to be achieved
            int[] coinCount = new int[4];

            for(int i=0;i<4;i++)
            {
                coinCount[i] = sc.nextInt();
            }
            int count = solve(n, coinCount);

            System.out.println(count);
            t--;
        }
    }
}
