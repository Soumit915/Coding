import java.util.*;

class BST
{
    static class Node
    {
        int data;
        Node left;
        Node right;
        Node parent;
        Node()
        {
            data = 0;
            left = null;
            right = null;
            parent = null;
        }
    }
    static class BinarySearchTree
    {
        Node root;
        BinarySearchTree()
        {
            root = null;
        }

        public boolean isEmpty()
        {
            return root == null;
        }

        public void insert(int val)
        {
            if(isEmpty())
            {
                root = new Node();
                root.data = val;
                return;
            }

            this.insert(root, val);
        }
        private void insert(Node root, int val)
        {
            if(val<root.data)
            {
                if(root.left == null)
                {
                    Node nptr = new Node();
                    nptr.data = val;
                    root.left = nptr;
                    nptr.parent = root;
                }
                else
                {
                    insert(root.left, val);
                }
            }
            else
            {
                if(root.right == null)
                {
                    Node nptr = new Node();
                    nptr.data = val;
                    root.right = nptr;
                    nptr.parent = root;
                }
                else
                {
                    insert(root.right, val);
                }
            }
        }

        public void display()
        {
            if(isEmpty())
            {
                System.out.println("The tree is empty.");
                return;
            }
            System.out.print("The values in the tree is : ");
            display(root);
            System.out.println();
        }
        private void display(Node start)
        {
            if(start == null)
            {
                return;
            }
            display(start.left);
            System.out.print(start.data+" ");
            display(start.right);
        }

        public boolean search(int val)
        {
            if(isEmpty())
            {
                return false;
            }
            return search(root, val);
        }
        private boolean search(Node root, int val)
        {
            if(root == null)
            {
                return false;
            }
            if(root.data == val)
            {
                return true;
            }
            if(root.data > val)
                return search(root.left, val);
            else
                return search(root.right, val);
        }

        private Node findInorderSuccessorChild(Node root)
        {
            while(root.left != null)
            {
                root = root.left;
            }
            return root;
        }
        public void delete(int val)
        {
            if(isEmpty())
            {
                System.out.println("Sorry! The tree is empty.");
                return;
            }

            root = this.delete(root,val);
        }
        private Node delete(Node root, int val)
        {
            if(root == null)
                return null;

            if(root.data == val)
            {
                if(root.left == null)
                {
                    if(root.right != null)
                        root.right.parent = root.parent;
                    return root.right;
                }
                else if(root.right == null)
                {
                    root.left.parent = root.parent;
                    return root.left;
                }
                else
                {
                    Node insuc = findInorderSuccessorChild(root.right);
                    root.data = insuc.data;
                    root.right = delete(root.right, insuc.data);
                    return root;
                }
            }
            else if(root.data>val)
            {
                root.left = delete(root.left,val);
                return root;
            }
            else
            {
                root.right = delete(root.right,val);
                return root;
            }
        }

        public Node inorderSucc(int val)
        {
            return inorderSucc(root, val);
        }
        private Node inorderSucc(Node root, int val)
        {
            if(root.data == val)
            {
                if(root.right != null)
                {
                    return findInorderSuccessorChild(root.right);
                }
                else
                {
                    Node save = root;
                    Node pptr = root.parent;
                    while(pptr != null)
                    {
                        if(pptr.left == save)
                        {
                            return pptr;
                        }
                        else
                        {
                            save = pptr;
                            pptr = pptr.parent;
                        }
                    }
                    return null;
                }
            }
            if(val<root.data)
            {
                return inorderSucc(root.left,val);
            }
            else
            {
                return inorderSucc(root.right,val);
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        while(true)
        {
            System.out.print("Enter,\n1 to insert a node,\n2 to traverse the tree,\n3 to search a value,\n4 to delete a value from the tree,\n5 to find the inorder successor,\n6 to exit,\nEnter your choice : ");
            int choice = sc.nextInt();
            int val;
            boolean flag;
            switch(choice)
            {
                case 1: System.out.print("Enter the value you want to insert : ");
                        val = sc.nextInt();
                        bst.insert(val);
                        break;

                case 2: bst.display();
                        break;

                case 3: System.out.print("Enter the value you want to search in the tree : ");
                        val = sc.nextInt();
                        flag = bst.search(val);
                        if(flag)
                        {
                            System.out.println(val+" is present in the tree.");
                        }
                        else
                        {
                            System.out.println(val+" is not present in the tree.");
                        }
                        break;

                case 4: System.out.print("Enter a value in the tree : ");
                        val = sc.nextInt();
                        bst.delete(val);
                        break;

                case 5: System.out.print("Enter the value whose inorder successor you would like to find : ");
                        val = sc.nextInt();
                        flag = bst.search(val);
                        if(!flag)
                            System.out.println("The node is present.");
                        Node k = bst.inorderSucc(val);
                        if(k == null)
                            System.out.println("The node has no inorder successor.");
                        else
                            System.out.println("The inorder successor is : "+k.data);
                        break;

                case 6: System.exit(0);

                default: System.out.println("Sorry! Wrong input.");
                         break;
            }
        }
    }
}
