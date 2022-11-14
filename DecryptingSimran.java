public class DecryptingSimran {

    static long reverse(long n){
        long p = 0;
        while(n > 0){
            p = p * 10 + (n%10);
            n /= 10;
        }

        return p;
    }

    static String reverse(String s){
        StringBuilder sb = new StringBuilder(s);

        return sb.reverse().toString();

    }

    public static boolean isOK(long n){
        long rev_n = reverse(n);

        String bin = Long.toBinaryString(n);
        String bin_rev = reverse(bin);

        return rev_n == n && bin.equals(bin_rev);
    }

    public static void main(String[] args) {

        long n = 1000000;
        long sum = 0;
        for(long i=0;i<n;i++){
            if(isOK(i)){
                System.out.println(i);
                sum += i;
            }
        }

        System.out.println(sum);

    }

}
