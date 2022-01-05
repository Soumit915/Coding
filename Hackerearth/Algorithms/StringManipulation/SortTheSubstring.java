package Hackerearth.Algorithms.StringManipulation;

import java.util.*;

public class SortTheSubstring {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            String s = sc.next();
            int start = sc.nextInt();
            int end = sc.nextInt();
            String ans="";

            String sString = s.substring(start, end+1);
            char[] arr = sString.toCharArray();
            Arrays.sort(arr);
            reverse(arr);

            if(start != 0)
                ans += s.substring(0,start);

            ans += new String(arr);
            if(end < s.length()-1)
                ans += s.substring(end+1);

            System.out.println(ans);

        }
    }
    public static void reverse(char[] arr)
    {
        int n = arr.length;
        for(int i=0;i<(n/2);i++)
        {
            char temp = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = temp;
        }
    }
}
