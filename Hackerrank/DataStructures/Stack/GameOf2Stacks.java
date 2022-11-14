package Hackerrank.DataStructures.Stack;

import java.util.*;

public class GameOf2Stacks {
    public static int twoStacks(int x, int[] a, int[] b)
    {
        int al = a.length;
        int bl = b.length;

        int ai = -1;
        int bi = -1;
        int[] stk = new int[al+bl];
        int si = -1;
        int sum = 0;
        int curCount = 0;

        while(ai<al-1 && sum<=x)
        {
            if(sum+a[ai+1]>x)
                break;
            ai++;
            si++;
            stk[si] = a[ai];
            sum += stk[si];
            curCount++;
        }

        while(bi<bl-1 && sum<=x)
        {
            if(sum+b[bi+1]>x)
                break;
            bi++;
            si++;
            stk[si] = b[bi];
            sum += stk[si];
            curCount++;
        }

        ArrayList<Integer> count = new ArrayList<>();
        count.add(curCount);

        while(ai>=0 && bi<bl-1)
        {
            sum -= stk[ai];
            curCount--;
            ai--;
            while(bi<bl-1 && sum+b[bi+1]<=x)
            {
                bi++;
                sum += b[bi];
                curCount++;
            }
            count.add(curCount);
        }

        return Collections.max(count);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();

        while(g-->0)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int x = sc.nextInt();

            int[] a = new int[n];
            int[] b = new int[m];
            for(int i=0;i<n;i++)
            {
                a[i] = sc.nextInt();
            }

            for(int i=0;i<m;i++)
            {
                b[i] = sc.nextInt();
            }

            System.out.println(twoStacks(x, a, b));
        }
    }
}
