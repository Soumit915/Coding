package Hackerrank.DataStructures.Heap;

import java.util.*;

public class RunningMedian
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        PriorityQueue<Integer> heap1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> heap2 = new PriorityQueue<>();
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
            if(heap1.size()==heap2.size())
            {
                if(heap1.size()==0)
                {
                    heap1.add(arr[i]);
                }
                else
                {
                    int h1v = heap1.remove();
                    int h2v = heap2.remove();
                    int min = Math.min(h1v, Math.min(h2v, arr[i]));
                    int max = Math.max(h1v, Math.max(h2v, arr[i]));
                    heap1.add(min);
                    heap2.add(max);
                    heap1.add(h1v+h2v+arr[i]-max-min);
                }
                double v = heap1.peek();
                System.out.printf("%.1f\n", v);
            }
            else
            {
                if(heap2.size()==0)
                {
                    heap2.add(arr[i]);
                }
                else
                {
                    int h1v = heap1.remove();
                    int h2v = heap2.remove();
                    int min = Math.min(h1v, Math.min(h2v, arr[i]));
                    int max = Math.max(h1v, Math.max(h2v, arr[i]));
                    heap1.add(min);
                    heap2.add(max);
                    heap2.add(h1v+h2v+arr[i]-max-min);
                }
                double v = heap1.peek()+heap2.peek();
                v /= 2;
                System.out.printf("%.1f\n", v);
            }
        }

    }
}