package Codeforces.Round670Div2;

import java.util.*;

public class E {
    static ArrayList<Integer> primes;
    public static void preComputePrimes(int n)
    {
        int[] parr = new int[n+1];
        for(int i=0;i<=n;i++)
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

        for(int i=0;i<=n;i++)
        {
            if(parr[i]!=1)
                primes.add(i);
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int input;
        primes = new ArrayList<>();
        preComputePrimes(n);
        int numberOfPrimes = primes.size();

        int sqrt = (int) Math.sqrt(n);
        int smallPrimes=0;
        for(int i: primes)
        {
            if(i>sqrt)
                break;
            System.out.println("B "+ i);
            System.out.flush();
            input = sc.nextInt();
            smallPrimes++;
        }

        int bigPrimes = numberOfPrimes-smallPrimes;
        System.out.println("A 1");
        System.out.flush();
        input = sc.nextInt();
        input -= 1;

        int ans = 1;
        if(input-bigPrimes==1)
        {
            //Compute for small primes
            ArrayList<Integer> primeFactor = new ArrayList<>();
            for(int ind=0;ind<smallPrimes;ind++)
            {
                int i = primes.get(ind);
                System.out.println("A "+i);
                System.out.flush();
                int k = sc.nextInt();
                //int k = chk.println('A', i);
                if(k==1)
                    primeFactor.add(i);
            }

            for(int i: primeFactor)
            {
                int counter = 1;
                while(true) {
                    if((i*counter) > n)
                        break;
                    //int k = chk.println('A', (i*counter));
                    System.out.println("A "+(i*counter));
                    System.out.flush();
                    int k = sc.nextInt();
                    counter = counter*i;
                    if(k==0)
                        break;
                    else ans *= i;
                }
            }
        }

        int start = smallPrimes;
        int bsize = 100;
        int prevCount = input;

        while(true)
        {
            if(start+bsize>=primes.size())
            {
                for(int i=start;i<primes.size();i++)
                {
                    int pf = primes.get(i);
                    System.out.println("B "+pf);
                    System.out.flush();
                    input = sc.nextInt();

                    System.out.println("A "+pf);
                    System.out.flush();
                    input = sc.nextInt();
                    if(input==1)
                    {
                        System.out.println("C "+(pf*ans));
                        System.out.flush();
                        System.exit(0);
                        return;
                    }
                }
                break;
            }

            int segcount = 0;
            for(int i=start;i<start+bsize;i++)
            {
                int pf = primes.get(i);
                System.out.println("B "+pf);
                System.out.flush();
                input = sc.nextInt();
                segcount += input;
            }
            System.out.println("A 1");
            System.out.flush();
            input = sc.nextInt();
            input -= 1;

            int store = input;
            if(input+segcount != prevCount)
            {
                for(int i=start;i<start+bsize;i++)
                {
                    int pf = primes.get(i);
                    System.out.println("A "+pf);
                    System.out.flush();
                    input = sc.nextInt();

                    if(input==1)
                    {
                        System.out.println("C "+(pf*ans));
                        System.out.flush();
                        System.exit(0);
                        return;
                    }
                }
                break;
            }

            start += bsize;
            prevCount = store;
        }

        System.out.println("C "+ans);
        System.out.flush();
    }
}