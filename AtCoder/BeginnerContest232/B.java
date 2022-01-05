package AtCoder.BeginnerContest232;

import java.io.*;
import java.util.*;

public class B {
    static String shift(String s, int k){
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            int ch = s.charAt(i) - 'a';
            ch += k;
            ch %= 26;
            ch += 'a';
            char c = (char) ch;
            sb.append(c);
        }

        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        String t = sc.next();

        /*int n = s.length();
        int diff = s.charAt(0) - t.charAt(0);
        boolean flag = true;
        for(int i=1;i<n;i++){
            if(s.charAt(i)-t.charAt(i)!=diff){
                flag = false;
                break;
            }
        }*/

        boolean flag = false;
        for(int i=0;i<26;i++){
            String shift = shift(s, i);
            if(shift.equals(t)){
                flag = true;
                break;
            }
        }

        if(flag){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }

        sc.close();
    }
}
