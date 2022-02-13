package Codeforces;

import java.io.*;
import java.util.*;

public class DreamoonAndWifi {
    public static String pad(String s, int n){
        return "0".repeat(Math.max(0, n - s.length())) +
                s;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String act = sc.next();
        String got = sc.next();

        int not_recognized = 0;
        for(int i=0;i<got.length();i++){
            if(got.charAt(i)=='?')
                not_recognized++;
        }

        int dest = 0;
        for(int i=0;i<act.length();i++){
            if(act.charAt(i)=='+')
                dest++;
            else dest--;
        }

        int lim = (int) (Math.pow(2, not_recognized));
        double success = 0;
        for(int i=0;i<lim;i++){
            String bin = Integer.toBinaryString(i);
            bin = pad(bin, not_recognized);

            int ind = 0;
            int finalpos = 0;
            for(int j=0;j<got.length();j++){
                if(got.charAt(j)=='+')
                    finalpos++;
                else if(got.charAt(j)=='-')
                    finalpos--;
                else {
                    if(bin.charAt(ind)=='1'){
                        finalpos++;
                    }
                    else{
                        finalpos--;
                    }
                    ind++;
                }
            }

            if(finalpos==dest){
                success++;
            }
        }

        success = success / Math.pow(2, not_recognized);
        System.out.println(success);
    }
}
