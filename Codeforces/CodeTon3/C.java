package Codeforces.CodeTon3;

import java.io.*;
import java.util.*;

public class C {

    static boolean allEqual(String a, String b){
        int n = a.length();

        for(int i=0;i<n;i++){
            if(a.charAt(i) != b.charAt(i))
                return false;
        }

        return true;
    }

    static boolean allOpposite(String a, String b){
        int n = a.length();

        for(int i=0;i<n;i++){
            if(a.charAt(i) == b.charAt(i))
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            int n = sc.nextInt();

            String a = sc.next();
            String b = sc.next();

            if(!allEqual(a, b) && !allOpposite(a, b)){
                sb.append("NO\n");
                continue;
            }

            boolean orientation = allEqual(a, b);

            StringBuilder csb = new StringBuilder();
            int count = 0;
            for(int i=0;i<n;i++){
                if(a.charAt(i) == '1'){
                    csb.append(i+1).append(" ").append(i+1).append("\n");
                    orientation = !orientation;
                    count++;
                }
            }

            if(!orientation){
                csb.append("1 ").append(n).append("\n");
                csb.append("1 1\n");
                csb.append("2 ").append(n).append("\n");
                count += 3;
            }

            sb.append("YES\n");
            sb.append(count).append("\n");
            sb.append(csb);
        }

        System.out.println(sb);

        sc.close();
    }

}
