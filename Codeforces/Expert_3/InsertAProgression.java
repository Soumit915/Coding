package Codeforces.Expert_3;

import java.io.*;

public class InsertAProgression{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(tc-->0){
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int x = Integer.parseInt(line[1]);

            line = br.readLine().split(" ");
            long[] a = new long[n];
            for(int i=0;i<n;i++){
                a[i] = Integer.parseInt(line[i]);
            }

            if(n == 0){
                sb.append("0\n");
                continue;
            }

            long min = a[0], max = a[0];
            long sum = 0;
            for(int i=0;i<n;i++){
                min = Math.min(min, a[i]);
                max = Math.max(max, a[i]);
                if(i>0){
                    sum += Math.abs(a[i] - a[i-1]);
                }
            }

            long dmin = Math.min(a[0], a[n-1]);
            long dmax = Math.max(a[0], a[n-1]);


            sum = Math.min(sum + 2 * (min - 1) , sum + (dmin - 1));
            //System.out.println(sum);
            if(max < x)
                sum = Math.min(sum + 2 * Math.max(0, x - max) , sum + Math.max(0, x - dmax));

            sb.append(sum).append("\n");
        }

        System.out.println(sb);
    }
}