package Hackerrank.Algorithms.Searching;

import java.util.*;

public class ConnectedCellInAGrid
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] matrix = new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                matrix[i][j] = sc.nextInt();
            }
        }

        boolean[][] isVisited = new boolean[n][m];
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                int count = 0;
                if(!isVisited[i][j] && matrix[i][j]!=0)
                {
                    Queue<Integer> qx = new LinkedList<>();
                    Queue<Integer> qy = new LinkedList<>();

                    qx.add(i);
                    qy.add(j);
                    count++;
                    isVisited[i][j] = true;

                    while(!qx.isEmpty())
                    {
                        int x = qx.remove();
                        int y = qy.remove();

                        if(x-1>=0 && !isVisited[x-1][y] && matrix[x-1][y]==1)
                        {
                            qx.add(x-1);
                            qy.add(y);
                            isVisited[x-1][y] = true;
                            count++;
                        }
                        if(x+1<n && !isVisited[x+1][y] && matrix[x+1][y]==1)
                        {
                            qx.add(x+1);
                            qy.add(y);
                            isVisited[x+1][y] = true;
                            count++;
                        }
                        if(y-1>=0 && !isVisited[x][y-1] && matrix[x][y-1]==1)
                        {
                            qx.add(x);
                            qy.add(y-1);
                            isVisited[x][y-1] = true;
                            count++;
                        }
                        if(y+1<m && !isVisited[x][y+1] && matrix[x][y+1]==1)
                        {
                            qx.add(x);
                            qy.add(y+1);
                            isVisited[x][y+1] = true;
                            count++;
                        }
                        if(x-1>=0 && y-1>=0 && !isVisited[x-1][y-1] && matrix[x-1][y-1]==1)
                        {
                            qx.add(x-1);
                            qy.add(y-1);
                            isVisited[x-1][y-1] = true;
                            count++;
                        }
                        if(x-1>=0 && y+1<m && !isVisited[x-1][y+1] && matrix[x-1][y+1]==1)
                        {
                            qx.add(x-1);
                            qy.add(y+1);
                            isVisited[x-1][y+1] = true;
                            count++;
                        }
                        if(x+1<n && y+1<m && !isVisited[x+1][y+1] && matrix[x+1][y+1]==1)
                        {
                            qx.add(x+1);
                            qy.add(y+1);
                            isVisited[x+1][y+1] = true;
                            count++;
                        }
                        if(x+1<n && y-1>=0 && !isVisited[x+1][y-1] && matrix[x+1][y-1]==1)
                        {
                            qx.add(x+1);
                            qy.add(y-1);
                            isVisited[x+1][y-1] = true;
                            count++;
                        }
                    }
                }
                max = Math.max(max, count);
            }
        }

        System.out.println(max);
    }
}