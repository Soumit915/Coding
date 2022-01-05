import java.io.*;
import java.util.*;

public class ShilpaD2C_Q3 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        long[] arr = new long[n];
        for(int i=0;i<n;i++)
            arr[i] = sc.nextLong();

        int ref = 0;
        for(int i=0;i<n;i++){
            if(arr[i]>=0) {
                ref = i;
                break;
            }
            else{
                ref = i;
            }
        }

        if(arr[ref]<0){
            System.out.println(Math.abs(arr[n-k]));
        }
        else{

            long mintime = Long.MAX_VALUE;
            if(ref+k<=n){
                mintime = Math.min(mintime, arr[ref+k-1]);
            }
            else if(ref-k>=0){
                mintime = Math.min(Math.abs(arr[ref-k]), mintime);
            }

            for(int i=ref;i<Math.min(n, ref+k);i++){
                if(k-(i-ref+1)<=ref){
                    long right = Math.abs(arr[i]);
                    long left = Math.abs(arr[ref-(k-(i-ref+1))]);

                    mintime = Math.min(mintime, (right*2)+left);
                }
            }

            for(int i=ref-1;i>=0;i--){
                if(ref+k-(ref-i)-1<n){
                    long right = Math.abs(arr[ref+k-(ref-i)-1]);
                    long left = Math.abs(arr[i]);

                    mintime = Math.min(mintime, (left*2)+right);
                }
            }

            System.out.println(mintime);
        }
    }
}
