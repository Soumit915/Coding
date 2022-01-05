package Hackerrank.Algorithms.Greedy;

import java.util.*;

public class CloudyDay {
    static class Town implements Comparable<Town>
    {
        long population;
        long xi;
        public int compareTo(Town t)
        {
            return Long.compare(this.xi, t.xi);
        }
    }
    static class Cloud
    {
        long yi;
        long ri;
        long left;
        long right;
    }
    public static int search(Town[] city, long k, int l, int r, int type)
    {
        int mid = (l+r)/2;
        if(k==city[mid].xi)
        {
            return mid;
        }
        else if(k<city[mid].xi)
        {
            if(mid>0 && k>city[mid-1].xi)
                return mid+type-1;
            else if(mid==0)
                return -1+type;
            return search(city, k, l, mid - 1, type);
        }
        else
        {
            if(mid<city.length-1 && k<city[mid+1].xi)
            {
                //System.out.println(type);
                return mid+type;
            }
            else if(mid==city.length-1)
                return city.length+type-1;
            return search(city, k, mid+1, r, type);
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[] pop = new long[n];
        long[] xi = new long[n];
        for(int i=0;i<n;i++)
        {
            pop[i] = sc.nextLong();
        }
        for(int i=0;i<n;i++)
        {
            xi[i] = sc.nextLong();
        }

        Town[] city = new Town[n];
        for(int i=0;i<n;i++)
        {
            city[i] = new Town();
            city[i].population = pop[i];
            city[i].xi = xi[i];
        }
        Arrays.sort(city);

        int m = sc.nextInt();
        long[] yi = new long[m];
        long[] ri = new long[m];
        for(int i=0;i<m;i++)
        {
            yi[i] = sc.nextLong();
        }
        for(int i=0;i<m;i++)
        {
            ri[i] = sc.nextLong();
        }

        Cloud[] clouds = new Cloud[m];
        for(int i=0;i<m;i++)
        {
            clouds[i] = new Cloud();
            clouds[i].yi = yi[i];
            clouds[i].ri = ri[i];
            clouds[i].left = yi[i]-ri[i];
            clouds[i].right = yi[i]+ri[i];
        }

        int[] countClouds = new int[n];
        for(int i=0;i<m;i++)
        {
            int left = search(city, clouds[i].left, 0, n-1, 1);
            int right = search(city, clouds[i].right, 0, n-1, 0);
            //System.out.println(left+" "+right);
            if(left>=n || right<0)
                continue;
            countClouds[left]++;
            if(right!=n-1)
                countClouds[right+1]--;
        }

        for(int i=1;i<n;i++)
        {
            countClouds[i] += countClouds[i-1];
        }

        long sunnyTownPopulation = 0;
        for(int i=0;i<n;i++)
        {
            if(countClouds[i]==0) {
                sunnyTownPopulation += city[i].population;
                //System.out.print(i+" ");
            }
        }
        //System.out.println();

        long[] count1clound = new long[n];
        count1clound[0] = (countClouds[0]==1)?city[0].population:0;
        for(int i=1;i<n;i++)
        {
            if(countClouds[i]==1)
                count1clound[i] = count1clound[i-1]+city[i].population;
            else count1clound[i] = count1clound[i-1];
        }

        long max = Long.MIN_VALUE;
        for(int i=0;i<m;i++)
        {
            int left = search(city, clouds[i].left, 0, n-1, 1);
            int right = search(city, clouds[i].right, 0, n-1, 0);
            if(right<0 || left>=n)
                continue;
            long s = count1clound[right];
            if(left>0)
                s = s-count1clound[left-1];
            max = Math.max(max, s);
        }

        if(max==Long.MIN_VALUE)System.out.println(sunnyTownPopulation);
        else System.out.println(max+sunnyTownPopulation);
    }
}
