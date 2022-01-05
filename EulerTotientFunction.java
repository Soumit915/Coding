import java.util.*;

public class EulerTotientFunction
{
    static ArrayList<Integer> primes = new ArrayList<>();
    public static void precompute(int n)
    {
        int[] p = new int[n+1];
        for(int i=0;i<=n;i++)
            p[i] = i;
        p[0] = 1;
        for(int i=0;i<=n;i++)
        {
            if(p[i]==1)
                continue;
            for(int j=2*i;j<=n;j+=i)
            {
                p[j] = 1;
            }
        }

        for(int i=0;i<=n;i++)
        {
            if(p[i]!=1)
                primes.add(i);
        }
    }
    public static int eulerTotient(int n)
    {
        int result=n;
        for(int i=0;i<primes.size() && n>1;i++)
        {
            int v = primes.get(i);
            if(n%v==0)
            {
                while (n%v==0)
                {
                    n = n/v;
                }
                result -= (result/v);
            }
        }

        if(n>1)
            result -= (result/n);
        return result;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number : ");
        int n = sc.nextInt();
        int root_n = (int) Math.sqrt(n);
        precompute(root_n);

        int etf = eulerTotient(n);
        System.out.println("The euler totient value of n is : "+etf);
    }
}
