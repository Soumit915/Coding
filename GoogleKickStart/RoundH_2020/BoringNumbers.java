package GoogleKickStart.RoundH_2020;

import java.util.*;
public class BoringNumbers
{
    public static long bor(long n)
    {
        String strn = Long.toString(n);
        int countBits = strn.length();

        long ans = 0;
        for(int i=1;i<countBits;i++)
        {
            long inter = 1;
            for(int j=1;j<=i;j++)
                inter *= 5;
            ans += inter;
        }

        //System.out.println(ans);
        for(int j=0;j<countBits;j++)
        {
            int ch = strn.charAt(j) - 48;
            int l = ch;
            if(!((j%2==0 && ch%2==1) || (j%2==1 && ch%2==0)))
            {
                ch++;
            }
            long val = (long) Math.ceil(((double) ch-1)/2);

            for(int i=j+1;i<countBits;i++)
            {
                val *= 5;
            }

            //System.out.println(val);
            ans += val;

            if(!((j%2==0 && l%2==1) || (j%2==1 && l%2==0)))
                break;

        }

        boolean flag = true;
        for(int j=0;j<countBits;j++)
        {
            int ch = strn.charAt(j) - 48 ;
            if(!((j%2==0 && ch%2==1) || (j%2==1 && ch%2==0)))
            {
                flag = false;
                break;
            }
        }

        if(flag)
            ans += 1;
        return ans;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int testi=1;testi<=t;testi++)
        {
            System.out.print("Case #"+testi+": ");

            long l = sc.nextLong();
            long r = sc.nextLong();

            //System.out.println(bor(119));

            System.out.println(bor(r)-bor(l-1));
        }
    }
}