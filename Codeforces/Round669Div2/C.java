package Codeforces.Round669Div2;

import java.util.*;

public class C {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] ans = new int[n];
        int std = 0;
        for(int i=1;i<n;i++)
        {
            System.out.println("? "+(std+1)+" "+(i+1));
            System.out.flush();
            int q1 = sc.nextInt();

            System.out.println("? "+(i+1)+" "+(std+1));
            System.out.flush();
            int q2 = sc.nextInt();

            if(q1>q2)
            {
                ans[std] = q1;
                std = i;
            }
            else
            {
                ans[i] = q2;
            }
        }

        for(int i=0;i<n;i++)
        {
            if(ans[i]==0) {
                ans[i] = n;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("! ");
        for(int i: ans)
            sb.append(i).append(" ");
        System.out.println(sb);

    }
}
