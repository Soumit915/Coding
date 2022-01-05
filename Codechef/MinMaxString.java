package Codechef;

import java.util.*;

public class MinMaxString {
    static class TrieNode
    {
        String str;
        TrieNode[] alphabet = new TrieNode[26];
        boolean isEndofWord;
        TrieNode()
        {
            this.str = "";
            for(int i=0;i<26;i++)
            {
                this.alphabet[i] = null;
            }
            isEndofWord = false;
        }
    }

    static class Trie
    {
        TrieNode root;
        Trie()
        {
            this.root = null;
        }
        public void insert(String word)
        {
            if(root == null)
            {
                root = new TrieNode();
                root.str = word;
                root.isEndofWord = true;
                return;
            }

            insert(root, word, 0);
        }
        public void insert(TrieNode root, String word, int i)
        {
            int l = word.length();
            int ptrstrl = root.str.length();
            for(int j=0;j<ptrstrl;j++,i++)
            {
                char chptrstr = root.str.charAt(j);
                //int indexptr = (int)(chptrstr-97);
                if(i>=l)
                {
                    TrieNode node = new TrieNode();
                    node.str = root.str.substring(j);
                    for(int ind=0;ind<26;ind++)
                    {
                        node.alphabet[ind] = root.alphabet[ind];
                        root.alphabet[ind] = null;
                    }
                    root.alphabet[chptrstr-97] = node;

                    root.str = root.str.substring(0,j);
                    node.isEndofWord = root.isEndofWord;
                    root.isEndofWord = true;
                    return;
                }

                char chw = word.charAt(i);
                //int indexw = (int)(chw-97);
                if(chw != chptrstr && root.alphabet[chw-97]==null)
                {
                    TrieNode node1 = new TrieNode();
                    node1.str = root.str.substring(j);
                    TrieNode node2 = new TrieNode();
                    node2.str = word.substring(i);

                    for(int ind=0;ind<26;ind++)
                    {
                        node1.alphabet[ind] = root.alphabet[ind];
                        root.alphabet[ind] = null;
                    }

                    root.alphabet[chptrstr-97] = node1;
                    root.alphabet[chw-97] = node2;

                    root.str = root.str.substring(0,j);
                    node1.isEndofWord = root.isEndofWord;
                    root.isEndofWord = false;
                    node2.isEndofWord = true;
                    return;
                }
            }

            if(i<l)
            {
                int indexw = word.charAt(i)-97;
                if(root.alphabet[indexw]==null)
                {
                    root.alphabet[indexw] = new TrieNode();
                    root.alphabet[indexw].str = word.substring(i);
                    root.alphabet[indexw].isEndofWord = true;
                }
                else
                {
                    insert(root.alphabet[indexw], word, i);
                }
            }
        }
        String curString = "";
        public String findminimal()
        {
            curString = "";
            findminimal(this.root);
            return curString;
        }
        public void findminimal(TrieNode root)
        {
            curString+=root.str;
            for(int i=0;i<26;i++)
            {
                if(root.alphabet[i]!=null) {
                    findminimal(root.alphabet[i]);
                    break;
                }
            }
        }
        public String findmaximal()
        {
            curString = "";
            findmaximal(this.root);
            return curString;
        }
        public void findmaximal(TrieNode root)
        {
            curString+=root.str;
            for(int i=25;i>=0;i--)
            {
                if(root.alphabet[i]!=null) {
                    findmaximal(root.alphabet[i]);
                    break;
                }
            }
        }
    }
    static int indmaxString, indminString;
    public static void updateminimal(Trie tr, HashMap<String, Integer> hash)
    {
        String s = tr.findminimal();
        indminString = hash.get(s);
    }
    public static void updatemaximal(Trie tr, HashMap<String, Integer> hash)
    {
        String s = tr.findmaximal();
        indmaxString = hash.get(s);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();

            ArrayList<String> arrstr = new ArrayList<>();
            HashMap<String, Integer> hash = new HashMap<>();
            Trie tr = new Trie();
            for(int i=0;i<n;i++)
            {
                String s = sc.next();
                arrstr.add(s);
                tr.insert(s);
                hash.put(s, i);
            }

            int q = sc.nextInt();

            while (q-->0)
            {
                int type = sc.nextInt();
                if(type==1)
                {
                    int b = sc.nextInt();
                    char c = sc.next().charAt(0);
                    String s = arrstr.get(b-1)+c;
                    arrstr.add(s);
                    tr.insert(s);
                    hash.put(s, arrstr.size()-1);
                    updateminimal(tr, hash);
                    updatemaximal(tr, hash);
                }
                else if(type==2)
                {
                    sb.append(indminString+1).append("\n");
                }
                else
                {
                    sb.append(indmaxString+1).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
