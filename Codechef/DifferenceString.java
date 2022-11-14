package Codechef;

import java.io.*;
import java.util.*;

public class DifferenceString {
    public static String pad(String s, int n){
        StringBuilder sb = new StringBuilder();
        for(int i=s.length();i<n;i++){
            sb.append("0");
        }
        sb.append(s);
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String[] strings = new String[n];
            HashSet<String> set = new HashSet<>();
            for(int i=0;i<n;i++){
                strings[i] = sc.next();
                set.add(strings[i]);
            }

            String ans = "";
            for(int i=0;i<=n+2;i++){
                String bin = Integer.toBinaryString(i);
                bin = pad(bin, n);
                if(!set.contains(bin)){
                    ans = bin;
                    break;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
