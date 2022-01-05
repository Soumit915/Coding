package Hackerrank.Algorithms.BitManipulation;

import java.util.*;
public class XorSequence
{
    public static long bruteforce(long l, long r)
    {
        long sl;
        if(l%4==0) sl = l;
        else if(l%4==1) sl = 1;
        else if(l%4==2) sl = l+1;
        else sl = 0;

        long xor = sl;
        long prev = sl;
        for(long i=l+1;i<=r;i++)
        {
            prev = prev ^ i;
            xor = xor ^ prev;
        }

        return xor;
    }
    public static long optimal(long l, long r)
    {
        long sl;
        if(l%4==0)    sl = 2;
        else if(l%4==3) sl = 0;
        else sl = l+1;

        long rl;
        if(r%4==3 || r%4==2) rl = 2;
        else rl = r;

        l = ((l/4)+1)*4-1;
        r = (r/4)*4-1;
        if(((l-r)/4)%2==0)
            return rl^sl;
        else return rl^sl^2;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            long l = sc.nextLong();
            long r = sc.nextLong();

            if(r-l<=10)
                System.out.println(bruteforce(l, r));
            else System.out.println(optimal(l,r));
        }
    }
}

