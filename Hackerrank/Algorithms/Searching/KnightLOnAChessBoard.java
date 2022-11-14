package Hackerrank.Algorithms.Searching;

import java.util.*;

public class KnightLOnAChessBoard
{
    static class Node
    {
        int x;
        int y;
        boolean isVisited;
        int dist;
        Node(int x, int y)
        {
            this.x = x;
            this.y = y;
            this.isVisited = false;
            this.dist = 0;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] dist = new int[n][n];
        for(int i=1;i<n;i++)
        {
            for(int j=1;j<n;j++)
            {
                Queue<Integer> queuex = new LinkedList<>();
                Queue<Integer> queuey = new LinkedList<>();
                Node[][] board = new Node[n][n];
                for(int i1=0;i1<n;i1++)
                {
                    for(int j1=0;j1<n;j1++)
                        board[i1][j1] = new Node(i1,j1);
                }

                queuex.add(0);
                queuey.add(0);
                board[0][0].isVisited = true;

                while(!queuex.isEmpty())
                {
                    int x = queuex.remove();
                    int y = queuey.remove();

                    if(x+i<n && y+j<n && !board[x+i][y+j].isVisited)
                    {
                        queuex.add(x+i);
                        queuey.add(y+j);
                        board[x+i][y+j].isVisited = true;
                        board[x+i][y+j].dist = board[x][y].dist+1;
                    }
                    if(x+i<n && y-j>=0 && !board[x+i][y-j].isVisited)
                    {
                        queuex.add(x+i);
                        queuey.add(y-j);
                        board[x+i][y-j].isVisited = true;
                        board[x+i][y-j].dist = board[x][y].dist+1;
                    }
                    if(x-i>=0 && y+j<n && !board[x-i][y+j].isVisited)
                    {
                        queuex.add(x-i);
                        queuey.add(y+j);
                        board[x-i][y+j].isVisited = true;
                        board[x-i][y+j].dist = board[x][y].dist+1;
                    }
                    if(x-i>=0 && y-j>=0 && !board[x-i][y-j].isVisited)
                    {
                        queuex.add(x-i);
                        queuey.add(y-j);
                        board[x-i][y-j].isVisited = true;
                        board[x-i][y-j].dist = board[x][y].dist+1;
                    }
                    if(x+j<n && y+i<n && !board[x+j][y+i].isVisited)
                    {
                        queuex.add(x+j);
                        queuey.add(y+i);
                        board[x+j][y+i].isVisited = true;
                        board[x+j][y+i].dist = board[x][y].dist+1;
                    }
                    if(x+j<n && y-i>=0 && !board[x+j][y-i].isVisited)
                    {
                        queuex.add(x+j);
                        queuey.add(y-i);
                        board[x+j][y-i].isVisited = true;
                        board[x+j][y-i].dist = board[x][y].dist+1;
                    }
                    if(x-j>=0 && y+i<n && !board[x-j][y+i].isVisited)
                    {
                        queuex.add(x-j);
                        queuey.add(y+i);
                        board[x-j][y+i].isVisited = true;
                        board[x-j][y+i].dist = board[x][y].dist+1;
                    }
                    if(x-j>=0 && y-i>=0 && !board[x-j][y-i].isVisited)
                    {
                        queuex.add(x-j);
                        queuey.add(y-i);
                        board[x-j][y-i].isVisited = true;
                        board[x-j][y-i].dist = board[x][y].dist+1;
                    }
                }

                dist[i][j] = board[n-1][n-1].dist;
            }
        }

        for(int i=1;i<n;i++)
        {
            for(int j=1;j<n;j++)
            {
                if(dist[i][j]!=0) System.out.print(dist[i][j]+" ");
                else System.out.print(-1+" ");
            }
            System.out.println();
        }
    }
}