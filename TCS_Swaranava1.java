
import java.util.*;

public class TCS_Swaranava1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        long in = sc.nextLong();
        long x = sc.nextLong();
        long y = sc.nextLong();

        boolean flag = false;
        for(int i=0;i<=in;i++){
            if(x*i+y*(in-i)==n){
                System.out.println(i);
                flag = true;
                break;
            }
        }

        if(!flag){
            System.out.println("NO DISHES CAN BE COOKED");
        }
    }
}
