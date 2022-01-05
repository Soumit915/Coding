package Hackerrank.DataStructures.Stack;

import java.util.*;

public class PoisonousPlants
{
    public static int nextPowerOf2(int n)
    {
        n = n|n>>1;
        n = n|n>>2;
        n = n|n>>4;
        n = n|n>>8;
        n = n|n>>16;
        n = n|n>>31;
        return n+1;
    }
    public static void update(int[] segTree, int si, int ind, int val, int ll, int ul)
    {
        if(ll==ul)
        {
            segTree[si] = val;
            return;
        }

        int mid = (ll+ul)/2;
        if(ind<=mid)
        {
            update(segTree, 2*si+1, ind, val, ll, mid);
        }
        else
        {
            update(segTree, 2*si+2, ind, val, mid+1, ul);
        }
        segTree[si] = Math.max(segTree[2*si+1], segTree[2*si+2]);
    }
    public static int query(int[] segTree, int si, int start, int end, int ll, int ul)
    {
        //Check for no-overlap
        if(start>ul || end<ll)
        {
            return Integer.MIN_VALUE;
        }

        //Check for total-overlap
        if(ll>=start && ul<=end)
            return segTree[si];

        //Check for partial overlap
        int mid = (ll+ul)/2;
        int left = query(segTree, 2*si+1, start, end, ll, mid);
        int right = query(segTree, 2*si+2, start, end, mid+1, ul);
        return Math.max(left, right);
    }
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
        Stack<Integer> stkind = new Stack<>();
        int[] left = new int[n];
        for(int i=n-1;i>=0;i--)
        {
            int val = arr[i];
            while(!stk.isEmpty() && val<stk.peek())
            {
                left[stkind.pop()] = i;
                stk.pop();
            }
            stk.push(val);
            stkind.push(i);
        }
        while(!stk.isEmpty())
        {
            left[stkind.pop()] = -1;
            stk.pop();
        }

        int seglen = 2*nextPowerOf2(n)-1;
        int[] segTree = new int[seglen];

        for(int i=0;i<n;i++)
        {
            if(left[i]==-1)
            {
                update(segTree, 0, i, 0, 0, n-1);
            }
            else if(left[i]==i-1)
            {
                update(segTree, 0, i, 1, 0, n-1);
            }
            else
            {
                int q = query(segTree, 0, left[i]+1, i-1, 0, n-1);
                update(segTree, 0, i, q+1, 0, n-1);
            }
        }
        System.out.println(query(segTree, 0, 0, n-1, 0, n-1));
    }
}
