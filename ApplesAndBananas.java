import java.io.*;
import java.util.*;

public class ApplesAndBananas {

    static int gcd(int a, int b){
        if(a%b==0){
            return b;
        }
        else return gcd(b, a%b);
    }

    static int x, y;
    static void gcdExtended(int a, int b, int mod){
        if(a%b==0){
            x = 1;
            y = 1 - (a/b);
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
        System.out.println(x);
        return x;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int apples_cost = sc.nextInt();
        int bananas_cost = sc.nextInt();
        int money_with_me = sc.nextInt();

        int gcd = gcd(apples_cost, bananas_cost);
        if(money_with_me%gcd==0){
            money_with_me /= gcd;
            apples_cost /= gcd;
            bananas_cost /= gcd;

            int apples_inv = (modInverse(apples_cost, bananas_cost) * money_with_me)%bananas_cost;
            int bananas_inv = (money_with_me - (apples_inv * apples_cost)) / bananas_cost;

            System.out.println(apples_inv+" "+bananas_inv);
        }
        else{
            System.out.println("No suitable answer");
        }
    }
}
