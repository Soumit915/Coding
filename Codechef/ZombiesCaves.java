package Codechef;

import java.util.*;

class ZombiesCaves {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t>0) {
            int n = sc.nextInt();
            //System.out.println(n);
            int[] rad = new int[n];
            int[] health = new int[n];
            int[] caves = new int[n];

            int i, start, end;
            for (i = 0; i < n; i++) {
                rad[i] = sc.nextInt();
            }
            for (i = 0; i < n; i++) {
                health[i] = sc.nextInt();
            }
            for (i = 0; i < n; i++) {
                caves[i] = 0;
            }

            for (i = 0; i < n; i++) {
                start = i - rad[i];
                end = i + rad[i];
                if (start < 0)
                    start = 0;
                if (end > n - 1)
                    end = n - 1;

                //System.out.println(start+" "+end);
                caves[start] += 1;
                try {
                    caves[end + 1] -= 1;
                } catch (Exception e) {
                    int err = 1;
                }
            }

            /*for (i = 0; i < n; i++) {
                System.out.print(caves[i]+" ");
            }
            System.out.println();*/
            int s = caves[0];
            for (i = 1; i < n; i++) {
                caves[i] += s;
                s = caves[i];
            }

            /*for (i = 0; i < n; i++) {
                System.out.print(caves[i]+" ");
            }
            System.out.println();*/

            Arrays.sort(caves);
            Arrays.sort(health);

            boolean flag=true;
            for (i = 0; i < n; i++)
            {
                if(caves[i] != health[i])
                {
                    flag = false;
                    break;
                }
            }

            /*System.out.println(n);

            for (i = 0; i < n; i++) {
                System.out.print(health[i]+" ");
            }
            System.out.println();*/

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
