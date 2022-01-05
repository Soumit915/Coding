import java.util.*;

public class FastFibonacciFinding
{
    static long mod = (long) 1e9+7;
    static int a=0,b=1;
    static Map<Integer, Integer> dict = new HashMap<>();
    public static int solve(int n)
    {
        if(n==0)
            return a;
        if(n==1 || n==2)
            return b;

        if(dict.containsKey(n))
            return dict.get(n);

        if(n%2==0)
        {
            int fn = solve(n/2);
            int fn_1 = solve(n/2-1);
            int f2n = (int) (((((((long)fn_1)*2)%mod+fn)%mod)*fn)%mod);
            int f2n_1 = (int) ((((((long)fn)*fn)%mod) + ((((long)fn_1)*fn_1)%mod))%mod);
            dict.put(n, f2n);
            dict.put(n-1, f2n_1);
            return f2n;
        }
        else
        {
            int fn = solve((n+1)/2);
            int fn_1 = solve((n-1)/2);
            int f2n = (int) (((((((long)fn_1)*2)%mod+fn)%mod)*fn)%mod);
            int f2n_1 = (int) ((((((long)fn)*fn)%mod) + ((((long)fn_1)*fn_1)%mod))%mod);
            dict.put(n+1, f2n);
            dict.put(n, f2n_1);
            return f2n_1;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        dict.put(0,0);
        dict.put(1,1);
        dict.put(2,1);
        dict.put(3,2);
        dict.put(4,3);
        dict.put(5,5);
        while(t>0)
        {
            //System.out.print("Enter the value of f0 : ");
            int f0 = sc.nextInt();

            //System.out.print("Enter the value of f1 : ");
            int f1 = sc.nextInt();

            //System.out.print("Enter the value of n : ");
            int n = sc.nextInt();

            solve(n);
            solve(n-1);

            int ans = (int) (((((long)dict.get(n))*f1)%mod+(((long)dict.get(n-1))*f0)%mod)%mod);

            System.out.println(ans);
            t--;
        }
    }
}