package TCS_Codevita9_QualificationRound;

import java.util.*;

public class D {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] locker = new int[r][c];
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                locker[i][j] = sc.nextInt();
            }
        }

        int l = Math.min(r,c)/2;
        int[] arr = new int[l];
        for(int i=0;i<l;i++)
        {
            arr[i] = sc.nextInt();
        }

        int[][] output = new int[r][c];
        for(int i=0;i<l;i++)
        {
            int totalnumberofelements = ((c-2*i)*2)+(r-2*(i+1))*2;
            arr[i] %= totalnumberofelements;

            if((i+1)%2!=1) {
                arr[i] = totalnumberofelements-arr[i];
            }
            int[] linerarr = new int[totalnumberofelements];
            int k=0;
            for(int i1=i;i1<c-i;i1++)
            {
                linerarr[k] = locker[i][i1];
                k++;
            }
            for(int i1=i+1;i1<r-i;i1++)
            {
                linerarr[k] = locker[i1][c-i-1];
                k++;
            }
            for(int i1=c-2-i;i1>=i;i1--)
            {
                linerarr[k] = locker[r-i-1][i1];
                k++;
            }
            for(int i1=r-i-2;i1>i;i1--)
            {
                linerarr[k] = locker[i1][i];
                k++;
            }

            int[] shifted = new int[totalnumberofelements];
            for(int i1=0;i1<totalnumberofelements;i1++)
            {
                int ind = (i1 + arr[i])%totalnumberofelements;
                shifted[i1] = linerarr[ind];
            }

            k=0;
            for(int i1=i;i1<c-i;i1++)
            {
                output[i][i1] = shifted[k];
                k++;
            }
            for(int i1=i+1;i1<r-i;i1++)
            {
                output[i1][c-i-1] = shifted[k];
                k++;
            }
            for(int i1=c-2-i;i1>=i;i1--)
            {
                output[r-i-1][i1] = shifted[k];
                k++;
            }
            for(int i1=r-i-2;i1>i;i1--)
            {
                output[i1][i] = shifted[k];
                k++;
            }
        }

        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                System.out.print(output[i][j]+" ");
            }
            System.out.println();
        }
    }
}
