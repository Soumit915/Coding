package GoogleKickStart.RoundA_2016;

import java.util.*;

public class CountryLeader
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int testi=1;testi<=t;testi++)
        {
            int n = sc.nextInt();
            String[] arr = new String[n];

            sc.nextLine();
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextLine();
            }

            Arrays.sort(arr);

            int max = Integer.MIN_VALUE;
            int ind=-1;
            for(int i=0;i<n;i++)
            {
                int[] hash = new int[26];
                for(int j=0;j<arr[i].length();j++)
                {
                    if(arr[i].charAt(j)!=' ')
                        hash[arr[i].charAt(j)-65] = 1;
                }

                int count=0;
                for(int j=0;j<26;j++)
                {
                    if(hash[j]==1)
                    {
                        count++;
                    }
                }

                if(count>max)
                {
                    max = count;
                    ind = i;
                }
            }

            System.out.println("Case #"+testi+": "+arr[ind]);
        }
    }
}