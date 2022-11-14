import java.util.*;

public class InsertionInUnsortedList
{
    static class Node
    {
        int data;
        Node link;
        Node()
        {
            data = 0;
            link = null;
        }
    }
    static class LinkedList
    {
        Node start;
        LinkedList()
        {
            start = null;
        }
        public boolean isEmpty()
        {
            return start==null;
        }
        public void insert(int val)
        {
            if(isEmpty())
            {
                start = new Node();
                start.data = val;
                start.link = null;
            }
            else
            {
                Node ptr = start;
                while(ptr.link != null)
                {
                    ptr = ptr.link;
                }
                Node nptr = new Node();
                nptr.data = val;
                ptr.link = nptr;
            }
        }
        public void display()
        {
            Node ptr = start;
            System.out.print("The list is :== ");
            while(ptr != null)
            {
                System.out.print(ptr.data+" ");
                ptr = ptr.link;
            }
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        LinkedList li = new LinkedList();
        System.out.print("Enter the size of the list: ");
        int n = sc.nextInt();

        int i,val;
        for(i=0;i<n;i++)
        {
            System.out.println("Enter the value you want to insert in the list : ");
            val = sc.nextInt();
            li.insert(val);
        }

        li.display();
    }
}
