package Codeforces;

import java.util.*;
import java.io.*;

public class TresureIsland {

    static boolean[][] isPossible(char[][] mat){
        int n = mat.length;
        int m = mat[0].length;

        boolean[][] dp = new boolean[n][m];
        dp[n-1][m-1] = true;
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(mat[i][j] == '#')
                    continue;

                if(i<n-1)
                    dp[i][j] |= dp[i+1][j];

                if(j<m-1)
                    dp[i][j] |= dp[i][j+1];
            }
        }

        return dp;
    }

    static class Node{
        int x, y, dist;
        boolean isVisited;
        boolean isOk;

        Node(int x, int y, boolean isOk){
            this.x = x;
            this.y = y;
            this.isVisited = false;
            this.dist = 0;
            this.isOk = isOk;
        }
    }

    static void dfs(Node[][] grid, ArrayList<Integer> list){

        int n = grid.length;
        int m = grid[0].length;

        Stack<Node> stk = new Stack<>();
        Stack<Integer> ptrstk = new Stack<>();

        Node source = grid[0][0];

        stk.push(source);
        ptrstk.push(-1);
        source.isVisited = true;
        source.dist = 0;

        list.add(1);

        while(!stk.isEmpty()){
            Node cur = stk.pop();
            int ptr = ptrstk.pop();

            if(ptr == - 1){

                if(cur.x+1 < n && !grid[cur.x+1][cur.y].isVisited && grid[cur.x+1][cur.y].isOk){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Node next = grid[cur.x+1][cur.y];
                    next.isVisited = true;
                    stk.push(next);
                    ptrstk.push(-1);
                    next.dist = cur.dist + 1;

                    if(next.dist < list.size()){
                        int v = list.get(next.dist);
                        list.set(next.dist, v + 1);
                    }
                    else{
                        list.add(1);
                    }
                }
                else{
                    if(cur.y + 1 < m && !grid[cur.x][cur.y+1].isVisited && grid[cur.x][cur.y + 1].isOk){
                        Node next = grid[cur.x][cur.y + 1];
                        next.isVisited = true;
                        stk.push(next);
                        ptrstk.push(-1);
                        next.dist = cur.dist + 1;

                        if(next.dist < list.size()){
                            int v = list.get(next.dist);
                            list.set(next.dist, v + 1);
                        }
                        else{
                            list.add(1);
                        }
                    }
                }
            }
            else if(ptr == 0){
                if(cur.y + 1 < m && !grid[cur.x][cur.y+1].isVisited && grid[cur.x][cur.y + 1].isOk){
                    Node next = grid[cur.x][cur.y + 1];
                    next.isVisited = true;
                    stk.push(next);
                    ptrstk.push(-1);
                    next.dist = cur.dist + 1;

                    if(next.dist < list.size()){
                        int v = list.get(next.dist);
                        list.set(next.dist, v + 1);
                    }
                    else{
                        list.add(1);
                    }
                }
            }
        }
    }

    static int getBlocksNeeded(boolean[][] isOk){
        int n = isOk.length;
        int m = isOk[0].length;

        Node[][] grid = new Node[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                grid[i][j] = new Node(i, j, isOk[i][j]);
            }
        }

        ArrayList<Integer> hash = new ArrayList<>();
        dfs(grid, hash);

        int ones = 0;
        for(int i: hash){
            if(i == 1){
                ones++;
            }
        }

        if(ones == 2)
            return 2;
        else return 1;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] mat = new char[n][m];
        int c = 0;
        for(int i=0;i<n;i++){
            mat[i] = sc.next().toCharArray();
            for(int j=0;j<m;j++){
                if(mat[i][j] == '#'){
                    c++;
                }
            }
        }

        if(n == 1 || m == 1){
            if(c > 0){
                System.out.println(0);
            }
            else {
                System.out.println(1);
            }

            System.exit(0);
        }

        boolean[][] flag = isPossible(mat);

        if(!flag[0][0]){
            System.out.println("0");
            System.exit(0);
        }

        boolean fl = true;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(mat[i][j] == '#'){
                    fl = false;
                    break;
                }
            }

            if(!fl){
                break;
            }
        }

        if(fl){
            System.out.println("2");
            System.exit(0);
        }

        int g = getBlocksNeeded(flag);

        System.out.println(g);

        sc.close();
    }
}
