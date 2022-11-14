package Codechef;

import java.util.*;

public class GameOnAStrip {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];

            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
            }

            int count = 0;
            int max = -1, smax = -2;
            Map<Integer, Integer> hash = new HashMap<>();
            for(int i=0;i<n;i++)
            {
                if(arr[i]==0)
                {
                    count++;
                }
                else
                {
                    if(hash.containsKey(count))
                    {
                        hash.put(count, hash.get(count)+1);
                    }
                    else
                    {
                        hash.put(count, 1);
                    }

                    if(count>=max)
                    {
                        smax = max;
                        max = count;
                    }
                    else if(count>=smax)
                    {
                        smax = count;
                    }

                    count = 0;

                }
            }

            max = Math.max(count, max);
            if(hash.containsKey(count))
            {
                hash.put(count, hash.get(count)+1);
            }
            else
            {
                hash.put(count, 1);
            }

            if(max == 0)
            {
                System.out.println("No");
            }
            else
            {
                if(max % 2 == 0)
                {
                    System.out.println("No");
                }
                else if(max/2 < smax)
                {
                    System.out.println("No");
                }
                else if(hash.get(max)>1)
                {
                    System.out.println("No");
                }
                else
                {
                    System.out.println("Yes");
                }
            }

            //System.out.println(hash);
        }
    }
}
