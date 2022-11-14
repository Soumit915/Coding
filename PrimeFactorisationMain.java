import java.util.*;

public class PrimeFactorisationMain {

    static class Primes
    {
        int primeNo;
        int freq;
        Primes(int primeNo, int freq)
        {
            this.primeNo = primeNo;
            this.freq = freq;
        }
    }


    static ArrayList<Integer> primes = new ArrayList<>();
    static TreeSet<Integer> factors = new TreeSet<>();
    public static void preCalculatePrimes(int n)
    {
        int s = (int) Math.sqrt(n);
        primes.add(2);
        for(int i=3;i<s;i++)
        {
            boolean flag = true;
            for (int v : primes) {
                if (i % v == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                primes.add(i);
        }
    }
    public static void generateFactors(ArrayList<Primes> primefactorization, int factor, int index)
    {
        factors.add(factor);

        if(index == primefactorization.size())
            return;

        int val = primefactorization.get(index).primeNo;
        int count = primefactorization.get(index).freq;
        for(int i=0;i<count;i++)
        {
            generateFactors(primefactorization, factor, index+1);
            factor = factor*val;
        }

        generateFactors(primefactorization, factor, index+1);
    }
    public static void calculatePrimes(int n)
    {
        ArrayList<Primes> primefactorization = new ArrayList<>();
        int p = n;
        primefactorization.add(new Primes(1, 1));
        for(int i: primes)
        {
            if(p<=1)
                break;
            int count = 0;
            while(p%i==0)
            {
                p = p/i;
                count++;
            }
            if(count!=0)
                primefactorization.add(new Primes(i, count));
        }

        for(Primes primenumbers: primefactorization)
        {
            System.out.println(primenumbers.primeNo+" "+primenumbers.freq);
        }

        generateFactors(primefactorization,1,0);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number : ");
        int n = sc.nextInt();

        preCalculatePrimes(n);

        calculatePrimes(n);

        for(int i: factors)
            System.out.print(i+" ");
        System.out.println();
    }
}
