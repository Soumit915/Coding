package FileIO;

import java.io.*;
import java.util.*;
public class CodeGladiators {
    public static void answer()
    {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();

        final long[] a = new long[n];
        for(int i=0;i<n;i++)
        {
            a[i] = sc.nextLong();
        }

        long min=Long.MAX_VALUE;
        long val;
        for(int i=0;i<n;i++)
        {
            val = sc.nextInt();
            final long k = val/a[i];
            min = Math.min(min, k);
        }

        System.out.println(min);
    }
    public static void main(final String[] args) throws IOException {
        final Scanner sc = new Scanner(System.in);
        final FileReader fr = new FileReader("Output.txt");
        final BufferedReader br = new BufferedReader(fr);

        String a;
        final String n = br.readLine();
        final int n1 = Integer.parseInt(n);
        while((a = br.readLine()) != null)
        {
            System.out.println(a);
        }

        br.close();
        fr.close();
    }
}
