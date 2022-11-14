package Codeforces.Round661Div3;

import java.util.*;

public class B_FixingGifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int[] candies = new int[n];
            int[] oranges = new int[n];

            int mincandy = Integer.MAX_VALUE;
            int minoranges = Integer.MAX_VALUE;
            for(int i=0;i<n;i++)
            {
                candies[i] = sc.nextInt();
                mincandy = Math.min(candies[i], mincandy);
            }
            for(int i=0;i<n;i++)
            {
                oranges[i] = sc.nextInt();
                minoranges = Math.min(oranges[i], minoranges);
            }

            long totaloperations = 0;
            for(int i=0;i<n;i++)
            {
                long common = Math.min(oranges[i]-minoranges, candies[i]-mincandy);
                totaloperations += common + (oranges[i]-minoranges-common) + (candies[i]-mincandy-common);
            }

            System.out.println(totaloperations);
        }
    }
}