package Codeforces.ITMO_PilotCourse.BinarySearch.Step2;

import java.util.*;

public class A {
    public static boolean isValid(long n, long w, long h, long x)
    {
        long x2 = (x/w)*(x/h);
        return (x2>=n);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long w = sc.nextLong();
        long h = sc.nextLong();
        long n = sc.nextLong();

        long l=1;

        long r = 2;
        while(!isValid(n, w, h, r))
        {
            r = r*2;
        }

        while (l<r)
        {
            long mid = (l+r)/2;
            if(isValid(n, w, h, mid)){
                r = mid;
            }
            else{
                l = mid+1;
            }
        }

        System.out.println(l);
    }
}
