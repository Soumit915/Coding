package Codeforces.ITMO_PilotCourse.TwoPointerMethods.Step3;

import java.io.*;
import java.util.*;

public class G_NotVeryRudeSubstring {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long k = sc.nextLong();
        String s = sc.next();

        long ca = 0;
        int ptr = -1;
        long cb = 0;
        long rudeness = 0;
        int max = -1;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='a')
                ca++;
            else if(s.charAt(i)=='b') {
                cb++;
                rudeness += ca;
            }

            while(rudeness>k && ptr+1<n){
                ptr++;
                if(s.charAt(ptr)=='a') {
                    rudeness -= cb;
                    ca--;
                }
                else if(s.charAt(ptr)=='b'){
                    cb--;
                }
            }

            max = Math.max(i-ptr, max);
        }

        System.out.println(max);
    }
}
