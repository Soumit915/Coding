package Hackerearth.DataStructures.Trie;

import java.util.*;

class SearchEngineWordPriority {
    static class TrieNode
    {
        String str;
        TrieNode[] child = new TrieNode[26];
        int weight;
        TrieNode()
        {
            str = "";
            for(int i=0;i<26;i++)
                child[i] = null;
            weight = 0;
        }
    }

    static class Trie
    {
        TrieNode root;
        Trie()
        {
            this.root = null;
        }
        public void insert(String word, int pw)
        {
            if(root == null)
            {
                root = new TrieNode();
                root.str = word;
                root.weight = pw;
                return;
            }

            insert(root, word, 0, pw);
        }
        public void insert(TrieNode root, String word, int i, int pw)
        {
            int l = word.length();
            int ptrstrl = root.str.length();
            for(int j=0;j<ptrstrl;j++,i++)
            {
                char chptrstr = root.str.charAt(j);
                if(i>=l)
                {
                    TrieNode node = new TrieNode();
                    node.str = root.str.substring(j);
                    node.weight = root.weight;
                    for(int ind=0;ind<26;ind++)
                    {
                        node.child[ind] = root.child[ind];
                        root.child[ind] = null;
                    }

                    root.str = root.str.substring(0,j);
                    if(root.weight<pw)
                        root.weight = pw;
                    root.child[chptrstr-97] = node;
                    return;
                }

                char chw = word.charAt(i);
                if(chw!=chptrstr)
                {
                    TrieNode node1 = new TrieNode();
                    TrieNode node2 = new TrieNode();

                    node1.str = root.str.substring(j);
                    node2.str = word.substring(i);
                    root.str = root.str.substring(0,j);

                    for(int ind=0;ind<26;ind++)
                    {
                        node1.child[ind] = root.child[ind];
                        root.child[ind] = null;
                    }

                    node1.weight = root.weight;
                    node2.weight = pw;
                    if(root.weight<pw)
                        root.weight = pw;
                    root.child[chptrstr-97] = node1;
                    root.child[chw-97] = node2;
                    return;
                }
            }

            if(root.weight<pw)
                root.weight = pw;

            if(i>=l)
            {
                return;
            }

            int chw = word.charAt(i)-97;
            if(root.child[chw]==null)
            {
                TrieNode node = new TrieNode();
                node.str = word.substring(i);
                node.weight = pw;
                root.child[chw]=node;
            }
            else
            {
                insert(root.child[chw], word, i, pw);
            }
        }
        public int getWeight(String word)
        {
            if(root==null)
                return -1;

            return getWeight(root, word, 0);
        }
        public int getWeight(TrieNode root, String word, int i)
        {
            if(root==null)
                return -1;

            int l = word.length();
            int ptrstrl = root.str.length();
            for(int j=0;j<ptrstrl;j++,i++)
            {
                if(i>=l)
                {
                    return root.weight;
                }

                char chw = word.charAt(i);
                char chptrstr = root.str.charAt(j);
                if(chw!=chptrstr)
                {
                    return -1;
                }
            }

            if(i>=l)
            {
                return root.weight;
            }

            int chw = word.charAt(i)-97;
            if(root.child[chw]==null)
            {
                return -1;
            }
            else
            {
                return getWeight(root.child[chw],word,i);
            }
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        Trie tr = new Trie();
        for(int i=0;i<n;i++)
        {
            String s = sc.next();
            int weight = sc.nextInt();
            tr.insert(s,weight);
        }

        for(int i=0;i<q;i++)
        {
            String s = sc.next();
            System.out.println(tr.getWeight(s));
        }
    }
}
