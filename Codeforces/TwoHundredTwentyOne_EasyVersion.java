package Codeforces;

import java.io.*;
import java.util.*;

public class TwoHundredTwentyOne_EasyVersion {

    static int getSignedSumOfSegment(int[] signedSum, int l, int r){
        if(l == 0)
            return signedSum[r];
        else return signedSum[r] - signedSum[l-1];
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = sc.nextInt();
            int q = sc.nextInt();

            String s = sc.next();

            int[] signedSum = new int[n];
            signedSum[0] = s.charAt(0) == '+' ? 1 : -1;
            for (int i = 1; i < n; i++) {
                if (i % 2 == 0)
                    signedSum[i] = (s.charAt(i) == '+') ? signedSum[i - 1] + 1 : signedSum[i - 1] - 1;
                else signedSum[i] = (s.charAt(i) == '+') ? signedSum[i - 1] - 1 : signedSum[i - 1] + 1;
            }

            while (q-- > 0) {
                int l = sc.nextInt() - 1;
                int r = sc.nextInt() - 1;

                int v = getSignedSumOfSegment(signedSum, l, r);

                if (v == 0) {
                    sb.append("0\n");
                } else if (Math.abs(v) % 2 == 1) {
                    sb.append("1\n");
                } else {
                    sb.append("2\n");
                }
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
