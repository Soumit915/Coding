package Hackerrank.DataStructures.Queue;

import java.util.*;
public class TruckTour
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[] dist = new long[n];
        long[] quantity = new long[n];
        for(int i=0;i<n;i++)
        {
            quantity[i] = sc.nextLong();
            dist[i] = sc.nextLong();
        }

        int ptr=0;
        int i=0;
        long std = 0;
        do
        {
            std += quantity[i];
            while(std<dist[i])
            {
                std = 0;
                i=(i+1)%n;
                std += quantity[i];
                ptr = i;
            }
            std -= dist[i];
            i++;
            i%=n;
        }
        while(i!=ptr);

        System.out.println(ptr);
    }
}
