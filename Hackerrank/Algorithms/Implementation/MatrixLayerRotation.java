package Hackerrank.Algorithms.Implementation;

import java.util.*;
public class MatrixLayerRotation
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[][] matrix = new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                matrix[i][j] = sc.nextInt();
            }
        }

        int number_of_layers = Math.min(n, m)/2;
        int[][] output = new int[n][m];
        for(int i=0;i<number_of_layers;i++)
        {
            int length = 2*(m-2*i)+2*(n-2*i-2);
            int[] temp = new int[length];
            int ind = 0;
            for(int i1=i;i1<m-i;i1++)
            {
                temp[ind] = matrix[i][i1];
                ind++;
            }
            for(int i1=i+1;i1<n-i;i1++)
            {
                temp[ind] = matrix[i1][m-i-1];
                ind++;
            }
            for(int i1=m-i-2;i1>=i;i1--)
            {
                temp[ind] = matrix[n-i-1][i1];
                ind++;
            }
            for(int i1=n-i-2;i1>i;i1--)
            {
                temp[ind] = matrix[i1][i];
                ind++;
            }

            int[] newarr = new int[length];
            int shift = k%length;
            for(int j=0;j<length;j++)
            {
                newarr[j] = temp[(j+shift)%length];
            }

            ind = 0;
            for(int i1=i;i1<m-i;i1++)
            {
                output[i][i1] = newarr[ind];
                ind++;
            }
            for(int i1=i+1;i1<n-i;i1++)
            {
                output[i1][m-i-1] = newarr[ind];
                ind++;
            }
            for(int i1=m-i-2;i1>=i;i1--)
            {
                output[n-i-1][i1] = newarr[ind];
                ind++;
            }
            for(int i1=n-i-2;i1>i;i1--)
            {
                output[i1][i] = newarr[ind];
                ind++;
            }
        }

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                System.out.print(output[i][j]+" ");
            }
            System.out.println();
        }
    }
}
