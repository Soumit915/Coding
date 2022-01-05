import java.util.*;

public class AhoCorasickTest {
    static class TrieNode
    {
        BitSet output;
        TrieNode parent;
        TrieNode failureLink;
        boolean isLeaf;
        TrieNode[] child = new TrieNode[26];
        TrieNode()
        {
            this.output = new BitSet();
            this.parent = null;
            for(int i=0;i<26;i++)
                child[i] = null;
            isLeaf = false;
        }
        public void makeLeaf()
        {
            for(int i=0;i<26;i++)
            {
                if(this.child[i]!=null)
                    return;
            }
            this.isLeaf = true;
        }
    }

    static class Trie
    {
        TrieNode root;
        Trie()
        {
            this.root = null;
        }
        public void insert(String word, int wi)
        {
            if(root==null)
                root = new TrieNode();

            int l = word.length();
            TrieNode ptr = root;
            for(int i=0;i<l;i++)
            {
                char ch = word.charAt(i);
                if(ptr.child[ch-97]==null)
                {
                    ptr.child[ch-97] = new TrieNode();
                    ptr.child[ch-97].parent = ptr;
                }
                ptr = ptr.child[ch-97];
            }

            //ptr.output |= (1<<wi);
            ptr.output.set(wi);
        }
        public void dfs(TrieNode root)
        {
            root.makeLeaf();
            for(int i=0;i<26;i++)
            {
                if(root.child[i]!=null)
                    dfs(root.child[i]);
            }
        }
        public void putSuffixLink()
        {
            if(root == null)
                return;

            Queue<TrieNode> d1q = new LinkedList<>();
            for(int i=0;i<26;i++)
            {
                if(root.child[i]!=null) {
                    d1q.add(root.child[i]);
                }
                else
                {
                    root.child[i] = root;
                }
            }
            root.failureLink = root;

            Queue<TrieNode> q = new LinkedList<>();
            Queue<Integer> qind = new LinkedList<>();
            while(!d1q.isEmpty())
            {
                TrieNode ptr = d1q.remove();
                ptr.failureLink = root;
                for(int i=0;i<26;i++) {
                    if (ptr.child[i] != null) {
                        q.add(ptr.child[i]);
                        qind.add(i);
                    } else {
                        ptr.child[i] = ptr.failureLink.child[i];
                    }
                }
            }

            while(!q.isEmpty())
            {
                TrieNode node = q.remove();
                int ind = qind.remove();
                node.failureLink = node.parent.failureLink.child[ind];
                if(node.failureLink == this.root)
                {
                    node.failureLink = this.root.child[ind];
                }
                else if(node.failureLink.isLeaf)
                {
                    //node.output |= node.failureLink.output;
                    node.output.or(node.failureLink.output);
                    node.failureLink = node.failureLink.failureLink;
                }
                for(int i=0;i<26;i++)
                {
                    if(node.child[i]!=null)
                    {
                        q.add(node.child[i]);
                        qind.add(i);
                    }
                    else {
                        node.child[i] = node.failureLink.child[i];
                    }
                }
                //node.output |= node.failureLink.output;
                node.output.or(node.failureLink.output);
            }
        }
        public void findPatterns(String text, String[] patterns)
        {
            if(root == null)
                return;
            int l = text.length();
            ArrayList<HashSet<Integer>> indexes = new ArrayList<>(patterns.length);
            for(int i=0;i<patterns.length;i++){
                indexes.add(new HashSet<>());
            }

            TrieNode ptr = root;
            for(int i=0;i<l;i++)
            {
                char ch = text.charAt(i);
                if(ptr.child[ch-97]==ptr.failureLink && ptr!=root)
                    i--;
                ptr = ptr.child[ch-97];
                //int out = ptr.output;
                BitSet out = (BitSet) ptr.output.clone();
                if(out.get(0))
                {
                    indexes.get(0).add(i-patterns[0].length()+2);
                    out.clear(0);
                }

                while(out.nextSetBit(0)!=-1)
                {
                    int nsb = out.nextSetBit(0);
                    indexes.get(nsb).add(i-patterns[nsb].length()+2);
                    out.clear(nsb);
                }
            }

            for(int i=0;i<patterns.length;i++)
            {
                System.out.print("The pattern "+patterns[i]+" is found at position: ");
                for(int ind: indexes.get(i))
                {
                    System.out.print(ind+" ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text : ");
        String text = sc.next();

        Trie tr = new Trie();

        System.out.print("Enter the number of strings you want to search : ");
        int n = sc.nextInt();
        String[] patterns = new String[n];
        System.out.print("Enter the patterns : ");
        for(int i=0;i<n;i++)
        {
            patterns[i] = sc.next();
            tr.insert(patterns[i],i);
        }

        tr.dfs(tr.root);
        tr.putSuffixLink();
        tr.findPatterns(text, patterns);
    }
}
