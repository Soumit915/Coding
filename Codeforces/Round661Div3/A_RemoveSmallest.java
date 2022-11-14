package Codeforces.Round661Div3;

import java.util.*;

public class A_RemoveSmallest {
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

            boolean flag = true;
            for(int i=0;i<n;i++)
            {
                if(arr[i]-arr[i-1]>1)
                {
                    flag= false;
                    break;
                }
            }

            if(!flag)
                System.out.println("NO");
            else System.out.println("YES");
        }
    }
}