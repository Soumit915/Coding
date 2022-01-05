package Codeforces.Round755Div2;

import java.util.*;

public class D {
    static long sols(long c){
        long dis = (long) Math.sqrt((long) -1 * (long) -1 - 4 * c);
        return (dis - (long) -1) / ((long) 2);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-->0){
            long n = sc.nextLong();

            System.out.println("? "+1+" "+n);
            System.out.flush();
            long totsum = sc.nextLong();

            long ll = 1;
            long ul = n;
            while(ll<ul){
                long mid = ll + (ul - ll)/2;
                System.out.println("? "+1+" "+mid);
                System.out.flush();
                long inverse = sc.nextLong();

                if(inverse==totsum){
                    ul = mid;
                }
                else{
                    ll = mid + 1;
                }
            }

            long k = ll;
            System.out.println("? "+1+" "+(k-1));
            System.out.flush();
            long inverse = sc.nextLong();

            long kj = totsum - inverse + 1;
            long j = k - kj + 1;

            totsum = totsum - (kj * (kj-1))/2;
            totsum *= 2;
            long ij = sols(-1 * totsum);
            long i = j - ij;

            System.out.println("! "+i+" "+j+" "+k);
            System.out.flush();
        }

        sc.close();
    }
}
