package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week3;

import java.util.*;

public class MaximumNoPrizes {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        long disc = 1 + 8*n;
        long dsq = (long) Math.sqrt(disc) - 1;
        long sols = dsq / 2;

        StringBuilder sb = new StringBuilder();
        //ArrayList<Integer> ans = new ArrayList<>();
        sb.append(sols).append("\n");
        for(int i=1;i<sols;i++)
        {
            sb.append(i).append(" ");
        }

        sb.append((n - (sols * (sols-1)) / 2)).append("\n");

        System.out.println(sb);
    }
}
