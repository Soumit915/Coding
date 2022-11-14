package Codechef;

import java.util.*;

public class BulbsAndWires {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();
            String s = sc.next();

            ArrayList<Integer> weight = new ArrayList<>();
            ArrayList<Character> str = new ArrayList<>();

            int count=1;
            for(int i=1;i<n;i++)
            {
                if(s.charAt(i)!=s.charAt(i-1))
                {
                    weight.add(count);
                    str.add(s.charAt(i-1));
                    count=1;
                }
                else
                    count++;
            }

            str.add(s.charAt(n-1));
            weight.add(count);

            if(k%2==1)
            {
                int last=0;
                int first=0;
                if(str.get(0)=='0')
                    first = weight.get(0);
                if(str.get(str.size()-1)=='0')
                    last = weight.get(weight.size()-1);

                k-=1;
                int max = Math.max(first, last);
                if(max==first)
                {
                    if(str.get(0)=='0')
                        weight.set(0, 0);
                }
                else
                {
                    if(str.get(str.size()-1)=='0')
                        weight.set(str.size()-1, 0);
                }
            }

            int sides = 0;
            if(str.get(0)=='0')
            {
                sides += weight.get(0);
            }
            if(str.get(str.size()-1)=='0')
            {
                sides += weight.get(weight.size()-1);
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
            for(int i=1;i<weight.size()-1;i++)
            {
                if(str.get(i)=='0')
                    heap.add(weight.get(i));
            }

            while(k>0)
            {
                int peek = 0;
                if(!heap.isEmpty())
                {
                    peek = heap.peek();
                }

                if(peek >= sides)
                {
                    heap.remove();
                }
                else
                {
                    sides = 0;
                }
                k-=2;
            }

            int ans = sides;
            while(!heap.isEmpty())
            {
                ans += heap.remove();
            }

            System.out.println(ans);
        }
    }
}
