package Codeforces.Round660Div2;

import java.util.*;

public class A_CaptainFlintAndCrewRecruitment {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            if(n<=30)
            {
                System.out.println("NO");
            }
            else if(n==36)
            {
                System.out.println("YES");
                System.out.println("5 6 10 15");
            }
            else if(n==40 || n==44)
            {
                System.out.println("YES");
                System.out.println("10 14 15 "+(n-10-14-15));
            }
            else
            {
                System.out.println("YES");
                System.out.println("6 10 14 "+(n-6-10-14));
            }
        }
    }
}
