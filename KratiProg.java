
import java.io.*;
import java.util.*;

public class KratiProg {

    static class Node{
        int id;

        int dist;

        boolean isVisited;

        boolean isFristBencher;

        List<Node> adlist = new ArrayList<>();

        Node(int id){
            this.id = id;
            this.isVisited = false;
            this.isFristBencher = false;
            this.dist = 0;
        }
    }

    static class Tree{
        List<Node> nodeList;

        Tree(int n){
            nodeList = new ArrayList<>();

            for(int i=0;i<n;i++){
                nodeList.add(new Node(i));
            }
        }

        public void addEdge(int u, int v){
            Node nu = nodeList.get(u);
            Node nv = nodeList.get(v);

            nu.adlist.add(nv);
            nv.adlist.add(nu);
        }

        public int getDiameter(){

            int max = 0;

            for(Node node: nodeList){
                if(!node.isVisited && !node.isFristBencher){
                    max = Math.max(max, dfs(node));
                }
            }

            return max;
        }

        public int dfs(Node source){
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            stk.push(source);
            ptrstk.push(-1);
            source.isVisited = true;

            int max = 0;

            while(!stk.isEmpty()){
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr < cur.adlist.size() - 1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = cur.adlist.get(ptr);

                    if(!next.isVisited && !next.isFristBencher){
                        next.isVisited = true;
                        stk.push(next);
                        ptrstk.push(-1);
                    }
                }
                else{
                    int first = 0, second = 0;

                    for(Node node: cur.adlist){
                        if(node.dist >= first){
                            second = first;
                            first = node.dist;
                        }
                        else if(node.dist >= second){
                            second = node.dist;
                        }
                    }

                    cur.dist = first + 1;
                    max = Math.max(max, first + second + 1);
                }
            }

            return max;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        int n = sc.nextInt();

        Tree tr = new Tree(n);

        for(int i=0;i<n-1;i++){
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;

            tr.addEdge(u, v);
        }

        int k = sc.nextInt();
        for(int i=0;i<k;i++){
            int firstBencher = sc.nextInt() - 1;
            tr.nodeList.get(firstBencher).isFristBencher = true;
        }

        int diameter = tr.getDiameter();

        System.out.println(diameter-1);
    }
}
