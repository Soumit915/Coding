package Codechef;

import java.util.*;

public class CoronavirusSpread2 {
    public static int findsols(int[] state, int ind)
    {
        int n = state.length;
        int count = 1;

        for(int i=ind+1;i<n;i++)
        {
            if(state[i] < state[ind])
                count++;
        }

        Arrays.sort(state, ind, n);

        for(int i=0;i<ind;i++)
        {
            if(state[i] > state[ind])
                count++;
        }

        int count1 = 1;
        for(int i=0;i<ind;i++)
        {
            if(state[i] > state[ind])
                count1++;
        }

        Arrays.sort(state, 0, ind+1);

        for(int i=ind+1;i<n;i++)
        {
            if(state[i] < state[ind])
                count1++;
        }

        count = Math.max(count1, count);

        return count;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
            }

            int[] state = new int[n];
            int maxd;
            int prevmax = 6;
            while(true) {
                maxd = -1;
                for (int i = 0; i < n; i++) {
                    if (arr[i] < prevmax)
                        maxd = Math.max(maxd, arr[i]);
                }

                if (maxd == -1)
                    break;

                for (int i = 0; i < n; i++) {
                    if (arr[i] == maxd)
                        state[i] = prevmax - 1;
                }
                prevmax--;
            }

            int max = 1;
            int min = 38;
            for(int i=n-1;i>=0;i--)
            {
                int[] stateduplicate = new int[n];
                System.arraycopy(state, 0, stateduplicate, 0, n);
                int k = findsols(stateduplicate, i);
                max = Math.max(k, max);
                min = Math.min(k, min);
            }

            System.out.println(min+ " " +max);
        }
    }
}
