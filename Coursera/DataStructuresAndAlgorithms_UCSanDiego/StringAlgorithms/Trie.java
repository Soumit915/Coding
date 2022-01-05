package Coursera.DataStructuresAndAlgorithms_UCSanDiego.StringAlgorithms;

import java.util.*;

public class Trie {
    static class Node
    {
        int id;
        Node a;
        Node c;
        Node g;
        Node t;
        Node(int id)
        {
            this.id = id;
            this.a = null;
            this.c = null;
            this.g = null;
            this.t = null;
        }
    }
    static class TrieTree
    {
        Node root;
        int ci = 0;
        StringBuilder output;
        TrieTree()
        {
            this.root = new Node(0);
        }
        public Node createNode()
        {
            ci++;
            return new Node(ci);
        }
        public void insert(String s)
        {
            insert(s, root, 0);
        }
        public void insert(String s, Node root, int i)
        {
            if(i==s.length())
                return;

            char ch = s.charAt(i);

            if(ch=='A')
            {
                if(root.a == null)
                {
                    root.a = createNode();
                }
                insert(s, root.a, i+1);
            }
            else if(ch=='C')
            {
                if(root.c == null)
                {
                    root.c = createNode();
                }
                insert(s, root.c, i+1);
            }
            else if(ch=='G')
            {
                if(root.g == null)
                {
                    root.g = createNode();
                }
                insert(s, root.g, i+1);
            }
            else
            {
                if(root.t == null)
                {
                    root.t = createNode();
                }
                insert(s, root.t, i+1);
            }
        }
        public void print()
        {
            this.output = new StringBuilder();
            print(root);
        }
        public void print(Node root)
        {
            if(root.a != null) {
                output.append(root.id).append("->").append(root.a.id).append(":").append("A").append("\n");
                print(root.a);
            }
            if(root.c != null) {
                output.append(root.id).append("->").append(root.c.id).append(":").append("C").append("\n");
                print(root.c);
            }
            if(root.g != null) {
                output.append(root.id).append("->").append(root.g.id).append(":").append("G").append("\n");
                print(root.g);
            }
            if(root.t != null) {
                output.append(root.id).append("->").append(root.t.id).append(":").append("T").append("\n");
                print(root.t);
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        TrieTree tr = new TrieTree();
        for(int i=0;i<n;i++)
        {
            String s = sc.next();
            tr.insert(s);
        }

        tr.print();
        System.out.print(tr.output);
    }
}
