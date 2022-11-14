import java.util.*;

public class KthLargest
{
    public static int findpivot(int[] a, int ll, int ul)
    {
        int length = ul-ll+1;
        int[] arr = new int[length];
        System.arraycopy(a, ll, arr, 0, length);

        int[] interarr = new int[length/5+1];
        if(length<=5)
        {
            Arrays.sort(arr);
            return arr[length/2];
        }
        else
        {
            for(int i=0;i<length/5+1;i++)
            {
                Arrays.sort(arr,i*5,Math.min((i+1)*5,length));
                interarr[i] = arr[Math.min((i*5+2),length-1)];
            }
            return findpivot(interarr, 0, length/5);
        }
    }
    public static int indexOf(int[] arr, int k)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i] == k)
                return i;
        }
        return -1;
    }
    public static void swap(int[] arr, int i, int j)
    {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    public static int partition(int[] arr, int ll, int ul, int pi)
    {
        int pivot = arr[pi];
        int st = ll-1;
        for(int i=ll;i<=ul;i++)
        {
            if(i!=pi)
            {
                if(arr[i]>pivot)
                {
                    st++;
                    swap(arr,i,st);
                    if(st==pi)
                        pi = i;
                }
            }
        }
        st++;
        swap(arr,st,pi);
        return st;
    }
    public static int kthlargest(int[] arr, int ll, int ul, int k)
    {
        int pivot = findpivot(arr,ll,ul);
        int pi = indexOf(arr,pivot);

        int pivotIndex = partition(arr,ll,ul,pi);
        if(pivotIndex == k)
            return pivot;
        else if(pivotIndex<k)
            return kthlargest(arr,pivotIndex+1,ul,k);
        else
            return kthlargest(arr,ll,pivotIndex-1,k);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the limit : ");
        int n = sc.nextInt();

        System.out.print("Enter the values of the array : ");
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter the value of k : ");
        int k = sc.nextInt();
        System.out.println("The kth largest element of the array is : "+kthlargest(arr,0,n-1,k-1));

    }
}
