package Hackerrank.DataStructures.Queue;

import java.util.*;

public class QueriesFixedLength
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        StringBuilder sb = new StringBuilder();
        while(q-->0)
        {
            int d = sc.nextInt();

            Deque<Integer> deque = new LinkedList<>();
            deque.addFirst(0);
            for(int i=1;i<d;i++)
            {
                if(arr[i]>=arr[deque.getFirst()])
                {
                    deque = new LinkedList<>();
                    deque.addFirst(i);
                }
                else
                {
                    while(arr[deque.getLast()]<=arr[i])
                    {
                        deque.removeLast();
                    }
                    deque.addLast(i);
                }
            }

            int min = arr[deque.getFirst()];
            for(int i=d;i<n;i++)
            {
                if(deque.getFirst()==i-d)
                {
                    deque.removeFirst();
                }

                if(deque.size()==0)
                {
                    deque.addFirst(i);
                }
                else if(arr[i]>=arr[deque.getFirst()])
                {
                    deque = new LinkedList<>();
                    deque.addFirst(i);
                }
                else
                {
                    while(arr[deque.getLast()]<=arr[i])
                    {
                        deque.removeLast();
                    }
                    deque.addLast(i);
                }

                min = Math.min(min, arr[deque.getFirst()]);
            }

            sb.append(min).append("\n");
        }

        System.out.println(sb);
    }
}