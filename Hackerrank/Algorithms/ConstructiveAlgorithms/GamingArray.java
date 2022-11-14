package Hackerrank.Algorithms.ConstructiveAlgorithms;

import java.util.*;
public class GamingArray
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();

        while(g-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
            }

            Stack<Integer> stk = new Stack<>();
            Stack<Integer> stkind = new Stack<>();

            int[] right = new int[n];
            for(int i=0;i<n;i++)
            {
                int val = arr[i];
                while(!stk.isEmpty() && val>stk.peek())
                {
                    right[stkind.pop()] = i;
                    stk.pop();
                }

                stk.push(val);
                stkind.push(i);
            }

            while(!stk.isEmpty())
            {
                right[stkind.pop()] = -1;
                stk.pop();
            }

            int count = 0;
            int ptr = 0;
            while(ptr!=-1)
            {
                count++;
                ptr = right[ptr];
            }

            if(count%2==1)
                System.out.println("BOB");
            else System.out.println("ANDY");
        }
    }
}

