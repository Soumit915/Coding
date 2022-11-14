package Codeforces;

import java.util.*;
import java.io.*;

public class Select3Sticks {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Input.txt")));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Output.txt")));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(tc-->0){
            int n = Integer.parseInt(br.readLine());

            String[] inputline = (br.readLine()).split(" ");
            int[] a = new int[n];
            for(int i=0;i<n;i++){
                a[i] = Integer.parseInt(inputline[i]);
            }

            Arrays.sort(a);
            long min = (long) 1e12;
            for(int i=1;i<n-1;i++){
                long cur = Math.max(0, a[i]);
                min = Math.min(min, Math.abs(cur-a[i-1]) + Math.abs(cur-a[i+1]));
            }

            sb.append(min).append("\n");
        }

        pw.println(sb);

        br.close();
        pw.close();
    }
}
