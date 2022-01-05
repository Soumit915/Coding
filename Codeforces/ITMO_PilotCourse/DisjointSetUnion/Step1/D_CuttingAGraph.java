package Codeforces.ITMO_PilotCourse.DisjointSetUnion.Step1;

import java.util.*;

public class D_CuttingAGraph {
    static class Query{
        String type;
        int u;
        int v;
        Query(String type, int u, int v)
        {
            this.type = type;
            this.u = u;
            this.v = v;
        }
    }
    static class Set{
        int id;
        int count;
        Set parent;
        Set(int id)
        {
            this.id = id;
            this.parent = this;
            this.count = 1;
        }
        public void union(Set b)
        {
            if(this.count>b.count)
            {
                b.parent = this;
                this.count += b.count;
            }
            else
            {
                this.parent = b;
                b.count += this.count;
            }
        }
        public Set get()
        {
            if(this.parent == this)
                return this;
            this.parent = this.parent.get();
            return this.parent;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        Query[] queries = new Query[k];
        Set[] sets = new Set[n];
        for(int i=0;i<n;i++)
        {
            sets[i] = new Set(i);
        }

        for(int i=0;i<m;i++)
        {
            sc.nextInt();
            sc.nextInt();
        }

        int ask_queries = 0;
        for(int i=0;i<k;i++)
        {
            queries[i] = new Query(sc.next(), sc.nextInt()-1, sc.nextInt()-1);
            if(queries[i].type.equals("ask"))
                ask_queries++;
        }

        String[] ans = new String[ask_queries];
        int j = ask_queries-1;
        for(int i=k-1;i>=0;i--)
        {
            if(queries[i].type.equals("cut"))
            {
                Set parentu = sets[queries[i].u].get();
                Set parentv = sets[queries[i].v].get();
                if(parentu!=parentv)
                {
                    parentu.union(parentv);
                }
            }
            else
            {
                Set parentu = sets[queries[i].u].get();
                Set parentv = sets[queries[i].v].get();
                if(parentu==parentv)
                    ans[j] = "YES";
                else ans[j] = "NO";
                j--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(String s: ans)
            sb.append(s).append("\n");
        System.out.println(sb);
    }
}
