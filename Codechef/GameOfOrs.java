package Codechef;

import java.util.*;

public class GameOfOrs {
    static class Value implements Comparable<Value>
    {
        int val;
        int ind;
        Value(int ind, int val)
        {
            this.ind = ind;
            this.val = val;
        }
        public int compareTo(Value v)
        {
            return Integer.compare(this.val, v.val);
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            Value[] arr = new Value[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = new Value(i, sc.nextInt());
            }
            Arrays.sort(arr);

            long happiness = 0;
            long[] suffix = new long[n];
            suffix[n-1] = arr[n-1].val;
            for(int i=n-2;i>=0;i--)
            {
                suffix[i] = suffix[i+1]|arr[i].val;
            }

            for(int i=0;i<n;i++)
            {
                happiness += suffix[i];
            }

            System.out.println(happiness);
            for(int i=0;i<n;i++)
            {
                System.out.print(arr[i].val+" ");
            }
        }
    }
}
