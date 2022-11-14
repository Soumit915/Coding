package TCS_Codevita9_QualificationRound;

import java.util.Scanner;

public class B {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt()%3;
            }

            int one=0, two=0, zero=0;
            for(int i=0;i<n;i++)
            {
                if(arr[i]==0)
                    zero++;
                else if(arr[i]==1)
                    one++;
                else two++;
            }

            if(zero-1>one+two)
                System.out.println("No");
            else if(one!=0 && two!=0 && zero==0)
                System.out.println("No");
            else System.out.println("Yes");
        }
    }
}
