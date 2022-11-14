package Codeforces.CM_2;

import java.util.*;
import java.io.*;

public class B {

    static boolean isValid(String[] possible, String s, String t){
        for(String str: possible){
            if(str.equals(s) || str.equals(t))
                return false;
        }

        return true;
    }

    static String generate(String[] possible, String s, String t, int n){
        StringBuilder sb = new StringBuilder();

        char ch1 = possible[0].charAt(0);
        char ch2 = possible[0].charAt(1);
        char ch3 = possible[2].charAt(0);

        String concat1 = ch3+""+ch1;
        String concat2 = ch3+""+ch2;
        String concat3 = ch1+""+ch3;

        if(!concat1.equals(s) && !concat1.equals(t)) {
            sb.append(String.valueOf(ch3).repeat(Math.max(0, n)));
            sb.append(possible[0].repeat(Math.max(0, n)));
        }
        else if(!concat2.equals(s) && !concat2.equals(t)) {
            sb.append(String.valueOf(ch3).repeat(Math.max(0, n)));
            sb.append(String.valueOf(possible[1]).repeat(Math.max(0, n)));
        }
        else if(!concat3.equals(s) && !concat3.equals(t)) {
            sb.append(String.valueOf(possible[1]).repeat(Math.max(0, n)));
            sb.append(String.valueOf(ch3).repeat(Math.max(0, n)));
        }
        else{
            sb.append(possible[0].repeat(Math.max(0, n)));
            sb.append(String.valueOf(ch3).repeat(Math.max(0, n)));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s1 = sc.next();
        String s2 = sc.next();

        String[][] possible = {{"ab", "ba", "cc"}, {"ac", "ca", "bb"}, {"bc", "cb", "aa"}};

        String s;
        if(isValid(possible[0], s1, s2)){
            s = generate(possible[0], s1, s2, n);
        }
        else if(isValid(possible[1], s1, s2)){
            s = generate(possible[1], s1, s2, n);
        }
        else{
            s = generate(possible[2], s1, s2, n);
        }

        System.out.println("YES");
        System.out.println(s);

    }
}
