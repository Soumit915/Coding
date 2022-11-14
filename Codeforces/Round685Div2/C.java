package Codeforces.Round685Div2;

import java.util.*;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();

            String a = sc.next();
            String b = sc.next();

            int[] hash = new int[26];
            for(int i=0;i<n;i++)
            {
                hash[a.charAt(i)-97]++;
            }

            for(int i=0;i<n;i++)
            {
                hash[b.charAt(i)-97]--;
            }

            boolean gflag = true;
            for(int i=0;i<hash.length;i++)
            {


                if(hash[i]%k!=0)
                    gflag = false;
            }

            boolean dflag = true;
            int count = 0;
            for(int i=0;i<hash.length;i++) {
                if (hash[i] > 0)
                    count+=(hash[i]/k);
                else if (hash[i] < 0) count+=(hash[i]/k);

                if(count<0)
                    dflag = false;
                //System.out.println(count+" "+hash[i]);
            }

            boolean flag = true;

            //System.out.println(dflag+" "+gflag);

            if(flag && dflag && gflag)
                sb.append("Yes\n");
            else sb.append("No\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
