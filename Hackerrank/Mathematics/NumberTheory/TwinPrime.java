//Given a range 1e9 find the number of twin-primes, i.e., primes with absolute difference of 2

package Hackerrank.Mathematics.NumberTheory;

import java.util.*;

public class TwinPrime {
    static ArrayList<Integer> primes = new ArrayList<>();
    static int[] calp = new int[100000+1];
    public static void preCalculatePrimes()
    {
        for(int i=0;i<=100000;i++)
        {
            calp[i] = i;
        }
        calp[0] = 1;

        for(int i=2;i<=100000;i++)
        {
            if(calp[i]==1)
                continue;
            for(int j=i*2;j<=100000;j+=i)
            {
                calp[j] = 1;
            }
        }

        for(int i=1;i<=100000;i++)
        {
            if(calp[i]!=1)
                primes.add(i);
        }
    }
    public static boolean isPrime(int n)
    {
        if(n<=100000)
        {
            //System.out.println("test"+n+" "+calp[n]);
            return (calp[n] != 1);
        }
        for (int p : primes) {
            if (n % p == 0)
                return false;
        }

        return true;
    }
    public static int solve(int a, int b)
    {
        int count = 0;
        for(int i=a;i<=b-2;i++)
        {
            if(isPrime(i)&&isPrime(i+2))
            {
                count++;
            }
        }

        return count;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        preCalculatePrimes();

        int count = solve(a, b);
        System.out.println(count);
    }
}
