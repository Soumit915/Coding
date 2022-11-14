package Hackerrank.Algorithms.Sorting;

import java.util.*;

public class LilysHomework {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        int[] arr1 = new int[n];
        int[] sortedarr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
            sortedarr[i] = arr[i];
            arr1[i] = arr[i];
        }

        Arrays.sort(sortedarr);
        HashMap<Integer, Integer> arrmap = new HashMap<>();
        HashMap<Integer, Integer> arrmap1 = new HashMap<>();
        for(int i=0;i<n;i++)
        {
            arrmap.put(arr[i], i);
            arrmap1.put(arr[i], i);
        }

        int count = 0;
        int countrev = 0;
        for(int i=0;i<n;i++)
        {
            if(arr[i]!=sortedarr[i])
            {
                count++;
                int ind = arrmap.get(sortedarr[i]);
                int t = arr[i];
                arr[i] = arr[ind];
                arr[ind] = t;
                arrmap.put(arr[ind], ind);
            }
        }

        for(int i=0;i<n;i++)
        {
            if(arr1[i]!=sortedarr[n-i-1])
            {
                countrev++;
                int ind = arrmap1.get(sortedarr[n-i-1]);
                int t = arr1[i];
                arr1[i] = arr1[ind];
                arr1[ind] = t;
                arrmap1.put(arr1[ind], ind);
            }
        }

        System.out.println(Math.min(count, countrev));
    }
}
