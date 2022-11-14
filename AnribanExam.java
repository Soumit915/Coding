
import java.io.*;
import java.util.*;

public class AnribanExam {
    static int solution(int[] A){
        int n = A.length;
        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = A[i];
        }

        Map<Long, ArrayList<Integer>> map = new HashMap<>();
        for(int i=0;i<n-1;i++){
            long key = arr[i] + arr[i + 1];
            ArrayList<Integer> arlist = map.getOrDefault(key, new ArrayList<>());
            arlist.add(i);
            map.put(key, arlist);
        }

        int max = -1;
        for(long i: map.keySet()){
            ArrayList<Integer> arlist = map.get(i);

            int[] dp = new int[arlist.size()];
            dp[0] = 1;
            for(int j=1;j<arlist.size();j++){
                if(arlist.get(j)-1==arlist.get(j-1)){
                    if(j==1){
                        dp[j] = 1;
                    }
                    else{
                        dp[j] = Math.max(dp[j-2] + 1, dp[j-1]);
                    }
                }
                else{
                    if(j==1){
                        dp[j] = dp[j-1] + 1;
                    }
                    else{
                        dp[j] = Math.max(dp[j-1] + 1, dp[j-2]);
                    }
                }
            }

            max = Math.max(max, dp[arlist.size()-1]);
        }

        return max;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        System.out.println(solution(arr));
    }
}
