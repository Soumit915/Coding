package Coursera.DataStructuresAndAlgorithms_UCSanDiego.DataStructures.Week1;

import java.util.*;

public class TreeHeight {
    static class Node
    {
        int id;
        int level;
        ArrayList<Node> child = new ArrayList<>();
        Node(int id)
        {
            this.id = id;
            this.level = 0;
        }
    }
    static class Tree
    {
        Node root;
        int height;
        ArrayList<Node> nodelist = new ArrayList<>();
        Tree(int n)
        {
            for(int i=0;i<n;i++)
            {
                this.nodelist.add(new Node(i));
            }
            this.height = 0;
        }
        public void assignRoot(int id)
        {
            this.root = nodelist.get(id);
        }
        public void addChild(int child, int parent)
        {
            Node np = nodelist.get(parent);
            Node nc = nodelist.get(child);
            np.child.add(nc);
        }
        /*public void dfs()
        {
            dfs(root, 1);
        }
        public void dfs(Node root, int height)
        {
            this.height = Math.max(this.height, height);
            for(Node nd: root.child)
                dfs(nd, height+1);
        }*/
        public void dfs()
        {
            Stack<Node> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(root);
            ptrstk.push(-1);
            root.level = 1;

            while(!stk.isEmpty())
            {
                Node cur = stk.pop();
                int ptr = ptrstk.pop();

                if(ptr<cur.child.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node child = cur.child.get(ptr);
                    child.level = cur.level+1;
                    stk.push(child);
                    ptrstk.push(-1);
                }
            }

            for(Node nd: nodelist)
            {
                this.height = Math.max(this.height, nd.level);
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Tree tr = new Tree(n);
        for(int i=0;i<n;i++)
        {
            int v = sc.nextInt();
            if(v == -1)
                tr.assignRoot(i);
            else tr.addChild(i, v);
        }

        tr.dfs();
        System.out.println(tr.height);
    }
}
