package Hackerrank.Algorithms.ConstructiveAlgorithms;

import java.util.*;
public class FlippingMatrix
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();

        while(q-->0)
        {
            int n = sc.nextInt();
            int[][] matrix = new int[2*n][2*n];
            for(int i=0;i<2*n;i++)
            {
                for(int j=0;j<2*n;j++)
                {
                    matrix[i][j] = sc.nextInt();
                }
            }

            long sum = 0;
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    sum += Math.max(Math.max(matrix[i][j], matrix[2*n-1-i][j]),
                                Math.max(matrix[i][2*n-1-j], matrix[2*n-1-i][2*n-1-j]));

                }
            }

            System.out.println(sum);
        }
    }
}

