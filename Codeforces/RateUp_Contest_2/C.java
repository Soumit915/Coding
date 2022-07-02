package Codeforces.RateUp_Contest_2;

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        String s = sc.next();

        int index = n;
        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                if(s.charAt(j) > s.charAt((i+j) % n)){
                    break;
                }
                else if(s.charAt(j) < s.charAt((i+j) % n)){
                    index = i;
                    break;
                }
            }

            if(index != n)
                break;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<k;i++){
            sb.append(s.charAt(i%index));
        }
        sb.append("\n");

        System.out.println(sb);
    }
}
