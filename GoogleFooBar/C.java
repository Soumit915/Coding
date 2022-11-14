package GoogleFooBar;

import java.util.ArrayList;

public class C {
    public static int solution(int total_lambs) {
        //Your code here

        if(total_lambs==1){
            return 0;
        }
        else if(total_lambs==2){
            return 1;
        }

        ArrayList<Integer> fibolist = new ArrayList<>();
        ArrayList<Integer> pow2list = new ArrayList<>();

        int pow2 = 1;
        int sum_pow2 = 1;
        pow2list.add(1);
        while(sum_pow2<=total_lambs){
            pow2 = pow2*2;
            sum_pow2 += pow2;
            pow2list.add(sum_pow2);
        }

        int fib1 = 1;
        int fib2 = 1;
        int sum_fib = 2;
        fibolist.add(fib1);
        fibolist.add(2);
        while(sum_fib<=total_lambs){
            int nextfib = fib2 + fib1;
            fib1 = fib2;
            fib2 = nextfib;
            sum_fib += nextfib;
            fibolist.add(sum_fib);
        }

        int fibindex = -1;
        for(int i=0;i<fibolist.size();i++){
            if(fibolist.get(i)<=total_lambs){
                fibindex = i;
            }
            else break;
        }
        int powindex = -1;
        for(int i=0;i<pow2list.size();i++){
            if(pow2list.get(i)<=total_lambs){
                powindex = i;
            }
            else break;
        }

        return fibindex - powindex;
    }
    public static void main(String[] args){
        for(int i=1;i<100;i++){
            System.out.println(i +" "+solution(i));
        }
    }
}
