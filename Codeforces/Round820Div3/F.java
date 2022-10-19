package Codeforces.Round820Div3;

import java.util.*;
import java.io.*;

public class F {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-->0){

            String s = br.readLine();
            int n = s.length();

            String[] secondLine = (br.readLine()).split(" ");
            int w = Integer.parseInt(secondLine[0]);
            int m = Integer.parseInt(secondLine[1]);

            int[] digits = new int[n];
            int[] psum_digits = new int[n];
            for(int i=0;i<n;i++){
                digits[i] = (s.charAt(i) - '0');
                psum_digits[i] = ((i==0)?0:psum_digits[i-1]) + digits[i];
            }

            int[] remainder = new int[n];
            for(int i=w-1;i<n;i++){
                remainder[i] = (psum_digits[i] - (((i-w)<0)?0:psum_digits[i-w])) % 9;
            }

            HashMap<Integer, List<Integer>> rem_hash = new HashMap<>();
            for(int i=w-1;i<n;i++){
                int rem = remainder[i];
                int l = i - w + 1;

                List<Integer> rem_list = rem_hash.getOrDefault(rem, new ArrayList<>());
                rem_list.add(l);
                rem_hash.put(rem, rem_list);
            }

            for(int i=0;i<m;i++){
                String[] query = (br.readLine()).split(" ");
                int l = Integer.parseInt(query[0]) - 1;
                int r = Integer.parseInt(query[1]) - 1;
                int k = Integer.parseInt(query[2]);

                int rem_lr = psum_digits[r] - (l==0?0:psum_digits[l-1]);
                rem_lr %= 9;

                int l1 = Integer.MAX_VALUE, l2 = Integer.MAX_VALUE;
                for(int r1=0;r1<9;r1++){
                    for(int r2=0;r2<9;r2++){
                        if((r1*rem_lr + r2) % 9 == k){
                            if(r1 == r2){
                                List<Integer> li = rem_hash.getOrDefault(r1, new ArrayList<>());
                                if(li.size() > 1){
                                    int cl1 = li.get(0), cl2 = li.get(1);

                                    if(cl1 < l1){
                                        l1 = cl1;
                                        l2 = cl2;
                                    }
                                    else if(cl1 == l1){
                                        l2 = Math.min(l2, cl2);
                                    }
                                }
                            }
                            else{
                                List<Integer> li1 = rem_hash.getOrDefault(r1, new ArrayList<>());
                                List<Integer> li2 = rem_hash.getOrDefault(r2, new ArrayList<>());

                                if(li1.size() > 0 && li2.size() > 0){
                                    int cl1 = li1.get(0), cl2 = li2.get(0);

                                    if(cl1 < l1){
                                        l1 = cl1;
                                        l2 = cl2;
                                    }
                                    else if(cl1 == l1){
                                        l2 = Math.min(l2, cl2);
                                    }
                                }
                            }
                        }
                    }
                }

                if(l1 == Integer.MAX_VALUE || l2 == Integer.MAX_VALUE){
                    sb.append("-1 -1\n");
                }
                else{
                    sb.append(l1+1).append(" ").append(l2 + 1).append("\n");
                }
            }
        }

        System.out.println(sb);

        br.close();
    }
}
