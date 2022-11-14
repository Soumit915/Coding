package Codechef;

import java.io.*;
import java.util.*;

public class BinaryStringMEX {
    static int[][] dp;
    static void preCompute(String s){
        int l = s.length();
        dp = new int[2][l];
        for(int i=0;i<dp.length;i++){
            int last = -1;
            dp[i][l-1] = last;
            if(s.charAt(l-1)-48==i){
                last = l-1;
            }
            for(int j=l-2;j>=0;j--){
                dp[i][j] = last;
                if(s.charAt(j)-48==i){
                    last = j;
                }
            }
        }
    }
    static boolean checkAll0(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='1')
                return false;
        }
        return true;
    }
    static boolean checkAll1(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='0')
                return false;
        }
        return true;
    }
    static String create(ArrayList<Integer> li0, ArrayList<Integer> li1){
        StringBuilder sb = new StringBuilder();
        if(li0.size()>li1.size()){
            li1.add(-1);
            sb.append(0);

            int last = li0.get(li0.size()-1);
            for(int i=li0.size()-2;i>=0;i--){
                sb.append(last);

                if(last==0){
                    last = li0.get(i);
                }
                else{
                    last = li1.get(i);
                }
            }
            sb.append(last);
        }
        else{
            sb.append(1);

            int last = li1.get(li0.size()-1);
            for(int i=li0.size()-2;i>=0;i--){
                sb.append(last);

                if(last==0){
                    last = li0.get(i);
                }
                else{
                    last = li1.get(i);
                }
            }
            sb.append(last);
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            String s = sc.next();
            int n = s.length();

            preCompute(s);
            int[] dp0 = new int[n];
            ArrayList<Integer> prev0 = new ArrayList<>();
            int[] dp1 = new int[n];
            ArrayList<Integer> prev1 = new ArrayList<>();

            if(checkAll0(s)){
                sb.append("1\n");
            }
            else if(checkAll1(s)){
                sb.append("0\n");
            }
            else{
                dp0[0] = -1;
                if(s.charAt(0)=='1'){
                    dp1[0] = 0;
                }
                else{
                    dp1[0] = dp[1][0];
                }

                if(dp[0][dp1[0]]==-1){
                    sb.append("10\n");
                    continue;
                }
                else{
                    dp0[1] = dp[0][dp1[0]];
                    prev0.add(1);
                }

                if(dp[1][dp1[0]]==-1){
                    sb.append("11\n");
                    continue;
                }
                else{
                    dp1[1] = dp[1][dp1[0]];
                    prev1.add(1);
                }

                String ans = "";
                for(int i=2;i<n;i++){
                    int p1 = dp[0][dp0[i-1]];
                    int p2 = dp[0][dp1[i-1]];
                    if(p1==-1){
                        prev0.add(0);
                        ans = create(prev0, prev1);
                        break;
                    }
                    else if(p2==-1){
                        prev0.add(1);
                        ans = create(prev0, prev1);
                        break;
                    }
                    else{
                        if(p1>=p2){
                            dp0[i] = p1;
                            prev0.add(0);
                        }
                        else{
                            dp0[i] = p2;
                            prev0.add(1);
                        }
                    }

                    p1 = dp[1][dp0[i-1]];
                    p2 = dp[1][dp1[i-1]];
                    if(p1==-1){
                        prev1.add(0);
                        ans = create(prev0, prev1);
                        break;
                    }
                    else if(p2==-1){
                        prev1.add(1);
                        ans = create(prev0, prev1);
                        break;
                    }
                    else{
                        if(p1>=p2){
                            dp1[i] = p1;
                            prev1.add(0);
                        }
                        else{
                            dp1[i] = p2;
                            prev1.add(1);
                        }
                    }
                }
                sb.append(ans).append("\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
