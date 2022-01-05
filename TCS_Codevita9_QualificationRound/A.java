package TCS_Codevita9_QualificationRound;

import java.util.*;

public class A {
    public static int countempty(char[][] wall, int starti)
    {
        int n = wall.length;
        for(int i=1;i<=n-starti;i++)
        {
            boolean flag = true;
            for(int j=0;j<i && j<n;j++)
            {
                for(int k=starti;k<(starti+i) && k<n;k++)
                {
                    if(wall[j][k]!='-')
                    {
                        flag = false;
                        break;
                    }
                }
            }
            if(!flag)
            {
                return i-1;
            }
        }
        return n-starti;
    }
    public static int findHeatedFurnace(char[][] wall)
    {
        int n = wall.length;
        char[][] newwall = new char[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                newwall[i][j] = '-';
            }
        }

        for(int i=0;i<n;i++)
        {
            int k=n-1;
            for(int j=n-1;j>=0;j--)
            {
                if(wall[j][i]=='C')
                {
                    newwall[k][i] = 'C';
                    k--;
                }
            }
        }

        /*for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(newwall[i][j]+" ");
            }
            System.out.println();
        }*/

        int max = 0;
        for(int i=0;i<n;i++)
        {
            if(newwall[0][i]=='-')
            {
                max = Math.max(max, countempty(newwall, i));
                //System.out.println("test: "+i+" "+max);
            }
        }
        return max;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        char[][] wall = new char[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                wall[i][j] = sc.next().charAt(0);
            }
        }

        int max = findHeatedFurnace(wall);
        char[][] rotatedwall = new char[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                rotatedwall[i][j] = wall[j][i];
            }
        }

        wall = rotatedwall;
        max = Math.max(findHeatedFurnace(wall), max);

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                rotatedwall[i][j] = wall[j][i];
            }
        }

        wall = rotatedwall;
        max = Math.max(findHeatedFurnace(wall), max);

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                rotatedwall[i][j] = wall[j][i];
            }
        }

        wall = rotatedwall;
        max = Math.max(findHeatedFurnace(wall), max);

        System.out.println(max);
    }
}
