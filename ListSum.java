import java.util.*;

public class ListSum
{
    static class Node
    {
        int data;
        Node link;
        Node()
        {
            this.data = 0;
            this.link = null;
        }
    }
    static class LinkedList
    {
        Node start;
        LinkedList()
        {
            this.start = null;
        }
        public boolean isEmpty()
        {
            return this.start ==null;
        }
        public void insert(final int val)
        {
            if(this.start == null)
            {
                this.start = new Node();
                this.start.data = val;
                return;
            }

            final Node nptr = new Node();
            nptr.data = val;
            nptr.link = this.start;
            this.start = nptr;
        }
        public int add_List()
        {
            if(this.isEmpty())
            {
                return 0;
            }
            return add_List(this.start);
        }
        private int add_List(final Node start)
        {
            if(start.link == null)
                return start.data;
            else
                return start.data+ this.add_List(start.link);
        }
        public int count_nodes()
        {
            if(this.isEmpty())
                return 0;
            return this.count_nodes(this.start);
        }
        private int count_nodes(final Node start)
        {
            if(start.link == null)
                return 1;
            else
                return 1+ this.count_nodes(start.link);
        }
        public void display()
        {
            if(this.isEmpty())
            {
                System.out.println("The list is empty.");
                return;
            }
            this.display(this.start);
        }
        private void display(final Node start)
        {
            if(start.link == null)
            {
                System.out.println(start.data+" ");
                return;
            }

            System.out.println(start.data+" ");
            this.display(start.link);
        }
    /*public void display()
    {
        Node nptr=this.start;
        if(isEmpty())
        {
            System.out.println("The list is empty.");
            return;
        }
        System.out.print("The list is : ");
        while(nptr!=null)
        {
            System.out.print(nptr.data+" ");
            nptr = nptr.link;
        }
        System.out.println();
    }*/
    }
    public static void main(final String[] args)
    {
        final Scanner sc = new Scanner(System.in);
        final LinkedList li = new LinkedList();
        while(true)
        {
            System.out.println("Enter,\n1 to insert a node,\n2 to find the sum of the current list,\n3 to exit,\nEnter your choice : ");
            final int choice = sc.nextInt();
            final int val;
            switch (choice) {
                case 1:
                    System.out.print("Enter the value you want to insert : ");
                    val = sc.nextInt();
                    li.insert(val);
                    final int c = li.count_nodes();
                    System.out.println("The number of nodes in the list : " +c);
                    li.display();
                    break;

                case 2:
                    final int sum = li.add_List();
                    System.out.println("The sum is : " + sum);
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}
