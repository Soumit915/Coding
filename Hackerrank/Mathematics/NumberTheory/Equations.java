package Hackerrank.Mathematics.NumberTheory;

import java.util.*;

public class Equations {
    static ArrayList<Integer> primes = new ArrayList<>();
    static int[] spf;
    static boolean[] isPrime;
    public static void computeSPF(int n)
    {
        isPrime[0] = false;
        isPrime[1] = false;

        for(int i=0;i<n;i++)
        {
            if(isPrime[i])
            {
                primes.add(i);
                spf[i] = i;
            }

            for(int j=0;j<primes.size() && i*primes.get(j)<n && primes.get(j)<=spf[i];j++)
            {
                spf[i*primes.get(j)] = primes.get(j);
                isPrime[i*primes.get(j)] = false;
            }
        }
    }
    public static int computeSolution(int n)
    {
        long[] freq = new long[n];
        for(int i=0;i<n;i++)
        {
            int val = i;
            while (val>1)
            {
                freq[spf[val]]++;
                val /= spf[val];
            }
        }

        long ans = 1;
        for(int i=0;i<n;i++)
        {
            ans = (ans*(2*freq[i]+1))%1000007;
        }

        return (int) ans;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        spf = new int[n+1];
        isPrime = new boolean[n+1];

        for(int i=0;i<=n;i++)
        {
            isPrime[i] = true;
            spf[i] = 2;
        }

        computeSPF(n+1);
        System.out.println(computeSolution(n+1));

    }
}
