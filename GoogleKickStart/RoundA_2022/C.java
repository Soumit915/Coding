package GoogleKickStart.RoundA_2022;

import java.io.*;
import java.util.*;

public class C {

    static String getModified(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            sb.append("$").append(s.charAt(i));
        }
        sb.append("$");

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Input.txt"));

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++){
            sb.append("Case #").append(testi).append(": ");

            sc.nextInt();
            String s = sc.next();

            char[] ch = getModified(s).toCharArray();
            int n = ch.length;
            boolean flag = true;
            for(int i=0;i<n;i++){
                int l = i - 1;
                int r = i + 1;

                List<Integer> L = new ArrayList<>();
                List<Integer> R = new ArrayList<>();
                while(l>=0 && r<n){
                    if(ch[l]=='?' && ch[r]=='?'){
                        l++;
                        r--;
                        break;
                    }
                    if(ch[l] == ch[r]){
                        if((r - l + 1)/2 > 4)
                            break;

                        l--;r++;
                    }
                    else{
                        if(ch[l]=='?' && ch[r]!='?'){
                            if (ch[r] == '0')
                                ch[l] = '1';
                            else ch[l] = '0';
                        }
                        else if(ch[r]=='?' && ch[l]!='?'){
                            L.add(l);
                            R.add(r);
                            l--;
                            r++;
                            continue;
                        }
                        l++;
                        r--;
                        break;
                    }
                }

                if(l<0 || r>=n){
                    l++;
                    r--;
                }

                int len = (r - l + 1) / 2;
                if(len > 4){
                    if(L.size() > 0){
                        l = L.get(0);
                        r = R.get(0);

                        if (ch[l] == '0')
                            ch[r] = '1';
                        else ch[r] = '0';
                    }
                    else{
                        flag = false;
                        break;
                    }
                }
            }

            if(flag){
                sb.append("POSSIBLE\n");
            }
            else{
                sb.append("IMPOSSIBLE\n");
            }
        }

        System.out.println(sb);

        sc.close();
    }
}
