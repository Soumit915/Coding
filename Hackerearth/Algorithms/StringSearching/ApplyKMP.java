package Hackerearth.Algorithms.StringSearching;

import java.util.*;

public class ApplyKMP {
    public static void preprocess(int[] pi, String p)
    {
        int pl = p.length();
        int init = -1;
        pi[0] = -1;
        for(int i=1;i<pl;i++)
        {
            init++;
            if(p.charAt(init)==p.charAt(i))
                pi[i] = init;
            else
            {
                pi[i] = -1;
                init = 0;
            }
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String p = sc.nextLine();
        int pl = p.length();

        String s = sc.nextLine();
        int sl = s.length();

        int[] pi = new int[pl];
        preprocess(pi, p);

        int ind = -1;
        int count = 0;
        //System.out.println(pi[0]+" "+pi[1]);
        for(int i=0;i<sl;i++)
        {
            char chp = p.charAt(ind+1);
            char chs = s.charAt(i);
            if(chp == chs && ind == pl-2)
            {
                count++;
                ind = pi[ind+1];
            }
            else if(chp != chs && ind>-1)
            {
                ind = pi[ind];
                i--;
            }
            else if(chp == chs)
                ind++;
        }

        System.out.println(count);
    }
}
