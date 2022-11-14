package TCS_Codevita10_QualificationRound;

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        int m = sc.nextInt();

        String[][] mat = new String[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                mat[i][j] = sc.next();
            }
        }

        int k = sc.nextInt();
        k %= n * m;

        String tofind = sc.next();

        String[] linear_actual = new String[n*m];
        for(int i=0;i<n;i++){
            if(i%2==0){
                System.arraycopy(mat[i], 0, linear_actual, i * m, m);
            }
            else{
                for(int j=m-1;j>=0;j--){
                    linear_actual[i*m + (m - j - 1)] = mat[i][j];
                }
            }
        }

        String[] linear_displaced = new String[n*m];
        for(int i=0;i<n*m;i++){
            linear_displaced[i] = linear_actual[(i-k+(n*m))%(n*m)];
        }

        for(int i=0;i<n;i++){
            if(i%2==0){
                System.arraycopy(linear_displaced, i * m, mat[i], 0, m);
            }
            else{
                for(int j=m-1;j>=0;j--){
                    mat[i][j] = linear_displaced[i*m + (m - j - 1)];
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                sb.append(mat[i][j]).append(" ");
            }
            sb.append("\n");
        }

        boolean flag = false;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(mat[i][j].equals(tofind)){
                    sb.append("[").append(i).append(", ").append(j).append("]\n");
                    flag = true;
                    break;
                }
            }

            if(flag)
                break;
        }

        if(!flag){
            sb.append("Not Available\n");
        }

        System.out.println(sb);

        sc.close();
    }
}
