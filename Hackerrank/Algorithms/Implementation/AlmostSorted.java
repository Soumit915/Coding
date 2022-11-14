package Hackerrank.Algorithms.Implementation;

import java.util.*;

public class AlmostSorted
{
    public static boolean swap(int[] arr)
    {
        int count=0;
        int n = arr.length;
        for(int i=1;i<n;i++)
        {
            if(arr[i]<arr[i-1])
                count++;
        }
        //System.out.println(count);
        if(count>2)
            return false;
        else if(count==0)
        {
            System.out.println("yes");
            return true;
        }
        else if(count==1)
        {
            for(int i=1;i<n;i++)
            {
                if(arr[i]<arr[i-1])
                {
                    if((i+1<n && arr[i+1]<arr[i-1]) || (i-2>=0 && arr[i]<arr[i-2]))
                    {
                        return false;
                    }
                    System.out.println("yes");
                    System.out.println("swap "+(i)+" "+(i+1));
                    return true;
                }
            }
        }
        else
        {
            boolean flag = false;
            int ind=0;
            for(int i=1;i<n;i++)
            {
                if(arr[i]<arr[i-1] && !flag)
                {
                    flag = true;
                    ind = i-1;
                }
                else if(arr[i]<arr[i-1] && flag)
                {
                    System.out.println("yes");
                    System.out.println("swap "+(ind+1)+" "+(i+1));
                    return true;
                }
            }
        }
        return true;
    }
    public static boolean reverse(int[] arr)
    {
        int n = arr.length;
        int start=-1, end=-1;
        boolean flag=false;
        for(int i=1;i<n;i++)
        {
            if(arr[i]<arr[i-1] && !flag)
            {
                int j;
                for(j=i+1;j<n;j++)
                {
                    if(arr[j]>arr[j-1])
                    {
                        start = i-1;
                        end = j-1;
                        i = j;
                        flag = true;
                        break;
                    }
                }
                if(j==n)
                {
                    System.out.println("yes");
                    System.out.println("reverse "+(i)+" "+n);
                    return true;
                }
            }
            else if(arr[i]<arr[i-1] && flag)
            {
                return false;
            }
        }

        //System.out.println(flag);

        if(start>=0 && end+1<n && arr[end+1]>arr[start])
        {
            if(start>0 && arr[end]<arr[start-1])
            {
                return false;
            }
            //System.out.println("test");
            System.out.println("yes");
            System.out.println("reverse "+(start+1)+" "+(end+1));
            return true;
        }
        return false;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sc.nextInt();
        }

        if(swap(arr))
        {
            System.exit(0);
        }
        else if(reverse(arr))
        {
            System.exit(0);
        }
        else
        {
            System.out.println("no");
        }
    }
}