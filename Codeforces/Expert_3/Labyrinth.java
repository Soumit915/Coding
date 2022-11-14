package Codeforces.Expert_3;

import java.util.*;
import java.io.*;

public class Labyrinth{

    static class Node{
        int id;
        List<Node> adlist = new ArrayList<>();

        HashMap<Integer, Node> parent = new HashMap<>();

        Node(int id){
            this.id = id;
        }
    }

    static class Graph{
        List<Node> nodelist = new ArrayList<>();

        Graph(int n){
            for(int i=0;i<n;i++){
                nodelist.add(new Node(i));
            }
        }

        public void addEdge(int u, int v){
            Node nu = nodelist.get(u);
            Node nv = nodelist.get(v);

            nu.adlist.add(nv);
        }

        public Node dfs(int s){
            Node source = nodelist.get(s);

            Node dest = null;
            for(int i=0;i<source.adlist.size();i++){
                Set<Node> visited = new HashSet<>();

                Node init = source.adlist.get(i);
                Stack<Node> stk = new Stack<>();
                Stack<Integer> ptrstk = new Stack<>();

                visited.add(source);
                visited.add(init);

                init.parent.put(i, source);

                if(init.parent.size() > 1){
                    dest = init;
                    break;
                }

                stk.push(init);
                ptrstk.push(-1);

                while(!stk.isEmpty()){
                    Node cur = stk.pop();
                    int ptr = ptrstk.pop();

                    if(ptr < cur.adlist.size() - 1){
                        ptr++;
                        stk.push(cur);
                        ptrstk.push(ptr);

                        Node next = cur.adlist.get(ptr);

                        if(!visited.contains(next)){
                            if(next.parent.size() > 0){
                                next.parent.put(i, cur);
                                dest = next;
                                break;
                            }

                            next.parent.put(i, cur);
                            stk.push(next);
                            ptrstk.push(-1);
                            visited.add(next);
                        }
                    }
                }

                if(dest!=null){
                    break;
                }
            }

            return dest;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int s = Integer.parseInt(line[2]) - 1;

        StringBuilder sb = new StringBuilder();
        Graph gr = new Graph(n);

        for(int i=0;i<m;i++){
            line = br.readLine().split(" ");
            int u = Integer.parseInt(line[0]);
            int v = Integer.parseInt(line[1]);

            gr.addEdge(u-1, v-1);
        }

        Node dest = gr.dfs(s);
        Node source = gr.nodelist.get(s);

        if(dest == null){
            sb.append("Impossible\n");
        }
        else {
            sb.append("Possible\n");
            int id1 = 0, id2 = 0;
            for(int i: dest.parent.keySet()){
                id1 = id2;
                id2 = i;
            }

            Stack<Node> stk1 = new Stack<>();
            Stack<Node> stk2 = new Stack<>();

            stk1.push(dest);
            stk2.push(dest);

            Node ptr = dest.parent.get(id1);
            stk1.push(ptr);
            while(ptr != source){
                ptr = ptr.parent.get(id1);
                stk1.push(ptr);
            }

            ptr = dest.parent.get(id2);
            stk2.push(ptr);
            while(ptr != source){
                ptr = ptr.parent.get(id2);
                stk2.push(ptr);
            }

            sb.append(stk1.size()).append("\n");
            while(!stk1.isEmpty()){
                Node node = stk1.pop();
                sb.append(node.id+1).append(" ");
            }
            sb.append("\n");

            sb.append(stk2.size()).append("\n");
            while(!stk2.isEmpty()){
                Node node = stk2.pop();
                sb.append(node.id+1).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}