package GoogleFooBar;

public class E {
    public static int solution(int[] arr) {
        int[] pair_hash = new int[1001000];
        int[] count_hash = new int[1001000];
        int count = 0;
        for (int k : arr) {
            int lim = (int) (Math.sqrt(k));
            for (int j = 1; j <= lim; j++) {
                if (k % j == 0) {
                    count += pair_hash[j];
                    if (k / j != j) {
                        count += pair_hash[k / j];
                    }
                }
            }
            //System.out.println(i+" "+arr[i]+" "+count+" "+ Arrays.toString(count_hash)+" "+Arrays.toString(pair_hash));
            for (int j = 1; j <= lim; j++) {
                if (k % j == 0) {
                    pair_hash[k] += count_hash[j];
                    if (k / j != j) {
                        pair_hash[k] += count_hash[k / j];
                    }
                }
            }
            count_hash[k]++;
        }
        return count;
    }
    static boolean isValid(int a, int b, int c){
        return c%b==0 && b%a==0;
    }
    static int bruteForce(int[] arr){
        int n = arr.length;
        int c = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    if(isValid(arr[i], arr[j], arr[k])){
                        c++;
                    }
                }
            }
        }
        return c;
    }
    public static void main(String[] args) {
        int t = 1000;
        while (t-->0){
            int[] arr = new int[100];
            for(int i=0;i<arr.length;i++){
                arr[i] = (int) (Math.random()*20+1);
            }

            int a1 = solution(arr);
            int a2 = bruteForce(arr);
            if(a1!=a2){
                System.out.println("wrong answer");
                System.exit(0);
            }
        }
        System.out.println("AC");
    }
}
