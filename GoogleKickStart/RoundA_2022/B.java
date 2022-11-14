package GoogleKickStart.RoundA_2022;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class B {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++){
            sb.append("Case #").append(testi).append(": ");

            String number = sc.next();
            int l = number.length();

            BigInteger bigN = new BigInteger(number);
            BigInteger big9 = new BigInteger("9");

            int mod = 9 - (bigN.mod(big9).toString().charAt(0) - '0');
            if(mod == 9)
                mod = 0;

            boolean flag = false;
            int i = 0;
            if(mod == 0)
                i = 1;
            for(;i<l;i++){
                if((number.charAt(i)-'0') > mod){
                    number = number.substring(0, i) + mod + number.substring(i);
                    flag = true;
                    break;
                }
            }

            if(!flag){
                number = number + mod;
            }

            sb.append(number).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
