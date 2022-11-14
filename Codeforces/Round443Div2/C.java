package Codeforces.Round443Div2;

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        char[] type = new char[n];
        int[] val = new int[n];
        for(int i=0;i<n;i++){
            type[i] = sc.next().charAt(0);
            val[i] = sc.nextInt();
        }

        int[] expect0 = new int[10];
        int[] expect1 = new int[10];
        int v = 0;
        for(int i=0;i<n;i++){
            if(type[i]=='&'){
                v &= val[i];
            }
            else if(type[i]=='^'){
                v ^= val[i];
            }
            else v |= val[i];
        }
        for(int i=0;i<expect0.length;i++){
            if((v&(1<<i))==0)
                expect0[i] = 0;
            else expect0[i] = 1;
        }

        v = (1<<10)-1;
        for(int i=0;i<n;i++){
            if(type[i]=='&'){
                v &= val[i];
            }
            else if(type[i]=='^'){
                v ^= val[i];
            }
            else v |= val[i];
        }
        for(int i=0;i<expect0.length;i++){
            if((v&(1<<i))==0)
                expect1[i] = 0;
            else expect1[i] = 1;
        }

        int xor = 0, and = (1<<10)-1, or = 0;
        for(int i=0;i<10;i++){
            if(expect0[i]!=0 && expect1[i]!=1){
                xor |= (1<<i);
            }
            else if(expect0[i]==0 && expect1[i]==0){
                and ^= (1<<i);
            }
            else if(expect0[i]==1 && expect1[i]==1){
                or |= (1<<i);
            }
        }

        System.out.println(3);
        System.out.println("^ "+xor);
        System.out.println("& "+and);
        System.out.println("| "+or);

        sc.close();
    }

}
