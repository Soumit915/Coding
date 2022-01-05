package Hackerrank.Mathematics.NumberTheory;

import java.math.BigInteger;
import java.util.*;

public class PowerOfLargeNumbers {
    public static long power(long a, long b, long mod)
    {
        long p=1;
        while (b>0)
        {
            if(b%2==1)
            {
                p = (p*a)%mod;
            }
            a = (a*a)%mod;
            b/=2;
        }
        return p;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        long mod = (long) 1e9+7;
        while (t-->0)
        {
            String a = sc.next();
            String b = sc.next();

            BigInteger abig = new BigInteger(a);
            BigInteger bbig = new BigInteger(b);

            long A = abig.mod(BigInteger.valueOf(mod)).longValue();
            long B = bbig.mod(BigInteger.valueOf(mod-1)).longValue();
            System.out.println(power(A, B, mod));
        }
    }
}
