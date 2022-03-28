package Codechef;

import java.io.*;
import java.util.*;

public class C {

    static boolean isBiPolar(String s){
        int n = s.length();
        Set<Character> set = new HashSet<>();
        for(int i=0;i<n;i++){
            set.add(s.charAt(i));
        }

        return set.size() == 2;
    }

    static boolean isEqual(int[] a, int[] b){
        int n = a.length;
        for(int i=0;i<n;i++){
            if(a[i] != b[i])
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i] = sc.nextInt();
            }
            String s = sc.next();

            int[] copyarr = new int[n];
            System.arraycopy(arr, 0, copyarr, 0, n);
            Arrays.sort(copyarr);

            if(isEqual(arr, copyarr)){
                sb.append("0\n");
                continue;
            }

            if(isBiPolar(s)){
                boolean[] prefix = new boolean[n];
                boolean[] suffix = new boolean[n];

                prefix[0] = arr[0] == copyarr[0];
                for(int i=1;i<n;i++){
                    prefix[i] = prefix[i-1] & (arr[i] == copyarr[i]);
                }

                suffix[n-1] = arr[n-1] == copyarr[n-1];
                for(int i=n-2;i>=0;i--){
                    suffix[i] = suffix[i+1] & (arr[i] == copyarr[i]);
                }

                if(s.charAt(0) != s.charAt(n-1)){
                    sb.append("1\n");
                    continue;
                }

                boolean flag = false;
                for(int i=1;i<n-1;i++){
                    if(s.charAt(i)!=s.charAt(0)){
                        if(prefix[i-1] || suffix[i+1]){
                            flag = true;
                            break;
                        }
                    }
                }

                if(flag)
                    sb.append("1\n");
                else sb.append("2\n");
            }
            else{
                sb.append("-1\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
