package Hackerrank.Algorithms.Searching;

import java.util.*;
public class RedKnightShortestPath
{
    static int n;
    public static boolean legal(int x, int y)
    {
        return (x>=0 && x<n && y>=0 && y<n);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int istart = sc.nextInt();
        int jstart = sc.nextInt();

        int iend = sc.nextInt();
        int jend = sc.nextInt();

        boolean[][] isVisited = new boolean[n][n];
        String[][] direction = new String[n][n];
        int[][] prevx = new int[n][n];
        int[][] prevy = new int[n][n];

        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.add(istart);
        qy.add(jstart);
        isVisited[istart][jstart] = true;

        while(!qx.isEmpty())
        {
            int x = qx.remove();
            int y = qy.remove();

            if(legal(x-2, y-1) && !isVisited[x-2][y-1])
            {
                qx.add(x-2);
                qy.add(y-1);
                isVisited[x-2][y-1] = true;
                prevx[x-2][y-1] = x;
                prevy[x-2][y-1] = y;
                direction[x-2][y-1] = "UL";
            }

            if(legal(x-2, y+1) && !isVisited[x-2][y+1])
            {
                qx.add(x-2);
                qy.add(y+1);
                isVisited[x-2][y+1] = true;
                prevx[x-2][y+1] = x;
                prevy[x-2][y+1] = y;
                direction[x-2][y+1] = "UR";
            }

            if(legal(x, y+2) && !isVisited[x][y+2])
            {
                qx.add(x);
                qy.add(y+2);
                isVisited[x][y+2] = true;
                prevx[x][y+2] = x;
                prevy[x][y+2] = y;
                direction[x][y+2] = "R";
            }

            if(legal(x+2, y+1) && !isVisited[x+2][y+1])
            {
                qx.add(x+2);
                qy.add(y+1);
                isVisited[x+2][y+1] = true;
                prevx[x+2][y+1] = x;
                prevy[x+2][y+1] = y;
                direction[x+2][y+1] = "LR";
            }

            if(legal(x+2, y-1) && !isVisited[x+2][y-1])
            {
                qx.add(x+2);
                qy.add(y-1);
                isVisited[x+2][y-1] = true;
                prevx[x+2][y-1] = x;
                prevy[x+2][y-1] = y;
                direction[x+2][y-1] = "LL";
            }

            if(legal(x, y-2) && !isVisited[x][y-2])
            {
                qx.add(x);
                qy.add(y-2);
                isVisited[x][y-2] = true;
                prevx[x][y-2] = x;
                prevy[x][y-2] = y;
                direction[x][y-2] = "L";
            }
        }

        if(isVisited[iend][jend])
        {
            Stack<String> stk = new Stack<>();
            stk.push(direction[iend][jend]);
            int ptrx = prevx[iend][jend];
            int ptry = prevy[iend][jend];
            while(ptrx!=istart || ptry!=jstart)
            {
                stk.push(direction[ptrx][ptry]);
                int tempx = ptrx;
                ptrx = prevx[ptrx][ptry];
                ptry = prevy[tempx][ptry];
            }

            System.out.println(stk.size());
            while(!stk.isEmpty())
            {
                System.out.print(stk.pop()+" ");
            }
            System.out.println();
        }
        else
        {
            System.out.println("Impossible");
        }
    }
}

