package Codeforces.ITMO_PilotCourse.DisjointSetUnion.Step2;

import java.io.*;
import java.util.*;

public class PeopleAreLeaving {
    static class Dsu{
        int id;
        Dsu parent;
        Dsu(int id){
            this.id = id;
            this.parent = this;
        }
        public Dsu get(){
            if(this.parent == this){
                return this;
            }
            this.parent = this.parent.get();
            return this.parent;
        }
        public void union(Dsu sb){
            Dsu pa = this.get();
            Dsu pb = sb.get();

            if(pa.id>pb.id){
                pb.parent = pa;
            }
            else{
                pa.parent = pb;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();
        Dsu[] sets = new Dsu[n+1];
        for(int i=0;i<=n;i++)
            sets[i] = new Dsu(i);

        StringBuilder sb = new StringBuilder();
        while (q-->0){
            char ch = sc.next().charAt(0);
            int ind = sc.nextInt();

            if(ch=='-'){
                sets[ind-1].union(sets[ind]);
            }
            else{
                Dsu set = sets[ind-1].get();
                if(set.id==n) sb.append(-1).append("\n");
                else sb.append(set.id+1).append("\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
