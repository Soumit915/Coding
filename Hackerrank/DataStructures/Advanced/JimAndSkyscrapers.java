package Hackerrank.DataStructures.Advanced;

import java.util.*;

public class JimAndSkyscrapers
{
    public static long nC2(int n)
    {
        return n*(n-1)/2;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Stack<Integer> stk = new Stack<>();
        long ans = 0;
        for(int i=0;i<n;i++)
        {
            int val = sc.nextInt();
            int last = 0;
            int count = 0;
            while(!stk.isEmpty() && stk.peek()<val)
            {
                int cur = stk.pop();
                if(cur == last)
                {
                    count++;
                    //System.out.println(count);
                }
                else
                {
                    ans += nC2(count);
                    count = 1;
                }
                last = cur;
            }
            ans += nC2(count);
            stk.push(val);
        }

        int last = 0;
        int count = 0;
        while(!stk.isEmpty())
        {
            int cur = stk.pop();
            if(cur == last)
            {
                count++;
            }
            else
            {
                ans += nC2(count);
                count = 1;
            }
            last = cur;
        }
        ans += nC2(count);

        System.out.println(ans*2);
    }
}
