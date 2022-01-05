package Codeforces.Round665Div2;

import java.util.*;

public class B {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            long a0 = sc.nextLong();
            long a1 = sc.nextLong();
            long a2 = sc.nextLong();

            long b0 = sc.nextLong();
            long b1 = sc.nextLong();
            long b2 = sc.nextLong();

            long dila0 = Math.min(a0, b2);
            a0 -= dila0;
            b2 -= dila0;

            long dila2 = Math.min(a2, b1);
            long sum = dila2*2;
            a2 -= dila2;
            b1 -= dila2;

            long dila1 = Math.min(a1, b0);
            a1 -= dila1;
            b0 -= dila1;

            if(b2>0)
            {
                long newdil = Math.min(b2, a2);
                b2 -= newdil;
                a2 -= newdil;

                if(b2>0)
                {
                    sum -= b2*2;
                }
            }

            System.out.println(sum);
        }
    }
}
