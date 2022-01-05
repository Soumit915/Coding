package Hackerrank.Algorithms.Searching;

import java.util.*;
public class BeautifulQuads
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[4];
        for(int i=0;i<4;i++)
        {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int a = arr[0];
        int b = arr[1];
        int c = arr[2];
        int d = arr[3];

        int[][] hashxor = new int[c+1][4097];
        for(int i=1;i<=c;i++)
        {
            for(int j=i;j<=d;j++)
            {
                int xor = i^j;
                hashxor[i][xor]++;
            }
        }

        for(int i=0;i<4097;i++)
        {
            for(int j=1;j<=c;j++)
            {
                hashxor[j][i] += hashxor[j-1][i];
            }
        }

        long count = 0;
        for(int i=1;i<=a;i++)
        {
            for(int j=i;j<=b;j++)
            {
                int curxor = i ^ j;
                int addxor = ((d+1)*(c-j+1)) - ((c*(c+1))/2) + ((j*(j-1))/2);
                int tobeSub = (hashxor[c][curxor] - hashxor[j-1][curxor]);
                count += (addxor - tobeSub);
            }
        }

        System.out.println(count);
    }
}

