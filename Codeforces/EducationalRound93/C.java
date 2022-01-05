package Codeforces.EducationalRound93;

import java.util.*;

public class C {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            String s = sc.next();
            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[i] = s.charAt(i)-48;
            }

            Map<Integer, Integer> hash = new HashMap<>();
            hash.put(1, 1);

            int sum = 0;
            for(int i=0;i<n;i++)
            {
                sum += arr[i];
                hash.put(sum-i, hash.getOrDefault(sum - i, 0)+1);
            }

            long ans = 0;
            Set<Integer> keyset = hash.keySet();
            for(int I: keyset)
            {
                long val = hash.get(I);
                ans += (val * (val-1))/2;
            }

            System.out.println(ans);
        }
    }
}
