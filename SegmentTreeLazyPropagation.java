import java.util.*;

public class SegmentTreeLazyPropagation {
    public static int nextPowerof2(int n)
    {
        n = n|(n>>1);
        n = n|(n>>2);
        n = n|(n>>4);
        n = n|(n>>8);
        return n+1;
    }
    public static boolean isPowerof2(int n)
    {
        return ((n&(n-1))==0);
    }
    public static void built(int[] segTree, int[] arr, int sn, int start, int end)
    {
        if(start==end)
        {
            segTree[sn] = arr[start];
            return;
        }

        int mid = (start+end)/2;
        built(segTree, arr, sn*2+1, start, mid);
        built(segTree, arr, sn*2+2, mid+1, end);
        segTree[sn] = Math.min(segTree[sn*2+1],segTree[sn*2+2]);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        int sn;
        if(isPowerof2(n))
            sn = 2*n-1;
        else
            sn = 2*nextPowerof2(n)-1;
        int[] segTree = new int[n];
        for(int i=0;i<sn;i++)
            segTree[i] = Integer.MAX_VALUE;
        built(segTree, arr, 0, 0, n-1);
    }
}
