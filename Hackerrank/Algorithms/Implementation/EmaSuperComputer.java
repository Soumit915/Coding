package Hackerrank.Algorithms.Implementation;

import java.util.*;

public class EmaSuperComputer
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] matrix = new char[n][m];
        for(int i=0;i<n;i++)
        {
            String s = sc.next();
            matrix[i] = s.toCharArray();
        }

        int max = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j]=='B')
                    continue;
                int len=0;
                int sgl=1;
                boolean[][] isVisited = new boolean[n][m];
                isVisited[i][j] = true;
                while(true)
                {
                    if(i-len-1>=0 && matrix[i-len-1][j]=='G' && i+len+1<n && matrix[i+len+1][j]=='G'
                            && j-len-1>=0 && matrix[i][j-len-1]=='G' && j+len+1<m && matrix[i][j+len+1]=='G')
                    {
                        len++;
                        isVisited[i-len][j] = true;
                        isVisited[i+len][j] = true;
                        isVisited[i][j-len] = true;
                        isVisited[i][j+len] = true;
                        sgl += 4;

                        for(int i1=i;i1<n;i1++)
                        {
                            for(int j1=0;j1<m;j1++)
                            {
                                if(matrix[i1][j1]=='B' || isVisited[i1][j1])
                                    continue;
                                int len1=0;
                                int sgl1;
                                while(true)
                                {
                                    if(i1-len1-1>=0 && matrix[i1-len1-1][j1]=='G' && !isVisited[i1-len1-1][j1]
                                    && i1+len1+1<n && matrix[i1+len1+1][j1]=='G' && !isVisited[i1+len1+1][j1]
                                    && j1-len1-1>=0 && matrix[i1][j1-len1-1]=='G' && !isVisited[i1][j1-len1-1]
                                    && j1+len1+1<m && matrix[i1][j1+len1+1]=='G' && !isVisited[i1][j1+len1+1])
                                    {
                                        len1++;
                                    }
                                    else
                                    {
                                        break;
                                    }
                                }
                                sgl1 = len1*4+1;
                                //System.out.println(sgl+" "+sgl1);
                                if(sgl1*sgl>max)
                                {
                                    max = sgl1*sgl;
                                }
                            }
                        }
                    }
                    else
                    {
                        break;
                    }
                }

                for(int i1=i;i1<n;i1++)
                {
                    for(int j1=0;j1<m;j1++)
                    {
                        if(matrix[i1][j1]=='B' || isVisited[i1][j1])
                            continue;
                        int len1=0;
                        int sgl1;
                        while(true)
                        {
                            if(i1-len1-1>=0 && matrix[i1-len1-1][j1]=='G' && !isVisited[i1-len1-1][j1]
                                    && i1+len1+1<n && matrix[i1+len1+1][j1]=='G' && !isVisited[i1+len1+1][j1]
                                    && j1-len1-1>=0 && matrix[i1][j1-len1-1]=='G' && !isVisited[i1][j1-len1-1]
                                    && j1+len1+1<m && matrix[i1][j1+len1+1]=='G' && !isVisited[i1][j1+len1+1])
                            {
                                len1++;
                            }
                            else
                            {
                                break;
                            }
                        }
                        sgl1 = len1*4+1;
                        //System.out.println(sgl+" "+sgl1);
                        if(sgl1*sgl>max)
                        {
                            max = sgl1*sgl;
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }
}