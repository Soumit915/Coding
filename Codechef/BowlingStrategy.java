package Codechef;

import java.util.*;

public class BowlingStrategy {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int l = sc.nextInt();

            if(k*l<n || (n>1 && k==1))
            {
                System.out.println(-1);
                continue;
            }

            int[] hash = new int[k+1];
            boolean flag = true;
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(1);
            for(int i=2;i<=n;i++)
            {
                int v = -1;
                int last = ans.get(ans.size()-1);
                int now = last+1;
                if(now>k)
                {
                    now %= k;
                }

                ans.add(now);
            }

            if(!flag)
                continue;

            StringBuilder sb = new StringBuilder();
            for(int i: ans)
            {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
        }
    }
}
