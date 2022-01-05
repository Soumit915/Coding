import java.util.*;

public class EuclidGCD {
    static int x;
    static int y;
    public static int gcd(int a, int b)
    {
        if(a%b == 0) {
            x = 1;
            y = 1-(a/b);
            System.out.println(a+" "+b+" "+x+" "+y);
            return b;
        }
        int g = gcd(b,a%b);
        int t = y;
        y = x-(a/b)*y;
        x = t;
        System.out.println(a+" "+b+" "+(x%b+b)%b+" "+y);
        return g;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the 2 numbers whose GCD you want to find : ");
        int a = sc.nextInt();
        int b = sc.nextInt();

        int g = gcd(a,b);
        System.out.println("The gcd of the two numbers is : "+g);
    }
}
