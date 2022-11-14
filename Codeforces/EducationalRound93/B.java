package Codeforces.EducationalRound93;

import java.util.*;

public class B {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            String s = sc.next();
            int l = s.length();
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i=0;i<l;i++)
            {
                int count = 0;
                while(s.charAt(i)=='1')
                {
                    count++;
                    i++;
                }

                if(count!=0)
                    arr.add(count);
            }

            Collections.sort(arr);
            int v=0;
            for(int i=0;i<arr.size();i+=2)
            {
                v+=arr.get(i);
            }
            System.out.println(v);
        }
    }
}
