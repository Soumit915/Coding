package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week4;

import java.io.*;
import java.util.*;

public class AbsolutePatternMatching {
    static long base = 263;
    static long p1 = (long) 1e9+7;
    static long p2 = (long) 1e9+9;
    static long[] dp1a;
    static long[] dp2a;
    static long[] dp1b;
    static long[] dp2b;
    static long[] pow1;
    static long[] pow2;
    static void precompute(String a, String b)
    {
        dp1a = new long[a.length()];
        dp2a = new long[a.length()];
        dp1b = new long[b.length()];
        dp2b = new long[b.length()];
        dp1a[0] = a.charAt(0);
        dp2a[0] = a.charAt(0);
        dp1b[0] = b.charAt(0);
        dp2b[0] = b.charAt(0);
        for(int i=1;i<a.length();i++)
        {
            long ch = a.charAt(i);
            dp1a[i] = (((dp1a[i-1] * base)%p1) + ch)%p1;
            dp2a[i] = (((dp2a[i-1] * base)%p2) + ch)%p2;
        }
        for(int i=1;i<b.length();i++)
        {
            long ch = b.charAt(i);
            dp1b[i] = (((dp1b[i-1] * base)%p1) + ch)%p1;
            dp2b[i] = (((dp2b[i-1] * base)%p2) + ch)%p2;
        }
    }
    static void precomputePow()
    {
        pow1 = new long[100100];
        pow2 = new long[100100];
        pow1[0] = 1;
        pow2[0] = 1;
        for(int i=1;i<100100;i++)
        {
            pow1[i] = (pow1[i-1]*base)%p1;
            pow2[i] = (pow2[i-1]*base)%p2;
        }
    }
    static boolean isValid(String a, String b, int astart, int bstart, int length)
    {
        if(astart+length-1 >= a.length())
            return false;
        long hash1a = dp1a[astart+length-1];
        long hash2a = dp2a[astart+length-1];
        if(astart!=0) {
            hash1a = (hash1a - (dp1a[astart - 1] * pow1[length]) % p1 + p1) % p1;
            hash2a = (hash2a - (dp2a[astart - 1] * pow2[length]) % p2 + p2) % p2;
        }

        //System.out.println("Testing: "+bstart+" "+length);
        if(bstart+length-1 >= b.length())
            return false;

        long hash1b = dp1b[bstart+length-1];
        long hash2b = dp2b[bstart+length-1];
        if(bstart!=0) {
            hash1b = (hash1b - (dp1b[bstart - 1] * pow1[length]) % p1 + p1) % p1;
            hash2b = (hash2b - (dp2b[bstart - 1] * pow2[length]) % p2 + p2) % p2;
        }

        return hash1a == hash1b && hash2a == hash2b;
    }
    public static void main(String[] args) throws IOException {
        /*long start = System.currentTimeMillis();

        File file = new File("Input.txt");
        Scanner sc = new Scanner(file);

        FileWriter fw = new FileWriter("Output1.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);*/

        Scanner sc = new Scanner(System.in);
        precomputePow();

        StringBuilder sb = new StringBuilder();
        while(sc.hasNext())
        {
            int k = sc.nextInt();
            String a = sc.next();
            int al = a.length();
            String b = sc.next();
            int bl = b.length();

            precompute(a, b);
            ArrayList<Integer> list = new ArrayList<>();
            for(int i=0;i<=al-bl;i++)
            {
                int last = i-1;
                for(int j=1;j<=k+1;j++)
                {
                    int ll = 1, ul = al-i+1;
                    while(ll<ul)
                    {
                        int mid = (ll+ul)/2;
                        //System.out.println("Loops: "+ll+" "+ul+" "+mid+" "+(last+1)+" "+(last-i+1));
                        //System.out.println(isValid(a, b, last+1, last-i+1, mid));
                        if(isValid(a, b, last+1, last-i+1, mid))
                        {
                            ll = mid+1;
                        }
                        else
                        {
                            ul = mid;
                        }
                    }

                    last = last + ll;
                    //System.out.println(i+" "+j+" "+(last));
                }

                //System.out.println(i+bl+" "+last);
                if(i+bl<last+1)
                    list.add(i);
            }

            sb.append(list.size()).append(" ");
            for(int i:list)
                sb.append(i).append(" ");
            sb.append("\n");

            //System.out.println("Test");
            //System.out.println(list);
        }

        System.out.println(sb);

        /*pw.close();
        bw.close();
        fw.close();

        long end = System.currentTimeMillis();
        System.out.println(((double)end-start)/1000);*/
    }
}
