package Codechef.ICPC2019_Practice;

import java.util.*;

public class SelectTrainingSet {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();
            HashMap<String, Integer> hash = new HashMap<>();
            HashMap<String, Integer> hash1 = new HashMap<>();
            for(int i=0;i<n;i++)
            {
                String s = sc.next();
                int bool = sc.nextInt();

                if(bool==0)
                {
                    hash.put(s, hash.getOrDefault(s, 0)+1);
                    hash1.put(s, hash1.getOrDefault(s, 0));
                }
                else
                {
                    hash.put(s, hash.getOrDefault(s, 0));
                    hash1.put(s, hash1.getOrDefault(s, 0)+1);
                }
            }

            int ans = 0;
            for(String s: hash.keySet())
            {
                ans += Math.max(hash.get(s) , hash1.get(s));
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
