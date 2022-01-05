package DynamicProgramming;

import java.util.*;

public class OptimalBinarySearchTree {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of nodes : ");
        int n = sc.nextInt();

        int[] freq = new int[n];
        int[] cumfreq = new int[n+1];
        cumfreq[0] = 0;
        System.out.print("Enter the frequency of the corresponding keys : ");
        for(int i=0;i<n;i++)
        {
            freq[i] = sc.nextInt();
            cumfreq[i+1] = cumfreq[i]+freq[i];
        }

        int[][] Dp = new int[n+1][n+1];
        int[][] root = new int[n+1][n+1];

        for(int i=0;i<=n;i++)
        {
            Dp[i][i] = 0;   //Since, the cost of the tree is 0 when no nodes are there in the tree
        }
        for(int i=1;i<=n;i++)       //To consider the number of nodes
        {
            for(int j=0;j<=n-i;j++)      //To consider the set of nodes
            {
                int min = Integer.MAX_VALUE;
                int rootnode = -1;
                for(int k=j+1;k<=j+i;k++)        //Will consider the roots
                {
                    int cost = Dp[j][k-1]+Dp[k][j+i]+cumfreq[j+i]-cumfreq[j];
                    min = Math.min(cost,min);
                    if(min == cost)
                    {
                        rootnode = k;
                    }
                }
                Dp[j][j+i] = min;
                root[j][j+i] = rootnode;
            }
        }

        System.out.println("The optimal search cost of the binary tree is : "+Dp[0][n]);
        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=n;j++)
            {
                System.out.print(root[i][j]+" ");
            }
            System.out.println();
        }
    }
}
