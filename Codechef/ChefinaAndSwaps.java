package Codechef;

import java.util.*;

public class ChefinaAndSwaps
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            long[] a = new long[n];
            long[] b = new long[n];

            HashMap<Long, Integer> hashcount = new HashMap<>();
            long min = Long.MAX_VALUE;
            for(int i=0;i<n;i++)
            {
                a[i] = sc.nextInt();
                min = Math.min(min, a[i]);
                if(hashcount.containsKey(a[i]))
                {
                    hashcount.put(a[i], hashcount.get(a[i])+1);
                }
                else
                {
                    hashcount.put(a[i],1);
                }
            }

            for(int i=0;i<n;i++)
            {
                b[i] = sc.nextInt();
                min = Math.min(min, b[i]);
                if(hashcount.containsKey(b[i]))
                {
                    hashcount.put(b[i], hashcount.get(b[i])+1);
                }
                else
                {
                    hashcount.put(b[i],1);
                }
            }

            HashMap<Long, Integer> acurfreq = new HashMap<>();
            HashMap<Long, Integer> bcurfreq = new HashMap<>();
            Set<Long> keyset = hashcount.keySet();
            boolean flag = true;
            for(long i: keyset)
            {
                if(hashcount.get(i)%2==1)
                {
                    flag = false;
                    break;
                }
                else
                {
                    acurfreq.put(i, hashcount.get(i)/2);
                    bcurfreq.put(i, hashcount.get(i)/2);
                }
            }

            if(!flag)
            {
                System.out.println(-1);
                continue;
            }

            ArrayList<Long> aswaps = new ArrayList<>();
            ArrayList<Long> bswaps = new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                if(acurfreq.get(a[i])!=0)
                    acurfreq.put(a[i],acurfreq.get(a[i])-1);
                else
                    aswaps.add(a[i]);
            }
            for(int i=0;i<n;i++)
            {
                if(bcurfreq.get(b[i])!=0)
                    bcurfreq.put(b[i],bcurfreq.get(b[i])-1);
                else
                    bswaps.add(b[i]);
            }

            if(aswaps.size()!=bswaps.size())
            {
                System.out.println(-1);
                continue;
            }

            if(aswaps.size()==0)
            {
                System.out.println(0);
                continue;
            }

            Collections.sort(aswaps);
            bswaps.sort(Collections.reverseOrder());

            long sum=0;
            for(int i=0;i<aswaps.size();i++)
            {
                sum += Math.min(2*min, Math.min(aswaps.get(i), bswaps.get(i)));
            }

            System.out.println(sum);

        }
    }
}