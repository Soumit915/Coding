package Codeforces.Round661Div3;

import java.util.*;

public class C_BoatsCompetition {
    public static void main(String[] args) {
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
            Arrays.sort(arr);

            int max = Integer.MIN_VALUE;
            for(int i=2*n;i>1;i--)
            {
                int ptr1 = 0, ptr2 = n-1;
                int count = 0;
                while(ptr1<ptr2)
                {
                    if(arr[ptr1]+arr[ptr2]==i)
                    {
                        count++;
                        ptr1++;
                        ptr2--;
                    }
                    else if(arr[ptr1]+arr[ptr2]>i)
                    {
                        ptr2--;
                    }
                    else
                    {
                        ptr1++;
                    }
                }

                max = Math.max(max, count);
            }
        }
    }
}