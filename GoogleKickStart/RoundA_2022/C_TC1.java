package GoogleKickStart.RoundA_2022;

import java.io.*;
import java.util.*;

public class C_TC1 {

    static int getLargestPalindrome(String s){

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

        return max / 2;
    }

    static boolean isSetBit(int n, int i){
        return ((1<<i) & n) != 0;
    }

    static String toString(char[] ch) {
        StringBuilder sb = new StringBuilder();
        for(char c: ch){
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++){
            sb.append("Case #").append(testi).append(": ");

            int n = sc.nextInt();
            String s = sc.next();

            int count = 0;
            for(int i=0;i<n;i++){
                if(s.charAt(i) == '?'){
                    count++;
                }
            }

            int lim = (1 << count);
            boolean flag = false;
            for(int i=0;i<lim;i++){
                char[] ch = s.toCharArray();

                int k = 0;
                for(int j=0;j<n;j++){
                    if(ch[j] == '?'){

                        if(isSetBit(i, k)){
                            ch[j] = '1';
                        }
                        else{
                            ch[j] = '0';
                        }

                        k++;
                    }
                }

                if(getLargestPalindrome(toString(ch)) < 5){
                    flag = true;
                    break;
                }
            }

            if(flag){
                sb.append("POSSIBLE\n");
            }
            else{
                sb.append("IMPOSSIBLE\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
