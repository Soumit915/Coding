package Codeforces.Expert_6;

import java.util.*;
import java.io.*;

public class E {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int l = s.length();

        if(l < 2){
            if(s.equals("0") || s.equals("_") || s.equals("X")){
                System.out.println(1);
            }
            else{
                System.out.println(0);
            }
            System.exit(0);
        }
        else if(l == 2){
            int count = 0;
            for(int i=10;i<=95;i+=5){
                if(i%25 != 0)
                    continue;
                String str = Integer.toString(i);

                if('0'<=s.charAt(0) && s.charAt(0)<='9'){
                    if('0'<=s.charAt(1) && s.charAt(1)<='9'){
                        if(s.equals(str)){
                            count++;
                        }
                    }
                    else {
                        if(s.charAt(0)==str.charAt(0))
                            count++;
                    }
                }
                else if(s.charAt(0)=='_'){
                    if('0'<=s.charAt(1) && s.charAt(1)<='9'){
                        if(s.charAt(1)==str.charAt(1)){
                            count++;
                        }
                    }
                    else {
                        count++;
                    }
                }
                else{
                    if('0'<=s.charAt(1) && s.charAt(1)<='9'){
                        if(s.charAt(1)==str.charAt(1)){
                            count++;
                        }
                    }
                    else if(s.charAt(1)=='_'){
                        count++;
                    }
                }
            }

            System.out.println(count);
            System.exit(0);
        }
        else{
            int start = (int) (Math.pow(10, l-1));
            int end = (int) (Math.pow(10, l));

            int count = 0;
            for(int i=start;i<end;i+=25){
                String str = Integer.toString(i);

                Set<Character> set = new HashSet<>();
                boolean flag = true;
                for(int j=0;j<l;j++){
                    if(s.charAt(j)=='_')
                        continue;
                    else if(s.charAt(j)=='X'){
                        set.add(str.charAt(j));
                    }
                    else if(s.charAt(j)!=str.charAt(j)){
                        flag = false;
                        break;
                    }
                }

                if(flag && set.size() < 2){
                    count++;
                }
            }

            System.out.println(count);
        }

        sc.close();
    }
}
