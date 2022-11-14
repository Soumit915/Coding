import java.util.*;

public class UkkonensAlgorithmTest {
    static class End {
        int val;
        End() {
            this.val = -1;
        }
    }

    static class TrieNode {
        int start;
        End end;
        TrieNode[] child = new TrieNode[27];
        TrieNode suffixLink;
        boolean isLeaf;
        int index;
        TrieNode()
        {
            this.start = -1;
            this.end = null;
            for(int i=0;i<27;i++)
                child[i] = null;
            this.suffixLink = null;
            this.isLeaf = false;
            this.index = -1;
        }
    }

    static class SuffixTree {
        TrieNode root;
        private final String str;
        private int remaining;
        private final End end;
        private TrieNode activeNode;
        private int activeEdge;
        private int activelength;
        private TrieNode prev = null;
        private boolean hasSuffixLink = false;
        SuffixTree(String str)
        {
            this.root = new TrieNode();
            this.str = str;
            this.remaining = 0;
            this.end = new End();
            activeNode = null;
            activeEdge = -1;
            activelength = 0;
        }
        public void insert(char ch)
        {
            this.remaining++;
            this.end.val++;
            if(activeNode == null)      //Case when we are actually performing a fresh insert with no active nodes
            {
                insertNormal(ch);
            }
            else if(activeNode == root)     //Condition when we are having a active node as root
            {
                prev = null;
                hasSuffixLink = false;
                insertRoot(ch);
            }
            else        //condition when active node is not root
            {
                prev = null;
                hasSuffixLink = false;
                insertInternal(ch);
            }

            prev = null;
            hasSuffixLink = false;
        }
        public void insertNormal(char ch)
        {
            activeNode = root;
            if(root.child[ch-97]==null)     //Condition when the root doesn't have a link to the incoming character
            {
                //create one leaf node
                TrieNode node = new TrieNode();
                node.start = this.end.val;
                node.end = this.end;
                node.isLeaf = true;
                root.child[ch-97] = node;
                this.remaining--;
                activeNode = null;
            }
            else        //Condition if the root has a link to the incoming character
            {
                //System.out.println("test: "+ch);
                activeEdge = this.end.val;
                //activeEdge = activeNode.child[ch-97].start;
                activelength = 1;
                //System.out.println(activeEdge);
            }
        }
        public void insertRoot(char ch)
        {
            while(remaining>0)
            {
                if(activelength == 0)
                {
                    insertNormal(ch);
                    return;
                }
                int chr = str.charAt(activeEdge)-97;    //to extract the character that is linked to the active edge
                TrieNode ptr = activeNode.child[chr];   //ptr points to the current node to which active node is pointing
                if(ptr.end.val-ptr.start+1 == activelength)     //when the incoming character might be in the next node
                {
                    if(ptr.child[ch-97]==null)      //if the incoming character is not in the next node
                    {
                        //Create a new node and since active node is root thus go to the next link pointed by the root
                        TrieNode node = new TrieNode();
                        node.start = this.end.val;
                        node.end = this.end;
                        ptr.child[ch-97] = node;
                        node.isLeaf = true;
                        activelength--;
                        activeEdge++;
                        remaining--;
                        prev = ptr;
                        hasSuffixLink = false;
                    }
                    else
                    {
                        //then go to the next node, change the active credentials and return
                        activeNode = ptr;
                        activeEdge = this.end.val;
                        activelength = 1;
                        return;
                    }
                }
                else if(ptr.end.val-ptr.start+1 < activelength)
                {
                    int startingposition = this.end.val-remaining+1;
                    int thisnodelength = ptr.end.val-ptr.start+1;
                    activeNode = ptr;
                    activeEdge = startingposition+thisnodelength;
                    activelength -= thisnodelength;
                    insertInternal(ch);
                }
                else if(str.charAt(ptr.start+activelength)==ch) //when you hit a rule 3 increment activelength and return
                {
                    activelength++;
                    return;
                }
                else    //case to create an internal node
                {
                    TrieNode node1 = new TrieNode();
                    TrieNode node2 = new TrieNode();
                    node1.start = ptr.start+activelength;
                    node2.start = this.end.val;
                    node1.end = ptr.end;
                    node2.end = this.end;
                    node1.isLeaf = ptr.isLeaf;
                    node2.isLeaf = true;
                    for(int i=0;i<27;i++)
                    {
                        node1.child[i] = ptr.child[i];
                        ptr.child[i] = null;
                    }
                    node1.suffixLink = ptr.suffixLink;
                    if(hasSuffixLink)
                    {
                        prev.suffixLink = ptr;
                    }

                    ptr.end = new End();
                    ptr.end.val = ptr.start+activelength-1;

                    int ch1 = this.str.charAt(node1.start)-97;
                    int ch2 = ch-97;
                    ptr.child[ch1] = node1;
                    ptr.child[ch2] = node2;

                    ptr.suffixLink = root;
                    ptr.isLeaf = false;

                    this.activelength--;
                    this.activeEdge++;
                    remaining--;
                    prev = ptr;
                    hasSuffixLink = true;
                }
            }
            activeNode = null;
        }
        public void insertInternal(char ch)
        {
            int chr = str.charAt(activeEdge)-97;    //to extract the character that is linked to the active edge
            TrieNode ptr = activeNode.child[chr];   //ptr points to the current node to which active node is pointing
            if(ptr.end.val-ptr.start+1 == activelength)     //when the incoming character might be in the next node
            {
                if(ptr.child[ch-97]==null)      //if the incoming character is not in the next node
                {
                    //Create a new node and since active node is not root thus
                    //follow the suffixlink of the active node and continue

                    TrieNode node = new TrieNode();
                    node.start = this.end.val;
                    node.end = this.end;
                    node.isLeaf = true;
                    ptr.child[ch-97] = node;

                    this.activeNode = activeNode.suffixLink;
                    remaining--;
                    if(activeNode == root)
                        insertRoot(ch);
                    else
                        insertInternal(ch);
                }
                else
                {
                    //then go to the next node, change the active credentials and return
                    activeNode = ptr;
                    activeEdge = this.end.val;
                    activelength = 1;
                }
            }
            else if(ptr.end.val-ptr.start+1 < activelength)
            {
                int startingposition = this.end.val-remaining+1;
                int thisnodelength = ptr.end.val-ptr.start+1;
                activeNode = ptr;
                activeEdge = startingposition+thisnodelength;
                activelength -= thisnodelength;
                insertInternal(ch);
            }
            else if(str.charAt(ptr.start+activelength)==ch) //when you hit a rule 3 increment activelength and return
            {
                activelength++;
            }
            else    //case to create an internal node
            {
                TrieNode node1 = new TrieNode();
                TrieNode node2 = new TrieNode();
                node1.start = ptr.start+activelength;
                node2.start = this.end.val;
                node1.end = ptr.end;
                node2.end = this.end;
                node1.isLeaf = ptr.isLeaf;
                node2.isLeaf = true;
                for(int i=0;i<27;i++)
                {
                    node1.child[i] = ptr.child[i];
                    ptr.child[i] = null;
                }
                node1.suffixLink = ptr.suffixLink;
                if(hasSuffixLink)
                {
                    prev.suffixLink = ptr;
                }

                ptr.end = new End();
                ptr.end.val = ptr.start+activelength-1;

                int ch1 = this.str.charAt(node1.start)-97;
                int ch2 = ch-97;
                ptr.child[ch1] = node1;
                ptr.child[ch2] = node2;

                ptr.suffixLink = root;
                ptr.isLeaf = false;

                this.activeNode = activeNode.suffixLink;
                remaining--;
                prev = ptr;
                hasSuffixLink = true;

                if(activeNode == root)
                    insertRoot(ch);
                else
                    insertInternal(ch);
            }
        }
        public void setIndex(TrieNode root, int i, String s)
        {
            if(root.end!=null)
            {
                i += (root.end.val-root.start+1);
                s += str.substring(root.start, root.end.val+1);
            }
            if(root.isLeaf)
            {
                System.out.println(str.length()-i+" "+s.substring(0,s.length()-1));
                return;
            }
            for(int in=0;in<27;in++)
            {
                if(root.child[in]!=null)
                    setIndex(root.child[in], i, s);
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        //System.out.print("Enter the string: ");
        String s = sc.next();
        s+='{';
        int l = s.length();

        SuffixTree stree = new SuffixTree(s);
        for(int i=0;i<l;i++)
        {
            char ch = s.charAt(i);
            stree.insert(ch);
        }

        stree.setIndex(stree.root, 0, "");
    }
}

//Test case: eivieieviei
//            mississi
//            giigiggik