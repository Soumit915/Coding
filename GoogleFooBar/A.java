package GoogleFooBar;

import java.util.ArrayList;
import java.util.Scanner;

public class A{
    public static String solution(int index) {
        ArrayList<Boolean> isPrime;
        ArrayList<Integer> primes = new ArrayList<>();
        ArrayList<Integer> spf;

        int n = 50000;

        isPrime = new ArrayList<>(n);
        spf = new ArrayList<>(n);
        for(int i=0;i<n;i++)
        {
            isPrime.add(true);
            spf.add(2);
        }

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

        StringBuilder sb = new StringBuilder();
        for(int prime: primes){
            sb.append(prime);
        }
        String s = sb.toString();

        return s.substring(index, index+5);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(solution(n));
    }
}