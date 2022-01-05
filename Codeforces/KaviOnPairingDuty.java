package Codeforces;

import java.io.*;
import java.util.*;

public class KaviOnPairingDuty {
    static long mod = 998244353;

    static ArrayList<Boolean> isPrime;
    static ArrayList<Integer> primes;
    static ArrayList<Integer> spf;
    static void preComputePrimes(int n){
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
                primes.add(i);
                spf.set(i, i);
            }

            for(int j=0;j<primes.size() && primes.get(j)<=spf.get(i) && primes.get(j)*i<n;j++){
                isPrime.set(i*primes.get(j), false);
                spf.set(i*primes.get(j), primes.get(j));
            }
        }
    }

    static long getNumberOfDivisors(int n){
        Map<Integer, Integer> map = new HashMap<>();

        while(n > 1){
            int i = spf.get(n);
            map.put(i, map.getOrDefault(i, 0)+1);

            n /= i;
        }

        long p = 1;
        for(int i : map.keySet()){
            p *= (map.get(i)+1);
        }

        return p;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        preComputePrimes(n+10);

        long[] primesDp = new long[spf.size()];

        primesDp[1] = 1;
        for(int i=2;i<spf.size();i++){
            if(isPrime.get(i)){
                primesDp[i] = 2;
            }
            else{
                primesDp[i] = getNumberOfDivisors(i);
            }
        }

        long sum = 1;
        long last = 1;
        for(int i=1;i<n;i++){
            last = (primesDp[i+1] + sum)%mod;
            sum = (sum + last)%mod;
        }

        System.out.println(last);

        sc.close();
    }
}
