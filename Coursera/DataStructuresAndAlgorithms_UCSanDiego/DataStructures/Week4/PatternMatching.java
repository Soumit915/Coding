package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week4;

import java.util.*;

public class PatternMatching {
    static long p1 = (long) 1e9+7;
    static long p2 = (long) 1e9+9;
    static long base = 263;
    static long getHash(String a, long p) {

        long hashVal = 0;
        for(int i=0;i<a.length();i++)
        {
            long ch = a.charAt(i);
            hashVal = ((hashVal*base)%p + ch)%p;
        }
        return hashVal;
    }
    static long pow(long a, long b, long mod)
    {
        long p = 1;
        while(b>0)
        {
            if(b%2==1)
            {
                p = (p*a)%mod;
            }
            a = (a*a)%mod;
            b /= 2;
        }
        return p;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String b = sc.next();
        int bl = b.length();

        String a = sc.next();
        int al = a.length();

        long hash1 = getHash(b, p1);
        long hash2 = getHash(b, p2);

        long[] dp1 = new long[al];
        long[] dp2 = new long[al];

        ArrayList<Integer> arlist = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        dp1[0] = ((long) a.charAt(0))%p1;
        dp2[0] = ((long) a.charAt(0))%p2;
        for(int i=1;i<bl;i++)
        {
            long ch = a.charAt(i);
            dp1[i] = ((dp1[i-1] * base)%p1 + ch)%p1;
            dp2[i] = ((dp2[i-1] * base)%p2 + ch)%p2;
        }

        if(dp1[bl-1]==hash1 && dp2[bl-1]==hash2)
            arlist.add(0);

        for(int i=bl;i<al;i++)
        {
            long ch = a.charAt(i);
            dp1[i] = ((dp1[i-1] * base)%p1 + ch)%p1;
            dp2[i] = ((dp2[i-1] * base)%p2 + ch)%p2;
            long dh1 = (dp1[i] - (dp1[i-bl] * pow(base, bl, p1))%p1 + p1)%p1;
            long dh2 = (dp2[i] - (dp2[i-bl] * pow(base, bl, p2))%p2 + p2)%p2;

            if(dh1==hash1 && dh2==hash2) {
                arlist.add(i - bl + 1);
            }
        }

        for(int i: arlist)
            sb.append(i).append(" ");
        System.out.println(sb);
    }
}
