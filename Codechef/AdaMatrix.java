package Codechef;

import java.util.*;

class AdaMatrix {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            int[][] matrix = new int[n][n];
            int[][] goalmatrix = new int[n][n];
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    matrix[i][j] = sc.nextInt();
                    goalmatrix[i][j] = i*n + (j+1);
                }
            }

            int[][] state = new int[n][n];
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(matrix[i][j] != goalmatrix[i][j])
                        state[i][j] = 1;
                }
            }

            int init = 1;
            int count = 0;
            for(int i=n-1;i>0;)
            {
                if(state[0][i] == init)
                {
                    count++;
                    while(i>0 && state[0][i] == init)
                    {
                        i--;
                    }
                    init = init^1;
                }
                else i--;
            }

            System.out.println(count);
        }
    }
}
