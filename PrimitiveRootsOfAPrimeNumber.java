//Problem in Hackerrank->Maths->Number Theory->Primitive Problem

import java.util.*;

public class PrimitiveRootsOfAPrimeNumber {
    static ArrayList<Integer> primes = new ArrayList<>();
    static ArrayList<Integer> primeFactors = new ArrayList<>();
    public static void preComputePrimes(int n)
    {
        int[] primearr = new int[n];
        for(int i=1;i<n;i++)
        {
            primearr[i] = i;
        }
        primearr[0] = 1;

        for(int i=1;i*i<n;i++)
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
            if(primearr[i]!=1)
                primes.add(i);
        }
    }
    public static void computePrimeFactors(int n)
    {
        for(int i=0;i<primes.size()&&n>1;i++)
        {
            if(n%primes.get(i)==0)
            {
                primeFactors.add(primes.get(i));
                while (n%primes.get(i)==0)
                {
                    n/=primes.get(i);
                }
            }
        }

        if(n>1)
        {
            primeFactors.add(n);
        }
    }
    public static int computePhi(int n)
    {
        for (Integer primeFactor : primeFactors) {
            n -= n / primeFactor;
        }
        return n;
    }
    public static int power(long a, long b, long p)
    {
        long pow = 1;
        while(b>0)
        {
            if(b%2==1)
            {
                pow = (pow*a)%p;
            }
            a = (a*a)%p;
            b/=2;
        }

        return (int) pow;
    }
    public static int findSmallestPrimitiveRoot(int n)
    {
        int i=1;
        while(true)
        {
            i++;
            boolean flag = true;
            for(int primeFactor: primeFactors)
            {
                if(power(i, n/primeFactor, n)==1)
                {
                    flag = false;
                    break;
                }
            }
            if(flag)
                return i;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        preComputePrimes((int) Math.sqrt(n)+10);
        computePrimeFactors(n-1);
        System.out.print(findSmallestPrimitiveRoot(n)+" ");
        System.out.println(computePhi(n-1));
    }
}
