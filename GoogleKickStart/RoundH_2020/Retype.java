package GoogleKickStart.RoundH_2020;

import java.util.*;
public class Retype
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int testi=1;testi<=t;testi++)
        {
            System.out.print("Case #"+testi+": ");

            long n = sc.nextLong();
            long k = sc.nextLong();
            long s = sc.nextLong();

            long taken = (k-1);
            long res = (taken) + 1 + (n);
            long nres = (k-s) + (taken) + (n-s+1);
            System.out.println(Math.min(res, nres));
        }
    }
}