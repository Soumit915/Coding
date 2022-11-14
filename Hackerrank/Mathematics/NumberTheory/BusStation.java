package Hackerrank.Mathematics.NumberTheory;

import java.util.*;

public class BusStation {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        int[] cumArr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
            if(i==0)
                cumArr[i] = arr[i];
            else
                cumArr[i] = cumArr[i-1]+arr[i];
        }

        int sum = cumArr[n-1];
        HashSet<Integer> cumSet = new HashSet<>();
        for(int i: cumArr)
            cumSet.add(i);

        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            int v = cumArr[i];
            if(sum%v!=0)
                continue;
            boolean flag = true;
            for(int j=2;j<=(sum/v);j++)
            {
                if(!cumSet.contains(v*j))
                {
                    flag = false;
                    break;
                }
            }
            if(flag)
                ans.add(v);
        }

        Collections.sort(ans);
        for(int i: ans)
            System.out.print(i+" ");
        System.out.println();
    }
}
