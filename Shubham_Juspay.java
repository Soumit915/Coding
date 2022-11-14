import java.io.*;
import java.util.*;

public class Shubham_Juspay {
    static class Node{
        int id;
        Node next;
        boolean isVisited;
        Node(int id){
            this.id = id;
            this.isVisited = false;
            this.next = null;
        }
    }

    static class Graph{
        ArrayList<Node> nodelist = new ArrayList<>();
        Graph(int n){
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v){
            if(v==-1)
                return;
            Node nu = nodelist.get(u);
            nu.next = nodelist.get(v);
        }
        public Node findMid(Node nu, Node nv){
            Queue<Node> q = new LinkedList<>();
            q.add(nu);
            nu.isVisited = true;

            q.add(nv);
            nv.isVisited = true;

            while(!q.isEmpty()){
                Node cur = q.remove();
                if(cur.next!=null){
                    if(cur.next.isVisited)
                        return cur.next;
                    else {
                        cur.next.isVisited = true;
                        q.add(cur.next);
                    }
                }
            }

            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();

            Graph gr = new Graph(n);
            for(int i=0;i<n;i++){
                gr.addEdge(i, sc.nextInt());
            }

            int c1 = sc.nextInt();
            int c2 = sc.nextInt();

            Node mid = gr.findMid(gr.nodelist.get(c1), gr.nodelist.get(c2));
            if(mid==null || mid.id==c1 || mid.id==c2)
                sb.append("-1\n");
            else sb.append(mid.id).append("\n");
        }

        System.out.println(sb);
    }
}
