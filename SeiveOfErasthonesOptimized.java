//It is the modified version of SeiveOfErasthonos that has a complexity of O(n)

import java.util.*;

public class SeiveOfErasthonesOptimized {
    static ArrayList<Boolean> isPrime;
    static ArrayList<Integer> primes = new ArrayList<>();
    static ArrayList<Integer> spf;
    public static void computePrimes(int n)
    {
        isPrime.set(0,false);
        isPrime.set(1,false);

        for(int i=2;i<n;i++)
        {
            if(isPrime.get(i))
            {
                primes.add(i);
                spf.set(i, i);
            }

            for(int j=0;j<primes.size() && i*primes.get(j)<n && primes.get(j)<=spf.get(i);j++)
            {
                isPrime.set(i*primes.get(j), false);
                spf.set(i*primes.get(j), primes.get(j));
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the limit: ");
        int n = sc.nextInt();

        n+=1;
        isPrime = new ArrayList<>(n);
        spf = new ArrayList<>(n);
        for(int i=0;i<n;i++)
        {
            isPrime.add(true);
            spf.add(2);
        }

        computePrimes(n);

        StringBuilder sb = new StringBuilder();
        for(int i: primes){
            sb.append(i).append(" ");
        }

        System.out.println(primes);
    }
}
