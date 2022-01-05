package Codechef;

import java.util.*;

public class CheckPoints {
    static class Points
    {
        int x;
        int y;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();
            int c = sc.nextInt();

            Points[] coordinates = new Points[n];
            for(int i=0;i<n;i++)
            {
                Points p = new Points();
                p.x = sc.nextInt();
                p.y = sc.nextInt();
            }

            Map<Integer, Map<Integer, Integer>> hash = new HashMap<>();
            for(int i=0;i<n;i++)
            {
                Points p = coordinates[i];

            }
        }
    }
}
