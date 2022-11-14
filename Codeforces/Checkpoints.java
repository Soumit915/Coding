package Codeforces;

import java.util.*;

public class Checkpoints {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while(tc-->0){
            long k = sc.nextLong();

            if(k%2 == 1){
                sb.append("-1\n");
            }
            else{
                StringBuilder csb = new StringBuilder();
                csb.append("1 ");

                long c = 2;
                k -= 2;
                while(k > 0){
                    if(c * 2 > k){
                        csb.append("1 ");
                        k -= 2;
                        c = 2;
                    }
                    else{
                        c *= 2;
                        k -= c;
                        csb.append("0 ");
                    }
                }

                sb.append(csb.length()/2).append("\n");
                sb.append(csb).append("\n");
            }
        }

        System.out.println(sb);
    }
}