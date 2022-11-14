package Codeforces.ITMO_PilotCourse.BinarySearch.Step2;

import java.util.*;

public class E {
    public static boolean isValid(double val, double c)
    {
        double x2 = Math.pow(val, 2);
        double xsqrt = Math.sqrt(val);

        return x2 + xsqrt >= c;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        double c = sc.nextDouble();

        double ll = 0;
        double ul = 2e18;
        double factor = 0.000000001;
        while(ul-ll>factor)
        {
            double mid = ll + (ul-ll)/2;
            if(isValid(mid, c))
            {
                ul = mid;
            }
            else
            {
                ll = mid+factor;
            }
            //System.out.println(ll+" "+ul+" "+mid);
        }

        System.out.println(ll);
    }
}
