package Hacket;

import java.io.*;
import java.util.*;

public class H24 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();

        long[] hash = new long[26];
        for(int i=0;i<n;i++){
            hash[s.charAt(i)-'a']++;
        }

        long ans = 1;
        for (long l : hash) ans *= l;

        System.out.println(ans);
    }
}
