package Codeforces.Round663Div2;

import java.util.*;

public class C {
    public static void main(String[] arg)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long c = 1;
        long mod = 1000000007;
        for(int i=1;i<=n;i++)
        {
            c = (c*i)%mod;
        }

        long mul = 4;
        for(int i=4;i<=n;i++)
        {
            mul = (mul*2)%mod;
        }

        c = ((c-mul)%mod+mod)%mod;

        System.out.println(c);
    }
}