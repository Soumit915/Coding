package GoogleKickStart.RoundA_2019;

import java.util.*;

public class Parcels {
    static int r, c;
    public static boolean isValid(int x, int y)
    {
        return (x>=0 && x<r && y>=0 && y<c);
    }
    public static boolean check(char[][] matrix, int[][] dist, int k)
    {
        int maxsum = -1, minsum = 100000000;
        int maxdiff = Integer.MIN_VALUE, mindiff = Integer.MAX_VALUE;
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                if(dist[i][j]>k && matrix[i][j]!='1')
                {
                    maxsum = Math.max(i+j, maxsum);
                    minsum = Math.min(i+j, minsum);
                    mindiff = Math.min(i-j, mindiff);
                    maxdiff = Math.max(i-j, maxdiff);
                }
            }
        }

        boolean flag = false;
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                if(Math.abs(maxsum-i-j)<=k && Math.abs(minsum-i-j)<=k &&
                        Math.abs(maxdiff-(i-j))<=k && Math.abs(mindiff-(i-j))<=k)
                {
                    return true;
                }
            }
        }

        return flag;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int testi=1;testi<=t;testi++)
        {
            r = sc.nextInt();
            c = sc.nextInt();
            char[][] matrix = new char[r][c];
            for(int i=0;i<r;i++)
            {
                String s = sc.next();
                matrix[i] = s.toCharArray();
            }

            Queue<Integer> qx = new LinkedList<>();
            Queue<Integer> qy = new LinkedList<>();
            boolean[][] isVisited = new boolean[r][c];
            int[][] dist = new int[r][c];
            for(int i=0;i<r;i++)
            {
                for(int j=0;j<c;j++)
                {
                    if(matrix[i][j]=='1')
                    {
                        qx.add(i);
                        qy.add(j);
                        isVisited[i][j] = true;
                        dist[i][j] = 0;
                    }
                }
            }

            while(!qx.isEmpty())
            {
                int cx = qx.remove();
                int cy = qy.remove();

                if(isValid(cx+1,cy) && !isVisited[cx+1][cy])
                {
                    dist[cx+1][cy] = 1+dist[cx][cy];
                    isVisited[cx+1][cy] = true;
                    qx.add(cx+1);
                    qy.add(cy);
                }
                if(isValid(cx-1,cy) && !isVisited[cx-1][cy])
                {
                    dist[cx-1][cy] = 1+dist[cx][cy];
                    isVisited[cx-1][cy] = true;
                    qx.add(cx-1);
                    qy.add(cy);
                }
                if(isValid(cx,cy+1) && !isVisited[cx][cy+1])
                {
                    dist[cx][cy+1] = 1+dist[cx][cy];
                    isVisited[cx][cy+1] = true;
                    qx.add(cx);
                    qy.add(cy+1);
                }
                if(isValid(cx,cy-1) && !isVisited[cx][cy-1])
                {
                    dist[cx][cy-1] = 1+dist[cx][cy];
                    isVisited[cx][cy-1] = true;
                    qx.add(cx);
                    qy.add(cy-1);
                }
            }

            int high = -1;
            for(int i=0;i<r;i++)
            {
                for(int j=0;j<c;j++)
                {
                    high = Math.max(high, dist[i][j]);
                }
            }

            int low=0;
            while(low<high)
            {
                int mid = (low+high)/2;
                if(check(matrix, dist, mid))
                {
                    high = mid;
                }
                else
                {
                    low = mid+1;
                }
            }

            System.out.println("Case #"+testi+": "+low);
        }
    }
}
