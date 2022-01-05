package Codeforces.Round670Div2;

import java.util.*;

public class A {
    public static int findmex(Set<Integer> set)
    {
        int[] arr = new int[1000];
        for(int i: set)
        {
            arr[i]++;
        }

        for(int i=0;i<1000;i++)
        {
            if(arr[i]==0)
                return i;
        }

        return -1;
    }
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
            }

            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for(int i=0;i<n;i++)
            {
                if(set1.contains(arr[i]))
                {
                    set2.add(arr[i]);
                }
                else
                {
                    set1.add(arr[i]);
                }
            }

            int m1 = findmex(set1);
            int m2 = findmex(set2);

            System.out.println(m1+m2);
        }
    }
}
