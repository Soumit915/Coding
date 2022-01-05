
import java.util.*;

public class Anirban {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int t = sc.nextInt();

        int[] points = new int[n];
        int[] time = new int[n];
        for(int i=0;i<n;i++){
            points[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++){
            time[i] = sc.nextInt();
        }

        int[][] dp = new int[n+1][t+1];
        for(int i=0;i<=t;i++){
            dp[0][i] = 0;
        }
        for(int i=0;i<=n;i++){
            dp[i][0] = 0;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=t;j++){
                if(j-time[i-1]>=0){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-time[i-1]]+points[i-1]);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[n][t]);
    }
}
