package Hackerrank.Mathematics.Probability;

import java.util.*;

public class RandomNumberGenerator {
    static int gcd(int a, int b)
    {
        if(a%b==0)
            return b;
        return gcd(b,a%b);
    }
    static String solve(int a, int b, int c)
    {
        String s;
        int ar,at;
        ar = 2*a*b;
        if(c>=(a+b))    //when c > (a+b)
            return "1/1";
        else if(c<=a && c<=b)   //when c <a,b
        {
            at = c*c;
        }
        else if(c>a && c>b)     //when c>a,b
        {
            at = c*c-(c-a)*(c-a)-(c-b)*(c-b);
        }
        else
        {
            //System.out.println("test");
            int min = Math.min(a,b);
            at = c*c-(c-min)*(c-min);
        }

        int g = gcd(ar,at);
        ar = ar/g;
        at = at/g;

        s = at+"/"+ar;

        return s;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t>0)
        {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            String ans = solve(a,b,c);
            System.out.println(ans);
            t--;
        }
    }
}
