import java.io.*;
import java.util.*;

public class GlobalLogic1 {
    static long solve(long[] a){
        int n = a.length;
        long[] acopy = new long[n];
        System.arraycopy(a, 0, acopy, 0, n);
        Arrays.sort(a);

        long good = 0;
        long bad = 0;
        for(int i=0;i<n;i++){
            if(acopy[i]==a[i]){
                good += a[i];
            }
            else{
                bad += a[i];
            }
        }

        return good - bad;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long[] a = new long[n];
        for(int i=0;i<n;i++)
            a[i] = sc.nextLong();

        long v = solve(a);
        System.out.println(v);

        sc.close();
    }
}
