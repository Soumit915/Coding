package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week4;

import java.io.*;
import java.util.*;

public class LongestCommonSubstring {
    static int astart , bstart;
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
    static boolean isValid(String a, String b, int k)
    {
        if(k==0)
            return true;
        int al = a.length(), bl = b.length();

        HashMap<Long, HashMap<Long, Integer>> map = new HashMap<>();

        for(int i=Math.max(1, k-1);i<al;i++)
        {
            long hash1 = dp1a[i];
            long hash2 = dp2a[i];
            if(i!=k-1) {
                hash1 = (hash1 - (dp1a[i - k] * pow1[k]) % p1 + p1) % p1;
                hash2 = (hash2 - (dp2a[i - k] * pow2[k]) % p2 + p2) % p2;
            }

            HashMap<Long, Integer> innerMap = map.getOrDefault(hash1, new HashMap<>());
            innerMap.put(hash2, i-k+1);
            map.put(hash1, innerMap);
        }

        for(int i=Math.max(1, k-1);i<bl;i++)
        {
            long hash1 = dp1b[i];
            long hash2 = dp2b[i];
            if(i!=k-1) {
                hash1 = (hash1 - (dp1b[i - k] * pow1[k]) % p1 + p1) % p1;
                hash2 = (hash2 - (dp2b[i - k] * pow2[k]) % p2 + p2) % p2;
            }

            if(map.containsKey(hash1) && map.get(hash1).containsKey(hash2)) {
                astart = map.get(hash1).get(hash2);
                bstart = i-k+1;
                return true;
            }
        }

        return false;
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
            String a = sc.next();
            int al = a.length();
            String b = sc.next();
            int bl = b.length();

            astart = 0;
            bstart = 0;
            precompute(a, b);

            int ll = 0, ul = Math.min(al, bl);
            while(ll<ul)
            {
                int mid = (int) Math.ceil((ll+ul)/2.0);
                if(isValid(a, b, mid))
                {
                    ll = mid;
                }
                else {
                    ul = mid-1;
                }
            }

            sb.append(astart).append(" ").append(bstart).append(" ").append(ll).append("\n");
        }

        System.out.println(sb);
    }
}
