package Hackerrank.Algorithms.Strings;

import java.util.*;

public class BigSorting {
    static class Sorter implements Comparator<String>
    {
        public int compare(String a, String b)
        {
            int x = Integer.compare(a.length(), b.length());
            if(x == 0)
            {
                return a.compareTo(b);
            }
            else
                return x;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String[] arr = new String[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.next();
        }

        Arrays.sort(arr, new Sorter());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++)
        {
            sb.append(arr[i]);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
