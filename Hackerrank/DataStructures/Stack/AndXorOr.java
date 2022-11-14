package Hackerrank.DataStructures.Stack;

import java.util.*;
public class AndXorOr
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[] arr = new long[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        Stack<Long> stk = new Stack<>();
        Stack<Integer> stkind = new Stack<>();

        int[] left = new int[n];
        int[] right = new int[n];

        stk.push(arr[0]);
        stkind.push(0);
        for(int i=1;i<n;i++)
        {
            long val = arr[i];
            while(!stk.isEmpty() && val<arr[stkind.peek()])
            {
                right[stkind.pop()] = i;
                stk.pop();
            }
            stk.push(arr[i]);
            stkind.push(i);
        }
        while(!stk.isEmpty())
        {
            right[stkind.pop()] = -1;
            stk.pop();
        }

        stk = new Stack<>();
        stkind = new Stack<>();
        stk.push(arr[n-1]);
        stkind.push(n-1);
        for(int i=n-2;i>=0;i--)
        {
            long val = arr[i];
            while(!stk.isEmpty() && val<stk.peek())
            {
                left[stkind.pop()] = i;
                stk.pop();
            }
            stk.push(arr[i]);
            stkind.push(i);
        }
        while(!stk.isEmpty())
        {
            left[stkind.pop()] = -1;
            stk.pop();
        }

        long max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++)
        {
            long xor;
            if(left[i]!=-1)
            {
                xor = arr[i] ^ arr[left[i]];
                max = Math.max(xor, max);
            }

            if(right[i]!=-1)
            {
                xor = arr[i] ^ arr[right[i]];
                max = Math.max(xor, max);
            }
        }

        System.out.println(max);
    }
}
