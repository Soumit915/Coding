package Hackerrank.Algorithms.Sorting;

import java.util.*;
import java.io.*;

public class TheFullCountingSort {

    public static void main(String[] args) throws IOException
    {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(sc.readLine());

        StringBuffer[] hash = new StringBuffer[100];
        for(int i=0;i<100;i++)
        {
            hash[i] = new StringBuffer();
        }
        for(int i=0;i<n;i++)
        {
            StringTokenizer st = new StringTokenizer(sc.readLine(), " ");
            int xi = Integer.parseInt(st.nextToken());
            String si = st.nextToken();
            hash[xi].append(i < (n/ 2) ? "-" : si).append(" ");
        }

        for(int i=0;i<100;i++)
        {
            System.out.print(hash[i]);
        }
        System.out.println();
    }
}
