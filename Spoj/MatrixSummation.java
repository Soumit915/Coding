package Spoj;

import java.io.*;

public class MatrixSummation {

    static void update(int[][] bit, int x, int y, int num){
        int n = bit.length;

        while(x<=n){
            int y1 = y;
            while(y1<=n){
                bit[x-1][y1-1] += num;
                y1 += (y1 & (-y1));
            }

            x += (x & (-x));
        }
    }

    static int query(int[][] bit, int x, int y){

        if(x <= 0 || y <= 0)
            return 0;

        int sum = 0;
        while(x>0){
            int y1 = y;
            while(y1>0){
                sum += bit[x-1][y1-1];
                y1 -= (y1 & (-y1));
            }

            x -= (x & (-x));
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = Integer.parseInt(br.readLine());

            int[][] bit = new int[n][n];

            while(true){

                String line = br.readLine();

                if(line.equals("END"))
                    break;

                String[] command = line.split(" ");

                if(command[0].equals("SET")){
                    int x = Integer.parseInt(command[1]) + 1;
                    int y = Integer.parseInt(command[2]) + 1;
                    int num = Integer.parseInt(command[3]);

                    update(bit, x, y, num);
                }
                else{
                    int x1 = Integer.parseInt(command[1]) + 1;
                    int y1 = Integer.parseInt(command[2]) + 1;

                    int x2 = Integer.parseInt(command[3]) + 1;
                    int y2 = Integer.parseInt(command[4]) + 1;

                    int ans = query(bit, x2, y2) - query(bit, x2, y1 - 1) - query(bit, x1 - 1, y2) + query(bit, x1 - 1, y1 - 1);
                    sb.append(ans).append("\n");
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);

    }

}
