package Hackerrank.DataStructures.Advanced;

import java.util.*;

public class MaximumIndexProduct
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] a = new int[n];
        for(int i=0;i<n;i++)
        {
            a[i] = sc.nextInt();
        }

        Stack<Integer> stk = new Stack<>();
        Stack<Integer> ptrstk = new Stack<>();

        int[] l = new int[n];
        int[] r = new int[n];
        for(int i=0;i<n;i++)
        {
            while(!stk.isEmpty() && stk.peek()<a[i])
            {
                r[ptrstk.pop()] = (i+1);
                stk.pop();
            }
            stk.push(a[i]);
            ptrstk.push(i);
        }

        while(!stk.isEmpty())
        {
            r[ptrstk.pop()] = 0;
            stk.pop();
        }

        for(int i=n-1;i>=0;i--)
        {
            while(!stk.isEmpty() && stk.peek()<a[i])
            {
                l[ptrstk.pop()] = (i+1);
                stk.pop();
            }
            stk.push(a[i]);
            ptrstk.push(i);
        }

        while(!stk.isEmpty())
        {
            l[ptrstk.pop()] = 0;
            stk.pop();
        }

        Long[] ip = new Long[n];
        for(int i=0;i<n;i++)
        {
            ip[i] = ((long)l[i])*r[i];
        }

        long max = Collections.max(Arrays.asList(ip));
        System.out.println(max);
    }
}
