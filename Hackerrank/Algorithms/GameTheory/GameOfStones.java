package Hackerrank.Algorithms.GameTheory;

import java.util.HashMap;
import java.util.Scanner;

public class GameOfStones {
    static HashMap<Integer, Boolean> hash = new HashMap<>();
    public static void preComputeResult()
    {
        hash.put(0, false);
        hash.put(1, false);
        hash.put(2, true);
        hash.put(3, true);
        hash.put(4, true);
        hash.put(5, true);

        for(int i=6;i<=200;i++)
        {
            hash.put(i, !hash.get(i - 5) || !hash.get(i - 3) || !hash.get(i - 2));
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        preComputeResult();

        int q = sc.nextInt();
        while(q-->0)
        {
            int n = sc.nextInt();
            if(hash.get(n))
                System.out.println("First");
            else
                System.out.println("Second");
        }
    }
}
