import java.util.*;

public class MedianOf2SortedArrays {
    public static int binsearch(int[] arr, int ll, int ul, int val)
    {
        int mid = (ll+ul)/2;
        if(ll==ul)
        {
            if(arr[ll]==val)
                return ll+1;
            else if(arr[ll]>val)
                return ll+1;
            else return ll+2;
        }

        if(arr[mid]==val)
        {
            return mid+1;
        }
        else if(val>arr[mid])
        {
            if(mid == arr.length - 1 || arr[mid + 1] >= val)
                return mid+2;
            return binsearch(arr, mid+1, ul, val);
        }
        else
        {
            if(mid==0 || arr[mid-1]<val)
                return mid+1;
            return binsearch(arr, ll, mid-1, val);
        }
    }
    public static int findElement(int[] arr1, int[] arr2, int ind)
    {
        int m = arr1.length;
        int n = arr2.length;

        int l=0, r=n-1;
        while(l<=r)
        {
            int mid = (l+r)/2;
            int k = binsearch(arr1, 0, m-1, arr2[mid]);
            if(k+mid == ind)
            {
                return arr2[mid];
            }
            else if(k+mid<ind)
            {
                l = mid+1;
            }
            else r = mid-1;
        }

        l=0; r=m-1;
        while(l<=r)
        {
            int mid = (l+r)/2;
            int k = binsearch(arr2, 0, n-1, arr1[mid]);
            if(k+mid == ind)
            {
                return arr1[mid];
            }
            else if(k+mid<ind)
            {
                l = mid+1;
            }
            else r = mid-1;
        }

        return Integer.MIN_VALUE;
    }
    public static double findmedian(int[] arr1, int[] arr2)
    {
        int m = arr1.length;
        int n = arr2.length;
        int medianInd = (n+m)/2+1;

        int k1 = findElement(arr1, arr2, medianInd);
        if((m+n)%2==1)
        {
            return k1;
        }
        else
        {
            int k2 = findElement(arr1, arr2, medianInd-1);
            double med = k1+k2;
            med /= 2;
            return med;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of m: ");
        int m = sc.nextInt();
        int[] arr1 = new int[m];
        for(int i=0;i<m;i++)
        {
            arr1[i] = sc.nextInt();
        }

        System.out.print("Enter the value of n: ");
        int n = sc.nextInt();
        int[] arr2 = new int[n];
        for(int i=0;i<n;i++)
        {
            arr2[i] = sc.nextInt();
        }

        System.out.println("The median is : " + findmedian(arr1, arr2));
    }
}
