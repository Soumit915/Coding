package Codeforces.Round665Div2;

import java.util.*;

public class C {
    public static int gcd(int a, int b)
    {
        if(a%b==0)
            return b;
        else return gcd(b, a%b);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];
            int[] sorted = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
                sorted[i] = arr[i];
            }
            Arrays.sort(sorted);
            int min = sorted[0];

            /*Map<Integer, Integer> hash = new HashMap<>();
            for(int i=0;i<n;i++)
            {
                hash.put(sorted[i], i);
            }*/

            boolean flag = true;
            for(int i=0;i<n;i++)
            {
                if(arr[i]!=sorted[i])
                {
                    if(gcd(arr[i], min)!=min)
                    {
                        flag = false;
                        break;
                    }
                }
            }


            if(flag)
                System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
