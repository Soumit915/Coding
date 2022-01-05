import java.util.*;

public class SeiveOfAtkin {
    static boolean[] primesarr;
    public static void computePrimes(int n)
    {
        for(int i=0;i<n;i++)
        {
            primesarr[i] = false;
        }

        for(int i=0;i*i<n;i++)
        {
            for(int j=0;j*j<n;j++)
            {
                int sn = 4*i*i+j*j;
                if(sn<n && (sn%12==1 || sn%12==5))
                {
                    primesarr[sn] ^= true;
                }

                sn = 3*i*i+j*j;
                if(sn<n && sn%12==7)
                {
                    primesarr[sn] ^= true;
                }

                sn = 3*i*i-j*j;
                if(i>j && sn<n && (sn%12==11))
                {
                    primesarr[sn] ^= true;
                }
            }
        }

        for(int i=5;i*i<n;i++)
        {
            for(int j=i*i;j<n;j+=i*i)
                primesarr[j] = false;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the limit: ");
        int n = sc.nextInt();
        primesarr = new boolean[n+1];

        computePrimes(n+1);

        for(int i=0;i<n;i++)
        {
            if(primesarr[i])
                System.out.print(i+" ");
        }
        System.out.println();
    }
}
