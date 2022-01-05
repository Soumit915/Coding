package Codechef;

import java.util.*;

class Break {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int sbt = sc.nextInt();

        while(t>0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];

            int i;
            for (i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (i = 0; i < n; i++) {
                b[i] = sc.nextInt();
            }

            Arrays.sort(a);
            Arrays.sort(b);

            Map<Integer,Boolean> hash = new HashMap<>();

            int turnattk,turndef;
            boolean flag=true;
            hash.put(a[0],true);
            if(b[0]<=a[0])
            {
                flag = false;
            }
            else
            {
                //System.out.println("Test");
                hash.put(b[0],true);
            }

            for(i=1;i<n && flag;i++)
            {
                turnattk = a[i];
                turndef = b[i];

                if(hash.containsKey(turnattk))
                {
                    if(turnattk<turndef)
                    {
                        hash.put(turndef,true);
                    }
                }
                else
                {
                    flag = false;
                    break;
                }
            }

            if(flag)
            {
                System.out.println("YES");
            }
            else
            {
                System.out.println("NO");
            }
            t--;
        }
    }
}
