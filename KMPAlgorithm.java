import java.util.*;

public class KMPAlgorithm {
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
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your string : ");
        String s = sc.nextLine();
        int sl = s.length();

        System.out.println("Enter the pattern that you want to find in the string : ");
        String p = sc.nextLine();
        int pl = p.length();

        int[] pi = new int[pl];
        preprocess(pi, p);

        int ind = -1;
        ArrayList<Integer> indices = new ArrayList<>();
        for(int i=0;i<sl;i++)
        {
            char chp = p.charAt(ind+1);
            char chs = s.charAt(i);
            if(chp == chs && ind == pl-2)
            {
                indices.add(i-pl+2);
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

        System.out.println("The indices where the pattern was found are : "+ indices);
    }
}
