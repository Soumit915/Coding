package Codeforces.Round669Div2;

import java.util.*;

public class B {
    public static int gcd(int a, int b)
    {
        if(a%b==0)
            return b;
        return gcd(b, a%b);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];

            int max = -1;
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
                max = Math.max(max, arr[i]);
            }

            ArrayList<Integer> ans = new ArrayList<>();
            boolean[] ispresent = new boolean[n];
            for(int i=0;i<n;i++)
            {
                if(arr[i]==max)
                {
                    ispresent[i] = true;
                    break;
                }
            }
            ans.add(max);
            int curgcd = max;

            for(int i=1;i<n;i++)
            {
                int cmax = -1;
                int cind = -1;
                for(int j=0;j<n;j++)
                {
                    if(ispresent[j])
                        continue;
                    if(gcd(curgcd, arr[j])>cmax)
                    {
                        cmax = gcd(curgcd, arr[j]);
                        cind = j;
                    }
                }

                ans.add(arr[cind]);
                curgcd = gcd(curgcd, arr[cind]);
                ispresent[cind] = true;
            }

            for(int i: ans)
                System.out.print(i+" ");
            System.out.println();
        }
    }
}
