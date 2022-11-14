package Codeforces;

import java.io.*;
import java.util.*;

public class BarCode {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        char[][] mat = new char[n][m];
        for(int i=0;i<n;i++){
            mat[i] = sc.next().toCharArray();
        }

        long[] black = new long[m];
        long[] white = new long[m];
        for(int i=0;i<m;i++){
            int blackcount = 0;
            for(int j=0;j<n;j++){
                if(mat[j][i]=='#')
                    blackcount++;
            }

            black[i] = n - blackcount;
            white[i] = blackcount;
        }

        long[] blackPrefixSum = new long[m];
        long[] whitePrefixSum = new long[m];
        blackPrefixSum[0] = black[0];
        whitePrefixSum[0] = white[0];
        for(int i=1;i<m;i++){
            blackPrefixSum[i] = blackPrefixSum[i-1] + black[i];
            whitePrefixSum[i] = whitePrefixSum[i-1] + white[i];
        }

        long[] blackDp = new long[m];
        long[] whiteDp = new long[m];
        for(int i=0;i<x-1;i++){
            blackDp[i] = 10000000;
            whiteDp[i] = 10000000;
        }
        blackDp[x-1] = blackPrefixSum[x-1];
        whiteDp[x-1] = whitePrefixSum[x-1];

        for(int i=x;i<m;i++){
            long cur_black_sum = blackPrefixSum[i] - blackPrefixSum[i-x];
            long cur_white_sum = whitePrefixSum[i] - whitePrefixSum[i-x];

            long min_black_sum = cur_black_sum + whiteDp[i-x];
            long min_white_sum = cur_white_sum + blackDp[i-x];
            for(int j=i-x;j>Math.max(i-y, -1);j--){
                cur_black_sum += black[j];
                cur_white_sum += white[j];

                if(j!=0){
                    min_black_sum = Math.min(min_black_sum, cur_black_sum + whiteDp[j-1]);
                    min_white_sum = Math.min(min_white_sum, cur_white_sum + blackDp[j-1]);
                }
                else{
                    min_black_sum = Math.min(min_black_sum, cur_black_sum);
                    min_white_sum = Math.min(min_white_sum, cur_white_sum);
                }
            }

            blackDp[i] = min_black_sum;
            whiteDp[i] = min_white_sum;
        }

        System.out.println(Math.min(blackDp[m-1], whiteDp[m-1]));

        sc.close();
    }
}
