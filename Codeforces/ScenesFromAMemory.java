package Codeforces;

import java.io.*;
import java.util.*;

public class ScenesFromAMemory {

    static int getSpecial(int[] hash){
        for(int i=1;i<=9;i++){
            if(i==2 || i==3 || i==5 || i==7)
                continue;

            if(hash[i] > 0)
                return i;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0){
            int n = sc.nextInt();
            String s = sc.next();

            int[] hash = new int[10];
            for(int i=0;i<n;i++){
                hash[s.charAt(i) - '0']++;
            }

            int special = getSpecial(hash);
            if(special != -1){
                sb.append("1\n").append(special).append("\n");
            }
            else if(hash[2] > 1){
                sb.append("2\n22\n");
            }
            else if(hash[3] > 1){
                sb.append("2\n33\n");
            }
            else if(hash[5] > 1){
                sb.append("2\n55\n");
            }
            else if(hash[7] > 1){
                sb.append("2\n77\n");
            }
            else{
                if(n==2){
                    sb.append("2\n").append(s).append("\n");
                }
                else{
                    sb.append("2\n");

                    boolean two = false, three = false, five = false, seven = false;
                    for(int i=0;i<n;i++){

                        if(s.charAt(i) == '2'){
                            if(three){
                                sb.append("32\n");
                                break;
                            }

                            if(five){
                                sb.append("52\n");
                                break;
                            }

                            if(seven){
                                sb.append("72\n");
                                break;
                            }

                            two = true;
                        }

                        if(s.charAt(i) == '3'){
                            three = true;
                        }

                        if(s.charAt(i) == '5'){
                            if(two){
                                sb.append("25\n");
                                break;
                            }

                            if(three){
                                sb.append("35\n");
                                break;
                            }

                            if(seven){
                                sb.append("75\n");
                                break;
                            }

                            five = true;
                        }

                        if(s.charAt(i) == '7'){
                            if(two){
                                sb.append("27\n");
                                break;
                            }

                            if(five){
                                sb.append("57\n");
                                break;
                            }

                            seven = true;
                        }
                    }
                }
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
