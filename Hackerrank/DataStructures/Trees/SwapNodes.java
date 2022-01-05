package Hackerrank.DataStructures.Trees;

import java.util.*;

public class SwapNodes
{
    static int indexin;
    static class Node
    {
        int data;
        int height;
        Node left;
        Node right;
        Node(int data)
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        public void insert(int[][] indexes)
        {
            int[] info = indexes[this.data-1];
            if(info[0]!=-1)
            {
                this.left = new Node(info[0]);
                this.left.height = this.height+1;
                this.left.insert(indexes);
            }
            if(info[1]!=-1)
            {
                this.right = new Node(info[1]);
                this.right.height = this.height+1;
                this.right.insert(indexes);
            }
        }
        public int[] inorderTraversal(int n)
        {
            int[] in = new int[n];
            indexin = 0;
            inorderTraversal(this, in);
            return in;
        }
        public void inorderTraversal(Node root, int[] in)
        {
            if(root == null)
                return;
            inorderTraversal(root.left, in);
            in[indexin] = root.data;
            indexin++;
            inorderTraversal(root.right, in);
        }
    }
    public static void swapNodes(Node root, int k)
    {
        if(root==null)
            return;

        if(root.height%k==0)
        {
            Node t = root.left;
            root.left = root.right;
            root.right = t;
        }
        swapNodes(root.left,k);
        swapNodes(root.right,k);
    }
    static int[][] swapNodes(int[][] indexes, int[] queries) {

        int n = indexes.length;
        int t = queries.length;

        Node root = new Node(1);
        root.height = 1;
        root.insert(indexes);
        int[][] inorderTraversals = new int[t][n];

        for(int i=0;i<queries.length;i++)
        {
            int k = queries[i];
            swapNodes(root,k);
            inorderTraversals[i] = root.inorderTraversal(n);
        }

        return inorderTraversals;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] indexes = new int[n][3];
        for(int i=0;i<n;i++)
        {
            int a = sc.nextInt();
            int b = sc.nextInt();

            indexes[i][0] = a;
            indexes[i][1] = b;
        }

        int t = sc.nextInt();
        int[] queries = new int[t];
        for(int i=0;i<t;i++)
        {
            queries[i] = sc.nextInt();
        }

        int[][] inorderTraversal = swapNodes(indexes, queries);

        for(int[] arr: inorderTraversal)
        {
            for(int i: arr)
                System.out.print(i+" ");
            System.out.println();
        }
    }
}
