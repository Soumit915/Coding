import java.util.*;

class InsertionAndDeletion
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
    static class LinkedList {
        Node start;
        int size;
        LinkedList() {
            start = null;
            size = 0;
        }

        public boolean isEmpty() {
            return start == null;
        }

        public void insert(int val) {
            Node ptr = start, save = null, nptr;
            size++;
            /*Base Condition when list is empty:--*/
            if (isEmpty()) {
                start = new Node();
                start.data = val;
                return;
            }
            while (ptr != null) {
                if (ptr.data >= val) {
                    nptr = new Node();
                    nptr.data = val;
                    if (save == null) {
                        nptr.link = start;
                        start = nptr;
                    } else {
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

        public void delete(int val) {

            Node ptr = start, save = null;
            if (ptr.data == val) {
                start = ptr.link;
                size--;
                return;
            } else {
                while (ptr != null) {
                    if (ptr.data == val) {
                        save.link = ptr.link;
                        size--;
                        return;
                    }
                    save = ptr;
                    ptr = ptr.link;
                }
            }

            System.out.println("Sorry! Value not present.");
        }

        public boolean search(int val) {
            Node ptr = start;
            while (ptr != null) {
                if (ptr.data == val)
                    return true;

                ptr = ptr.link;
            }
            return false;
        }

        public void display() {
            Node ptr = start;
            System.out.print("The list is :== ");
            while (ptr != null) {
                System.out.print(ptr.data + " ");
                ptr = ptr.link;
            }
            System.out.println();
        }

        public void reverseNew(LinkedList rev)
        {
            if(this.isEmpty())
            {
                System.out.println("Sorry! The list is empty.");
                return;
            }

            Node ptr = this.start;
            Node nptr;
            while(ptr != null)
            {
                nptr = new Node();
                nptr.data = ptr.data;
                nptr.link = rev.start;
                rev.start = nptr;
                ptr = ptr.link;
            }
        }

        public void reverse()
        {
            if(this.isEmpty())
            {
                System.out.println("Sorry! The list is empty");
                return;
            }

            Node ptr=start;
            int n = size/2,i;
            Node[] stk = new Node[size];
            int top=0;
            while(ptr!=null)
            {
                stk[top] = ptr;
                top++;
                ptr = ptr.link;
            }

            int t;
            for(i=0;i<n;i++)
            {
                t = stk[i].data;
                stk[i].data = stk[size-i-1].data;
                stk[size-i-1].data = t;
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        LinkedList li = new LinkedList();
        LinkedList rev;

        while(true)
        {
            System.out.print("Enter,\n1 to insert,\n2 to delete,\n3 to search,\n4 to display,\n5 to get the reversed list,\n6 to reverse the current list,\n7 to exit : ");
            int choice = sc.nextInt();

            int val;
            switch(choice)
            {
                case 1: System.out.print("Enter the value you want to insert : ");
                        val = sc.nextInt();
                        li.insert(val);
                        break;

                case 2: if(li.isEmpty())
                        {
                            System.out.println("Sorry! List is empty");
                            break;
                        }
                        System.out.print("Enter the value you want to delete : ");
                        val = sc.nextInt();
                        li.delete(val);
                        break;

                case 3: if(li.isEmpty())
                        {
                            System.out.println("Sorry! List is empty");
                            break;
                        }
                        System.out.print("Enter the value you want to search : ");
                        val = sc.nextInt();
                        boolean flag = li.search(val);
                        if(flag)
                        {
                            System.out.println("The search is successful.");
                        }
                        else
                        {
                            System.out.println("The search is unsuccessful.");
                        }
                        break;

                case 4: li.display();
                        break;

                case 5: rev = new LinkedList();
                        li.reverseNew(rev);
                        System.out.println("The actual list is :== ");
                        li.display();
                        System.out.println("Reversed list is :== ");
                        rev.display();
                        break;

                case 6: System.out.println("List before reversal:== ");
                        li.display();
                        li.reverse();
                        System.out.println("List after reversal:== ");
                        li.display();
                        break;

                case 7: System.exit(0);
            }
        }
    }
}
