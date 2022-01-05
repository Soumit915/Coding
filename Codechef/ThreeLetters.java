package Codechef;

import java.util.*;

public class ThreeLetters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            String s = sc.next();
            int n = s.length();

            int[] hash= new int[26];
            for(int i=0;i<n;i++)
            {
                hash[s.charAt(i)-97]++;
            }

            int pairs = 0;
            for (int j : hash) {
                pairs += j / 2;
            }

            int singles = 0;
            for(int i=0;i<n;i++)
            {
                singles = n - pairs*2;
            }

            while (pairs > singles) {
                pairs--;
                singles += 2;
            }

            sb.append(pairs).append("\n");
        }
        System.out.println(sb);
    }
}
