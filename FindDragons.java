import java.util.*;

public class FindDragons {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int k = sc.nextInt();
        int dragons = sc.nextInt();

        int i;
        int j;
        int rn;
        int coln;
        int[] dr;
        int ctr;
        int[] dc;
        int[] hash = new int[row];
        for(i=0;i<row;i++)
            hash[i] = -1;
        for(i=0;i<dragons;i++)
        {
            rn = sc.nextInt();
            coln = sc.nextInt();
            hash[rn] = coln;
        }

        dr = new int[dragons];
        dc = new int[dragons];
        ctr = 0;
        for(i=0;i<row;i++)
        {
            if(hash[i] != -1)
            {
                dr[ctr] = i;
                dc[ctr] = hash[i];
                ctr++;
            }
        }

        long[][] ans = new long[k][dragons];
        long min,path;
        int k1;
        for(i=0;i<k;i++)
        {
            for(j=i;j<=(dragons-k+i);j++)
            {
                if(i==0)
                {
                    ans[i][j] = dr[j]+dc[j];
                }
                else
                {
                    min = ans[i-1][i-1] + Math.abs(dr[i-1]-dr[j]) + Math.abs(dc[i-1]-dc[j]);
                    //System.out.print(min+" ");
                    for(k1=i;k1<j;k1++)
                    {
                        path = ans[i-1][k1] + Math.abs(dr[k1]-dr[j]) + Math.abs(dc[k1]-dc[j]);
                        //System.out.print(path+" ");
                        min = Math.min(min,path);
                    }
                    ans[i][j] = min;
                    //System.out.println();
                }
            }
            //System.out.println();
        }

        long minpath = ans[k-1][k-1];
        for(i=k;i<dragons;i++)
        {
            minpath = Math.min(ans[k-1][i],minpath);
        }

        /*for(i=0;i<k;i++)
        {
            for(j=0;j<dragons;j++)
            {
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }*/
        System.out.println(minpath);
    }
}
