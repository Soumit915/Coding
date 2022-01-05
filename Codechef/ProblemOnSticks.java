package Codechef;

import java.util.*;

public class ProblemOnSticks {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();

            Set<Integer> set = new HashSet<>();
            for(int i=0;i<n;i++)
            {
                int val = sc.nextInt();
                if(val!=0)
                    set.add(val);
            }

            System.out.println(set.size());
        }
    }
}
