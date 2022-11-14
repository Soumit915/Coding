package Codeforces.ICPC_Mirror;

import java.io.*;
import java.util.*;

public class K {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-->0)
        {
            String s = sc.next();
            int n = s.length();

            int hori = 0;
            int verti = 0;

            int ansx = 1000000000;
            int ansy = -1000000000;
            for(int i=0;i<n;i++)
            {
                if(s.charAt(i)=='L')
                    hori--;
                else if(s.charAt(i)=='R')
                    hori++;
                else if(s.charAt(i)=='U')
                    verti++;
                else verti--;

                int posx = 0;
                int posy = 0;
                for(int j=0;j<s.length();j++)
                {
                    if(s.charAt(j)=='L') {
                        if(posx-1==hori && posy==verti) {
                            continue;
                        }
                        posx--;
                    }
                    else if(s.charAt(j)=='R') {
                        if(posx+1==hori && posy==verti) {
                            continue;
                        }
                        posx++;
                    }
                    else if(s.charAt(j)=='U') {
                        if(posx==hori && posy+1==verti) {
                            continue;
                        }
                        posy++;
                    }
                    else {
                        if(posx==hori && posy-1==verti) {
                            continue;
                        }
                        posy--;
                    }
                }

                if(posx==0 && posy==0)
                {
                    ansx = hori;
                    ansy = verti;
                    break;
                }
            }

            if(verti==0 && hori==0)
            {
                sb.append("10000000 10000000\n");
            }
            else if(ansx!=1000000000 && ansy!=-1000000000)
            {
                sb.append(ansx).append(" ").append(ansy).append("\n");
            }
            else
            {
                sb.append("0 0\n");
            }
        }

        System.out.println(sb.toString());

        sc.close();
    }

}
