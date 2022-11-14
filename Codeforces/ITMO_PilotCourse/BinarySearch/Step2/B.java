package Codeforces.ITMO_PilotCourse.BinarySearch.Step2;

import java.util.*;

public class B {
    public static boolean isValid(long[] arr, int k, double len)
    {
        int count = 0;
        for (long l : arr) {
            if (count >= k)
                return true;
            count += (l / len);
        }

        return (count >= k);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        long[] arr = new long[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextLong();
        }

        double l = 0;
        double r = (long) 1e8;

        double factor = 0.00000001;
        while(r-l>factor)
        {
            double mid =  (l+r)/2;
            if(isValid(arr, k, mid))
            {
                l = mid;
            }
            else
            {
                r = mid;
            }
        }

        System.out.println(l);
    }
}
