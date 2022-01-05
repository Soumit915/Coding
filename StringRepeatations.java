import java.util.*;

public class StringRepeatations {
    public static boolean isPresent(String s, String p, int[] lps)
    {
        int ind = -1;
        for(int i=0;i<s.length() && ind<p.length()-1;i++)
        {
            if(s.charAt(i)==p.charAt(ind+1))
            {
                ind++;
            }
            else if(ind!=-1)
            {
                ind = lps[ind];
            }
        }

        return p.length()-1==ind;
    }
    public static void computepitable(String p, int[] lps)
    {
        lps[0] = -1;
        int ind = -1;
        for(int i=1;i<p.length();i++)
        {
            if(p.charAt(i)==p.charAt(ind+1))
            {
                lps[i] = ind+1;
                ind++;
            }
            else
            {
                lps[i] = -1;
                ind = -1;
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String p = sc.next();

        int[] lps = new int[p.length()];
        computepitable(p, lps);

        if(p.length()<=s.length())
        {
            if(isPresent(s, p, lps)) System.out.println(1);
            else System.out.println(-1);
        }
        else
        {
            int ind = 0;
            boolean flag = true;
            int count = 1;
            for(int i=-1;i<p.length()-1;)
            {
                if(ind==s.length())
                {
                    if(i==-1) {
                        flag = false;
                        break;
                    }
                    ind = 0;
                    count++;
                }
                if(s.charAt(ind)!=p.charAt(i+1))
                {
                    if(count!=1)
                    {
                        flag = false;
                        break;
                    }
                    if(i!=-1) i = lps[i];
                }
                else i++;
                ind++;
            }

            if(flag)
                System.out.println(count);
            else System.out.println(-1);
        }
    }
}
