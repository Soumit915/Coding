import java.util.*;

public class AVLTreeMain {
    static class Node
    {
        int data;
        Node left;
        Node right;
        Node parent;
        int height;
        Node()
        {
            this.data = 0;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.height = 0;
        }
    }

    static class AVLTree
    {
        Node root;
        AVLTree()
        {
            this.root = null;
        }
        public boolean isEmpty()
        {
            return this.root ==null;
        }
        private int height(final Node root)
        {
            if(root==null)
            {
                return 0;
            }

            return root.height;
        }
        private int balanceFactor(final Node root)
        {
            if(root == null)
            {
                return 0;
            }
            return this.height(root.left)- this.height(root.right);
        }
        private Node ll_rotation(final Node root)
        {
            final Node k = root.left;
            root.left = k.right;
            k.right = root;

            root.height = 1+Math.max(this.height(root.left), this.height(root.right));
            k.height = 1+Math.max(this.height(k.left), this.height(k.right));

            return k;
        }
        private Node rr_rotation(final Node root)
        {
            final Node k = root.right;
            root.right = k.left;
            k.left = root;

            root.height = 1+Math.max(this.height(root.left), this.height(root.right));
            k.height = 1+Math.max(this.height(k.left), this.height(k.right));

            return k;
        }

        public void insert(final int val)
        {
            if(this.isEmpty())
            {
                this.root = new Node();
                this.root.data = val;
                return;
            }

            this.root = this.insert(this.root,val);
        }
        private Node insert(Node root, final int val)
        {
            if(root == null)
            {
                final Node nptr = new Node();
                nptr.data = val;
                nptr.height = 1;
                return nptr;
            }

            if(val<root.data)
            {
                root.left = this.insert(root.left,val);
            }
            else
            {
                root.right = this.insert(root.right,val);
            }

            root.height = 1+Math.max(this.height(root.left), this.height(root.right));
            int bf = this.balanceFactor(root);

            if(bf > 1)
            {
                if(val>root.left.data)
                {
                    root.left = this.rr_rotation(root.left);
                }
                root = this.ll_rotation(root);
            }
            else if(bf<-1)
            {
                if(val<root.right.data)
                {
                    root.right = this.ll_rotation(root.right);
                }
                root = this.rr_rotation(root);
            }
            return root;
        }

        private Node findInorderSuccessorChild(Node root)
        {
            while(root.left != null)
            {
                root = root.left;
            }
            return root;
        }
        public void delete(final int val)
        {
            if(this.isEmpty())
            {
                System.out.println("Sorry! The value is not present.");
                return;
            }
            this.root = this.delete(this.root,val);
        }
        private Node delete(Node root, final int val)
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
                    final Node insuc = this.findInorderSuccessorChild(root.right);
                    root.data = insuc.data;
                    root.right = this.delete(root.right, insuc.data);
                    return root;
                }
            }
            else if(root.data>val)
            {
                root.left = this.delete(root.left,val);
            }
            else
            {
                root.right = this.delete(root.right,val);
            }

            final int bf;
            root.height = 1+Math.max(this.height(root.left), this.height(root.right));
            bf = this.balanceFactor(root);
            if(bf > 1 && this.balanceFactor(root.left) == 0)
            {
                root = this.ll_rotation(root);
            }
            else if(bf > 1 && this.balanceFactor(root.left) == 1)
            {
                root = this.ll_rotation(root);
            }
            else if(bf > 1 && this.balanceFactor(root.left) == -1)
            {
                root.left = this.rr_rotation(root.left);
                root = this.ll_rotation(root);
            }
            else if(bf < -1 && this.balanceFactor(root.right) == 0)
            {
                root = this.rr_rotation(root);
            }
            else if(bf < -1 && this.balanceFactor(root.right) == 1)
            {
                root.right = this.ll_rotation(root.right);
                root = this.rr_rotation(root);
            }
            else if(bf < -1 && this.balanceFactor(root.right) == -1)
            {
                root = this.rr_rotation(root);
            }
            return root;
        }

        public void display()
        {
            if(this.isEmpty())
            {
                System.out.println("Sorry! The tree is empty.");
                return;
            }
            System.out.print("The traversal of the tree is : ");
            this.display(this.root);
            System.out.println();
        }
        private void display(final Node root)
        {
            if(root == null)
                return;
            this.display(root.left);
            System.out.print(root.data+" ");
            this.display(root.right);
        }
    }
    public static void main(final String[] args)
    {
        final Scanner sc = new Scanner(System.in);
        final AVLTree avl = new AVLTree();

        while(true)
        {
            System.out.print("Enter,\n1 to insert,\n2 to traverse,\n3 to delete,\n4 to exit,\nEnter your choice : ");
            final int choice = sc.nextInt();

            final int val;
            switch(choice)
            {
                case 1: System.out.print("Enter the value you want to insert : ");
                        val = sc.nextInt();
                        avl.insert(val);
                        break;

                case 2: avl.display();
                        break;

                case 3: System.out.print("Enter the value you want to delete : ");
                        val = sc.nextInt();
                        avl.delete(val);
                        break;

                case 4: System.exit(0);
            }
        }
    }
}
