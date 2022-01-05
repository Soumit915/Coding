//Problem in Hackerrank->Maths->Number Theory->Cheese and Random Toppings

/*
Lucas theorem for composite p:
    nCr (mod p) = Sum of i=1 to k ( (lucas(n, r) (mod pi))/(p/pi)*(p/pi)) (mod p)
*/

import java.util.*;

public class LucasTheorem {
    static long x;
    static long y;
    public static void gcdExtended(long a, long b, long mod)
    {
        if(a%b==0)
        {
            x = 1;
            y = 1-(a/b);
            return;
        }

        gcdExtended(b, a%b, mod);
        long t = y;
        y = x-((a/b)*y)%mod;
        x = t;
    }
    public static long inverseModulo(long a, long mod)
    {
        gcdExtended(a, mod, mod);
        x = (x%mod+mod)%mod;
        return x;
    }
    public static long fact(long n, long p)
    {
        long f = 1;
        for(int i=1;i<=n;i++)
            f = (f*i)%p;
        return f;
    }
    public static long nCr(long n, long r, long p)
    {
        if(n<r)
            return 0;
        if(n==r)
            return 1;
        long num = fact(n, p);
        long deno = (fact(n-r, p)*fact(r, p))%p;
        long invdeno = inverseModulo(deno, p);
        return (num*invdeno)%p;
    }
    public static long lucas(long n, long r, long p)
    {
        long n1 = n;
        ArrayList<Long> ni = new ArrayList<>();
        while(n1>0)
        {
            ni.add(n1%p);
            n1 = n1/p;
        }

        long r1 = r;
        ArrayList<Long> ri = new ArrayList<>();
        while(r1>0)
        {
            ri.add(r1%p);
            r1 = r1/p;
        }

        for(int i=ri.size()+1;i<=ni.size();i++)
        {
            ri.add((long) 0);
        }

        long pro = 1;
        for(int i=0;i<ri.size();i++)
        {
            long nival = ni.get(i);
            long rival = ri.get(i);
            pro = (pro*(nCr(nival, rival, p)%p))%p;
        }

        return pro;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            long n = sc.nextLong();
            long r = sc.nextLong();
            long m = sc.nextLong();

            int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
            long sum = 0;
            for(int p: primes)
            {
                if(m%p==0)
                {
                    long num = lucas(n, r, p);
                    long deno = inverseModulo(m/p, p);
                    sum = (sum+((((num*deno)%m)*(m/p))%m))%m;
                }
            }

            System.out.println(sum);
        }
    }
}
