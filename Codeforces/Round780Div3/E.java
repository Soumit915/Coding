package Codeforces.Round780Div3;

import java.io.*;
import java.util.*;

public class E {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();

            char[][] mat = new char[n][n];
            for(int i=0;i<n;i++){
                mat[i] = sc.next().toCharArray();
            }

            int[] arr = new int[n];
            int sum = 0;
            for(int i=0;i<n;i++){
                int ones = 0;
                for(int j=0;j<n;j++){
                    char ch = mat[j][(i+j)%n];

                    if(ch == '1'){
                        ones++;
                    }
                }

                arr[i] = ones;
                sum += arr[i];
            }

            int min = Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                int cur = sum - arr[i];
                cur += (n - arr[i]);
                min = Math.min(min, cur);
            }

            sb.append(min).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
