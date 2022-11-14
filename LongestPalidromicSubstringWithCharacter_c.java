import java.io.*;
import java.util.Scanner;

public class LongestPalidromicSubstringWithCharacter_c {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-->0)
        {
            char ch = sc.next().charAt(0);
            String s = sc.next();
            int l = s.length();

            StringBuilder sb = new StringBuilder();
            for(int i=0;i<l;i++)
            {
                sb.append("$");
                sb.append(s.charAt(i));
            }
            sb.append("$");
            s = sb.toString();
            l = s.length();

            int[] lps = new int[l];
            boolean[] isPresent = new boolean[l];
            for(int i=0;i<l;i++)
            {
                int ci = Math.max(1, lps[i]);
                int left = i-1;
                int right = i+1;
                if(lps[i]!=0) {
                    left = (int) (i - Math.ceil(((double) lps[i]) / 2));
                    right = (int) (i + Math.ceil(((double) lps[i]) / 2));
                }
                boolean flag = (s.charAt(i)==ch | isPresent[i]);
                while(left>=0 && right<l && s.charAt(left)==s.charAt(right))
                {
                    flag = (flag | s.charAt(left)==ch);
                    ci+=2;
                    left--;
                    right++;
                }
                lps[i] = ci;
                isPresent[i] = flag;
                left++;
                right--;

                //current palindrome expands till the end of the input
                if(right==l-1)
                    break;
                for(int j=i+1;j<=right;j++)
                {
                    //totally contained under current palindrome
                    if(lps[2*i-j]/2+j<right)
                    {
                        isPresent[j] = isPresent[2*i-j];
                        lps[j] = lps[2*i-j];
                    }
                    //mirror is a perfect prefix of the palidrome
                    else if(lps[2*i-j]/2+j==right && 2*i-j-lps[2*i-j]/2==left)
                    {
                        isPresent[j] = isPresent[2*i-j];
                        lps[j] = lps[2*i-j];
                        i = j-1;
                        break;
                    }
                }
            }

            int max = -1;
            for(int i=0;i<l;i++)
            {
                if(isPresent[i])
                    max = Math.max(max, lps[i]);
            }

            System.out.println(max/2);
        }

        sc.close();
    }

}
