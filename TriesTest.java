import java.util.*;

public class TriesTest
{
    static class TrieNode {
        TrieNode[] alphabet = new TrieNode[26];
        boolean endOfWord;
        TrieNode() {
            this.endOfWord = false;
            for(int i=0;i<26;i++)
            {
                this.alphabet[i] = null;
            }
        }
        public boolean isEmpty() {
            for(int i=0;i<26;i++)
                if(alphabet[i]!=null)
                    return false;
            return true;
        }
    }

    static class Trie {
        TrieNode root;
        Trie() {
            this.root = null;
        }
        public void insert(final String word) {
            if(this.root == null)
                this.root = new TrieNode();
            this.insert(this.root, word);
        }
        public void insert(final TrieNode root, final String word) {
            TrieNode ptr = root;
            final int l = word.length();
            for(int i=0;i<l;i++)
            {
                final char ch = word.charAt(i);
                final int index = ch-97;
                if(ptr.alphabet[index] == null)
                {
                    ptr.alphabet[index] = new TrieNode();
                }

                ptr = ptr.alphabet[index];
            }

            ptr.endOfWord = true;
        }
        public boolean search(final String word) {
            if(root == null)
                return false;

            TrieNode ptr = this.root;
            final int l = word.length();
            for(int i=0;i<l;i++)
            {
                final int index = word.charAt(i)-97;
                if(ptr.alphabet[index] == null)
                    return false;

                ptr = ptr.alphabet[index];
            }

            return ptr.endOfWord;
        }
        public ArrayList<String> getElementsOfTrie()
        {
            final ArrayList<String> strings = new ArrayList<>();
            if(this.root == null)
                return strings;
            return this.getElementsOfTrie(this.root, strings, "");
        }
        public ArrayList<String> getElementsOfTrie(final TrieNode root, final ArrayList<String> strings,
                                                   final String curString)
        {
            if(root.endOfWord)
                strings.add(curString);
            for(int i=0;i<26;i++)
            {
                if(root.alphabet[i]!=null)
                {
                    final char ch = (char)(97+i);
                    this.getElementsOfTrie(root.alphabet[i], strings, curString+ch);
                }
            }

            return strings;
        }
        public void delete(final String word)
        {
            if(this.root == null)
                return;

            final Stack<TrieNode> stk = new Stack<>();
            TrieNode ptr = this.root;
            final int l = word.length();
            for(int i=0;i<l;i++)
            {
                final int index = word.charAt(i)-97;
                if(ptr.alphabet[index]==null)
                    return;

                stk.push(ptr);
                ptr = ptr.alphabet[index];
            }

            if(!ptr.endOfWord)
                return;
            stk.push(ptr);
            int i=word.length()-1;
            while(!stk.isEmpty())
            {
                ptr = stk.pop();
                if(!ptr.isEmpty())
                    return;
                if(stk.isEmpty())
                {
                    this.root = null;
                    return;
                }

                final int index = word.charAt(i)-97;
                i--;
                stk.peek().alphabet[index] = null;
                if(stk.peek().endOfWord)
                    return;
            }
        }
    }
    public static void main(final String[] args)
    {
        final Scanner sc = new Scanner(System.in);
        final Trie tr = new Trie();

        while(true)
        {
            System.out.print("Enter,\n1 to insert a word,\n2 to search if a word is present,\n3 to delete a word,\n4 to print the trie,\n5 to exit,\nEnter your choice : ");
            final int choice = sc.nextInt();

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
                        final ArrayList<String> strings = tr.getElementsOfTrie();
                        for(final String s: strings)
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
