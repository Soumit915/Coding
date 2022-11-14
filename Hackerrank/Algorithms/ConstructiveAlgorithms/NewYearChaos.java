package Hackerrank.Algorithms.ConstructiveAlgorithms;

import java.util.*;
public class NewYearChaos
{
    static Map<Integer, Integer> hashcount;
    public static int mergeInverse(int[] arr, int ll, int mid, int ul)
    {
        int count = 0;
        int la = mid-ll+1;
        int lb = ul-mid;

        int[] arrA = new int[la];
        int[] arrB = new int[lb];

        if (la >= 0) System.arraycopy(arr, ll, arrA, 0, la);
        for(int i=mid+1;i<=ul;i++)
        {
            arrB[i-mid-1] = arr[i];
        }

        int p=0, q=0, k=ll;
        while (p<la && q<lb)
        {
            if(arrA[p]>arrB[q])
            {
                arr[k] = arrA[p];
                p++;
            }
            else
            {
                arr[k] = arrB[q];
                count += (la-p);
                hashcount.put(arrB[q], hashcount.get(arrB[q])+(la-p));
                q++;
            }
            k++;
        }

        while(p<la)
        {
            arr[k] = arrA[p];
            p++;k++;
        }

        while(q<lb)
        {
            arr[k] = arrB[q];
            q++;k++;
        }

        return count;
    }
    public static int countInverse(int[] arr, int ll, int ul)
    {
        if(ll<ul)
        {
            int mid = (ll+ul)/2;
            int left = countInverse(arr, ll, mid);
            int right = countInverse(arr, mid+1, ul);
            int cur = mergeInverse(arr, ll, mid, ul);
            return left + right + cur;
        }
        return 0;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();

        while(q-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            {
                arr[n-1-i] = sc.nextInt();
            }

            hashcount = new HashMap<>();
            for(int i=1;i<=n;i++)
                hashcount.put(i, 0);
            int cIcount = countInverse(arr, 0, n-1);

            boolean flag = true;
            for(int i=1;i<=n;i++)
            {
                if(hashcount.get(i)>2)
                {
                    flag = false;
                    break;
                }
            }

            if(flag)
                System.out.println(cIcount);
            else System.out.println("Too chaotic");
        }
    }
}

