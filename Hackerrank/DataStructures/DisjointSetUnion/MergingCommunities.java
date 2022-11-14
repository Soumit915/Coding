package Hackerrank.DataStructures.DisjointSetUnion;

import java.util.*;

public class MergingCommunities {
    static class Dsu
    {
        int id;
        int count;
        Dsu parent;
        Dsu(int id)
        {
            this.id = id;
            this.count = 1;
            this.parent = null;
        }
        public Dsu find()
        {
            if(this.parent==null)
                return this;

            this.parent = this.parent.find();
            return this.parent;
        }
        public void union(Dsu set)
        {
            Dsu set1 = this.find();
            Dsu set2 = set.find();

            int c1 = set1.count;
            int c2 = set2.count;

            if(c1>c2)
            {
                set2.parent = set1;
                set1.count += set2.count;
            }
            else
            {
                set1.parent = set2;
                set2.count += set1.count;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        Dsu[] dsu = new Dsu[n];
        for(int i=0;i<n;i++)
            dsu[i] = new Dsu(i);

        for(int i=0;i<q;i++)
        {
            char ch = sc.next().charAt(0);

            if(ch=='M')
            {
                int a = sc.nextInt()-1;
                int b = sc.nextInt()-1;

                if(dsu[a].find()==dsu[b].find())
                {
                    continue;
                }
                dsu[a].union(dsu[b]);
            }
            else
            {
                int a = sc.nextInt()-1;

                System.out.println(dsu[a].find().count);
            }
        }

    }
}