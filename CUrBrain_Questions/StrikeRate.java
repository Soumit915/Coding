package CUrBrain_Questions;

import java.util.*;

public class StrikeRate {

    static long mod = (long) 1e9 + 7;

    static long x, y;
    static void gcdExtended(long a, long b){
        if(a%b==0){
            x = 1;
            y = 1 - (a/b);
            return;
        }
        gcdExtended(b, a%b);
        long t = y;
        y = x - ((a/b)*y)%mod;
        x = t;
    }
    static long moduloInverse(long a){
        gcdExtended(a, mod);
        x = (x%mod + mod)%mod;
        return x;
    }

    static long gcd(long a, long b){
        if(a%b == 0)
            return b;
        else return gcd(b, a%b);
    }

    static long lcm(long a, long b){
        long gcd = gcd(a, b);
        return ((a * b) / gcd);
    }

    static class Number{
        long num;
        long deno;

        Number(long num, long deno){

            if(deno != 0) {
                long gcd = gcd(num, deno);
                num /= gcd;
                deno /= gcd;
            }

            this.num = num;
            this.deno = deno;
        }

        public Number add(Number number){
            Number sum = new Number(0, 0);

            sum.deno = lcm(this.deno, number.deno);
            sum.num = this.num * (sum.deno / this.deno) + number.num * (sum.deno / number.deno);

            return sum;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            long[][] match = new long[n][m];
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    match[i][j] = sc.nextLong();
                }
            }

            Number[] col_sum = new Number[m];
            for(int i=0;i<m;i++){
                Number number = new Number(0, 0);

                for(int j=0;j<n;j++){
                    if(match[i].length > i) {
                        number.num += match[j][i];
                        number.deno++;
                    }
                }

                col_sum[i] = number;
            }

            Number[] prefix_sum = new Number[m];
            prefix_sum[0] = col_sum[0];
            for(int i=1;i<m;i++){
                prefix_sum[i] = prefix_sum[i-1].add(col_sum[i]);
            }

            for(int i=0;i<m;i++){

            }
        }

        System.out.println(sb);
    }

}
