//This is the most efficient O(n) solution that can be performed using a stack
//Read article for maximum area of consecutive rectangle in histogram

package Hackerrank.DataStructures.Stack;

import java.util.*;

public class LargestRectangle {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        Stack<Integer> stk = new Stack<>();
        Stack<Integer> index = new Stack<>();

        int[] left = new int[n];
        int[] right = new int[n];

        for(int i=0;i<n;i++)
        {
            int val = arr[i];
            while(!stk.isEmpty() && stk.peek()>val)
            {
                right[index.pop()] = i-1;
                stk.pop();
            }

            stk.push(val);
            index.push(i);
        }

        while(!stk.isEmpty())
        {
            right[index.pop()] = n-1;
            stk.pop();
        }

        for(int i=n-1;i>=0;i--)
        {
            int val = arr[i];
            while(!stk.isEmpty() && stk.peek()>val)
            {
                left[index.pop()] = i+1;
                stk.pop();
            }

            stk.push(val);
            index.push(i);
        }

        while (!stk.isEmpty())
        {
            left[index.pop()] = 0;
            stk.pop();
        }

        int max = -1;
        for(int i=0;i<n;i++)
        {
            int val = arr[i];
            int area = val * (right[i]-left[i]+1);

            if(max<area)
                max = area;
        }

        System.out.println(max);
    }
}
