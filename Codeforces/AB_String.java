package Codeforces;

import java.util.*;
import java.io.*;

public class AB_String {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='A')
                a.add(i);
            else b.add(i);
        }

        a.add(n);
        b.add(n);

        long ans = 0;
        for(int i=0;i<a.size() - 1;i++){
            int next = a.get(i+1);
            long count = (next - a.get(i));

            ans += count;
        }

        for(int i=0;i<b.size() - 1;i++){
            int next = b.get(i+1);
            long count = (next - b.get(i));

            ans += count;
        }

        int last = -1;
        for(int i=0;i<a.size() - 1;i++){
            long count = (a.get(i) - last);
            count -= 2;

            if(count > 0)
                ans += count;
            last = a.get(i);
        }

        last = -1;
        for(int i=0;i<b.size() - 1;i++){
            long count = (b.get(i) - last);
            count -= 2;

            if(count > 0)
                ans += count;
            last = b.get(i);
        }

        long tot = ((long) n * (n + 1)) / 2 ;
        ans = tot - ans;
        System.out.println(ans);

        sc.close();
    }
}
