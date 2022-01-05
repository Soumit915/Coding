package Hackerrank.DataStructures.Queue;

import java.util.*;

public class CastleOnTheGrid
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String[] arr = new String[n];
        boolean[][] isVisited = new boolean[n][n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.next();
        }

        int starty = sc.nextInt();
        int startx = sc.nextInt();
        int endy = sc.nextInt();
        int endx = sc.nextInt();

        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        Queue<Integer> count = new LinkedList<>();

        qx.add(startx);
        qy.add(starty);
        count.add(0);
        isVisited[startx][starty] = true;

        while(!qx.isEmpty())
        {
            int indx = qx.remove();
            int indy = qy.remove();
            int c = count.remove();

            int ix = indx;
            int iy = indy;
            while(((ix-1)>=0) && (arr[iy].charAt(ix-1)!='X'))
            {
                ix-=1;
                if((!isVisited[ix][iy]))
                {
                    qx.add(ix);
                    qy.add(iy);
                    count.add(c+1);
                    isVisited[ix][iy] = true;
                }
            }

            ix = indx;
            iy = indy;
            while(((ix+1)<n) && (arr[iy].charAt(ix+1)!='X'))
            {
                ix+=1;
                if((!isVisited[ix][iy]))
                {
                    qx.add(ix);
                    qy.add(iy);
                    count.add(c+1);
                    isVisited[ix][iy] = true;
                }
            }

            ix = indx;
            iy = indy;
            while(((iy-1)>=0) && (arr[iy-1].charAt(ix)!='X'))
            {
                iy-=1;
                if((!isVisited[ix][iy]))
                {
                    qx.add(ix);
                    qy.add(iy);
                    count.add(c+1);
                    isVisited[ix][iy] = true;
                }
            }

            ix = indx;
            iy = indy;
            while(((iy+1)<n) && (arr[iy+1].charAt(ix)!='X'))
            {
                iy+=1;
                if((!isVisited[ix][iy]))
                {
                    qx.add(ix);
                    qy.add(iy);
                    count.add(c+1);
                    isVisited[ix][iy] = true;
                }
            }

            if(isVisited[endx][endy])
                break;
        }

        while(true)
        {
            int ix = qx.remove();
            int iy = qy.remove();
            int cx = count.remove();
            if(ix==endx && iy==endy)
            {
                System.out.println(cx);
                break;
            }
        }
    }
}
