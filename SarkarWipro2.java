import java.io.*;
import java.util.*;

public class SarkarWipro2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int middle = n / 2;
        long ans = arr[middle-1] + arr[middle];

        System.out.println(ans);
    }
}
