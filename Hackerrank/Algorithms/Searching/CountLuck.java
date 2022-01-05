package Hackerrank.Algorithms.Searching;

import java.util.*;

public class CountLuck
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();

            char[][] map = new char[n][m];
            for(int i=0;i<n;i++)
            {
                map[i] = sc.next().toCharArray();
            }
            int k = sc.nextInt();

            boolean[][] isVisited = new boolean[n][m];
            int sourcex=-1, sourcey=-1;
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    if(map[i][j]=='M')
                    {
                        sourcex = i;
                        sourcey = j;
                    }
                }
            }

            Stack<Integer> stkx = new Stack<>();
            Stack<Integer> stky = new Stack<>();
            Stack<Integer> stkcount = new Stack<>();
            stkx.push(sourcex);stky.push(sourcey);stkcount.push(0);
            isVisited[sourcex][sourcey] = true;
            boolean flag = false;
            while(!stkx.isEmpty())
            {
                int x = stkx.pop();
                int y = stky.pop();
                int count = stkcount.pop();
                int c=0;
                if(x-1>=0 && map[x-1][y]!='X' && !isVisited[x-1][y])
                {
                    if(map[x-1][y]=='*')
                        flag = true;
                    stkx.push(x-1);
                    stky.push(y);
                    isVisited[x-1][y] = true;
                    c++;
                }
                if(x+1<n && map[x+1][y]!='X' && !isVisited[x+1][y])
                {
                    if(map[x+1][y]=='*')
                        flag = true;
                    stkx.push(x+1);
                    stky.push(y);
                    isVisited[x+1][y] = true;
                    c++;
                }
                if(y-1>=0 && map[x][y-1]!='X' && !isVisited[x][y-1])
                {
                    if(map[x][y-1]=='*')
                        flag = true;
                    stkx.push(x);
                    stky.push(y-1);
                    isVisited[x][y-1] = true;
                    c++;
                }
                if(y+1<m && map[x][y+1]!='X' && !isVisited[x][y+1])
                {
                    if(map[x][y+1]=='*')
                        flag = true;
                    stkx.push(x);
                    stky.push(y+1);
                    isVisited[x][y+1] = true;
                    c++;
                }

                if(c==1)
                {
                    stkcount.push(count);
                }
                else
                {
                    for(int i=1;i<=c;i++)
                    {
                        stkcount.push(count+1);
                    }
                }
                if(flag)
                    break;
            }

            if(stkcount.pop()==k)
                System.out.println("Impressed");
            else
                System.out.println("Oops!");
        }
    }
}