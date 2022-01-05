package Codeforces.Round665Div2;

import java.util.*;

public class A {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();

            if(n-k<0)
                System.out.println(k-n);
            else if(n-k%2==1)
                System.out.println(1);
            else System.out.println(0);
        }
    }
}
