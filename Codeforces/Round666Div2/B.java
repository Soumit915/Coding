package Codeforces.Round666Div2;

import java.util.*;

public class B {
    public static long pow(long a, long b)
    {
        long p = 1;
        while(b>0)
        {
            if(b%2==1)
            {
                p = (p*a);
            }
            a = (a*a);
            b/=2;
        }
        return p;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[] arr = new long[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextLong();
        }

        Arrays.sort(arr);


        double d = 1.0/(n-1);
        long maxvalue = 1000000000000L;
        long limit = (long) Math.pow(maxvalue, d);
        long max = Long.MAX_VALUE;
        for(long j=limit;j>0;j--)
        {

            long count = 0;
            for(int i=0;i<n;i++)
            {
                count += Math.abs(arr[i]- pow(j, i));
            }

            max = Math.min(count, max);
        }


        System.out.println(max);
    }
}
