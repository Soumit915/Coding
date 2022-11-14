package Codeforces;

import java.io.*;
import java.util.*;

public class EbonyAndIvory {

    static int gcd(int a, int b){
        if(a%b==0)
            return b;
        else return gcd(b, a%b);
    }

    static int x, y;
    static void gcdExtended(int a, int b, int mod){
        if(a%b==0){
            x = 1;
            y = 1-(a/b);
            return;
        }
        gcdExtended(b, a%b, mod);
        int t = y;
        y = x - ((a/b)*y)%mod;
        x = t;
    }
    static int modInverse(int a, int b){
        gcdExtended(a, b, b);
        x = (x%b + b)%b;
        return x;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int gcd = gcd(a, b);
        if(c%gcd(a,b)==0) {
            c /= gcd;
            a /= gcd;
            b /= gcd;

            int x = (c * modInverse(a, b))%b;
            int y = (c - a * x)/b;

            if(y>0){
                System.out.println("Yes");
            }
            else{
                int minK = (x / b) * -1;
                int maxK = ((y - a + 1) / a);
                if(minK <= maxK){
                    System.out.println("Yes");
                }
                else{
                    System.out.println("No");
                }
            }
        }
        else {
            System.out.println("No");
        }

        sc.close();
    }
}
