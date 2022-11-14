package GoogleFooBar;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

public class D {
    static String getBinary(String s){
        StringBuilder sb = new StringBuilder();
        BigInteger n = new BigInteger(s);
        while(!n.subtract(new BigInteger("1")).equals(new BigInteger("0"))){
            if(n.mod(new BigInteger("2")).equals(new BigInteger("1"))){
                sb.append("1");
            }
            else {
                sb.append("0");
            }
            n = n.divide(new BigInteger("2"));
        }
        sb.append("10");

        return sb.toString();
    }
    static int solution(String s){
        char[] bin = getBinary(s).toCharArray();
        int n = bin.length;
        int c = 0;
        int count1 = 0;
        if(bin[0]=='1')
            count1 = 1;
        for(int i=1;i<n;i++){
            if(bin[i]=='0'){
                if(bin[i-1]=='1'){
                    if(i==n-1 && count1==2){
                        return bin.length - 3 + c + 2;
                    }
                    if((i==n-1 && count1>1) || (i<n-1)){
                        c++;
                        //System.out.println(i);
                        if(count1!=1){
                            bin[i] = '1';
                            count1 = 1;
                        }
                        else{
                            count1 = 0;
                        }
                    }
                }
            }
            else{
                count1++;
            }
        }
        System.out.println(c+" "+ Arrays.toString(bin));
        if(bin[n-1]=='1'){
            return c + n - 1;
        }
        else{
            return c + n - 2;
        }
    }
    static HashMap<Integer, Integer> map = new HashMap<>();
    static int bruteforce(int n){
        if(n==1)
            return 0;
        if(map.containsKey(n))
            return map.get(n);
        int min = 100000000;
        if(n%2==0){
            for(int i=0;i<n;i+=2){
                min = Math.min(min, bruteforce((n+i)/2) + i/2+1);
            }
        }
        else{
            for(int i=1;i<n;i+=2){
                min = Math.min(min, bruteforce((n+i)/2) + (i+1)/2+1);
            }
        }
        min = Math.min(min, bruteforce(n-1) + 1);
        map.put(n, min);
        return min;
    }
    public static void main(String[] args) {
        for(long l=2;l<20;l++){
            //l = Long.parseLong("11101", 2);
            String s = Long.toString(l);
            int ans1 = solution(s);
            int ans2 = bruteforce((int) l);
            if(ans1!=ans2){
                System.out.println("Wrong answer in "+l);
                System.out.println(Long.toBinaryString(l));
                System.out.println(ans1+" "+ans2);
                //System.exit(0);
            }
            //System.out.println(l);
        }
        //System.out.println(solution("3"));
    }
}
