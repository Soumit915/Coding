package Codechef;

import java.util.*;

public class PolygonRelationship {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            int[] x = new int[n];
            int[] y = new int[n];

            Map<Integer, Integer> hash = new HashMap<>();
            hash.put(3, 0);
            hash.put(4, 0);
            hash.put(5, 0);

            for(int i=0;i<n;i++)
            {
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
            }

            long sum = n;
            while(n>5)
            {
                int k = n/2;
                sum += k;
                n/=2;
                if(hash.containsKey(k))
                    break;
            }

            System.out.println(sum);
        }
    }
}
