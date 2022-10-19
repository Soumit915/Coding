package Codeforces;

import java.util.*;
import java.io.*;

public class BAString {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            long k = sc.nextLong();
            long x = sc.nextLong() - 1;

            String s = sc.next();

            List<Long> list = new ArrayList<>();
            long c = 0;
            for(int i=0;i<n;i++){
                if(s.charAt(i) == '*'){
                    c++;
                }
                else{
                    if(c > 0)
                        list.add(c);
                    c = 0;
                }
            }
            list.add(c);

            list.replaceAll(aLong -> (aLong * k) + 1);

            List<Long> b_count = new ArrayList<>();
            for(int i=0;i<list.size();i++){
                b_count.add(0L);
            }

            int index = list.size() - 1;
            while(x > 0){
                long v = list.get(index);
                long cb = x%v;

                b_count.set(index, cb);

                x /= v;
                index--;
            }

            index = 0;
            for(int i=0;i<n;i++){
                if(s.charAt(i) == '*'){
                    if(i == 0 || s.charAt(i-1) != '*'){
                        for(int j=0;j<b_count.get(index);j++){
                            sb.append("b");
                        }
                        index++;
                    }
                }
                else{
                    sb.append("a");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
