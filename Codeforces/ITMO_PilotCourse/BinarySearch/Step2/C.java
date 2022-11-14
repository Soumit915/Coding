package Codeforces.ITMO_PilotCourse.BinarySearch.Step2;

import java.util.*;

public class C {
    public static boolean isValid(long n, long x, long y, long t)
    {
        n--;
        t -= x;
        long count = 0;
        count += (t / x);
        count += (t / y);

        return count>=n;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long x = sc.nextLong();
        long y = sc.nextLong();

        if(x>y)
            x = y+x-(y = x);

        if(n<3)
        {
            System.out.println(n*x);
            System.exit(0);
        }

        long l = 1;
        long r = (long) 1e12;
        while(l<r){
            long mid = (l+r)/2;
            if(isValid(n, x, y, mid)){
                r = mid;
            }
            else
            {
                l = mid + 1;
            }
        }

        System.out.println(l);
    }
}
