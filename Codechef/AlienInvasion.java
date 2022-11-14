package Codechef;

import java.util.*;

public class AlienInvasion {
    public static int check(long[] arr, long t, long d)
    {
        int n = arr.length;
        long nlt = arr[0]+t;
        for(int i=1;i<n;i++)
        {
            if(nlt<arr[i])
            {
                nlt = arr[i];
            }
            else if(nlt>(arr[i]+d))
            {
                return 1;
            }
            nlt += t;
        }

        return 0;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            long d = sc.nextLong();

            long[] arr = new long[n];
            long cf = (long) 1e8;
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextLong()*cf;
            }

            Arrays.sort(arr);
            d *= cf;

            long l=0;
            long r=(long) 1e18;
            r += 20;
            while(l<r)
            {
                long mid = (l+r)/2;
                int x = check(arr, mid, d);
                if(x==-1)
                {
                    l = mid+1;
                }
                else if(x==1)
                {
                    r = mid-1;
                }
                else
                {
                    l = mid+1;
                }
            }

            double maxTime = l;
            maxTime /= cf;
            System.out.printf("%.9f\n", maxTime);
        }
    }
}
