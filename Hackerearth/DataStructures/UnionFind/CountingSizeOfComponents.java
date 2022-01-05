package Hackerearth.DataStructures.UnionFind;

import java.util.*;

public class CountingSizeOfComponents {
    public static int parent(int[] nodes, int u)
    {
        while(true)
        {
            if(nodes[u]==u)
                return u;
            u = nodes[u];
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] nodes = new int[n];
        int[] num = new int[n];
        for(int i=0;i<n;i++)
        {
            nodes[i] = i;
            num[i] = 1;
        }
        for(int i=0;i<m;i++)
        {
            int u = sc.nextInt();
            int v = sc.nextInt();
            u-=1;
            v-=1;

            int pu = parent(nodes, u);
            int pv = parent(nodes, v);

            if(num[pu]<num[pv])
            {
                num[pv] += num[pu];
                nodes[pu] = pv;
                nodes[u] = pv;
            }
            else
            {
                num[pu] += num[pv];
                nodes[pv] = pu;
                nodes[v] = pu;
            }

            ArrayList<Integer> noc = new ArrayList<>();
            for(int j=0;j<n;j++)
            {
                if(nodes[j]==j)
                    noc.add(num[j]);
            }
            Collections.sort(noc);
            for(int j: noc)
                System.out.print(j+" ");
            System.out.println();
        }
    }
}
