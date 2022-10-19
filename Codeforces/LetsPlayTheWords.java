package Codeforces;

import java.util.*;
import java.io.*;

public class LetsPlayTheWords {

    static String reverse(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while(tc-->0){
            int n = sc.nextInt();
            String[] str = new String[n];
            for(int i=0;i<n;i++){
                str[i] = sc.next();
            }

            HashMap<String, Integer> str_01 = new HashMap<>();
            HashMap<String, Integer> str_10 = new HashMap<>();

            int c00 = 0, c11 = 0, c01 = 0, c10 = 0;
            for(int i=0;i<n;i++){
                boolean b = str[i].charAt(str[i].length() - 1) == '0';
                if(str[i].charAt(0) == '0'){
                    if(b){
                        c00++;
                    }
                    else{
                        c01++;
                        str_01.put(str[i], i);
                    }
                }
                else{
                    if(b){
                        c10++;
                        str_10.put(str[i], i);
                    }
                    else{
                        c11++;
                    }
                }
            }

            if(c00>0 && c11>0){
                if(c10 + c01 == 0){
                    sb.append("-1\n");
                    continue;
                }
            }

            if(c01 > c10){
                int diff = c01 - c10;
                int tobechanged = diff / 2;

                List<Integer> list = new ArrayList<>();
                for(String string: str_01.keySet()){
                    if(!str_10.containsKey(reverse(string))){
                        list.add(str_01.get(string));
                    }
                }

                if(tobechanged <= list.size()){
                    sb.append(tobechanged).append("\n");
                    for(int i=0;i<tobechanged;i++){
                        sb.append(list.get(i)+1).append(" ");
                    }
                    sb.append("\n");
                }
            }
            else{
                int diff = c10 - c01;
                int tobechanged = diff / 2;

                List<Integer> list = new ArrayList<>();
                for(String string: str_10.keySet()){
                    if(!str_01.containsKey(reverse(string))){
                        list.add(str_10.get(string));
                    }
                }

                if(tobechanged <= list.size()){
                    sb.append(tobechanged).append("\n");
                    for(int i=0;i<tobechanged;i++){
                        sb.append(list.get(i)+1).append(" ");
                    }
                    sb.append("\n");
                }
            }

        }

        System.out.println(sb);

        sc.close();
    }

}
