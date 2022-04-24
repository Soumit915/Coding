package Codeforces.Round780Div3;

import java.io.*;
import java.util.*;

public class F1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String s = sc.next();

            long count = 0;
            for(int i=0;i<n;i++){
                int plus = 0;
                int minus_cons = 0;
                for(int j=i;j<n;j++){
                    if(s.charAt(j) == '+'){
                        plus++;
                    }
                    else{
                        if(j!=i){
                            minus_cons += s.charAt(j-1) == '-' ? 1 : 0;
                        }
                    }

                    int minus = j - i + 1 - plus;
                    if(minus == plus){
                        count++;
                    }
                    else if(minus > plus){
                        if((minus-plus)%3==0 && (minus-plus)/3 <= minus_cons){
                            count++;
                        }
                    }
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
