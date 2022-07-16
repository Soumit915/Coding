package Codeforces;

import java.util.*;
import java.io.*;

public class DivideAndSum {

    static long mod = 998244353 ;

    static long x , y;
    static void gcdExtended(long a, long b){
        if(a%b == 0){
            x = 1;
            y = 1 - (a / b);
            return;
        }

        gcdExtended(b, a%b);
        long t = y;
        y = x - ((a/b)*y) % mod;
        x = t;
    }

    static long modInverse(long a){
        gcdExtended(a, mod);
        x = (x%mod + mod)%mod;
        return x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] line = (br.readLine()).split(" ");

        long[] arr = new long[2 * n];
        for(int i=0;i<2*n;i++){
            arr[i] = Long.parseLong(line[i]);
        }

        Arrays.sort(arr);
        long sum = 0;
        for(int i=0;i<n;i++){
            sum += Math.abs(arr[i] - arr[2*n - i - 1]);
            sum = sum%mod;
        }

        long factN = 1;
        for(long i=1;i<=n;i++){
            factN = (factN * i) % mod;
        }

        long fact2N = factN;
        for(long i = n+1; i<= 2L *n; i++){
            fact2N = (fact2N * i) % mod;
        }

        long deno = (factN * factN) % mod;
        deno = modInverse(deno) % mod;
        long num = (deno * fact2N) % mod;

        num = (num * sum) % mod;

        System.out.println(num);
    }
}