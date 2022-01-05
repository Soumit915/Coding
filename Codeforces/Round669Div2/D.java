package Codeforces.Round669Div2;

import java.util.*;

public class D {
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
        Stack<Integer> ptrstk = new Stack<>();

        int[] lh = new int[n];
        int[] rh = new int[n];
        int[] ls = new int[n];
        int[] rs = new int[n];
        int[] re = new int[n];
        for(int i=0;i<n;i++)
        {
            re[i] = 1000000;
        }
        for(int i=0;i<n;i++)
        {
            while(!stk.isEmpty() && stk.peek()<arr[i])
            {
                rh[ptrstk.pop()] = (i);
                stk.pop();
            }
            stk.push(arr[i]);
            ptrstk.push(i);
        }
        while(!stk.isEmpty())
        {
            rh[ptrstk.pop()] = 1000000;
            stk.pop();
        }

        for(int i=n-1;i>=0;i--)
        {
            while(!stk.isEmpty() && stk.peek()<arr[i])
            {
                lh[ptrstk.pop()] = (i);
                stk.pop();
            }
            stk.push(arr[i]);
            ptrstk.push(i);
        }
        while(!stk.isEmpty())
        {
            lh[ptrstk.pop()] = -1;
            stk.pop();
        }

        for(int i=0;i<n;i++)
        {
            while(!stk.isEmpty() && stk.peek()>arr[i])
            {
                rs[ptrstk.pop()] = (i);
                stk.pop();
            }
            stk.push(arr[i]);
            ptrstk.push(i);
        }
        while(!stk.isEmpty())
        {
            rs[ptrstk.pop()] = 1000000;
            stk.pop();
        }

        for(int i=n-1;i>=0;i--)
        {
            while(!stk.isEmpty() && stk.peek()>arr[i])
            {
                ls[ptrstk.pop()] = (i);
                stk.pop();
            }
            stk.push(arr[i]);
            ptrstk.push(i);
        }
        while(!stk.isEmpty())
        {
            ls[ptrstk.pop()] = -1;
            stk.pop();
        }

        Map<Integer, Integer> hash = new HashMap<>();
        for(int i=0;i<n;i++)
        {
            if(hash.containsKey(arr[i]))
            {
                re[hash.get(arr[i])] = i;
            }
            hash.put(arr[i], i);
        }

        boolean[] isvisited = new boolean[n];

        /*for(int i: rh)
            System.out.print(i+" ");
        System.out.println();

        for(int i: rs)
            System.out.print(i+" ");
        System.out.println();

        for(int i: re)
            System.out.print(i+" ");
        System.out.println();*/

        int ptr = 0;
        int count = 0;
        while(ptr != (n-1))
        {
            int nexthigher = rh[ptr];
            int nextlower = rs[ptr];
            int nextequal = re[ptr];

            if(nextequal<n && nexthigher<n && nextlower<n)
            {
                if(Math.min(nextequal, Math.min(nexthigher, nextlower))==nextequal) {
                    ptr = nextequal;
                }
                else if(Math.max(nextequal, Math.max(nexthigher, nextlower))==nextequal)
                {
                    ptr = nextequal;
                }
            }
            else if(nextequal<n)
            {
                ptr = nextequal;
            }
            else if(nexthigher>n)
            {
                ptr = nextlower;
            }
            else if(nextlower>n)
            {
                ptr = nexthigher;
            }
            else
            {
                ptr = Math.max(nexthigher, nextlower);
            }

            int previoushigher = lh[ptr];
            int previouslower = ls[ptr];

            int hc = 0, lc = 0;
            if(previoushigher!=-1 && isvisited[previoushigher] && checkpath(isvisited, previoushigher, ptr)>0)
            {
                hc = checkpath(isvisited, previoushigher, ptr);
            }
            if(previouslower!=-1 && isvisited[previouslower] && checkpath(isvisited, previouslower, ptr)>0)
            {
                lc = checkpath(isvisited, previouslower, ptr);
            }

            hc = Math.max(hc, lc);
            count -= hc;
            count++;
            //System.out.println(ptr);
            if(hc == lc)
            {
                for(int i=previouslower+1;i<ptr;i++)
                {
                    isvisited[i] = false;
                }
            }
            else
            {
                for(int i=previoushigher+1;i<ptr;i++)
                {
                    isvisited[i] = false;
                }
            }
            isvisited[ptr] = true;
        }

        System.out.println(count);
    }
    public static int checkpath(boolean[] isvisited, int previoushigher, int ptr)
    {
        int count = 0;
        for(int i=previoushigher+1;i<ptr;i++)
        {
            if(isvisited[i])
                count++;
        }
        return count;
    }
}
