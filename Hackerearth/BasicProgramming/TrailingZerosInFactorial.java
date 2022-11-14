package Hackerearth.BasicProgramming;

import java.util.*;

public class TrailingZerosInFactorial {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-->0)
        {
            int n = sc.nextInt();

            int i = 1;
            int count = 0;
            while(count<n)
            {
                i *= 5;
                count = count*5 + 1;
            }

            //System.out.println("SSS: "+count+" "+i);
            if(count == n)
            {
                System.out.println(5);
                System.out.println(i+" "+(i+1)+" "+(i+2)+" "+(i+3)+" "+(i+4));
                continue;
            }

            int a = (count-1)/5;
            int f = i/5;
            count = 0;
            i = 0;
            do
            {
                //System.out.println("test: "+count+" "+i+" "+f+" "+a);
                if(n==count)
                {
                    break;
                }

                if(n<=a+count)
                {
                    if(n == a+count) {
                        count += a;
                        i += f;
                        break;
                    }
                }
                else if(n<=2*a+count)
                {
                    if(n == 2*a+count) {
                        count += 2*a;
                        i += 2*f;
                        break;
                    }
                    count = count+a;
                    i = i+f;
                }
                else if(n<=3*a+count)
                {
                    if(n == 3*a+count) {
                        count += 3*a;
                        i += 3*f;
                        break;
                    }
                    count = count+2*a;
                    i = i+2*f;
                }
                else if(n<=4*a+count)
                {
                    if(n == 4*a+count)
                    {
                        count += 4*a;
                        i += 4*f;
                        break;
                    }
                    count = count+3*a;
                    i = i+3*f;
                }
                else
                {
                    if(n == 5*a+count)
                    {
                        count = -1;
                        break;
                    }
                    count = count+4*a;
                    i = i+4*f;
                }

                a--;
                a /= 5;
                f /= 5;
            }while (count>0);

            if(n != count)
            {
                System.out.println(0);
            }
            else
            {
                System.out.println(5);
                System.out.println(i+" "+(i+1)+" "+(i+2)+" "+(i+3)+" "+(i+4));
            }
        }
    }
}
