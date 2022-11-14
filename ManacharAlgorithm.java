import java.util.*;

public class ManacharAlgorithm {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String s = sc.next();

        StringBuilder sb = new StringBuilder();
        sb.append('$');
        for(int i=0;i<s.length();i++)
        {
            sb.append(s.charAt(i));
            sb.append('$');
        }
        s = sb.toString();

        int l = s.length();
        int[] lps = new int[l];
        for(int i=0;i<l;i++)
        {
            int left = i-1;
            int right = i+1;
            int ci = 1;
            while(left>=0 && right<l && s.charAt(left)==s.charAt(right))
            {
                ci+=2;
                left--;
                right++;
            }
            lps[i] = ci;

            for(int j=i+1;j<=right;j++)
            {
                if(2*i-j<0) // when the left-image is not possible, i.e. the left-image is out of index
                {
                    i = j-1;
                    break;
                }
                if(lps[2*i-j]+j>=right) //when according to the left-image the current middle is
                    // exceeding the length of the string
                {
                    i = j-1;
                    break;
                }
                else
                {
                    lps[j] = lps[2*i-j];
                }
            }
        }

        int max = -1;
        for(int i: lps)
        {
            if(max<i)
                max = i;
        }

        String lpalin = "";
        for(int i=0;i<l;i++)
        {
            if(lps[i] == max)
            {
                int start = (max/2);
                int end = i+start;
                start = i-start;
                lpalin = s.substring(start,end+1);
                break;
            }
        }

        lpalin = lpalin.replace("$","");
        System.out.println("The length of the longest palindromic substring in the string is: "+max/2);
        System.out.println("The longest palindromic substring is: "+lpalin);

    }
}
