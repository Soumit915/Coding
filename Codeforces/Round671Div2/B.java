package Codeforces.Round671Div2;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        long lim = (long) 1e18;
        ArrayList<Long> arr = new ArrayList<>();

        long val = 0;
        for(int i=0;i<60;i++)
        {
            long k = (long) Math.pow(2, i);
            val += k;
            arr.add(val);
        }

        //System.out.println(arr);

        while (t-->0)
        {
            long n = sc.nextLong();

            int ans = 0;
            for(int i=0;n>0;i++)
            {
                long cur = arr.get(i);
                long st = (cur * (cur+1))/2;
                if(n-st<0)
                    break;
                ans++;
                n -= st;
            }

            System.out.println(ans);
        }
    }
}
