import java.io.*;
import java.util.*;

public class NameGame {

    static class Node{
        int id;
        boolean isVisited;
        ArrayList<Node> adlist = new ArrayList<>();
        Node(int id){
            this.id = id;
            this.isVisited = false;
        }
    }

    static class Graph{
        ArrayList<Node> nodelist;
        Graph(int n){
            this.nodelist = new ArrayList<>(n);
            for(int i=0;i<n;i++){
                this.nodelist.add(new Node(i));
            }
        }
        public void addEdge(int u, int v){
            Node nu = this.nodelist.get(u);
            Node nv = this.nodelist.get(v);

            nu.adlist.add(nv);
            nv.adlist.add(nu);
        }
        public int[] getCC(){
            int[] ans = new int[2];
            for(Node node: this.nodelist){
                if(!node.isVisited){
                    ans[1]++;
                    ans[0] = Math.max(ans[0], dfs(node));
                }
            }
            return ans;
        }
        public int dfs(Node source){

            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.push(source);
            ptrstk.push(-1);
            source.isVisited = true;
            int c = 1;

            while(!stk.isEmpty()){
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.adlist.size()-1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adlist.get(ptr);
                    if(next.isVisited)
                        continue;

                    c++;
                    stk.push(next);
                    ptrstk.push(-1);
                    next.isVisited = true;
                }
            }

            return c;
        }
    }

    static HashMap<Integer, Integer> maskHash;
    static void addEdges(String s, int index, Graph gr){
        int[] hash_s = new int[26];
        for(int i=0;i<s.length();i++){
            hash_s[s.charAt(i)-65] = 1;
        }

        int mask = 0;
        for(int i=0;i<26;i++){
            if(hash_s[i]==1){
                mask |= (1<<i);
            }
        }
        if(maskHash.containsKey(mask)){
            gr.addEdge(index, maskHash.get(mask));
        }
        else{
            maskHash.put(mask, index);
        }

        for(int i=0;i<26;i++){
            int newmask = mask^(1<<i);
            if(maskHash.containsKey(newmask)){
                gr.addEdge(index, maskHash.get(newmask));
            }
        }

        for(int i=0;i<26;i++){
            if(hash_s[i]==1){
                int newmask = mask^(1<<i);
                for(int j=0;j<26;j++){
                    if(hash_s[j]!=1){
                        int localmask = newmask|(1<<j);
                        if(maskHash.containsKey(localmask)){
                            gr.addEdge(index, maskHash.get(localmask));
                        }
                    }
                }
            }
        }
    }

    static int[] getAns(int n, String[] names){

        Graph gr = new Graph(n);
        maskHash = new HashMap<>();
        for(int i=0;i<n;i++){
            addEdges(names[i], i, gr);
        }

        return gr.getCC();
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        File file = new File("Input.txt");
        Scanner sc = new Scanner(file);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String[] names = new String[n];
            for(int i=0;i<n;i++){
                names[i] = sc.next();
            }

            int[] ans = getAns(n, names);
            for(int i: ans)
                sb.append(i).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);

        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000.0);
    }
}
