package Hackerrank.Mathematics.NumberTheory;

import java.util.*;

public class SalaryBlues {
    public static long gcd(long a, long b)
    {
        if(a==0)
            return b;
        else if(b==0)
            return a;
        if(a%b==0)
            return b;
        return gcd(b, a%b);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        long[] salary = new long[n];

        for(int i=0;i<n;i++)
        {
            salary[i] = sc.nextLong();
        }

        long g = 0;
        Arrays.sort(salary);
        for(int i=1;i<n;i++)
        {
            g = gcd(g, salary[i]-salary[0]);
        }

        while (q-->0)
        {
            long query = sc.nextLong();
            System.out.println(gcd(g, salary[0]+query));
        }
    }
}
