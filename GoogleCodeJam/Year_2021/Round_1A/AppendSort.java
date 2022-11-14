package GoogleCodeJam.Year_2021.Round_1A;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class AppendSort {
    static String getValue(String n2, String n1){
        if(n1.equals(n2)){
            return n2+"0";
        }
        if(n2.length()>n1.length())
            return n2;

        boolean greater_flag = false;
        boolean lesser_flag = false;
        for(int i=0;i<n2.length();i++){
            if(n2.charAt(i)>n1.charAt(i)){
                greater_flag = true;
                break;
            }
            else if(n1.charAt(i)>n2.charAt(i)){
                lesser_flag = true;
                break;
            }
        }

        if(greater_flag && n1.length()==n2.length())
            return n2;
        else if(greater_flag){
            StringBuilder sb = new StringBuilder();
            sb.append(n2);
            for(int i=n2.length();i<n1.length();i++){
                sb.append('0');
            }
            return sb.toString();
        }

        if(!greater_flag && !lesser_flag){
            boolean nine_flag = true;
            for(int i=n2.length();i<n1.length();i++){
                if(n1.charAt(i)!='9'){
                    nine_flag = false;
                    break;
                }
            }

            if(nine_flag){
                StringBuilder sb = new StringBuilder();
                sb.append(n2);
                for(int i=n2.length();i<=n1.length();i++){
                    sb.append('0');
                }
                return sb.toString();
            }

            BigInteger bi1 = new BigInteger(n1);
            BigInteger bi2 = new BigInteger("1");
            bi1 = bi1.add(bi2);
            return bi1.toString();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(n2);
        for(int i=n2.length();i<=n1.length();i++){
            sb.append('0');
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++){
            sb.append("Case #").append(testi).append(": ");

            int n = sc.nextInt();
            String[] s = new String[n];
            for(int i=0;i<n;i++){
                s[i] = sc.next();
            }

            int c = 0;
            String last = s[0];
            for(int i=1;i<n;i++){
                String newv = getValue(s[i], last);
                c += newv.length() - s[i].length();
                last = newv;
            }

            sb.append(c).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
