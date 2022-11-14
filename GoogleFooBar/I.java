package GoogleFooBar;

import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.math.BigDecimal;

public class I {

    static BigInteger zero;
    static BigInteger one;
    static BigInteger two;
    static BigDecimal root2;

    static void populateStaticVariables(){
        zero = BigInteger.ZERO;
        one = BigInteger.ONE;
        two = BigInteger.valueOf(2);
        root2 = new BigDecimal("1.4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727350138462309122970249248360558507372126441214971");
        root2 = root2.subtract(BigDecimal.ONE);
    }

    static BigInteger getAns(BigInteger n) {

        if (n.equals(zero)) {
            return zero;
        }
        else {
            BigInteger n_root2 = root2.multiply(new BigDecimal(n)).toBigInteger();
            BigInteger nsquare_root2 = n.multiply(n_root2);
            BigInteger sum_of_first_n_natural_nos = n.multiply(n.add(one)).divide(two);
            BigInteger rayleigh = n_root2.multiply(n_root2.add(one)).divide(two);

            return nsquare_root2.add(sum_of_first_n_natural_nos).subtract(rayleigh).subtract(getAns(n_root2));
        }
    }

    public static String solution(String s) {

        populateStaticVariables();

        return getAns(new BigInteger(s)).toString();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        String s = sc.next();
        System.out.println(solution(s));

        sc.close();
    }
}
