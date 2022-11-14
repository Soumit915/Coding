package Codeforces.CEOI2020_Mirror;

import java.util.*;

public class B {
    static class Line
    {
        long x1;
        long y1;
        long x2;
        long y2;
        Line(long x1, long y1, long x2, long y2)
        {
            if(x1>x2)
            {
                this.x1 = x2;
                this.y1 = y2;
                this.x2 = x1;
                this.y2 = y1;
            }
            else
            {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


    }
}
