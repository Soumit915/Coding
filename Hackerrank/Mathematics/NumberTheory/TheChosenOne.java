//Computing prefix-GCD and suffix-GCD and then find the answer

package Hackerrank.Mathematics.NumberTheory;

import java.util.*;

public class TheChosenOne {
    public static long gcd(long a, long b)
    {
        if(a%b==0)
            return b;
        return gcd(b, a%b);
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

        long[] prefixGCD = new long[n];
        long[] suffixGCD = new long[n];

        prefixGCD[0] = arr[0];
        for (int i=1;i<n;i++)
        {
            prefixGCD[i] = gcd(arr[i], prefixGCD[i-1]);
        }

        suffixGCD[n-1] = arr[n-1];
        for(int i=n-2;i>=0;i--)
        {
            suffixGCD[i] = gcd(arr[i], suffixGCD[i+1]);
        }

        if(arr[0]%suffixGCD[1]!=0)
        {
            System.out.println(suffixGCD[1]);
        }
        else if(arr[n-1]%prefixGCD[n-2]!=0)
        {
            System.out.println(prefixGCD[n-2]);
        }
        else
        {
            for(int i=1;i<n-1;i++)
            {
                if(arr[i]%gcd(prefixGCD[i-1], suffixGCD[i+1])!=0)
                {
                    System.out.println(gcd(prefixGCD[i-1], suffixGCD[i+1]));
                }
            }
        }
    }
}
