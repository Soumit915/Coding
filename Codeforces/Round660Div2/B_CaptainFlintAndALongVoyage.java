package Codeforces.Round660Div2;

import java.util.*;

public class B_CaptainFlintAndALongVoyage {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            int n8 = (n-1)/4+1;
            int n9 = n-n8;

            StringBuilder sb = new StringBuilder();
            for(int i=1;i<=n9;i++)
                sb.append("9");
            for(int i=1;i<=n8;i++)
                sb.append("8");
            System.out.println(sb);
        }
    }
}
