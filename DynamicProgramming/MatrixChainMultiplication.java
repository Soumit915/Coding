package DynamicProgramming;

import java.util.*;

public class MatrixChainMultiplication
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of matrix : ");
        int n = sc.nextInt();

        System.out.print("Enter the size array : ");
        int[] arr = new int[n+1];
        for(int i=0;i<=n;i++)
        {
            arr[i] = sc.nextInt();
        }

        int[][] M = new int[n][n];
        int[][] P = new int[n][n];
        for(int i=0;i<n;i++)
        {
            M[i][i] = 0;
        }
        for(int i=1;i<n;i++)
        {
            for(int j=0;j<n-i;j++)
            {
                int min = Integer.MAX_VALUE;
                int mid = -1;
                for(int k=0;k<=i-1;k++)
                {
                    int cost = M[j][j+k]+M[j+k+1][j+i] + (arr[j]*arr[j+k+1]*arr[j+i+1]);
                    min = Math.min(min, cost);
                    if(min==cost)
                        mid = j+k;
                }
                M[j][j+i] = min;
                P[j][j+i] = mid;
            }
        }

        System.out.println(M[0][n-1]);
        for(int i=0;i<n;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                System.out.print(P[i][j]+" ");
            }
            System.out.println();
        }

        printParenthesis(P, 0,n-1);
    }
    static char name = 65;
    public static void printParenthesis(int[][] P, int i, int j)
    {
        if(i==j)
        {
            System.out.print(name);
            name++;
            return;
        }

        System.out.print("(");

        printParenthesis(P, i, P[i][j]);
        printParenthesis(P, P[i][j]+1, j);

        System.out.print(")");
    }
}
