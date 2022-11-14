package GoogleKickStart.RoundF_2022;

import java.util.*;
import java.io.*;

public class A {

    static class Node{
        String s;
        int id, d;

        int a;

        Node(String s, int d, int id){
            this.s = s;
            this.d = d;
            this.id = id;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=tc;testi++){
            sb.append("Case #").append(testi).append(": ");

            int n = sc.nextInt();
            Node[] nodes = new Node[n];
            for(int i=0;i<n;i++){
                nodes[i] = new Node(sc.next(), sc.nextInt(), sc.nextInt());
            }

            Arrays.sort(nodes, (n1, n2) -> {
                if(n1.s.equals(n2.s)){
                    return Integer.signum(n1.id - n2.id);
                }
                else{
                    return Integer.signum(n1.s.compareTo(n2.s));
                }
            });

            for(int i=0;i<n;i++){
                nodes[i].a = i;
            }

            Arrays.sort(nodes, (n1, n2) -> {
                if(n1.d == n2.d){
                    return Integer.signum(n1.id - n2.id);
                }
                else{
                    return Integer.signum(n1.d - n2.d);
                }
            });

            int c = 0;
            for(int i=0;i<n;i++){
                if(nodes[i].a == i){
                    c++;
                }
            }

            sb.append(c).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
