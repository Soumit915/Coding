package Hackerrank.Algorithms.Strings;

import java.util.*;

public class SherlockAndAnagrams
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();

        while(q-->0)
        {
            String s = sc.next();
            char[] str = s.toCharArray();
            int l = s.length();

            int[][] prefixfreq = new int[26][l+1];
            for(int i=0;i<26;i++)
            {
                prefixfreq[i][0] = 0;
            }
            for(int i=1;i<=l;i++)
            {
                for(int j=0;j<26;j++)
                {
                    if(str[i-1]-97==j)
                    {
                        prefixfreq[j][i] = 1+prefixfreq[j][i-1];
                    }
                    else
                    {
                        prefixfreq[j][i] = prefixfreq[j][i-1];
                    }
                }
            }

            long count=0;
            for(int i=l-1;i>1;i--)
            {
                for(int j=i;j<=l;j++)
                {
                    for(int k=j+1;k<=l;k++)
                    {
                        boolean flag = true;
                        for(int alphai=0;alphai<26;alphai++)
                        {
                            if(prefixfreq[alphai][j]-prefixfreq[alphai][j-i] !=
                                    prefixfreq[alphai][k]-prefixfreq[alphai][k-i])
                            {
                                flag = false;
                                break;
                            }
                        }
                        if(flag)
                            count++;
                    }
                }
            }

            for(int i=0;i<26;i++)
            {
                int val = prefixfreq[i][l];
                count += (val*(val-1))/2;
            }

            System.out.println(count);
        }
    }
}