import java.util.*;

public class CompressedTrie {
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
        public int getNumberOfChildren()
        {
            int count = 0;
            for(int i=0;i<26;i++)
                if(this.alphabet[i]!=null)
                    count++;
            return count;
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
                //When the word to be inserted is found to be ending in the current node, that is it is a
                //prefix of an already existing node
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
                //When the we have a mismatch in the root string and word string
                if(chw != chptrstr)
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

            if(i==l){
                root.isEndofWord = true;
            }

            //When the root string is finished traversing and we need to recurse
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
        public boolean search(String word)
        {
            if(root == null)
                return false;

            return search(root, word, 0);
        }
        public boolean search(TrieNode root, String word, int i)
        {
            int l = word.length();
            int ptrstrl = root.str.length();
            for(int j=0;j<ptrstrl;j++,i++)
            {
                if(i>=l)
                    return false;
                char chptrstr = root.str.charAt(j);
                char chw = word.charAt(i);
                if(chw!=chptrstr)
                    return false;
            }

            if(i>=l) {
                return root.isEndofWord;
            }

            char chw = word.charAt(i);
            if(root.alphabet[chw-97]==null) {
                return false;
            }
            else {
                return search(root.alphabet[chw-97], word, i);
            }
        }
        public ArrayList<String> getElementsOfTrie()
        {
            ArrayList<String> strings = new ArrayList<>();
            if(root == null)
                return strings;
            return getElementsOfTrie(root, strings, "");
        }
        public ArrayList<String> getElementsOfTrie(TrieNode root, ArrayList<String> strings,
                                                   String curString)
        {
            curString+=root.str;
            if(root.isEndofWord)
                strings.add(curString);
            for(int i=0;i<26;i++)
            {
                if(root.alphabet[i]!=null)
                    getElementsOfTrie(root.alphabet[i], strings, curString);
            }

            return strings;
        }
        public void delete(String word)
        {
            if(root == null)
                return;

            root = delete(root, word, 0);
        }
        public TrieNode delete(TrieNode root, String word, int i)
        {
            int l = word.length();
            int ptrstrl = root.str.length();
            for(int j=0;j<ptrstrl;j++,i++)
            {
                if(i>=l)
                    return root;
                char chptrstr = root.str.charAt(j);
                char chw = word.charAt(i);
                if(chw!=chptrstr)
                    return root;
            }

            if(i<l)
            {
                char chw = word.charAt(i);
                if (root.alphabet[chw - 97] != null)
                {
                    root.alphabet[chw - 97] = delete(root.alphabet[chw - 97], word, i);
                }
                return root;
            }

            if(!root.isEndofWord)
                return root;
            root.isEndofWord = false;
            int noc = root.getNumberOfChildren();
            if(noc == 0)
            {
                return null;
            }
            else if(noc == 1)
            {
                for(int child=0;child<26;child++)
                {
                    if(root.alphabet[child]!=null)
                    {
                        TrieNode ptr = root.alphabet[child];
                        root.str += ptr.str;
                        System.arraycopy(ptr.alphabet, 0, root.alphabet, 0, 26);
                    }
                }
                return root;
            }
            else
            {
                return root;
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Trie tr = new Trie();

        while(true)
        {
            System.out.print("Enter,\n1 to insert a word,\n2 to search if a word is present,\n3 to delete a word,\n4 to print the trie,\n5 to exit,\nEnter your choice : ");
            int choice = sc.nextInt();

            switch (choice)
            {
                case 1: System.out.print("Enter the word you want to insert in the trie : ");
                        String word = sc.next();
                        tr.insert(word);
                        break;

                case 2: System.out.print("Enter the word you want to search in the trie : ");
                        word = sc.next();
                        if (tr.search(word))
                            System.out.println("Search is successful.");
                        else
                            System.out.println("Search unsuccessful.");
                        break;

                case 3: System.out.print("Enter the word you want to delete from the trie : ");
                        word = sc.next();
                        tr.delete(word);
                        System.out.println("The word is successfully deleted.");

                case 4: if (tr.root == null)
                        {
                            System.out.println("The trie is currently empty.");
                            break;
                        }
                        System.out.println("The elements in the trie is : ");
                        ArrayList<String> strings = tr.getElementsOfTrie();
                        for(String s: strings)
                        {
                            System.out.println(s);
                        }
                        break;

                case 5: System.exit(0);

                default:System.out.println("Wrong input.");
            }
        }
    }
}
