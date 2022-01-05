package Hackerrank.Mathematics.NumberTheory;

import java.util.*;

public class BreakingSticks
{
    static ArrayList<Long> primes = new ArrayList<>();
    public static void preComputePrimes()
    {
        int n = 1000005;
        long[] primearr = new long[n];
        for(int i=0;i<n;i++)
        {
            primearr[i] = i;
        }
        primearr[0] = 1;
        for(int i=2;i<n;i++)
        {
            if(primearr[i]==1)
                continue;
            for(int j=2*i;j<n;j+=i)
            {
                primearr[j] = 1;
            }
        }

        for(int i=0;i<n;i++)
        {
            if(primearr[i] != 1)
                primes.add((long) i);
        }
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

        preComputePrimes();

        long totalcount=0;
        for(int i=0;i<n;i++)
        {
            long val = arr[i];
            long count=0;
            for(int j=0;j<primes.size()&&val>1;j++)
            {
                long p = primes.get(j);
                while(val>1 && val%p==0)
                {
                    count+=(val/p);
                    val/=p;
                }
            }
            if(val>1)
                count++;
            totalcount += count+arr[i];
        }

        System.out.println(totalcount);
    }
}