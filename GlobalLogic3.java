import java.util.*;

public class GlobalLogic3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        int diff = 10000;
        int ele = -10000;
        for(int i=0;i<n;i++){
            if(Math.abs(arr[i])<diff){
                diff = arr[i];
                ele = arr[i];
            }
            else if(Math.abs(arr[i])==diff){
                ele = Math.max(ele, arr[i]);
            }
        }

        System.out.println(ele);
    }
}
