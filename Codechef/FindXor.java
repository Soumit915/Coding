package Codechef;

import java.util.*;

public class FindXor {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            Map<Long, Integer> hash = new HashMap<>();
            if(n%2==1)
            {
                for(long i=n;i>=0;i--)
                {
                    long val = n - 2*(n-i);
                    if(i%2==0)
                        hash.put(val, 1);
                    else hash.put(val, 0);
                }
            }
            else
            {
                for(long i=n;i>=0;i--)
                {
                    long val = n - 2*(n-i);
                    if(i%2==0)
                        hash.put(val, 0);
                    else hash.put(val, 1);
                }
            }


            long ans = 0;
            long[] xorvals = new long[20];

            for(int i=0;i<20;i++)
            {
                long val = (1<<(i+1));
                val--;
                System.out.println(1+" "+val);
                System.out.flush();

                long q = sc.nextLong();
                xorvals[i] = q;
            }

            long k = xorvals[0]%2;
            if(n%2==1 && k==0)
                ans = 1;
            else if(n%2==0)
                ans = k;

            for(int i=1;i<=19;i++)
            {
                long prev = xorvals[i-1];
                long cur = xorvals[i];

                long diff = cur - prev;
                diff = (diff>>i);
                int hg = hash.get(diff);
                ans = ans | (hg<<i);
            }

            System.out.println(2+" "+ans);
            System.out.flush();
            long verdict = sc.nextLong();

            if(verdict==-1)
                System.exit(0);
        }
    }
}
