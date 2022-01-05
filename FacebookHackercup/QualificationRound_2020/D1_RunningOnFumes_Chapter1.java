package FacebookHackercup.QualificationRound_2020;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class D1_RunningOnFumes_Chapter1
{
    public static int nextPowerOf2(int n)
    {
        n = n|(n>>1);
        n = n|(n>>2);
        n = n|(n>>4);
        n = n|(n>>8);
        n = n|(n>>16);
        n = n|(n>>24);
        return (n+1);
    }
    public static long query(long[] segTree, int ind, int start, int end, int l, int r)
    {
        if(start<=l && end>=r)
        {
            return segTree[ind];
        }

        if(start>r || end<l)
        {
            return Long.MAX_VALUE;
        }

        int mid = (l+r)/2;
        return Math.min(query(segTree, 2*ind+1, start, end, l, mid),
                query(segTree, 2*ind+2, start, end, mid+1, r));
    }
    public static void update(long[] segTree, int ind, int l, int r, int ni, long val)
    {
        if(l==r)
        {
            segTree[ind] = val;
            return;
        }

        int mid = (l+r)/2;
        if(ni<=mid)
        {
            update(segTree, 2*ind+1, l, mid, ni, val);
        }
        else
        {
            update(segTree, 2*ind+2, mid+1, r, ni, val);
        }

        segTree[ind] = Math.min(segTree[2*ind+1], segTree[2*ind+2]);
    }
    public static void main(String[] args) throws IOException {

        File file = new File("Input.txt");

        FileWriter fw = new FileWriter("Output.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            int t = sc.nextInt();

            for(int testi=1;testi<=t;testi++)
            {
                int n = sc.nextInt();
                int m = sc.nextInt();
                long[] cost = new long[n];
                for(int i=0;i<n;i++)
                {
                    long val = sc.nextLong();
                    if(val==0 && i!=0)
                    {
                        cost[i] = Long.MAX_VALUE;
                    }
                    else
                    {
                        cost[i] = val;
                    }
                }
                cost[n-1] = 0;

                int sn = 2*nextPowerOf2(n)-1;
                long[] segTree = new long[sn];
                for(int i=0;i<sn;i++)
                    segTree[i] = Long.MAX_VALUE;

                long[] optimalcost = new long[n];
                optimalcost[0] = 0;
                update(segTree, 0, 0, n-1, 0, 0);
                for(int i=1;i<n;i++)
                {
                    long min = query(segTree, 0, Math.max(i-m, 0), i-1, 0, n-1);
                    //System.out.println(min);
                    if(min == Long.MAX_VALUE)
                    {
                        optimalcost[i] = Long.MAX_VALUE;
                    }
                    else if(cost[i] == Long.MAX_VALUE)
                    {
                        optimalcost[i] = Long.MAX_VALUE;
                    }
                    else
                    {
                        optimalcost[i] = min+cost[i];
                        update(segTree, 0, 0, n-1, i, optimalcost[i]);
                    }
                }

                pw.print("Case #"+testi+": ");
                if(optimalcost[n-1]==Long.MAX_VALUE)
                {
                    pw.println(-1);
                }
                else {
                    long ans = optimalcost[n - 1] - cost[n - 1];
                    pw.println(ans);
                }
            }

            pw.close();
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}