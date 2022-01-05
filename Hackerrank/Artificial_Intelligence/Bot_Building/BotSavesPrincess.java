package Hackerrank.Artificial_Intelligence.Bot_Building;

import java.io.*;
import java.util.*;

public class BotSavesPrincess {
    static class GridPoint {
        int x;
        int y;
        GridPoint(){
            this.x = -1;
            this.y = -1;
        }
        GridPoint(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static boolean isValid(int n, int x, int y){
        return 0<=x && x<n && 0<=y && y<n;
    }
    static String getDirection(GridPoint a, GridPoint b){
        if(a.y+1==b.y)
            return "LEFT";
        if(a.y-1==b.y)
            return "RIGHT";
        if(a.x+1==b.x)
            return "UP";
        else return "DOWN";
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        char[][] mat = new char[n][n];
        for(int i=0;i<n;i++){
            String s = sc.next();
            mat[i] = s.toCharArray();
        }

        boolean[][] isVisited = new boolean[n][n];
        GridPoint[][] previous = new GridPoint[n][n];

        GridPoint start = new GridPoint();
        GridPoint end = new GridPoint();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if (mat[i][j] == 'm') {
                    start = new GridPoint(i, j);
                }

                if(mat[i][j] == 'p')
                    end = new GridPoint(i, j);
            }
        }

        Queue<GridPoint> q = new LinkedList<>();
        q.add(start);
        isVisited[start.x][start.y] = true;
        while (!q.isEmpty()){
            GridPoint cur = q.remove();
            if(isValid(n, cur.x+1, cur.y) && !isVisited[cur.x+1][cur.y]){
                q.add(new GridPoint(cur.x+1, cur.y));
                isVisited[cur.x+1][cur.y] = true;
                previous[cur.x+1][cur.y] = cur;
            }

            if(isValid(n, cur.x-1, cur.y) && !isVisited[cur.x-1][cur.y]){
                q.add(new GridPoint(cur.x-1, cur.y));
                isVisited[cur.x-1][cur.y] = true;
                previous[cur.x-1][cur.y] = cur;
            }

            if(isValid(n, cur.x, cur.y+1) && !isVisited[cur.x][cur.y+1]){
                q.add(new GridPoint(cur.x, cur.y+1));
                isVisited[cur.x][cur.y+1] = true;
                previous[cur.x][cur.y+1] = cur;
            }

            if(isValid(n, cur.x, cur.y-1) && !isVisited[cur.x][cur.y-1]){
                q.add(new GridPoint(cur.x, cur.y-1));
                isVisited[cur.x][cur.y-1] = true;
                previous[cur.x][cur.y-1] = cur;
            }
        }

        GridPoint ptr = end;
        Stack<String> stk = new Stack<>();
        while(previous[ptr.x][ptr.y]!=null){
            stk.push(getDirection(ptr, previous[ptr.x][ptr.y]));
            ptr = previous[ptr.x][ptr.y];
        }

        StringBuilder sb = new StringBuilder();
        while(!stk.isEmpty())
            sb.append(stk.pop()).append("\n");
        System.out.println(sb);

        sc.close();
    }
}