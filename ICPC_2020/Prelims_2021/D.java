package ICPC_2020.Prelims_2021;

import java.util.*;
import java.io.*;

public class D {

    static class Substring{
        int l, r;

        Substring(int l, int r){
            this.l = l;
            this.r = r;
        }
    }

    static void update(long[] bit, int index, long val){
        int n = bit.length;

        while(index <= n){
            bit[index - 1] = Math.max(bit[index - 1], val);
            index += (index & (-index));
        }
    }

    static long query(long[] bit, int index){
        long max = Long.MIN_VALUE;

        if(index <= 0)
            return 0;

        while(index > 0){
            max = Math.max(max, bit[index - 1]);
            index -= (index & (-index));
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();
            String s = sc.next();

            long[] a = new long[n];
            for(int i=0;i<n;i++){
                a[i] = sc.nextLong();
            }

            List<Substring> substrings = new ArrayList<>();
            for(int i=0;i<n;i++){
                for(int j=i;j<n;j++){
                    Substring substring = new Substring(i, j);
                    substrings.add(substring);
                }
            }

            substrings.sort((s1, s2) -> {
                int n1 = s1.r - s1.l + 1;
                int n2 = s2.r - s2.l + 1;

                for (int i = 0; i < Math.min(n1, n2); i++) {
                    int ch1 = s.charAt(s1.l + i);
                    int ch2 = s.charAt(s2.l + i);

                    if (ch1 != ch2)
                        return Integer.signum(ch1 - ch2);
                }

                if(n1 == n2){
                    return Integer.signum(s2.r - s1.r);
                }

                return Integer.signum(n1 - n2);
            });

            long[] bit = new long[n];

            for (Substring substring : substrings) {
                int l = substring.l;
                int r = substring.r;

                long prev = query(bit, l);
                long cur = prev + a[r - l];

                update(bit, r + 1, cur);
            }

            long max = 0;
            for(int i=0;i<n;i++){
                max = Math.max(max, bit[i]);
            }

            sb.append(max).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
