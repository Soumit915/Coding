package Codeforces.Round671Div2;

import java.util.*;

public class E {
    static ArrayList<Integer> primelist = new ArrayList<>();
    static HashMap<Integer, Integer> primes = new HashMap<>();
    static HashMap<Integer, Boolean> divisors = new HashMap<>();
    public static void preComputePrimes()
    {
        int n = 100000;
        int[] parr = new int[n+1];
        for(int i=0;i<n;i++)
        {
            parr[i] = i;
        }
        parr[0] = 1;
        for(int i=2;i<=n;i++)
        {
            if(parr[i]==1)
                continue;
            for(int j=2*i;j<=n;j+=i)
            {
                parr[j] = 1;
            }
        }

        for(int i=0;i<n;i++)
        {
            if(parr[i]!=1)
                primelist.add(i);
        }
    }
    public static void findPrimes(int n)
    {
        for(int i=0;i<n;i++)
        {
            int val = primelist.get(i);
            int count = 0;
            while(n%val==0)
            {
                count++;
            }

            primes.put(val, count);
        }

        if(n>1)
            primes.put(n, 1);
    }
    public static void findDivisors(int n)
    {
        int lim = (int) Math.sqrt(n);
        for(int i=2;i<=lim;i++)
        {
            if(n%i==0) {
                divisors.put(i, false);
                divisors.put(n/i, false);
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            findPrimes(n);
            findDivisors(n);

            ArrayList<Integer> ans = new ArrayList<>();

            for(int i=0;i<n;i++)
            {

            }
        }
    }
}
