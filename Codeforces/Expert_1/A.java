package Codeforces.Expert_1;

import java.io.*;
import java.util.*;

public class A {

    static int getScore(List<Integer> list, int zeros, int totOnes){
        int n = list.size();

        if(zeros == list.size())
            return zeros;

        int min = Math.max(zeros, totOnes - (list.get(zeros) - zeros));
        for(int i=zeros+1;i<n;i++){
            int len = list.get(i) - list.get(i - zeros - 1) - 1;
            min = Math.min(min, Math.max(zeros, totOnes - (len - zeros)));
        }
        int len = totOnes + list.size() - list.get(n - 1 - zeros) - 1;
        min = Math.min(min, Math.max(zeros, totOnes - (len - zeros)));

        return min;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            String s = sc.next();
            int n = s.length();

            List<Integer> list = new ArrayList<>();
            for(int i=0;i<n;i++){
                if(s.charAt(i) == '0')
                    list.add(i);
            }

            int l = 0, r = list.size();
            while (l < r){
                int mid = (l + r) / 2;

                if(getScore(list, mid, n - list.size()) > mid){
                    l = mid + 1;
                }
                else {
                    r = mid;
                }
            }

            sb.append(getScore(list, l, n - list.size())).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
