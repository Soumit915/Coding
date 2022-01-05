import java.util.*;

public class HeapSort {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the array : ");
        int n = sc.nextInt();

        System.out.print("Enter the elements in the array : ");
        int arr[] = new int[n];
        int i;
        for(i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        heapsort(arr,n);
        System.out.print("The sorted array is : ");
        for(i=n-1;i>=0;i--)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void heapsort(int arr[], int n)
    {
        int i,t;
        for(i=n/2-1;i>=0;i--)
        {
            heapify(arr,n,i);
        }

        for(i=n-1;i>=0;i--)
        {
            t = arr[i];
            arr[i]=arr[0];
            arr[0]=t;
            heapify(arr,i,0);
        }
    }
    public static void heapify(int arr[], int n, int i)
    {
        if(i>n/2-1)
            return;
        int smallest = i;
        int left = 2*i+1;
        int right = 2*i+2;

        if(left<n && arr[smallest]>arr[left])
            smallest=left;
        if(right<n && arr[smallest]>arr[right])
            smallest=right;

        if(smallest!=i)
        {
            int t=arr[smallest];
            arr[smallest]=arr[i];
            arr[i]=t;
            heapify(arr,n,smallest);
        }
    }
}
