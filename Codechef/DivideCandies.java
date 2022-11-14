package Codechef;

import java.util.*;

public class DivideCandies {
    static Scanner sc = new Scanner(System.in);
    public static void goto1()
    {
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            long n = sc.nextLong();

            double totsum = (double) (n * (n+1))/2;
            long k = (long) totsum / 2;
            double disc = 1 + (8*k);
            double D = Math.sqrt(disc);
            long sols = (long) Math.ceil((D-1)/2);
            long checksol =  (sols * (sols+1))/2;
            if(n%4==1 || n%4==2)
            {

                sb.append(1).append("\n");
                if(checksol == k)
                {
                    for(long i=1;i<=sols;i++)
                    {
                        sb.append("0");
                    }
                    for(long i=sols+1;i<=n;i++)
                    {
                        sb.append("1");
                    }
                }
                else
                {
                    long mid = checksol - k;
                    for(int i=1;i<=sols;i++)
                    {
                        if(mid == i)
                            sb.append("1");
                        else sb.append("0");
                    }
                    for(long i=sols+1;i<=n;i++)
                    {
                        sb.append("1");
                    }
                }
            }
            else
            {

                sb.append(0).append("\n");
                if(checksol == k)
                {
                    for(long i=1;i<=sols;i++)
                    {
                        sb.append("0");
                    }
                    for(long i=sols+1;i<=n;i++)
                    {
                        sb.append("1");
                    }
                }
                else
                {
                    long mid = checksol - k;
                    for(int i=1;i<=sols;i++)
                    {
                        if(mid == i)
                            sb.append("1");
                        else sb.append("0");
                    }
                    for(long i=sols+1;i<=n;i++)
                    {
                        sb.append("1");
                    }
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
    public static void goto2()
    {
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (t-->0)
        {
            long n = sc.nextLong();
            long totsum = (n * (n+1) * (2*n + 1))/6;

            TreeSet<Long> tree = new TreeSet<>();
            for(long i=1;i<=n;i++)
            {
                tree.add(i*i);
            }

            long target = totsum/2;
            Set<Long> ans_set = new HashSet<>();
            long mysum = 0;
            while(target>0)
            {
                long floor = Long.MIN_VALUE;
                if(tree.floor(target)!=null)
                    floor = tree.floor(target);
                /*long ceil = Long.MAX_VALUE;
                if(tree.ceiling(target)!=null)
                    ceil = tree.ceiling(target);*/

                /*if(Math.abs(target-floor) > Math.abs(target-ceil))
                {
                    if(Math.abs(totsum - 2*(mysum + ceil)) > Math.abs(totsum - 2*mysum))
                        break;
                    target -= ceil;
                    long index = (long) Math.sqrt(ceil);
                    ans_set.add(index);
                    mysum += ceil;
                    tree.remove(ceil);
                }
                else
                {*/
                    if(Math.abs(totsum - 2*(mysum + floor)) > Math.abs(totsum - 2*mysum))
                        break;
                    target -= floor;
                    long index = (long) Math.sqrt(floor);
                    ans_set.add(index);
                    mysum += floor;
                    tree.remove(floor);
                //}
            }

            long diff = Math.abs(totsum - 2*mysum);
            sb.append(diff).append("\n");
            for(long i=1;i<=n;i++)
            {
                if(ans_set.contains(i))
                    sb.append("1");
                else sb.append("0");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
    public static void main(String[] args)
    {
        int k = sc.nextInt();

        if(k == 1)
            goto1();
        else if(k==2)
            goto2();
        else System.out.println("1\n10");
    }
}
