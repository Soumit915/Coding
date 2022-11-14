
import java.util.*;

public class TCS_Swaranava2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextLong();
        }

        long ans = 0, sum = arr[0];
        for(int i=1;i<n;i++){
            if(arr[i]>arr[i-1]){
                sum += arr[i];
            }
            else{
                ans = Math.max(ans, sum);
                sum = arr[i];
            }
        }

        ans = Math.max(ans, sum);

        System.out.println(ans);
    }
}
