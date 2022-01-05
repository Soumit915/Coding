package Hackerrank.Algorithms.Implementation;

import java.util.*;

public class LarrysArray
{
    public static int inversions(int[] arr, int ll, int mid, int ul)
    {
        int count=0;
        int al = mid-ll+1;
        int bl = ul-mid;
        int[] L = new int[al];
        int[] R = new int[bl];

        if (al >= 0) System.arraycopy(arr, ll, L, 0, al);
        for(int i=0;i<bl;i++)
        {
            R[i] = arr[mid+i+1];
        }

        int p=0, q=0, k=0;
        while(p<al && q<bl)
        {
            if(L[p]<R[q])
            {
                arr[k+ll] = L[p];p++;k++;
            }
            else
            {
                arr[k+ll] = R[q];q++;k++;
                count+= al-p;
            }
        }

        while(p<al)
        {
            arr[k+ll] = L[p];p++;k++;
        }

        while(q<bl)
        {
            arr[k+ll] = R[q];q++;k++;
        }

        return count;
    }
    public static int countInversions(int[] arr, int l, int u)
    {
        if(l<u)
        {
            int mid = (l+u)/2;
            int li = countInversions(arr,l,mid);
            int ri = countInversions(arr,mid+1,u);
            int toti = inversions(arr,l,mid,u);
            //System.out.println(li+" "+ri+" "+toti);
            return li+ri+toti;
        }

        return 0;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];

            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
            }

            int inversions = countInversions(arr, 0, n-1);
            if(inversions%2==0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}