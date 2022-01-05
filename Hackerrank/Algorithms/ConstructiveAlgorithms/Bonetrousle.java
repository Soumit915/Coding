package Hackerrank.Algorithms.ConstructiveAlgorithms;

import java.util.*;
public class Bonetrousle
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while(t-->0)
        {
            long n = sc.nextLong();    //number of sticks needed
            long k = sc.nextLong();    //number of boxes present
            long b = sc.nextLong();    //number of boxes needed

            if(k<b)
            {
                sb.append("-1").append("\n");
                continue;
            }

            long minstick = (b * (b+1)) / 2;
            long maxstick = Long.MAX_VALUE;
            if(k<2000000000)
                maxstick = (k * (k+1)) / 2;

            if(n<minstick || n>maxstick)
            {
                sb.append("-1").append("\n");
                continue;
            }

            long[] ans = new long[(int) b];
            for(int i=1;i<=b;i++)
                ans[i-1] = i;

            long cursticks = minstick;
            long right = k;

            for(int i = (int) b; i>0; i--)
            {
                cursticks -= i;
                if(n-cursticks > right)
                {
                    ans[i-1] = right;
                    cursticks += right;
                    right--;
                }
                else
                {
                    ans[i-1] = n-cursticks;
                    break;
                }
            }

            long sum = 0;
            for(long i: ans) sum += i;

            if(sum!=n)
            {
                sb.append("-1").append("\n");
                continue;
            }
            for(long i: ans)
                sb.append(i).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);

    }
}

