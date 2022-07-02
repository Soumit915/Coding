package Codeforces.RateUp_Contest_3;

import java.io.*;
import java.util.*;

public class A {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            int n = sc.nextInt();
            String s = sc.next();
            String t = sc.next();

            StringBuilder sbs = new StringBuilder();
            List<Integer> lists = new ArrayList<>();

            StringBuilder sbt = new StringBuilder();
            List<Integer> listt = new ArrayList<>();

            for(int i=0;i<n;i++){
                if(s.charAt(i) != 'b') {
                    sbs.append(s.charAt(i));
                    lists.add(i);
                }

                if(t.charAt(i) != 'b') {
                    sbt.append(t.charAt(i));
                    listt.add(i);
                }
            }

            if(sbs.toString().equals(sbt.toString())){

                boolean flag = true;

                for(int i=0;i<lists.size();i++){
                    if(sbs.charAt(i) == 'a'){
                        if(lists.get(i) > listt.get(i)){
                            flag = false;
                            break;
                        }
                    }
                    else{
                        if(lists.get(i) < listt.get(i)){
                            flag = false;
                            break;
                        }
                    }
                }

                if(flag){
                    sb.append("YES\n");
                }
                else{
                    sb.append("NO\n");
                }
            }
            else{
                sb.append("NO\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
