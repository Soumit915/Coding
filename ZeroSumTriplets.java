import java.util.*;

public class ZeroSumTriplets {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        for(int i=0;i<n-2 && arr[i]<=0;i++)
        {
            int j=i+1;
            int k=n-1;
            int val = arr[i]*-1;
            while(j<k)
            {
                if(arr[j]+arr[k]<val)
                {
                    j++;
                }
                else if(arr[j]+arr[k]>val)
                {
                    k--;
                }
                else
                {
                    System.out.println(arr[i]+" "+arr[j]+" "+arr[k]);
                    j++;k--;
                }
            }
        }
    }
}
