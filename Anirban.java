
import java.util.*;

public class Anirban {

    static int minimizeMemory(List<Integer> process, int m){
        int n = process.size();
        long[] sum = new long[n];
        sum[0] = process.get(0);
        for(int i=1;i<n;i++){
            sum[i] = sum[i-1] + process.get(i);
        }

        long max = sum[m-1];
        for(int i=m;i<n;i++){
            max = Math.max(max, sum[i] - sum[i - m]);
        }

        return (int) (sum[n-1] - max);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Integer> arlist = new ArrayList<>();
        for(int i=0;i<n;i++){
            arlist.add(sc.nextInt());
        }
        int m = sc.nextInt();

        System.out.println(minimizeMemory(arlist, m));
    }
}
