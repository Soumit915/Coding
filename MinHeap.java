import java.util.*;

public class MinHeap {
    private int[] arr;
    int size;
    int maxsize;
    MinHeap(int maxsize)
    {
        this.maxsize = maxsize;
        arr = new int[maxsize];
        size = 0;
    }
    public void insert(int val)
    {
        if(size == maxsize)
        {
            System.out.println("Sorry! The heap is full.");
            return;
        }
        size++;
        arr[size-1] = val;
        heapifyUp(size-1);
    }
    public void heapifyUp(int i)
    {
        if(i==0)
            return;
        int parent = (i+1)/2-1;
        int t;
        if(arr[parent]>arr[i])
        {
            t = arr[i];
            arr[i] = arr[parent];
            arr[parent] = t;
            heapifyUp(parent);
        }
    }
    public int delete()
    {
        if(size == 0)
        {
            System.out.println("Sorry! Heap is empty");
            return Integer.MIN_VALUE;
        }
        int delval = arr[0];
        arr[0] = arr[size-1];
        size--;
        heapifyDown(0);

        return delval;
    }
    public void heapifyDown(int i)
    {
        if(i>size)
        {
            return;
        }

        int smallest = i;
        int left = 2*i+1;
        int right = 2*i+2;

        if(left<size && arr[smallest]>arr[left])
            smallest=left;
        if(right<size && arr[smallest]>arr[right])
            smallest=right;

        if(smallest!=i)
        {
            int t=arr[smallest];
            arr[smallest]=arr[i];
            arr[i]=t;
            heapifyDown(smallest);
        }
    }
    public int getRoot()
    {
        if(size == 0)
        {
            System.out.println("Sorry! Heap is empty");
            return Integer.MIN_VALUE;
        }
        return arr[0];
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        MinHeap hp = new MinHeap(500);
        int val;

        while(true)
        {
            System.out.print("Enter,\n1 to insert an element,\n2 to extract the minimum element,\n3 to get the minimum element,\n4 to exit,\nEnter your choice : ");
            int choice = sc.nextInt();

            switch(choice)
            {
                case 1: System.out.print("Enter the value of the element you want to insert : ");
                        val = sc.nextInt();
                        hp.insert(val);
                        break;

                case 2: val = hp.delete();
                        if(val!=Integer.MIN_VALUE)
                            System.out.println("The deleted value from the heap is : "+val);
                        break;

                case 3: val = hp.getRoot();
                        if(val!=Integer.MIN_VALUE)
                            System.out.println("The root of the heap is : "+val);
                        break;

                case 4: System.exit(0);
            }
        }
    }
}
