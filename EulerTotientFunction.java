import java.util.*;

public class EulerTotientFunction
{
    static ArrayList<Boolean> isPrime;
    static ArrayList<Integer> primes;
    static ArrayList<Integer> spf;
    static void preComputePrimes(int n){
        n += 10;
        isPrime = new ArrayList<>(n);
        primes = new ArrayList<>();
        spf = new ArrayList<>(n);

        for(int i=0;i<n;i++){
            isPrime.add(true);
            spf.add(2);
        }

        isPrime.set(0, false);
        isPrime.set(1, false);

        for(int i=2;i<n;i++){
            if(isPrime.get(i)){
                spf.set(i, i);
                primes.add(i);
            }

            for(int j=0;j<primes.size() && primes.get(j) <= spf.get(i) && primes.get(j)*i<n;j++){
                isPrime.set(primes.get(j)*i, false);
                spf.set(primes.get(j)*i, primes.get(j));
            }
        }
    }

    static Set<Integer> getPrimeFactors(int n){
        Set<Integer> set = new HashSet<>();
        while(n>1){
            int prime = spf.get(n);
            set.add(prime);
            n /= prime;
        }

        return set;
    }

    static int eulerTotient(int n){
        Set<Integer> pfactors = getPrimeFactors(n);

        for(int i: pfactors){
            n = n - (n / i);
        }

        return n;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number : ");
        int n = sc.nextInt();
        int root_n = (int) Math.sqrt(n);
        preComputePrimes(root_n);

        int etf = eulerTotient(n);
        System.out.println("The euler totient value of n is : "+etf);
    }
}
