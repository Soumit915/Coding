package Codeforces;

import java.io.*;
import java.util.*;

public class TwoHundredTwentyOne_HardVersion {

    static int getSignedSumOfSegment(int[] signedSum, int l, int r){
        if(l == 0)
            return signedSum[r];
        else return signedSum[r] - signedSum[l-1];
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int q = sc.nextInt();

            String s = sc.next();

            int[] signedSum = new int[n];
            signedSum[0] = (s.charAt(0) == '+')? 1 : -1;
            for(int i=1;i<n;i++){
                if(i%2==0)
                    signedSum[i] = (s.charAt(i)=='+')? signedSum[i-1] + 1 : signedSum[i-1] - 1;
                else signedSum[i] = (s.charAt(i)=='+')? signedSum[i-1] - 1 : signedSum[i-1] + 1;
            }

            Map<Integer, TreeSet<Integer>> increase = new HashMap<>();
            Map<Integer, TreeSet<Integer>> decrease = new HashMap<>();

            int last = 0;
            for(int i=0;i<n;i++){
                if(signedSum[i] > last){
                    TreeSet<Integer> set = increase.getOrDefault(signedSum[i], new TreeSet<>());
                    set.add(i);
                    increase.put(signedSum[i], set);
                }
                else{
                    TreeSet<Integer> set = decrease.getOrDefault(signedSum[i], new TreeSet<>());
                    set.add(i);
                    decrease.put(signedSum[i], set);
                }

                last = signedSum[i];
            }

            while(q-->0){
                int l = sc.nextInt()-1;
                int r = sc.nextInt()-1;

                int v = getSignedSumOfSegment(signedSum, l, r);

                if(v == 0){
                    sb.append("0\n");
                }
                else if(Math.abs(v)%2 == 1){
                    sb.append("1\n");

                    if(r == l){
                        sb.append(l+1).append("\n");
                    }
                    else{
                        int tobeRemoved_inc = (l==0?0:signedSum[l-1]) + ((v + 1) / 2);
                        int tobeRemoved_dec = (l==0?0:signedSum[l-1]) + ((v - 1) / 2);

                        TreeSet<Integer> set = increase.getOrDefault(tobeRemoved_inc, new TreeSet<>());
                        if (set.ceiling(l) == null || set.ceiling(l) > r) {
                            set = decrease.getOrDefault(tobeRemoved_dec, new TreeSet<>());
                        }
                        sb.append(set.ceiling(l)+1).append("\n");
                    }
                }
                else{
                    sb.append("2\n");

                    if(r == l+1){
                        sb.append(l+1).append(" ").append(r+1).append("\n");
                    }
                    else{
                        v = getSignedSumOfSegment(signedSum, l, r-1);

                        int tobeRemoved_inc = (l==0?0:signedSum[l-1]) + ((v + 1) / 2);
                        int tobeRemoved_dec = (l==0?0:signedSum[l-1]) + ((v - 1) / 2);

                        TreeSet<Integer> set = increase.getOrDefault(tobeRemoved_inc, new TreeSet<>());
                        if (set.ceiling(l) == null || set.ceiling(l) >= r) {
                            set = decrease.getOrDefault(tobeRemoved_dec, new TreeSet<>());
                        }
                        sb.append(set.ceiling(l)+1).append(" ").append(r+1).append("\n");
                    }
                }
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
