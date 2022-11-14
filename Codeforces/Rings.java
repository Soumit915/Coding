package Codeforces;

import java.io.*;
import java.util.*;

public class Rings {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String s = sc.next();

            int ind = -1;
            for(int i=n-1;i>=0;i--){
                if(s.charAt(i)=='0'){
                    ind = i;
                    break;
                }
            }

            StringBuilder localsb = new StringBuilder();
            if(ind==-1){
                localsb.append("1 ").append(n-1).append(" 2 ").append(n).append("\n");
            }
            else{
                if(ind >= (n/2)){
                    localsb.append("1 ").append(ind+1).append(" 1 ").append(ind).append("\n");
                }
                else{
                    localsb.append(ind + 2).append(" ").append(n).append(" ")
                            .append(ind + 1).append(" ").append(n).append("\n");
                }
            }

            sb.append(localsb);
        }

        System.out.println(sb);

        sc.close();
    }
}
