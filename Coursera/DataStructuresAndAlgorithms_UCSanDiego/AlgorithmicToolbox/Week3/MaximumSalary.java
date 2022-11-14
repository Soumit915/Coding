package Coursera.DataStructuresAndAlgorithms_UCSanDiego.AlgorithmicToolbox.Week3;

import java.util.*;

public class MaximumSalary {
    static class Number implements Comparable<Number>
    {
        int id;
        String val;
        Number(int id, String val)
        {
            this.id = id;
            this.val = val;
        }
        public int compareTo(Number n)
        {
            String s1 = this.val + n.val;
            String s2 = n.val + this.val;
            return s1.compareTo(s2);
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Number[] arr = new Number[n];
        int max = -1;
        for(int i=0;i<n;i++)
        {
            arr[i] = new Number(i, sc.next());
            max = Math.max(max, arr[i].val.length());
        }

        Arrays.sort(arr, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++)
        {
            sb.append(arr[i].val);
        }
        sb.append("\n");

        System.out.println(sb);
    }
}
