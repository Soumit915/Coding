import java.util.*;

public class FenwickTree {
    public static int query(int[] fenwickTree, int start, int end)
    {
        int i = end;
        int esum = 0;
        while(i>0)
        {
            esum += fenwickTree[i];
            i -= (i&(-i));
        }

        i = start-1;
        int ssum = 0;
        while(i>0)
        {
            ssum += fenwickTree[i];
            i -= (i&(-i));
        }
        return esum-ssum;
    }
    public static void update(int[] fenwickTree, int ind, int val)
    {
        int n = fenwickTree.length;
        while(ind <n)
        {
            fenwickTree[ind] += val;
            ind += (ind&(-ind));
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int[] arr = {1,5,7,6,3,4,9,5,2,8};
        int n = arr.length;

        int[] fenwickTree = new int[n+1];
        fenwickTree[0] = 0;
        for(int i=0;i<n;i++)
        {
            int index = i+1;
            update(fenwickTree, index, arr[i]);
        }

        int q = sc.nextInt();
        while(q>0)
        {
            char choice = sc.next().charAt(0);
            switch (choice)
            {
                case 'q': int l = sc.nextInt();
                          int r = sc.nextInt();
                          System.out.println(query(fenwickTree, l, r));
                          break;

                case 'u': int ind = sc.nextInt();
                          int val = sc.nextInt();
                          update(fenwickTree, ind, val);
                          break;
            }
            q--;
        }
    }
}
