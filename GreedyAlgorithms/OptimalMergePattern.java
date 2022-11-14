package GreedyAlgorithms;

import java.util.*;

public class OptimalMergePattern {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of lists : ");
        int n = sc.nextInt();

        System.out.print("Enter the size of the lists : ");
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int i=0;i<n;i++)
        {
            int val = sc.nextInt();
            heap.add(val);
        }

        int sum = 0;
        while(heap.size()!=1)
        {
            int l1 = heap.remove();
            int l2 = heap.remove();
            System.out.println("Add the list of size of "+l1+" and "+l2);
            sum += (l1+l2);
            heap.add(l1+l2);
        }

        System.out.println("\nThe total operations required to merge all the list is : "+sum);
    }
}
