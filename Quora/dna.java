//package Quora;

import java.util.*;

class dna {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();

        int max = 0;
        int k = 0;
        for(int i=0;i<n;i++){
            int lptr = i, rptr = i+1;
            int count = 0;
            while(lptr >=0 && rptr < n){
                if((s.charAt(lptr)=='A' && s.charAt(rptr)=='T') || (s.charAt(lptr)=='T' && s.charAt(rptr)=='A') ||
                        (s.charAt(lptr)=='G' && s.charAt(rptr)=='C') || (s.charAt(lptr)=='C' && s.charAt(rptr)=='G')){
                    count++;
                }

                lptr--;
                rptr++;
            }

            if(count > max){
                max = count;
                k = i;
            }
        }

        System.out.println((k+1)+" "+max);
    }
}
