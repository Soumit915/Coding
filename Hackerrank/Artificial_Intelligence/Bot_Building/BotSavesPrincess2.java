package Hackerrank.Artificial_Intelligence.Bot_Building;

import java.util.*;

public class BotSavesPrincess2 {
    static String getNextMove(char[][] mat, int n, int row, int col){
        int x = 0, y = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                if(mat[i][j]=='p'){
                    x = i;
                    y = j;
                }
        }

        if(x<row){
            return "UP";
        }
        else if(x>row)
            return "DOWN";
        else {
            if(y<col){
                return "LEFT";
            }
            else if(y>col){
                return "RIGHT";
            }
            else{
                return "";
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int row = sc.nextInt();
        int col = sc.nextInt();

        char[][] mat = new char[n][n];
        for(int i=0;i<n;i++)
            mat[i] = sc.next().toCharArray();

        System.out.println(getNextMove(mat, n, row, col));
    }
}
