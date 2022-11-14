package GreedyAlgorithms;

import java.util.*;

public class HuffmanCoding {
    static class Node implements Comparable<Node>
    {
        int freq;
        InternalNode parent;
        Node(int freq)
        {
            this.freq = freq;
            this.parent = null;
        }
        public int compareTo(Node a)
        {
            return Double.compare(this.freq, a.freq);
        }
    }

    static class AlphNode extends Node
    {
        char ch;
        String prefixCodes;
        AlphNode(char ch, int freq)
        {
            super(freq);
            this.ch = ch;
            this.prefixCodes = "";
        }
    }

    static class InternalNode extends Node
    {
        Node leftchild;
        Node rightchild;
        InternalNode(int freq)
        {
            super(freq);
            this.leftchild = null;
            this.rightchild = null;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string message : ");
        String s = sc.next();
        s = s.toLowerCase();
        int l = s.length();

        int[] arr = new int[26];
        for(int i=0;i<l;i++)
        {
            int ch = s.charAt(i);
            arr[ch-96]++;
        }

        PriorityQueue<Node> heap = new PriorityQueue<>();
        ArrayList<AlphNode> leafnodes = new ArrayList<>();
        for(int i=0;i<26;i++)
        {
            AlphNode leaf = new AlphNode((char)(i+96), arr[i]);
            if(arr[i] != 0)
            {
                heap.add(leaf);
                leafnodes.add(leaf);
            }
        }

        while(heap.size()!=1)
        {
            Node n1 = heap.remove();
            Node n2 = heap.remove();

            InternalNode par = new InternalNode(n1.freq+n2.freq);
            n1.parent = par;
            n2.parent = par;
            par.leftchild = n1;
            par.rightchild = n2;

            heap.add(par);
        }

        for(AlphNode leaf: leafnodes)
        {
            Node cur = leaf;
            while(cur.parent!=null)
            {
                InternalNode par = cur.parent;
                if(par.leftchild == cur)
                    leaf.prefixCodes = "0" + leaf.prefixCodes;
                else
                    leaf.prefixCodes = "1" + leaf.prefixCodes;
                cur = par;
            }
        }

        System.out.println("\n");
        for(AlphNode leaf: leafnodes)
        {
            System.out.println(leaf.ch+" --> "+leaf.prefixCodes);
        }
    }
}
