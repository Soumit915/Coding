package Codeforces.ITMO_PilotCourse.TwoPointerMethods.Step3;

import java.io.*;
import java.util.*;

public class F_CardSubstrings {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        String a = sc.next();
        String b = sc.next();

        int[] hash = new int[26];
        for(int i=0;i<m;i++)
            hash[b.charAt(i)-97]++;

        int ptr = -1;
        long count = 0;
        for(int i=0;i<n;i++){
            if(hash[a.charAt(i)-97]==0){
                while(ptr+1<n && a.charAt(ptr+1)!=a.charAt(i)){
                    ptr++;
                    hash[a.charAt(ptr)-97]++;
                }
                ptr++;
            }
            else{
                hash[a.charAt(i)-97]--;
            }

            count += i-ptr;
        }

        System.out.println(count);
    }
}
