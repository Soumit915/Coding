package Hackerrank.Algorithms.BitManipulation;

import java.util.*;

public class TheGreatXor
{
    public static long prevpower(long x)
    {
        if((x&(x-1))==0)
            return x>>1;
        x = x|x>>1;
        x = x|x>>2;
        x = x|x>>4;
        x = x|x>>8;
        x = x|x>>16;
        x = x|x>>32;

        x = x+1;
        x = x>>1;
        return x;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            long x = sc.nextLong();

            long count = 0;
            long x1 = x;
            while(x1>1)
            {
                long n = prevpower(x1);
                if((n^x)>x)
                {
                    count += x1-prevpower(x1);
                }
                x1 = prevpower(x1);
            }

            System.out.println(count);
        }
    }
}
