package Hackerrank.Mathematics.Combinatorics;

import java.util.*;
public class NumberList
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();
            long k = sc.nextLong();
            long[] arr = new long[n];

            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextLong();
            }

            long[] leftGEIndex = new long[n];
            long[] rightGIndex = new long[n];

            Stack<Long> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            for(int i=n-1;i>=0;i--)
            {
                long val = arr[i];
                while(!stk.isEmpty() && val>=stk.peek())
                {
                    leftGEIndex[ptrstk.pop()] = i;
                    stk.pop();
                }
                stk.push(val);
                ptrstk.push(i);
            }
            while(!stk.isEmpty())
            {
                leftGEIndex[ptrstk.pop()] = -1;
                stk.pop();
            }

            stk = new Stack<>();
            ptrstk = new Stack<>();
            for(int i=0;i<n;i++)
            {
                long val = arr[i];
                while(!stk.isEmpty() && val>stk.peek())
                {
                    rightGIndex[ptrstk.pop()] = i;
                    stk.pop();
                }
                stk.push(val);
                ptrstk.push(i);
            }
            while(!stk.isEmpty())
            {
                rightGIndex[ptrstk.pop()] = n;
                stk.pop();
            }

            long ans = 0;
            for(int i=0;i<n;i++)
            {
                if(arr[i]<=k)
                    continue;

                ans += (((long)i-leftGEIndex[i])*(rightGIndex[i]-i));
            }
            System.out.println(ans);
        }
    }
}
