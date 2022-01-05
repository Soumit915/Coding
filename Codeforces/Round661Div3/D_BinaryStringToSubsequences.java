package Codeforces.Round661Div3;

import java.util.*;

public class D_BinaryStringToSubsequences {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            String s = sc.next();

            int z=0;
            int[] ans = new int[n];
            Stack<Integer> zerostk = new Stack<>();
            Stack<Integer> onestk = new Stack<>();

            for(int i=0;i<n;i++)
            {
                if(s.charAt(i)=='0')
                {
                    if(zerostk.isEmpty())
                    {
                        z++;
                        ans[i] = z;
                        onestk.push(z);
                    }
                    else
                    {
                        ans[i] = zerostk.pop();
                        onestk.push(ans[i]);
                    }
                }
                else
                {
                    if(onestk.isEmpty())
                    {
                        z++;
                        ans[i] = z;
                        zerostk.push(z);
                    }
                    else
                    {
                        ans[i] = onestk.pop();
                        zerostk.push(ans[i]);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            int max = Integer.MIN_VALUE;
            for(int i: ans)
            {
                sb.append(i).append(" ");
                max = Math.max(max, i);
            }
            System.out.println(max);
            System.out.println(sb);
        }
    }
}