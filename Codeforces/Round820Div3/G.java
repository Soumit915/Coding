package Codeforces.Round820Div3;

import java.io.*;

public class G {

    static long mod = (long) 1e9 + 7;

    static void update(long[][] dp, int i, int j, long val){
        int n = dp.length;

        i++;

        while(i <= n){
            dp[i-1][j] = (dp[i-1][j] + val) % mod;
            i += (i & (-i));
        }
    }

    static long sum(long[][] dp, int i, int j){
        long sum = 0;

        i+=1;

        if(j < 0)
            return 0;

        while(i > 0){
            sum = (sum + dp[i - 1][j]) % mod;
            i -= (i & (-i));
        }

        return sum;
    }

    static int getCountOps(boolean[] match, int[] last, int i, int tl){

        if(i < 0)
            return 0;

        int index = i;
        for(int j=i;j>=i-tl+1;j--){
            if(match[j])
                index = j;
        }

        int prev = (index - tl < 0)?-1:last[index - tl];
        return getCountOps(match, last, prev, tl) + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            String s = br.readLine();
            String t = br.readLine();

            int sl = s.length();
            int tl = t.length();

            int[] lps = new int[tl];
            lps[0] = -1;
            for(int i=1;i<tl;i++){
                int nextIndex = lps[i-1] + 1;
                if(t.charAt(nextIndex) == t.charAt(i)){
                    lps[i] = nextIndex;
                }
                else{
                    lps[i] = -1;
                }
            }

            int si = 0, ti = -1;
            boolean[] match = new boolean[sl];
            while(si < sl){
                if(s.charAt(si) == t.charAt(ti + 1)){
                    ti++;
                    si++;

                    if(ti == tl-1){
                        match[si - 1] = true;
                        ti = lps[ti];
                    }
                }
                else{
                    if(ti == -1){
                        si++;
                    }
                    else{
                        ti = lps[ti];
                    }
                }
            }

            int[] last = new int[sl];
            last[0] = match[0]?0:-1;
            for(int i=1;i<sl;i++){
                if(match[i]){
                    last[i] = i;
                }
                else{
                    last[i] = last[i-1];
                }
            }

            int len = getCountOps(match, last, last[sl - 1], tl);

            if(len == 0){
                sb.append("0 1\n");
                continue;
            }

            long[][] dp = new long[sl][len];
            for(int i=tl-1;i<sl;i++){
                for(int j=0;j<len;j++){
                    if(match[i]) {
                        if(i - tl < 0 || last[i - tl] == -1) {
                            update(dp, i, j, 1);
                        }
                        else {
                            int prev = last[i - tl];

                            long sum = (sum(dp, prev, j-1) - sum(dp, prev-tl, j-1) + mod) % mod;

                            update(dp, i, j, sum);
                        }
                    }
                }
            }

            long ans = (sum(dp, last[sl-1], len-1) - sum(dp, last[sl-1]-tl, len-1) + mod) % mod;

            sb.append(len).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);

        br.close();
    }

}
