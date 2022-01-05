package Codeforces.CEOI2020_Mirror;

import java.util.*;

public class A {
    static long mod = (long) 1e9+7;
    static int n;
    static long df;
    static Rect[] segTree;
    static long[] prefixSum;
    static class Rect implements Comparable<Rect>
    {
        int id;
        long height;
        long width;
        Rect(int id, long height, long width)
        {
            this.id = id;
            this.height = height;
            this.width = width;
        }
        public int compareTo(Rect r)
        {
            return Long.compare(this.height, r.height);
        }
    }
    static long x, y;
    public static void gcdExtended(long a, long b)
    {
        if(a%b==0)
        {
            x = 1;
            y = 1-(a/b);
            return;
        }
        gcdExtended(b, a%b);
        long temp = y;
        y = x-((a/b)*y)%mod;
        x = temp;
    }
    public static void modInverse(long a)
    {
        gcdExtended(a, mod);
        x = (x%mod+mod)%mod;
        df = x;
    }
    public static void calcSum(long[] width)
    {
        prefixSum = new long[n+1];
        prefixSum[0] = 0;
        for(int i=0;i<n;i++)
        {
            prefixSum[i+1] = (prefixSum[i]+width[i])%mod;
        }
    }
    public static int nextPowerOf2(int n)
    {
        n = n|n>>1;
        n = n|n>>2;
        n = n|n>>4;
        n = n|n>>8;
        n = n|n>>16;
        n = n|n>>31;
        return n+1;
    }
    public static void built(Rect[] rectangles, int ind, int l, int r)
    {
        if(l==r)
        {
            segTree[ind] = rectangles[l];
            return;
        }

        int mid = (l+r)/2;
        built(rectangles, 2*ind+1, l, mid);
        built(rectangles, 2*ind+2, mid+1, r);
        if(segTree[2*ind+1].height<=segTree[2*ind+2].height)
        {
            segTree[ind] = segTree[2*ind+1];
        }
        else
        {
            segTree[ind] = segTree[2*ind+2];
        }
    }
    public static int query(Rect[] rectangles, int ind, int start, int end, int l, int r)
    {
        if(start>r || end<l)
        {
            return Integer.MAX_VALUE;
        }

        if(l>=start && r<=end)
        {
            return segTree[ind].id;
        }

        int mid = (l+r)/2;
        int left = query(rectangles, ind*2+1, start, end, l, mid);
        int right = query(rectangles, ind*2+2, start, end, mid+1, r);
        if(left>rectangles.length)
            return right;
        else if(right>rectangles.length)
            return left;
        else if(rectangles[left].height<=rectangles[right].height)
            return left;
        else return right;
    }
    public static long calcRectProduct(long h, long w)
    {
        long totrects = w%mod;
        totrects = (totrects*(w+1))%mod;
        totrects = (totrects*h)%mod;
        totrects = (totrects*(h+1))%mod;
        totrects = (totrects*df)%mod;
        return totrects;
    }
    public static long countRect(Rect[] rectangles, int start, int end, long overhead)
    {
        if(start>end)
        {
            return 0;
        }

        int minIndex = query(rectangles, 0, start, end, 0, n-1);
        long minHeight = rectangles[minIndex].height;
        long totalWidth = prefixSum[end+1]-prefixSum[start];

        long totrects = calcRectProduct(minHeight, totalWidth);
        long ov = calcRectProduct(overhead, totalWidth);
        totrects = ((totrects-ov)+mod)%mod;

        long left = countRect(rectangles, start, minIndex-1, minHeight);
        long right = countRect(rectangles, minIndex+1, end, minHeight);

        return (left+right+totrects)%mod;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        long[] height = new long[n];
        long[] width = new long[n];
        Rect[] rectangles = new Rect[n];
        for(int i=0;i<n;i++)
        {
            height[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++)
        {
            width[i] = sc.nextInt();
            rectangles[i] = new Rect(i, height[i], width[i]);
        }

        calcSum(width);
        modInverse(4);
        segTree = new Rect[(2*nextPowerOf2(n)-1)];
        built(rectangles, 0, 0, n-1);
        System.out.println(countRect(rectangles, 0, n-1, 0));
    }
}
