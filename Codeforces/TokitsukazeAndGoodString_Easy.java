package Codeforces;

import java.io.*;
import java.util.*;

public class TokitsukazeAndGoodString_Easy {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            int n = sc.nextInt();
            String s = sc.next();

            List<Integer> list = new ArrayList<>();
            int count = 1;
            for(int i=1;i<n;i++){
                if(s.charAt(i) == s.charAt(i-1)){
                    count++;
                }
                else{
                    list.add(count);
                    count = 1;
                }
            }
            list.add(count);

            int ops = 0;
            int bit = 0;
            for(int i: list){
                ops += (i + bit) % 2;
                bit = (i + bit) % 2;
            }

            sb.append(ops).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
