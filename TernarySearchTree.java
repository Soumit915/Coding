import java.util.*;

public class TernarySearchTree {
    static class Node
    {
        String str;
        Node left;
        Node right;
        Node middle;
        boolean isEndofWord;
        Node()
        {
            this.str = "";
            this.left = null;
            this.right = null;
            this.middle = null;
            this.isEndofWord = false;
        }
    }

    static class TST
    {
        Node root;
        TST()
        {
            root = null;
        }
        public void insert(String word)
        {
            if(root == null)
            {
                root = new Node();
                root.str = word;
                root.isEndofWord = true;
                return;
            }

            root = insert(root, word, 0);
        }
        public Node insert(Node root, String word, int i)
        {
            if(root == null)
            {
                root = new Node();
                root.str = word.substring(i);
                root.isEndofWord = true;
                return root;
            }

            //System.out.println(word.substring(i));
            int l = word.length();
            int strptrl = root.str.length();
            for(int j=0;j<strptrl;j++,i++)
            {
                if(i>=l)
                {
                    Node nd = new Node();
                    nd.str = root.str.substring(j);
                    nd.left = root.left;
                    nd.right = root.right;
                    nd.isEndofWord = root.isEndofWord;
                    nd.middle = root.middle;

                    root.str = root.str.substring(0,j);
                    root.left = null;
                    root.right = null;
                    root.isEndofWord = true;
                    root.middle = nd;
                    return root;
                }

                char chptrstr = root.str.charAt(j);
                char chw = word.charAt(i);
                if(chptrstr != chw)
                {
                    if(j!=strptrl-1)
                    {
                        Node nd = new Node();
                        nd.str = root.str.substring(j+1);
                        nd.left = root.left;
                        nd.right = root.right;
                        nd.isEndofWord = root.isEndofWord;
                        nd.middle = root.middle;

                        root.str = root.str.substring(0,j+1);
                        root.left = null;
                        root.right = null;
                        root.isEndofWord = false;
                        root.middle = nd;
                    }

                    if(chw<chptrstr)
                    {
                        root.left = insert(root.left, word, i);
                    }
                    else
                    {
                        root.right = insert(root.right, word, i);
                    }
                    return root;
                }
            }

            if(i<l)
            {
                root.middle = insert(root.middle, word, i);
                return root;
            }

            return root;
        }
        public boolean search(String word)
        {
            if(root == null)
                return false;

            return search(root, word, 0);
        }
        public boolean search(Node root, String word, int i)
        {
            if(root == null)
                return false;

            int l = word.length();
            int ptrstrl = root.str.length();
            for(int j=0;j<ptrstrl;j++,i++)
            {
                if(i>=l)
                    return false;

                char chw = word.charAt(i);
                char chptrstr = root.str.charAt(j);
                if(chw!=chptrstr)
                {
                    if(j==ptrstrl-1)
                    {
                        if(chw<chptrstr)
                            return search(root.left, word, i);
                        return search(root.right, word, i);
                    }
                    return false;
                }
            }

            if(i>=l)
            {
                return root.isEndofWord;
            }

            return search(root.middle, word, i);
        }
        public ArrayList<String> getElementsOfTST()
        {
            ArrayList<String> strings = new ArrayList<>();
            if(root == null)
                return strings;

            return getElementsOfTST(root, strings, "");
        }
        public ArrayList<String> getElementsOfTST(Node root, ArrayList<String> strings, String curString)
        {
            if(root == null)
                return strings;
            curString += root.str.substring(0,root.str.length()-1);
            strings = getElementsOfTST(root.left, strings, curString);
            if(root.isEndofWord)
                strings.add(curString+root.str.charAt(root.str.length()-1));
            strings = getElementsOfTST(root.middle, strings, curString+root.str.charAt(root.str.length()-1));
            strings = getElementsOfTST(root.right, strings, curString);
            return strings;
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        TST tr = new TST();

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

                /*case 3: System.out.print("Enter the word you want to delete from the trie : ");
                        word = sc.next();
                        tr.delete(word);
                        System.out.println("The word is successfully deleted.");*/

                case 4: if (tr.root == null)
                        {
                            System.out.println("The trie is currently empty.");
                            break;
                        }
                        System.out.println("The elements in the trie is : ");
                        ArrayList<String> strings = tr.getElementsOfTST();
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
