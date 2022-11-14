import java.util.*;

class InsertionInSortedList
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
            Node ptr=start,save=null,nptr;

            /*Base Condition when list is empty:--*/
            if(isEmpty())
            {
                start = new Node();
                start.data = val;
                return;
            }
            while(ptr != null)
            {
                if(ptr.data>=val)
                {
                    nptr = new Node();
                    nptr.data = val;
                    if(save == null)
                    {
                        nptr.link = start;
                        start = nptr;
                    }
                    else
                    {
                        nptr.link = ptr;
                        save.link = nptr;
                    }
                    return;
                }
                save = ptr;
                ptr = ptr.link;
            }

            nptr = new Node();
            nptr.data = val;
            save.link = nptr;
        }
        public void display()
        {
            Node ptr=start;
            System.out.print("The list is :== ");
            while(ptr!=null)
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
        System.out.print("Enter the size of the list : ");
        int n = sc.nextInt();
        LinkedList li = new LinkedList();

        int i;
        System.out.print("Enter the elements in the list : ");
        for(i=0;i<n;i++)
        {
            int val = sc.nextInt();
            li.insert(val);
        }
        li.display();
    }
}

