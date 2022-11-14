package Codeforces.RateUp_Contest_2;

import java.io.*;
import java.util.*;

public class B {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String[] words = new String[n];
            for(int i=0;i<n;i++){
                words[i] = sc.next();
            }

            int max = 0;
            for(char ch='a';ch<='e';ch++){
                int[] diff = new int[n];
                for(int i=0;i<n;i++){
                    for(int j=0;j<words[i].length();j++){
                        if(words[i].charAt(j) == ch){
                            diff[i]++;
                        }
                        else{
                            diff[i]--;
                        }
                    }
                }

                Arrays.sort(diff);
                int count = 0;
                int c = 0;
                for(int i=n-1;i>=0;i--){
                    if(count + diff[i] <= 0)
                        break;
                    count += diff[i];
                    c++;
                }

                max = Math.max(max, c);
            }

            sb.append(max).append("\n");
        }

        System.out.println(sb);
    }
}
