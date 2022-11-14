package Codechef;

import java.io.*;
import java.util.*;

public class BinaryStringOnSteroids {
    static ArrayList<Integer> getFactors(int n){
        ArrayList<Integer> arlist = new ArrayList<>();
        for(int i=1;i*i<=n;i++){
            if(n%i==0){
                int v = n/i;
                if(v==i){
                    arlist.add(i);
                }
                else{
                    arlist.add(i);
                    arlist.add(v);
                }
            }
        }
        return arlist;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String s = sc.next();

            ArrayList<Integer> factors = getFactors(n);
            int min = Integer.MAX_VALUE;
            for(int ones: factors){
                int gap = n/ones;
                int[] rangeAdd = new int[gap];
                int[] add = new int[gap];
                for(int i=0;i<n;i++){
                    int index_tobeone = i%gap;
                    if(s.charAt(i)=='1'){
                        if (index_tobeone != 0) {
                            rangeAdd[0]++;
                            rangeAdd[index_tobeone]--;
                        }
                        if(index_tobeone+1<gap)
                            rangeAdd[index_tobeone+1]++;
                    }
                    else{
                        add[index_tobeone]++;
                    }
                }

                for(int i=1;i<gap;i++){
                    rangeAdd[i] += rangeAdd[i-1];
                }
                for(int i=0;i<gap;i++){
                    add[i] += rangeAdd[i];
                }

                for(int i=0;i<gap;i++){
                    min = Math.min(min, add[i]);
                }
            }

            sb.append(min).append("\n");
        }

        System.out.println(sb);
    }
}
